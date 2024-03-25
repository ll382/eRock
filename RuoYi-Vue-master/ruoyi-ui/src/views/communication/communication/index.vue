<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教学资源ID" prop="teachingId">
        <el-input
          v-model="queryParams.teachingId"
          placeholder="请输入教学资源ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论者" prop="comBy">
        <el-input
          v-model="queryParams.comBy"
          placeholder="请输入评论者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论时间" prop="comTime">
        <el-date-picker clearable
          v-model="queryParams.comTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择评论时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="主贴回复ID" prop="comPid">
        <el-input
          v-model="queryParams.comPid"
          placeholder="请输入主贴回复ID"
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
          v-hasPermi="['communication:communication:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="communicationList"
      row-key="comId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="教学资源ID" align="center" prop="teachingId" />
      <el-table-column label="评论内容" align="center" prop="comContent" />
      <el-table-column label="评论者" align="center" prop="comBy" />
      <el-table-column label="评论时间" align="center" prop="comTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.comTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
       <el-table-column label="主贴回复ID" align="center" prop="comPid" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['communication:communication:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['communication:communication:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['communication:communication:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改A1交流对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教学资源ID" prop="teachingId">
          <el-input v-model="form.teachingId" placeholder="请输入教学资源ID" />
        </el-form-item>
        <el-form-item label="评论内容">
          <editor v-model="form.comContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="评论者" prop="comBy">
          <el-input v-model="form.comBy" placeholder="请输入评论者" />
        </el-form-item>
        <el-form-item label="评论时间" prop="comTime">
          <el-date-picker clearable
            v-model="form.comTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择评论时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="主贴回复ID" prop="comPid">
          <treeselect v-model="form.comPid" :options="communicationOptions" :normalizer="normalizer" placeholder="请选择主贴回复ID" />
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
import { listCommunication, getCommunication, delCommunication, addCommunication, updateCommunication } from "@/api/communication/communication";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Communication",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // A1交流表格数据
      communicationList: [],
      // A1交流树选项
      communicationOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        teachingId: null,
        comContent: null,
        comBy: null,
        comTime: null,
        comPid: null
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
    /** 查询A1交流列表 */
    getList() {
      this.loading = true;
      listCommunication(this.queryParams).then(response => {
        this.communicationList = this.handleTree(response.data, "comId", "comPid");
        this.loading = false;
      });
    },
    /** 转换A1交流数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.comId,
        label: node.comContent,
        children: node.children
      };
    },
	/** 查询A1交流下拉树结构 */
    getTreeselect() {
      listCommunication().then(response => {
        this.communicationOptions = [];
        const data = { comId: 0, comContent: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "comId", "comPid");
        this.communicationOptions.push(data);
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
        comId: null,
        teachingId: null,
        comContent: null,
        comBy: null,
        comTime: null,
        comPid: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.comId) {
        this.form.comPid = row.comId;
      } else {
        this.form.comPid = 0;
      }
      this.open = true;
      this.title = "添加A1交流";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.comPid = row.comPid;
      }
      getCommunication(row.comId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改A1交流";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.comId != null) {
            updateCommunication(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCommunication(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除A1交流编号为"' + row.comId + '"的数据项？').then(function() {
        return delCommunication(row.comId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
