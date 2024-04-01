<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课时标题" prop="lesName">
        <el-input
          v-model="queryParams.lesName"
          placeholder="请输入课时标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课时顺序ID" prop="lesOrderId">
        <el-input
          v-model="queryParams.lesOrderId"
          placeholder="请输入课时顺序ID"
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
          v-hasPermi="['unit:units:add']"
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
          v-hasPermi="['unit:units:edit']"
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
          v-hasPermi="['unit:units:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['unit:units:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="unitsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课时ID" align="center" prop="lesId" />
      <el-table-column label="课时标题" align="center" prop="lesName" />
      <el-table-column label="课时顺序ID" align="center" prop="lesOrderId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['unit:units:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['unit:units:remove']"
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

    <!-- 添加或修改课时单元对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课时标题" prop="lesName">
          <el-input v-model="form.lesName" placeholder="请输入课时标题" />
        </el-form-item>
        <el-form-item label="课时顺序ID" prop="lesOrderId">
          <el-input v-model="form.lesOrderId" placeholder="请输入课时顺序ID" />
        </el-form-item>
        <el-divider content-position="center">A1 线上学习学生线上观看记录表信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddTeachingTable">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteTeachingTable">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="teachingTableList" :row-class-name="rowTeachingTableIndex" @selection-change="handleTeachingTableSelectionChange" ref="teachingTable">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="老师工号" prop="teaId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.teaId" placeholder="请输入老师工号" />
            </template>
          </el-table-column>
          <el-table-column label="资源url" prop="resourceUrl" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.resourceUrl" placeholder="请输入资源url" />
            </template>
          </el-table-column>
          <el-table-column label="创建时间" prop="createdAt" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.createdAt" type="date" value-format="yyyy-MM-dd" placeholder="请选择创建时间" />
            </template>
          </el-table-column>
          <el-table-column label="教学标题" prop="teachingTitle" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.teachingTitle" placeholder="请输入教学标题" />
            </template>
          </el-table-column>
          <el-table-column label="排序ID" prop="teachingOrder" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.teachingOrder" placeholder="请输入排序ID" />
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
import { listUnits, getUnits, delUnits, addUnits, updateUnits } from "@/api/unit/units";

export default {
  name: "Units",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedTeachingTable: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 课时单元表格数据
      unitsList: [],
      // A1 线上学习学生线上观看记录表表格数据
      teachingTableList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        lesName: null,
        lesOrderId: null
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
    /** 查询课时单元列表 */
    getList() {
      this.loading = true;
      listUnits(this.queryParams).then(response => {
        this.unitsList = response.rows;
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
        lesId: null,
        lesName: null,
        lesOrderId: null
      };
      this.teachingTableList = [];
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
      this.ids = selection.map(item => item.lesId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课时单元";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const lesId = row.lesId || this.ids
      getUnits(lesId).then(response => {
        this.form = response.data;
        this.teachingTableList = response.data.teachingTableList;
        this.open = true;
        this.title = "修改课时单元";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.teachingTableList = this.teachingTableList;
          if (this.form.lesId != null) {
            updateUnits(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUnits(this.form).then(response => {
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
      const lesIds = row.lesId || this.ids;
      this.$modal.confirm('是否确认删除课时单元编号为"' + lesIds + '"的数据项？').then(function() {
        return delUnits(lesIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** A1 线上学习学生线上观看记录表序号 */
    rowTeachingTableIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** A1 线上学习学生线上观看记录表添加按钮操作 */
    handleAddTeachingTable() {
      let obj = {};
      obj.teaId = "";
      obj.resourceUrl = "";
      obj.createdAt = "";
      obj.teachingTitle = "";
      obj.teachingOrder = "";
      this.teachingTableList.push(obj);
    },
    /** A1 线上学习学生线上观看记录表删除按钮操作 */
    handleDeleteTeachingTable() {
      if (this.checkedTeachingTable.length == 0) {
        this.$modal.msgError("请先选择要删除的A1 线上学习学生线上观看记录表数据");
      } else {
        const teachingTableList = this.teachingTableList;
        const checkedTeachingTable = this.checkedTeachingTable;
        this.teachingTableList = teachingTableList.filter(function(item) {
          return checkedTeachingTable.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleTeachingTableSelectionChange(selection) {
      this.checkedTeachingTable = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('unit/units/export', {
        ...this.queryParams
      }, `units_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
