<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课堂ID" prop="crId">
        <el-input
          v-model="queryParams.crId"
          placeholder="请输入课堂ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="老师工号" prop="teaId">
        <el-input
          v-model="queryParams.teaId"
          placeholder="请输入老师工号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="枚举ID" prop="enumId">
        <el-input
          v-model="queryParams.enumId"
          placeholder="请输入枚举ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务名称" prop="etName">
        <el-input
          v-model="queryParams.etName"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务描述" prop="etDescription">
        <el-input
          v-model="queryParams.etDescription"
          placeholder="请输入任务描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="etCreateTime">
        <el-date-picker clearable
          v-model="queryParams.etCreateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
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
          v-hasPermi="['practice:task:add']"
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
          v-hasPermi="['practice:task:edit']"
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
          v-hasPermi="['practice:task:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['practice:task:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务ID" align="center" prop="etId" />
      <el-table-column label="课堂ID" align="center" prop="crId" />
      <el-table-column label="老师工号" align="center" prop="teaId" />
      <el-table-column label="枚举ID" align="center" prop="enumId" />
      <el-table-column label="任务名称" align="center" prop="etName" />
      <el-table-column label="任务描述" align="center" prop="etDescription" />
      <el-table-column label="创建时间" align="center" prop="etCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.etCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['practice:task:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['practice:task:remove']"
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

    <!-- 添加或修改练习、测试任务表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课堂ID" prop="crId">
          <el-input v-model="form.crId" placeholder="请输入课堂ID" />
        </el-form-item>
        <el-form-item label="老师工号" prop="teaId">
          <el-input v-model="form.teaId" placeholder="请输入老师工号" />
        </el-form-item>
        <el-form-item label="枚举ID" prop="enumId">
          <el-input v-model="form.enumId" placeholder="请输入枚举ID" />
        </el-form-item>
        <el-form-item label="任务名称" prop="etName">
          <el-input v-model="form.etName" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="任务描述" prop="etDescription">
          <el-input v-model="form.etDescription" placeholder="请输入任务描述" />
        </el-form-item>
        <el-form-item label="创建时间" prop="etCreateTime">
          <el-date-picker clearable
            v-model="form.etCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-divider content-position="center">练习、测试评分表信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddAMarkSheet">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteAMarkSheet">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="aMarkSheetList" :row-class-name="rowAMarkSheetIndex" @selection-change="handleAMarkSheetSelectionChange" ref="aMarkSheet">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="学生学号" prop="stuId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.stuId" placeholder="请输入学生学号" />
            </template>
          </el-table-column>
          <el-table-column label="老师工号" prop="teaId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.teaId" placeholder="请输入老师工号" />
            </template>
          </el-table-column>
          <el-table-column label="EROCK评分" prop="msScore" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.msScore" placeholder="请输入EROCK评分" />
            </template>
          </el-table-column>
          <el-table-column label="评分时间" prop="msTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.msTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择评分时间" />
            </template>
          </el-table-column>
          <el-table-column label="运球分数" prop="msDribble" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.msDribble" placeholder="请输入运球分数" />
            </template>
          </el-table-column>
          <el-table-column label="投篮分数" prop="msShooting" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.msShooting" placeholder="请输入投篮分数" />
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
import { listTask, getTask, delTask, addTask, updateTask } from "@/api/practice/task";

export default {
  name: "Task",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedAMarkSheet: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 练习、测试任务表表格数据
      taskList: [],
      // 练习、测试评分表表格数据
      aMarkSheetList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        crId: null,
        teaId: null,
        enumId: null,
        etName: null,
        etDescription: null,
        etCreateTime: null
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
    /** 查询练习、测试任务表列表 */
    getList() {
      this.loading = true;
      listTask(this.queryParams).then(response => {
        this.taskList = response.rows;
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
        etId: null,
        crId: null,
        teaId: null,
        enumId: null,
        etName: null,
        etDescription: null,
        etCreateTime: null
      };
      this.aMarkSheetList = [];
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
      this.ids = selection.map(item => item.etId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加练习、测试任务表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const etId = row.etId || this.ids
      getTask(etId).then(response => {
        this.form = response.data;
        this.aMarkSheetList = response.data.amarkSheetList;
        this.open = true;
        this.title = "修改练习、测试任务表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.aMarkSheetList = this.aMarkSheetList;
          if (this.form.etId != null) {
            updateTask(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTask(this.form).then(response => {
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
      const etIds = row.etId || this.ids;
      this.$modal.confirm('是否确认删除练习、测试任务表编号为"' + etIds + '"的数据项？').then(function() {
        return delTask(etIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 练习、测试评分表序号 */
    rowAMarkSheetIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 练习、测试评分表添加按钮操作 */
    handleAddAMarkSheet() {
      let obj = {};
      obj.stuId = "";
      obj.teaId = "";
      obj.msScore = "";
      obj.msTime = "";
      obj.msDribble = "";
      obj.msShooting = "";
      this.aMarkSheetList.push(obj);
    },
    /** 练习、测试评分表删除按钮操作 */
    handleDeleteAMarkSheet() {
      if (this.checkedAMarkSheet.length == 0) {
        this.$modal.msgError("请先选择要删除的练习、测试评分表数据");
      } else {
        const aMarkSheetList = this.aMarkSheetList;
        const checkedAMarkSheet = this.checkedAMarkSheet;
        this.aMarkSheetList = aMarkSheetList.filter(function(item) {
          return checkedAMarkSheet.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleAMarkSheetSelectionChange(selection) {
      this.checkedAMarkSheet = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('practice/task/export', {
        ...this.queryParams
      }, `task_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
