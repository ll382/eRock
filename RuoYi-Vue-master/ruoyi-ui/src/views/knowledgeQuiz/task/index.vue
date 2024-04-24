<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="老师外键" prop="teaId">
        <el-input
          v-model="queryParams.teaId"
          placeholder="请输入老师外键"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布时间" prop="taskDatetime">
        <el-date-picker clearable
          v-model="queryParams.taskDatetime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发布标题" prop="taskTitle">
        <el-input
          v-model="queryParams.taskTitle"
          placeholder="请输入发布标题"
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
          v-hasPermi="['knowledgeQuiz:task:add']"
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
          v-hasPermi="['knowledgeQuiz:task:edit']"
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
          v-hasPermi="['knowledgeQuiz:task:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['knowledgeQuiz:task:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="知识测试任务ID" align="center" prop="taskId" />
      <el-table-column label="老师外键" align="center" prop="teaId" />
      <el-table-column label="发布时间" align="center" prop="taskDatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.taskDatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布标题" align="center" prop="taskTitle" />
      <el-table-column label="发布简介" align="center" prop="taskContent" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['knowledgeQuiz:task:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['knowledgeQuiz:task:remove']"
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

    <!-- 添加或修改A1 知识测试任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="老师外键" prop="teaId">
          <el-input v-model="form.teaId" placeholder="请输入老师外键" />
        </el-form-item>
        <el-form-item label="发布时间" prop="taskDatetime">
          <el-date-picker clearable
            v-model="form.taskDatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发布标题" prop="taskTitle">
          <el-input v-model="form.taskTitle" placeholder="请输入发布标题" />
        </el-form-item>
        <el-form-item label="发布简介">
          <editor v-model="form.taskContent" :min-height="192"/>
        </el-form-item>
        <el-divider content-position="center">A1 知识测试 学生成绩信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddAnswer">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteAnswer">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="answerList" :row-class-name="rowAnswerIndex" @selection-change="handleAnswerSelectionChange" ref="answer">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="学生学号" prop="stuId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.stuId" placeholder="请输入学生学号" />
            </template>
          </el-table-column>
          <el-table-column label="回答次数" prop="ansResponse" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ansResponse" placeholder="请输入回答次数" />
            </template>
          </el-table-column>
          <el-table-column label="正确次数" prop="ansApos" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ansApos" placeholder="请输入正确次数" />
            </template>
          </el-table-column>
          <el-table-column label="时间" prop="ansTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.ansTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择时间" />
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
import { listTask, getTask, delTask, addTask, updateTask } from "@/api/knowledgeQuiz/task";

export default {
  name: "Task",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedAnswer: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // A1 知识测试任务表格数据
      taskList: [],
      // A1 知识测试 学生成绩表格数据
      answerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teaId: null,
        taskDatetime: null,
        taskTitle: null,
        taskContent: null
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
    /** 查询A1 知识测试任务列表 */
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
        taskId: null,
        teaId: null,
        taskDatetime: null,
        taskTitle: null,
        taskContent: null
      };
      this.answerList = [];
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
      this.ids = selection.map(item => item.taskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加A1 知识测试任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const taskId = row.taskId || this.ids
      getTask(taskId).then(response => {
        this.form = response.data;
        this.answerList = response.data.answerList;
        this.open = true;
        this.title = "修改A1 知识测试任务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.answerList = this.answerList;
          if (this.form.taskId != null) {
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
      const taskIds = row.taskId || this.ids;
      this.$modal.confirm('是否确认删除A1 知识测试任务编号为"' + taskIds + '"的数据项？').then(function() {
        return delTask(taskIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** A1 知识测试 学生成绩序号 */
    rowAnswerIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** A1 知识测试 学生成绩添加按钮操作 */
    handleAddAnswer() {
      let obj = {};
      obj.stuId = "";
      obj.ansResponse = "";
      obj.ansApos = "";
      obj.ansTime = "";
      this.answerList.push(obj);
    },
    /** A1 知识测试 学生成绩删除按钮操作 */
    handleDeleteAnswer() {
      if (this.checkedAnswer.length == 0) {
        this.$modal.msgError("请先选择要删除的A1 知识测试 学生成绩数据");
      } else {
        const answerList = this.answerList;
        const checkedAnswer = this.checkedAnswer;
        this.answerList = answerList.filter(function(item) {
          return checkedAnswer.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleAnswerSelectionChange(selection) {
      this.checkedAnswer = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('knowledgeQuiz/task/export', {
        ...this.queryParams
      }, `task_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
