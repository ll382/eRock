<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学期ID" prop="semesterId">
        <el-input
          v-model="queryParams.semesterId"
          placeholder="请输入学期ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开课时间" prop="crDate">
        <el-date-picker clearable
          v-model="queryParams.crDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开课时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="课题内容" prop="crMain">
        <el-input
          v-model="queryParams.crMain"
          placeholder="请输入课题内容"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开课时长" prop="crDuration">
        <el-input
          v-model="queryParams.crDuration"
          placeholder="请输入开课时长"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级编号" prop="classId">
        <el-input
          v-model="queryParams.classId"
          placeholder="请输入班级编号"
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
          v-hasPermi="['match:register:add']"
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
          v-hasPermi="['match:register:edit']"
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
          v-hasPermi="['match:register:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['match:register:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="registerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课堂ID" align="center" prop="crId" />
      <el-table-column label="学期ID" align="center" prop="semesterId" />
      <el-table-column label="开课时间" align="center" prop="crDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.crDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="课题内容" align="center" prop="crMain" />
      <el-table-column label="开课时长" align="center" prop="crDuration" />
      <el-table-column label="班级编号" align="center" prop="classId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['match:register:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['match:register:remove']"
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

    <!-- 添加或修改课堂记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学期ID" prop="semesterId">
          <el-input v-model="form.semesterId" placeholder="请输入学期ID" />
        </el-form-item>
        <el-form-item label="开课时间" prop="crDate">
          <el-date-picker clearable
            v-model="form.crDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开课时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="课题内容" prop="crMain">
          <el-input v-model="form.crMain" placeholder="请输入课题内容" />
        </el-form-item>
        <el-form-item label="开课时长" prop="crDuration">
          <el-input v-model="form.crDuration" placeholder="请输入开课时长" />
        </el-form-item>
        <el-form-item label="班级编号" prop="classId">
          <el-input v-model="form.classId" placeholder="请输入班级编号" />
        </el-form-item>
        <el-divider content-position="center">C 比赛记录信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCompetitionRecord">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCompetitionRecord">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="competitionRecordList" :row-class-name="rowCompetitionRecordIndex" @selection-change="handleCompetitionRecordSelectionChange" ref="competitionRecord">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="枚举ID" prop="enumId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.enumId" placeholder="请输入枚举ID" />
            </template>
          </el-table-column>
          <el-table-column label="场次时间" prop="ccRTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.ccRTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择场次时间" />
            </template>
          </el-table-column>
          <el-table-column label="比赛规格" prop="ccRSpeci" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ccRSpeci" placeholder="请输入比赛规格" />
            </template>
          </el-table-column>
          <el-table-column label="比赛名称" prop="ccRName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ccRName" placeholder="请输入比赛名称" />
            </template>
          </el-table-column>
          <el-table-column label="审核状态
0：未提交
1：未审核
2：已审核" prop="ccRAudit" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ccRAudit" placeholder="请输入审核状态
0：未提交
1：未审核
2：已审核" />
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
import { listRegister, getRegister, delRegister, addRegister, updateRegister } from "@/api/match/register";

export default {
  name: "Register",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCompetitionRecord: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 课堂记录表格数据
      registerList: [],
      // C 比赛记录表格数据
      competitionRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        semesterId: null,
        crDate: null,
        crMain: null,
        crDuration: null,
        classId: null
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
    /** 查询课堂记录列表 */
    getList() {
      this.loading = true;
      listRegister(this.queryParams).then(response => {
        this.registerList = response.rows;
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
        crId: null,
        semesterId: null,
        crDate: null,
        crMain: null,
        crDuration: null,
        classId: null
      };
      this.competitionRecordList = [];
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
      this.ids = selection.map(item => item.crId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课堂记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const crId = row.crId || this.ids
      getRegister(crId).then(response => {
        this.form = response.data;
        this.competitionRecordList = response.data.competitionRecordList;
        this.open = true;
        this.title = "修改课堂记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.competitionRecordList = this.competitionRecordList;
          if (this.form.crId != null) {
            updateRegister(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRegister(this.form).then(response => {
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
      const crIds = row.crId || this.ids;
      this.$modal.confirm('是否确认删除课堂记录编号为"' + crIds + '"的数据项？').then(function() {
        return delRegister(crIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** C 比赛记录序号 */
    rowCompetitionRecordIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** C 比赛记录添加按钮操作 */
    handleAddCompetitionRecord() {
      let obj = {};
      obj.enumId = "";
      obj.ccRTime = "";
      obj.ccRSpeci = "";
      obj.ccRName = "";
      obj.ccRAudit = "";
      this.competitionRecordList.push(obj);
    },
    /** C 比赛记录删除按钮操作 */
    handleDeleteCompetitionRecord() {
      if (this.checkedCompetitionRecord.length == 0) {
        this.$modal.msgError("请先选择要删除的C 比赛记录数据");
      } else {
        const competitionRecordList = this.competitionRecordList;
        const checkedCompetitionRecord = this.checkedCompetitionRecord;
        this.competitionRecordList = competitionRecordList.filter(function(item) {
          return checkedCompetitionRecord.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCompetitionRecordSelectionChange(selection) {
      this.checkedCompetitionRecord = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('match/register/export', {
        ...this.queryParams
      }, `register_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
