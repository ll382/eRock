<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="枚举ID" prop="enumId">
        <el-input
          v-model="queryParams.enumId"
          placeholder="请输入枚举ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="场次时间" prop="ccRTime">
        <el-date-picker clearable
                        v-model="queryParams.ccRTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择场次时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="比赛规格" prop="ccRSpeci">
        <el-input
          v-model="queryParams.ccRSpeci"
          placeholder="请输入比赛规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛名称" prop="ccRName">
        <el-input
          v-model="queryParams.ccRName"
          placeholder="请输入比赛名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态
0：未提交
1：未审核
2：已审核" prop="ccRAudit">
        <el-input
          v-model="queryParams.ccRAudit"
          placeholder="请输入审核状态
0：未提交
1：未审核
2：已审核"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课堂ID" prop="crId">
        <el-input
          v-model="queryParams.crId"
          placeholder="请输入课堂ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['match:record:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['match:record:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['match:record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['match:record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="ccRId" />
      <el-table-column label="枚举ID" align="center" prop="enumId" />
      <el-table-column label="场次时间" align="center" prop="ccRTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ccRTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="比赛规格" align="center" prop="ccRSpeci" />
      <el-table-column label="比赛名称" align="center" prop="ccRName" />
      <el-table-column label="审核状态 0：未提交1：未审核2：已审核" align="center" prop="ccRAudit" />
      <el-table-column label="课堂ID" align="center" prop="crId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['match:record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['match:record:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改C 比赛记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="枚举ID" prop="enumId">
          <el-input v-model="form.enumId" placeholder="请输入枚举ID" />
        </el-form-item>
        <el-form-item label="场次时间" prop="ccRTime">
          <el-date-picker clearable
                          v-model="form.ccRTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择场次时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="比赛规格" prop="ccRSpeci">
          <el-input v-model="form.ccRSpeci" placeholder="请输入比赛规格" />
        </el-form-item>
        <el-form-item label="比赛名称" prop="ccRName">
          <el-input v-model="form.ccRName" placeholder="请输入比赛名称" />
        </el-form-item>
        <el-form-item label="审核状态
0：未提交
1：未审核
2：已审核" prop="ccRAudit">
          <el-input v-model="form.ccRAudit" placeholder="请输入审核状态
0：未提交
1：未审核
2：已审核" />
        </el-form-item>
        <el-form-item label="课堂ID" prop="crId">
          <el-input v-model="form.crId" placeholder="请输入课堂ID" />
        </el-form-item>
        <el-divider content-position="center">球队参赛信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCBallteam">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCBallteam">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="cBallteamList" :row-class-name="rowCBallteamIndex" @selection-change="handleCBallteamSelectionChange" ref="cBallteam">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="小组ID" prop="ggId" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.ggId" placeholder="请选择小组ID">
                <el-option
                  v-for="dict in dict.type.stu_group"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="球队比分" prop="balNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.balNum" placeholder="请输入球队比分" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/match/record";

export default {
  name: "Record",
  dicts: ['stu_group'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCBallteam: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // C 比赛记录表格数据
      recordList: [],
      // 球队参赛表格数据
      cBallteamList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        enumId: null,
        ccRTime: null,
        ccRSpeci: null,
        ccRName: null,
        ccRAudit: null,
        crId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询C 比赛记录列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        ccRId: null,
        enumId: null,
        ccRTime: null,
        ccRSpeci: null,
        ccRName: null,
        ccRAudit: null,
        crId: null
      };
      this.cBallteamList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.ccRId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加C 比赛记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ccRId = row.ccRId || this.ids
      getRecord(ccRId).then(response => {
        this.form = response.data;
        this.cBallteamList = response.data.cballteamList;
        this.open = true;
        this.title = "修改C 比赛记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.cBallteamList = this.cBallteamList;
          if (this.form.ccRId != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ccRIds = row.ccRId || this.ids;
      this.$modal.confirm('是否确认删除C 比赛记录编号为"' + ccRIds + '"的数据项？').then(function() {
        return delRecord(ccRIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 球队参赛序号 */
    rowCBallteamIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 球队参赛添加按钮操作 */
    handleAddCBallteam() {
      let obj = {};
      obj.ggId = "";
      obj.balNum = "";
      this.cBallteamList.push(obj);
    },
    /** 球队参赛删除按钮操作 */
    handleDeleteCBallteam() {
      if (this.checkedCBallteam.length == 0) {
        this.$modal.msgError("请先选择要删除的球队参赛数据");
      } else {
        const cBallteamList = this.cBallteamList;
        const checkedCBallteam = this.checkedCBallteam;
        this.cBallteamList = cBallteamList.filter(function(item) {
          return checkedCBallteam.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCBallteamSelectionChange(selection) {
      this.checkedCBallteam = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('match/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
