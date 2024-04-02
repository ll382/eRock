<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="职位ID" prop="jobId">
        <el-select v-model="queryParams.jobId" placeholder="请选择职位ID" clearable>
          <el-option
            v-for="dict in dict.type.ballgame_position"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="球队ID" prop="balId">
        <el-input
          v-model="queryParams.balId"
          placeholder="请输入球队ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生学号" prop="stuId">
        <el-input
          v-model="queryParams.stuId"
          placeholder="请输入学生学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分数" prop="psNum">
        <el-input
          v-model="queryParams.psNum"
          placeholder="请输入分数"
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
          v-hasPermi="['match:sheet:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['match:sheet:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['match:sheet:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['match:sheet:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sheetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="人员ID" align="center" prop="psId"/>
      <el-table-column label="职位ID" align="center" prop="jobId"/>
      <el-table-column label="球队ID" align="center" prop="balId"/>
      <el-table-column label="学生学号" align="center" prop="stuId"/>
      <el-table-column label="分数" align="center" prop="psNum"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['match:sheet:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['match:sheet:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改C 球队内人员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="职位ID" prop="jobId">
          <el-select v-model="form.jobId" placeholder="请选择职位ID">
            <el-option
              v-for="dict in dict.type.ballgame_position"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="球队ID" prop="balId">
          <el-input v-model="form.balId" placeholder="请输入球队ID"/>
        </el-form-item>
        <el-form-item label="学生学号" prop="stuId">
          <el-input v-model="form.stuId" placeholder="请输入学生学号"/>
        </el-form-item>
        <el-form-item label="分数" prop="psNum">
          <el-input v-model="form.psNum" placeholder="请输入分数"/>
        </el-form-item>
        <el-divider content-position="center">C 佐证信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCProof">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCProof">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="cProofList" :row-class-name="rowCProofIndex" @selection-change="handleCProofSelectionChange"
                  ref="cProof">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="佐证URL" prop="proofUrl" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.proofUrl" placeholder="请输入佐证URL"/>
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
import {listSheet, getSheet, delSheet, addSheet, updateSheet} from "@/api/match/sheet";

export default {
  name: "Sheet",
  dicts: ['ballgame_position'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCProof: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // C 球队内人员表格数据
      sheetList: [],
      // C 佐证表格数据
      cProofList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        jobId: null,
        balId: null,
        stuId: null,
        psNum: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询C 球队内人员列表 */
    getList() {
      this.loading = true;
      listSheet(this.queryParams).then(response => {
        this.sheetList = response.rows;
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
        psId: null,
        jobId: null,
        balId: null,
        stuId: null,
        psNum: null
      };
      this.cProofList = [];
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
      this.ids = selection.map(item => item.psId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加C 球队内人员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const psId = row.psId || this.ids
      getSheet(psId).then(response => {
        this.form = response.data;
        this.cProofList = response.data.cProofList;
        this.open = true;
        this.title = "修改C 球队内人员";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.cProofList = this.cProofList;
          if (this.form.psId != null) {
            updateSheet(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSheet(this.form).then(response => {
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
      const psIds = row.psId || this.ids;
      this.$modal.confirm('是否确认删除C 球队内人员编号为"' + psIds + '"的数据项？').then(function () {
        return delSheet(psIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** C 佐证序号 */
    rowCProofIndex({row, rowIndex}) {
      row.index = rowIndex + 1;
    },
    /** C 佐证添加按钮操作 */
    handleAddCProof() {
      let obj = {};
      obj.proofUrl = "";
      this.cProofList.push(obj);
    },
    /** C 佐证删除按钮操作 */
    handleDeleteCProof() {
      if (this.checkedCProof.length == 0) {
        this.$modal.msgError("请先选择要删除的C 佐证数据");
      } else {
        const cProofList = this.cProofList;
        const checkedCProof = this.checkedCProof;
        this.cProofList = cProofList.filter(function (item) {
          return checkedCProof.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCProofSelectionChange(selection) {
      this.checkedCProof = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('match/sheet/export', {
        ...this.queryParams
      }, `sheet_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
