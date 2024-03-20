<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="枚举ID" prop="enumId">
        <el-input
          v-model="queryParams.enumId"
          placeholder="请输入枚举ID"
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
      <el-form-item label="训练URL" prop="tcUrl">
        <el-input
          v-model="queryParams.tcUrl"
          placeholder="请输入训练URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="tcTime">
        <el-date-picker clearable
                        v-model="queryParams.tcTime"
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
          v-hasPermi="['bModularity:club:add']"
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
          v-hasPermi="['bModularity:club:edit']"
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
          v-hasPermi="['bModularity:club:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bModularity:club:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="clubList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="训练ID" align="center" prop="tcId" />
      <el-table-column label="枚举ID" align="center" prop="enumId" />
      <el-table-column label="学生学号" align="center" prop="stuId" />
      <el-table-column label="训练URL" align="center" prop="tcUrl" />
      <el-table-column label="创建时间" align="center" prop="tcTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.tcTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bModularity:club:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bModularity:club:remove']"
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

    <!-- 添加或修改球队、社团训练对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="枚举ID" prop="enumId">
          <el-input v-model="form.enumId" placeholder="请输入枚举ID" />
        </el-form-item>
        <el-form-item label="学生学号" prop="stuId">
          <el-input v-model="form.stuId" placeholder="请输入学生学号" />
        </el-form-item>
        <el-form-item label="训练URL" prop="tcUrl">
          <el-input v-model="form.tcUrl" placeholder="请输入训练URL" />
        </el-form-item>
        <el-form-item label="创建时间" prop="tcTime">
          <el-date-picker clearable
                          v-model="form.tcTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-divider content-position="center">B1 资源信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddB1MassSource">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteB1MassSource">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="b1MassSourceList" :row-class-name="rowB1MassSourceIndex" @selection-change="handleB1MassSourceSelectionChange" ref="b1MassSource">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="资源URL" prop="teasUrl" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.teasUrl" placeholder="请输入资源URL" />
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
import { listClub, getClub, delClub, addClub, updateClub } from "@/api/bModularity/club";

export default {
  name: "Club",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedB1MassSource: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 球队、社团训练表格数据
      clubList: [],
      // B1 资源表格数据
      b1MassSourceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        enumId: null,
        stuId: null,
        tcUrl: null,
        tcTime: null
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
    /** 查询球队、社团训练列表 */
    getList() {
      this.loading = true;
      listClub(this.queryParams).then(response => {
        this.clubList = response.rows;
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
        tcId: null,
        enumId: null,
        stuId: null,
        tcUrl: null,
        tcTime: null
      };
      this.b1MassSourceList = [];
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
      this.ids = selection.map(item => item.tcId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加球队、社团训练";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tcId = row.tcId || this.ids
      getClub(tcId).then(response => {
        this.form = response.data;
        this.b1MassSourceList = response.data.b1MassSourceList;
        this.open = true;
        this.title = "修改球队、社团训练";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.b1MassSourceList = this.b1MassSourceList;
          if (this.form.tcId != null) {
            updateClub(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClub(this.form).then(response => {
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
      const tcIds = row.tcId || this.ids;
      this.$modal.confirm('是否确认删除球队、社团训练编号为"' + tcIds + '"的数据项？').then(function() {
        return delClub(tcIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** B1 资源序号 */
    rowB1MassSourceIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** B1 资源添加按钮操作 */
    handleAddB1MassSource() {
      let obj = {};
      obj.teasUrl = "";
      this.b1MassSourceList.push(obj);
    },
    /** B1 资源删除按钮操作 */
    handleDeleteB1MassSource() {
      if (this.checkedB1MassSource.length == 0) {
        this.$modal.msgError("请先选择要删除的B1 资源数据");
      } else {
        const b1MassSourceList = this.b1MassSourceList;
        const checkedB1MassSource = this.checkedB1MassSource;
        this.b1MassSourceList = b1MassSourceList.filter(function(item) {
          return checkedB1MassSource.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleB1MassSourceSelectionChange(selection) {
      this.checkedB1MassSource = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bModularity/club/export', {
        ...this.queryParams
      }, `club_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
