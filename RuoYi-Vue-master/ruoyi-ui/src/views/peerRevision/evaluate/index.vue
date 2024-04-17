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
      <el-form-item label="学生学号" prop="stuId">
        <el-input
          v-model="queryParams.stuId"
          placeholder="请输入学生学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="被评学生学号" prop="esId">
        <el-input
          v-model="queryParams.esId"
          placeholder="请输入被评学生学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评价类型" prop="eeType" />
      <el-form-item label="参评学生学号" prop="peId">
        <el-input
          v-model="queryParams.peId"
          placeholder="请输入参评学生学号"
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
          v-hasPermi="['peerRevision:evaluate:add']"
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
          v-hasPermi="['peerRevision:evaluate:edit']"
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
          v-hasPermi="['peerRevision:evaluate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['peerRevision:evaluate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="evaluateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生互评ID" align="center" prop="seId" />
      <el-table-column label="课堂ID" align="center" prop="crId" />
      <el-table-column label="学生学号" align="center" prop="stuId" />
      <el-table-column label="被评学生学号" align="center" prop="esId" />
      <el-table-column label="评价类型" align="center" prop="eeType" />
      <el-table-column label="参评学生学号" align="center" prop="peId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['peerRevision:evaluate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['peerRevision:evaluate:remove']"
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

    <!-- 添加或修改A2 合作学习 学生互评对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课堂ID" prop="crId">
          <el-input v-model="form.crId" placeholder="请输入课堂ID" />
        </el-form-item>
        <el-form-item label="学生学号" prop="stuId">
          <el-input v-model="form.stuId" placeholder="请输入学生学号" />
        </el-form-item>
        <el-form-item label="被评学生学号" prop="esId">
          <el-input v-model="form.esId" placeholder="请输入被评学生学号" />
        </el-form-item>
        <el-form-item label="评价类型" prop="eeType" />
        <el-form-item label="参评学生学号" prop="peId">
          <el-input v-model="form.peId" placeholder="请输入参评学生学号" />
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
import { listEvaluate, getEvaluate, delEvaluate, addEvaluate, updateEvaluate } from "@/api/peerRevision/evaluate";

export default {
  name: "Evaluate",
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
      // A2 合作学习 学生互评表格数据
      evaluateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        crId: null,
        stuId: null,
        esId: null,
        eeType: null,
        peId: null
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
    /** 查询A2 合作学习 学生互评列表 */
    getList() {
      this.loading = true;
      listEvaluate(this.queryParams).then(response => {
        this.evaluateList = response.rows;
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
        seId: null,
        crId: null,
        stuId: null,
        esId: null,
        eeType: null,
        peId: null
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
      this.ids = selection.map(item => item.seId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加A2 合作学习 学生互评";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const seId = row.seId || this.ids
      getEvaluate(seId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改A2 合作学习 学生互评";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.seId != null) {
            updateEvaluate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEvaluate(this.form).then(response => {
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
      const seIds = row.seId || this.ids;
      this.$modal.confirm('是否确认删除A2 合作学习 学生互评编号为"' + seIds + '"的数据项？').then(function() {
        return delEvaluate(seIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('peerRevision/evaluate/export', {
        ...this.queryParams
      }, `evaluate_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
