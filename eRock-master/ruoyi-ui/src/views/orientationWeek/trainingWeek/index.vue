<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="老师工号" prop="teaId">
        <el-input
          v-model="queryParams.teaId"
          placeholder="请输入老师工号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="phtrStartTime">
        <el-date-picker clearable
          v-model="queryParams.phtrStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="phtrEndTime">
        <el-date-picker clearable
          v-model="queryParams.phtrEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择结束时间">
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
          v-hasPermi="['orientationWeek:trainingWeek:add']"
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
          v-hasPermi="['orientationWeek:trainingWeek:edit']"
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
          v-hasPermi="['orientationWeek:trainingWeek:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['orientationWeek:trainingWeek:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="trainingWeekList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="训练周ID" align="center" prop="phtrId" />
      <el-table-column label="老师工号" align="center" prop="teaId" />
      <el-table-column label="开始时间" align="center" prop="phtrStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.phtrStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="phtrEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.phtrEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="周任务名称" align="center" prop="phtrName" />
      <el-table-column label="周任务描述" align="center" prop="phtrDescribe" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['orientationWeek:trainingWeek:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['orientationWeek:trainingWeek:remove']"
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

    <!-- 添加或修改A3 体能训练周对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="老师工号" prop="teaId">
          <el-input v-model="form.teaId" placeholder="请输入老师工号" />
        </el-form-item>
        <el-form-item label="开始时间" prop="phtrStartTime">
          <el-date-picker clearable
            v-model="form.phtrStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="phtrEndTime">
          <el-date-picker clearable
            v-model="form.phtrEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="周任务名称" prop="phtrName">
          <el-input v-model="form.phtrName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="周任务描述" prop="phtrDescribe">
          <el-input v-model="form.phtrDescribe" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">A3 学生周训资源信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddA3WeeklyTraining">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteA3WeeklyTraining">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="a3WeeklyTrainingList" :row-class-name="rowA3WeeklyTrainingIndex" @selection-change="handleA3WeeklyTrainingSelectionChange" ref="a3WeeklyTraining">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="学生学号" prop="stuId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.stuId" placeholder="请输入学生学号" />
            </template>
          </el-table-column>
          <el-table-column label="上传时间" prop="wetrTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.wetrTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择上传时间" />
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
import { listTrainingWeek, getTrainingWeek, delTrainingWeek, addTrainingWeek, updateTrainingWeek } from "@/api/orientationWeek/trainingWeek";

export default {
  name: "TrainingWeek",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedA3WeeklyTraining: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // A3 体能训练周表格数据
      trainingWeekList: [],
      // A3 学生周训资源表格数据
      a3WeeklyTrainingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teaId: null,
        phtrStartTime: null,
        phtrEndTime: null,
        phtrName: null,
        phtrDescribe: null
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
    /** 查询A3 体能训练周列表 */
    getList() {
      this.loading = true;
      listTrainingWeek(this.queryParams).then(response => {
        this.trainingWeekList = response.rows;
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
        phtrId: null,
        teaId: null,
        phtrStartTime: null,
        phtrEndTime: null,
        phtrName: null,
        phtrDescribe: null
      };
      this.a3WeeklyTrainingList = [];
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
      this.ids = selection.map(item => item.phtrId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加A3 体能训练周";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const phtrId = row.phtrId || this.ids
      getTrainingWeek(phtrId).then(response => {
        this.form = response.data;
        this.a3WeeklyTrainingList = response.data.a3WeeklyTrainingList;
        this.open = true;
        this.title = "修改A3 体能训练周";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.a3WeeklyTrainingList = this.a3WeeklyTrainingList;
          if (this.form.phtrId != null) {
            updateTrainingWeek(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTrainingWeek(this.form).then(response => {
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
      const phtrIds = row.phtrId || this.ids;
      this.$modal.confirm('是否确认删除A3 体能训练周编号为"' + phtrIds + '"的数据项？').then(function() {
        return delTrainingWeek(phtrIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** A3 学生周训资源序号 */
    rowA3WeeklyTrainingIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** A3 学生周训资源添加按钮操作 */
    handleAddA3WeeklyTraining() {
      let obj = {};
      obj.stuId = "";
      obj.wetrTime = "";
      obj.wetrUrl = "";
      this.a3WeeklyTrainingList.push(obj);
    },
    /** A3 学生周训资源删除按钮操作 */
    handleDeleteA3WeeklyTraining() {
      if (this.checkedA3WeeklyTraining.length == 0) {
        this.$modal.msgError("请先选择要删除的A3 学生周训资源数据");
      } else {
        const a3WeeklyTrainingList = this.a3WeeklyTrainingList;
        const checkedA3WeeklyTraining = this.checkedA3WeeklyTraining;
        this.a3WeeklyTrainingList = a3WeeklyTrainingList.filter(function(item) {
          return checkedA3WeeklyTraining.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleA3WeeklyTrainingSelectionChange(selection) {
      this.checkedA3WeeklyTraining = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('orientationWeek/trainingWeek/export', {
        ...this.queryParams
      }, `trainingWeek_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
