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
      <el-form-item label="运球进步分" prop="modscDribble">
        <el-input
          v-model="queryParams.modscDribble"
          placeholder="请输入运球进步分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投篮进步分" prop="modscShoot">
        <el-input
          v-model="queryParams.modscShoot"
          placeholder="请输入投篮进步分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="附加分" prop="modscAdditional">
        <el-input
          v-model="queryParams.modscAdditional"
          placeholder="请输入附加分"
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
          v-hasPermi="['score:dScore:add']"
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
          v-hasPermi="['score:dScore:edit']"
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
          v-hasPermi="['score:dScore:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['score:dScore:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dScoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="模块成绩主键" align="center" prop="modscId" />
      <el-table-column label="总分ID" align="center" prop="tsId" />
      <el-table-column label="运球进步分" align="center" prop="modscDribble" />
      <el-table-column label="投篮进步分" align="center" prop="modscShoot" />
      <el-table-column label="附加分" align="center" prop="modscAdditional" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['score:dScore:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['score:dScore:remove']"
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

    <!-- 添加或修改D 模块成绩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="总分ID" prop="tsId">
          <el-input v-model="form.tsId" placeholder="请输入总分ID" />
        </el-form-item>
        <el-form-item label="运球进步分" prop="modscDribble">
          <el-input v-model="form.modscDribble" placeholder="请输入运球进步分" />
        </el-form-item>
        <el-form-item label="投篮进步分" prop="modscShoot">
          <el-input v-model="form.modscShoot" placeholder="请输入投篮进步分" />
        </el-form-item>
        <el-form-item label="附加分" prop="modscAdditional">
          <el-input v-model="form.modscAdditional" placeholder="请输入附加分" />
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
import { listDScore, getDScore, delDScore, addDScore, updateDScore } from "@/api/score/dScore";

export default {
  name: "DScore",
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
      // D 模块成绩表格数据
      dScoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tsId: null,
        modscDribble: null,
        modscShoot: null,
        modscAdditional: null
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
    /** 查询D 模块成绩列表 */
    getList() {
      this.loading = true;
      listDScore(this.queryParams).then(response => {
        this.dScoreList = response.rows;
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
        modscId: null,
        tsId: null,
        modscDribble: null,
        modscShoot: null,
        modscAdditional: null
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
      this.ids = selection.map(item => item.modscId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加D 模块成绩";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const modscId = row.modscId || this.ids
      getDScore(modscId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改D 模块成绩";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.modscId != null) {
            updateDScore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDScore(this.form).then(response => {
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
      const modscIds = row.modscId || this.ids;
      this.$modal.confirm('是否确认删除D 模块成绩编号为"' + modscIds + '"的数据项？').then(function() {
        return delDScore(modscIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('score/dScore/export', {
        ...this.queryParams
      }, `dScore_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
