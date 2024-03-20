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
      <el-form-item label="资源url" prop="resourceUrl">
        <el-input
          v-model="queryParams.resourceUrl"
          placeholder="请输入资源url"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdAt">
        <el-date-picker clearable
          v-model="queryParams.createdAt"
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
          v-hasPermi="['teachingExchange:table:add']"
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
          v-hasPermi="['teachingExchange:table:edit']"
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
          v-hasPermi="['teachingExchange:table:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['teachingExchange:table:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="教学资源ID" align="center" prop="teachingId" />
      <el-table-column label="老师工号" align="center" prop="teaId" />
      <el-table-column label="资源url" align="center" prop="resourceUrl" />
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['teachingExchange:table:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['teachingExchange:table:remove']"
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

    <!-- 添加或修改A1 线上学习 教学
 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="老师工号" prop="teaId">
          <el-input v-model="form.teaId" placeholder="请输入老师工号" />
        </el-form-item>
        <el-form-item label="资源url" prop="resourceUrl">
          <el-input v-model="form.resourceUrl" placeholder="请输入资源url" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdAt">
          <el-date-picker clearable
            v-model="form.createdAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-divider content-position="center">A1交流信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddA1Communication">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteA1Communication">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="a1CommunicationList" :row-class-name="rowA1CommunicationIndex" @selection-change="handleA1CommunicationSelectionChange" ref="a1Communication">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="评论者" prop="comBy" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.comBy" placeholder="请输入评论者" />
            </template>
          </el-table-column>
          <el-table-column label="评论时间" prop="comTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.comTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择评论时间" />
            </template>
          </el-table-column>
          <el-table-column label="评论类型" prop="comType" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.comType" placeholder="请选择评论类型">
                <el-option
                  v-for="dict in dict.type.invitation_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="回复的目标人" prop="comTarget" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.comTarget" placeholder="请输入回复的目标人" />
            </template>
          </el-table-column>
          <el-table-column label="主贴回复ID" prop="comPid" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.comPid" placeholder="请输入主贴回复ID" />
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
import { listTable, getTable, delTable, addTable, updateTable } from "@/api/teachingExchange/table";

export default {
  name: "Table",
  dicts: ['invitation_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedA1Communication: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // A1 线上学习 教学
      tableList: [],
      // A1交流表格数据
      a1CommunicationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teaId: null,
        resourceUrl: null,
        createdAt: null
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
    /** 查询A1 线上学习 教学
 列表 */
    getList() {
      this.loading = true;
      listTable(this.queryParams).then(response => {
        this.tableList = response.rows;
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
        teachingId: null,
        teaId: null,
        resourceUrl: null,
        createdAt: null
      };
      this.a1CommunicationList = [];
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
      this.ids = selection.map(item => item.teachingId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加A1 线上学习 教学";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const teachingId = row.teachingId || this.ids
      getTable(teachingId).then(response => {
        this.form = response.data;
        this.a1CommunicationList = response.data.a1CommunicationList;
        this.open = true;
        this.title = "修改A1 线上学习 教学";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.a1CommunicationList = this.a1CommunicationList;
          if (this.form.teachingId != null) {
            updateTable(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTable(this.form).then(response => {
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
      const teachingIds = row.teachingId || this.ids;
      this.$modal.confirm('是否确认删除A1 线上学习 教学编号为"' + teachingIds + '"的数据项？').then(function() {
        return delTable(teachingIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** A1交流序号 */
    rowA1CommunicationIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** A1交流添加按钮操作 */
    handleAddA1Communication() {
      let obj = {};
      obj.comContent = "";
      obj.comBy = "";
      obj.comTime = "";
      obj.comType = "";
      obj.comTarget = "";
      obj.comPid = "";
      this.a1CommunicationList.push(obj);
    },
    /** A1交流删除按钮操作 */
    handleDeleteA1Communication() {
      if (this.checkedA1Communication.length == 0) {
        this.$modal.msgError("请先选择要删除的A1交流数据");
      } else {
        const a1CommunicationList = this.a1CommunicationList;
        const checkedA1Communication = this.checkedA1Communication;
        this.a1CommunicationList = a1CommunicationList.filter(function(item) {
          return checkedA1Communication.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleA1CommunicationSelectionChange(selection) {
      this.checkedA1Communication = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('teachingExchange/table/export', {
        ...this.queryParams
      }, `table_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
