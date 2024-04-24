<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="知识测试任务" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入知识测试任务"
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
      <el-form-item label="回答次数" prop="ansResponse">
        <el-input
          v-model="queryParams.ansResponse"
          placeholder="请输入回答次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="正确次数" prop="ansApos">
        <el-input
          v-model="queryParams.ansApos"
          placeholder="请输入正确次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时间" prop="ansTime">
        <el-date-picker clearable
          v-model="queryParams.ansTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择时间">
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
          v-hasPermi="['knowledgeQuiz:achievement:add']"
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
          v-hasPermi="['knowledgeQuiz:achievement:edit']"
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
          v-hasPermi="['knowledgeQuiz:achievement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['knowledgeQuiz:achievement:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="achievementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="答题ID" align="center" prop="ansId" />
      <el-table-column label="知识测试任务" align="center" prop="taskId" />
      <el-table-column label="学生学号" align="center" prop="stuId" />
      <el-table-column label="回答次数" align="center" prop="ansResponse" />
      <el-table-column label="正确次数" align="center" prop="ansApos" />
      <el-table-column label="时间" align="center" prop="ansTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ansTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['knowledgeQuiz:achievement:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['knowledgeQuiz:achievement:remove']"
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

    <!-- 添加或修改A1 知识测试 学生成绩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="知识测试任务" prop="taskId">
          <el-input v-model="form.taskId" placeholder="请输入知识测试任务" />
        </el-form-item>
        <el-form-item label="学生学号" prop="stuId">
          <el-input v-model="form.stuId" placeholder="请输入学生学号" />
        </el-form-item>
        <el-form-item label="回答次数" prop="ansResponse">
          <el-input v-model="form.ansResponse" placeholder="请输入回答次数" />
        </el-form-item>
        <el-form-item label="正确次数" prop="ansApos">
          <el-input v-model="form.ansApos" placeholder="请输入正确次数" />
        </el-form-item>
        <el-form-item label="时间" prop="ansTime">
          <el-date-picker clearable
            v-model="form.ansTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择时间">
          </el-date-picker>
        </el-form-item>
        <el-divider content-position="center">A1 知识测试 答题信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddA1Answered">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteA1Answered">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="a1AnsweredList" :row-class-name="rowA1AnsweredIndex" @selection-change="handleA1AnsweredSelectionChange" ref="a1Answered">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="题目编号" prop="qqId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.qqId" placeholder="请输入题目编号" />
            </template>
          </el-table-column>
          <el-table-column label="学生答案" prop="ansedCon" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ansedCon" placeholder="请输入学生答案" />
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
import { listAchievement, getAchievement, delAchievement, addAchievement, updateAchievement } from "@/api/knowledgeQuiz/achievement";

export default {
  name: "Achievement",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedA1Answered: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // A1 知识测试 学生成绩表格数据
      achievementList: [],
      // A1 知识测试 答题表格数据
      a1AnsweredList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskId: null,
        stuId: null,
        ansResponse: null,
        ansApos: null,
        ansTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taskId: [
          { required: true, message: "知识测试任务不能为空", trigger: "blur" }
        ],
        stuId: [
          { required: true, message: "学生学号不能为空", trigger: "blur" }
        ],
        ansResponse: [
          { required: true, message: "回答次数不能为空", trigger: "blur" }
        ],
        ansApos: [
          { required: true, message: "正确次数不能为空", trigger: "blur" }
        ],
        ansTime: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询A1 知识测试 学生成绩列表 */
    getList() {
      this.loading = true;
      listAchievement(this.queryParams).then(response => {
        this.achievementList = response.rows;
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
        ansId: null,
        taskId: null,
        stuId: null,
        ansResponse: null,
        ansApos: null,
        ansTime: null
      };
      this.a1AnsweredList = [];
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
      this.ids = selection.map(item => item.ansId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加A1 知识测试 学生成绩";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ansId = row.ansId || this.ids
      getAchievement(ansId).then(response => {
        this.form = response.data;
        this.a1AnsweredList = response.data.a1AnsweredList;
        this.open = true;
        this.title = "修改A1 知识测试 学生成绩";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.a1AnsweredList = this.a1AnsweredList;
          if (this.form.ansId != null) {
            updateAchievement(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAchievement(this.form).then(response => {
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
      const ansIds = row.ansId || this.ids;
      this.$modal.confirm('是否确认删除A1 知识测试 学生成绩编号为"' + ansIds + '"的数据项？').then(function() {
        return delAchievement(ansIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** A1 知识测试 答题序号 */
    rowA1AnsweredIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** A1 知识测试 答题添加按钮操作 */
    handleAddA1Answered() {
      let obj = {};
      obj.qqId = "";
      obj.ansedCon = "";
      this.a1AnsweredList.push(obj);
    },
    /** A1 知识测试 答题删除按钮操作 */
    handleDeleteA1Answered() {
      if (this.checkedA1Answered.length == 0) {
        this.$modal.msgError("请先选择要删除的A1 知识测试 答题数据");
      } else {
        const a1AnsweredList = this.a1AnsweredList;
        const checkedA1Answered = this.checkedA1Answered;
        this.a1AnsweredList = a1AnsweredList.filter(function(item) {
          return checkedA1Answered.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleA1AnsweredSelectionChange(selection) {
      this.checkedA1Answered = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('knowledgeQuiz/achievement/export', {
        ...this.queryParams
      }, `achievement_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
