<template>
  <div class="app-container" style="height: 100vh; overflow: auto">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="82px"
    >
      <span style="margin-right: 3vw">上课老师：{{ teaName }}</span>
      <span style="margin-right: 3vw">上课班级：{{ className }}</span>
      <span
        >上课时间：{{
          crDate + "\t" + time + "第" + whichClass + crMain + "课"
        }}</span
      >
      <el-dropdown @command="handleCommand">
        <span class="el-dropdown-link">
          切换课程<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="ms_shooting">投篮</el-dropdown-item>
          <el-dropdown-item command="ms_dribble">运球</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['view:grades:add']"
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
          v-hasPermi="['view:grades:edit']"
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
          v-hasPermi="['view:grades:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['view:grades:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table
      v-loading="loading"
      :data="gradesList"
      @selection-change="handleSelectionChange"
      style="margin-top: 2vh"
    >
      <!-- style="margin-top: 2vh; height: 80vh; border: 1px solid white" -->
      <el-table-column label="学生姓名" align="center" prop="stuName" />
      <el-table-column
        v-for="(column, index) in columns"
        :key="index"
        :label="column.label"
        :prop="column.prop"
        :align="column.align"
      />
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="primary" plain @click="hyperx(scope.row)"
            >查看</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- <div class="box_b1"></div> -->
    <!-- <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[5, 10, 20, 40]"
      :total="gradesList.length"
      style="margin-left: 66vw;position:relative;top:-2.4vh;z-index:2"
    /> -->

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改学生成绩查询对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listGrades,
  getGrades,
  delGrades,
  addGrades,
  updateGrades,
} from "@/api/view/grades";

export default {
  name: "Grades",
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
      // 学生成绩查询表格数据
      gradesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stuId: null,
        stuName: null,
        className: null,
        msDribble: null,
        score: null,
        crDate: null,
        crMain: null,
        teaName: null,
        crType: "ms_shooting",
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        stuId: [
          { required: true, message: "学生学号不能为空", trigger: "blur" },
        ],
      },
      teaName: null,
      className: null,
      crDate: null,
      time: null,
      whichClass: null,
      scoreIndex: 0,
      columns: [],
      // 初始化每页显示5条数据
      pageSize: 10,
      // 初始化当前第1页
      currentPage: 1,
      // 初始化分页后的数据
      pagedGradesList: [],
      leng: 0,
      crMain: null,
      timerID: null,
      queryDate: null,
      queryHour: null,
    };
  },
  created() {
    this.getList();
    this.updateData();
  },
  methods: {
    updateData() {
      this.timerID = setInterval(() => {
        this.updateScoreIndex();
      }, 500);
    },
    handleCommand(command) {
      this.queryParams.crType = command;
      console.log(this.queryParams.crType);
      this.getList();
    },
    updatePagedData() {
      this.scoreIndex = 0;
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      this.pagedGradesList = this.gradesList.slice(start, end);
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.updatePagedData();
    },
    // 处理当前页码变化
    handleCurrentChange(val) {
      this.currentPage = val;
      clearInterval(this.timerID);
      console.log(this.scoreIndex);
      this.getList();
      this.updateData();
    },
    /** 查询学生成绩查询列表 */
    getList() {
      this.loading = true;
      listGrades(this.queryParams).then((response) => {
        this.gradesList = response.rows;
        this.teaName = response.rows[0].teaName;
        this.className = response.rows[0].className;
        this.crDate = response.rows[0].crDate;
        this.time = response.rows[0].time;
        this.whichClass = response.rows[0].whichClass;
        this.total = response.total;
        this.leng = response.rows[0].score.length;
        this.crMain = response.rows[0].crMain;
        if (response.rows[0].time === "上午") {
          this.queryDate = response.rows[0].crDate + " 08:00:00";
        } else {
          this.queryDate = response.rows[0].crDate + " 14:00:00";
        }
        if (response.rows[0].time === "上午") {
          this.queryHour = "08";
          this.shotDribble = "ms_shooting";
        } else {
          this.queryHour = "14";
          this.shotDribble = "ms_dribble";
        }
        // 初始化所有成绩的表头
        this.columns = [];
        for (let i = 0; i < this.leng; i++) {
          this.columns.push({
            label: `第${i + 1}次成绩`,
            prop: ``, // 注意这里，确保prop正确指向成绩数据
            align: "center",
          });
        }
        this.updatePagedData();
        this.loading = false;
      });
    },

    updateScoreIndex() {
      if (this.scoreIndex < this.leng) {
        this.scoreIndex = (this.scoreIndex + 1) % (this.leng + 1);
        // 更新对应列的prop为score字段
        this.$set(this.columns, this.scoreIndex - 1, {
          ...this.columns[this.scoreIndex - 1],
          prop: `score[${this.scoreIndex - 1}]`,
        });
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        stuId: null,
        stuName: null,
        className: null,
        msDribble: null,
        score: null,
        crDate: null,
        crMain: null,
        teaName: null,
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
      this.ids = selection.map((item) => item.stuId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生成绩查询";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const stuId = row.stuId || this.ids;
      getGrades(stuId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生成绩查询";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.stuId != null) {
            updateGrades(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGrades(this.form).then((response) => {
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
      const stuIds = row.stuId || this.ids;
      this.$modal
        .confirm('是否确认删除学生成绩查询编号为"' + stuIds + '"的数据项？')
        .then(function () {
          return delGrades(stuIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "view/grades/export",
        {
          ...this.queryParams,
        },
        `grades_${new Date().getTime()}.xlsx`
      );
    },
    // 手动方法：跳转
    hyperx(row) {
      this.$router.push({
        path: "page_two",
        query: {
          id: row.stuId,
          name: row.stuName,
          queryDate: this.queryDate,
          queryHour: this.queryHour,
          shotDribble: this.shotDribble
        },
      });
      console.log(row.stuId);
      console.log(this.shotDribble);
    },
  },
};
</script>

<style>
/* .box_b1 {
    background-color: white;
    height: 1vh;
    position: relative;
    top: -1vh;
    z-index: 1;
} */
.el-dropdown-link {
  margin-left: 2vw;
  cursor: pointer;
  color: black;
  font-size: 16px;
}
.el-icon-arrow-down {
  font-size: 16px;
}
</style>