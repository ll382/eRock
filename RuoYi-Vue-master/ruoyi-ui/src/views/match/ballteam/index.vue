<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="记录ID" prop="ccRId">
        <el-input
          v-model="queryParams.ccRId"
          placeholder="请输入记录ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="小组ID" prop="ggId">
        <el-select v-model="queryParams.ggId" placeholder="请选择小组ID" clearable>
          <el-option
            v-for="dict in dict.type.stu_group"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="球队比分" prop="balNum">
        <el-input
          v-model="queryParams.balNum"
          placeholder="请输入球队比分"
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
          v-hasPermi="['match:ballteam:add']"
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
          v-hasPermi="['match:ballteam:edit']"
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
          v-hasPermi="['match:ballteam:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['match:ballteam:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ballteamList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="参赛ID" align="center" prop="balId" />
      <el-table-column label="记录ID" align="center" prop="ccRId" />
      <el-table-column label="小组ID" align="center" prop="ggId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.stu_group" :value="scope.row.ggId"/>
        </template>
      </el-table-column>
      <el-table-column label="球队比分" align="center" prop="balNum" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['match:ballteam:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['match:ballteam:remove']"
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

    <!-- 添加或修改球队参赛对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="记录ID" prop="ccRId">
          <el-input v-model="form.ccRId" placeholder="请输入记录ID" />
        </el-form-item>
        <el-form-item label="小组ID" prop="ggId">
          <el-select v-model="form.ggId" placeholder="请选择小组ID">
            <el-option
              v-for="dict in dict.type.stu_group"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="球队比分" prop="balNum">
          <el-input v-model="form.balNum" placeholder="请输入球队比分" />
        </el-form-item>
        <el-divider content-position="center">C 球队内人员信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCPersonnelSheet">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCPersonnelSheet">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="cPersonnelSheetList" :row-class-name="rowCPersonnelSheetIndex" @selection-change="handleCPersonnelSheetSelectionChange" ref="cPersonnelSheet">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="职位ID" prop="jobId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.jobId" placeholder="请输入职位ID" />
            </template>
          </el-table-column>
          <el-table-column label="学生学号" prop="stuId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.stuId" placeholder="请输入学生学号" />
            </template>
          </el-table-column>
          <el-table-column label="分数" prop="psNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.psNum" placeholder="请输入分数" />
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
import { listBallteam, getBallteam, delBallteam, addBallteam, updateBallteam } from "@/api/match/ballteam";

export default {
  name: "Ballteam",
  dicts: ['stu_group'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCPersonnelSheet: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 球队参赛表格数据
      ballteamList: [],
      // C 球队内人员表格数据
      cPersonnelSheetList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ccRId: null,
        ggId: null,
        balNum: null
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
    /** 查询球队参赛列表 */
    getList() {
      this.loading = true;
      listBallteam(this.queryParams).then(response => {
        this.ballteamList = response.rows;
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
        balId: null,
        ccRId: null,
        ggId: null,
        balNum: null
      };
      this.cPersonnelSheetList = [];
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
      this.ids = selection.map(item => item.balId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加球队参赛";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const balId = row.balId || this.ids
      getBallteam(balId).then(response => {
        this.form = response.data;
        this.cPersonnelSheetList = response.data.cPersonnelSheetList;
        this.open = true;
        this.title = "修改球队参赛";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.cPersonnelSheetList = this.cPersonnelSheetList;
          if (this.form.balId != null) {
            updateBallteam(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBallteam(this.form).then(response => {
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
      const balIds = row.balId || this.ids;
      this.$modal.confirm('是否确认删除球队参赛编号为"' + balIds + '"的数据项？').then(function() {
        return delBallteam(balIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** C 球队内人员序号 */
    rowCPersonnelSheetIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** C 球队内人员添加按钮操作 */
    handleAddCPersonnelSheet() {
      let obj = {};
      obj.jobId = "";
      obj.stuId = "";
      obj.psNum = "";
      this.cPersonnelSheetList.push(obj);
    },
    /** C 球队内人员删除按钮操作 */
    handleDeleteCPersonnelSheet() {
      if (this.checkedCPersonnelSheet.length == 0) {
        this.$modal.msgError("请先选择要删除的C 球队内人员数据");
      } else {
        const cPersonnelSheetList = this.cPersonnelSheetList;
        const checkedCPersonnelSheet = this.checkedCPersonnelSheet;
        this.cPersonnelSheetList = cPersonnelSheetList.filter(function(item) {
          return checkedCPersonnelSheet.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCPersonnelSheetSelectionChange(selection) {
      this.checkedCPersonnelSheet = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('match/ballteam/export', {
        ...this.queryParams
      }, `ballteam_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
