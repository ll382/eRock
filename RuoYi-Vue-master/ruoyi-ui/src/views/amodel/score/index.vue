<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="总分ID" prop="tsId">
        <el-input
          v-model="queryParams.tsId"
          placeholder="请输入总分ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="线上学习" prop="onlineCourse">
        <el-input
          v-model="queryParams.onlineCourse"
          placeholder="请输入线上学习"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="知识测试" prop="knowledgeTests">
        <el-input
          v-model="queryParams.knowledgeTests"
          placeholder="请输入知识测试"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="技能练习1" prop="skillTests1">
        <el-input
          v-model="queryParams.skillTests1"
          placeholder="请输入技能练习1"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课堂表现" prop="classroomPerformance">
        <el-input
          v-model="queryParams.classroomPerformance"
          placeholder="请输入课堂表现"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="思政教育" prop="ideologicalPolitical">
        <el-input
          v-model="queryParams.ideologicalPolitical"
          placeholder="请输入思政教育"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生互评" prop="peerAssessment">
        <el-input
          v-model="queryParams.peerAssessment"
          placeholder="请输入学生互评"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="助教评价" prop="teachingAssistant">
        <el-input
          v-model="queryParams.teachingAssistant"
          placeholder="请输入助教评价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="技能测试2" prop="skillTests2">
        <el-input
          v-model="queryParams.skillTests2"
          placeholder="请输入技能测试2"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="体能测试2" prop="fitnessTests2">
        <el-input
          v-model="queryParams.fitnessTests2"
          placeholder="请输入体能测试2"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="技术练习3" prop="skillTests3">
        <el-input
          v-model="queryParams.skillTests3"
          placeholder="请输入技术练习3"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="体能练习3" prop="fitnessTests3">
        <el-input
          v-model="queryParams.fitnessTests3"
          placeholder="请输入体能练习3"
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
          v-hasPermi="['amodel:score:add']"
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
          v-hasPermi="['amodel:score:edit']"
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
          v-hasPermi="['amodel:score:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['amodel:score:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="A模块成绩主键" align="center" prop="modscoId" />
      <el-table-column label="总分ID" align="center" prop="tsId" />
      <el-table-column label="线上学习" align="center" prop="onlineCourse" />
      <el-table-column label="知识测试" align="center" prop="knowledgeTests" />
      <el-table-column label="技能练习1" align="center" prop="skillTests1" />
      <el-table-column label="课堂表现" align="center" prop="classroomPerformance" />
      <el-table-column label="思政教育" align="center" prop="ideologicalPolitical" />
      <el-table-column label="学生互评" align="center" prop="peerAssessment" />
      <el-table-column label="助教评价" align="center" prop="teachingAssistant" />
      <el-table-column label="技能测试2" align="center" prop="skillTests2" />
      <el-table-column label="体能测试2" align="center" prop="fitnessTests2" />
      <el-table-column label="技术练习3" align="center" prop="skillTests3" />
      <el-table-column label="体能练习3" align="center" prop="fitnessTests3" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['amodel:score:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['amodel:score:remove']"
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

    <!-- 添加或修改 A 模块成绩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="总分ID" prop="tsId">
          <el-input v-model="form.tsId" placeholder="请输入总分ID" />
        </el-form-item>
        <el-form-item label="线上学习" prop="onlineCourse">
          <el-input v-model="form.onlineCourse" placeholder="请输入线上学习" />
        </el-form-item>
        <el-form-item label="知识测试" prop="knowledgeTests">
          <el-input v-model="form.knowledgeTests" placeholder="请输入知识测试" />
        </el-form-item>
        <el-form-item label="技能练习1" prop="skillTests1">
          <el-input v-model="form.skillTests1" placeholder="请输入技能练习1" />
        </el-form-item>
        <el-form-item label="课堂表现" prop="classroomPerformance">
          <el-input v-model="form.classroomPerformance" placeholder="请输入课堂表现" />
        </el-form-item>
        <el-form-item label="思政教育" prop="ideologicalPolitical">
          <el-input v-model="form.ideologicalPolitical" placeholder="请输入思政教育" />
        </el-form-item>
        <el-form-item label="学生互评" prop="peerAssessment">
          <el-input v-model="form.peerAssessment" placeholder="请输入学生互评" />
        </el-form-item>
        <el-form-item label="助教评价" prop="teachingAssistant">
          <el-input v-model="form.teachingAssistant" placeholder="请输入助教评价" />
        </el-form-item>
        <el-form-item label="技能测试2" prop="skillTests2">
          <el-input v-model="form.skillTests2" placeholder="请输入技能测试2" />
        </el-form-item>
        <el-form-item label="体能测试2" prop="fitnessTests2">
          <el-input v-model="form.fitnessTests2" placeholder="请输入体能测试2" />
        </el-form-item>
        <el-form-item label="技术练习3" prop="skillTests3">
          <el-input v-model="form.skillTests3" placeholder="请输入技术练习3" />
        </el-form-item>
        <el-form-item label="体能练习3" prop="fitnessTests3">
          <el-input v-model="form.fitnessTests3" placeholder="请输入体能练习3" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listScore, getScore, delScore, addScore, updateScore } from "@/api/amodel/score";

export default {
  name: "Score",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      //  A 模块成绩表格数据
      scoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tsId: null,
        onlineCourse: null,
        knowledgeTests: null,
        skillTests1: null,
        classroomPerformance: null,
        ideologicalPolitical: null,
        peerAssessment: null,
        teachingAssistant: null,
        skillTests2: null,
        fitnessTests2: null,
        skillTests3: null,
        fitnessTests3: null
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
    /** 查询 A 模块成绩列表 */
    getList() {
      this.loading = true;
      listScore(this.queryParams).then(response => {
        this.scoreList = response.rows;
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
        modscoId: null,
        tsId: null,
        onlineCourse: null,
        knowledgeTests: null,
        skillTests1: null,
        classroomPerformance: null,
        ideologicalPolitical: null,
        peerAssessment: null,
        teachingAssistant: null,
        skillTests2: null,
        fitnessTests2: null,
        skillTests3: null,
        fitnessTests3: null
      };
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
      this.ids = selection.map(item => item.modscoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加 A 模块成绩";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const modscoId = row.modscoId || this.ids
      getScore(modscoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改 A 模块成绩";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.modscoId != null) {
            updateScore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addScore(this.form).then(response => {
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
      const modscoIds = row.modscoId || this.ids;
      this.$modal.confirm('是否确认删除 A 模块成绩编号为"' + modscoIds + '"的数据项？').then(function() {
        return delScore(modscoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('amodel/score/export', {
        ...this.queryParams
      }, `score_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
