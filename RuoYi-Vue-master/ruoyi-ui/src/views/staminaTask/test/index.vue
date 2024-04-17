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
      <el-form-item label="枚举ID" prop="enumId">
        <el-input
          v-model="queryParams.enumId"
          placeholder="请输入枚举ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务名称" prop="phyName">
        <el-input
          v-model="queryParams.phyName"
          placeholder="请输入任务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务描述" prop="phyDescript">
        <el-input
          v-model="queryParams.phyDescript"
          placeholder="请输入任务描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="phyTime">
        <el-date-picker clearable
          v-model="queryParams.phyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="phyEndTime">
        <el-date-picker clearable
          v-model="queryParams.phyEndTime"
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
          v-hasPermi="['staminaTask:test:add']"
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
          v-hasPermi="['staminaTask:test:edit']"
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
          v-hasPermi="['staminaTask:test:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['staminaTask:test:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="testList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="老师工号" align="center" prop="teaId" />
      <el-table-column label="枚举ID" align="center" prop="enumId" />
      <el-table-column label="任务名称" align="center" prop="phyName" />
      <el-table-column label="任务描述" align="center" prop="phyDescript" />
      <el-table-column label="任务ID" align="center" prop="phyId" />
      <el-table-column label="创建时间" align="center" prop="phyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.phyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="phyEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.phyEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['staminaTask:test:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['staminaTask:test:remove']"
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

    <!-- 添加或修改A2 A3体能测试对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="老师工号" prop="teaId">
          <el-input v-model="form.teaId" placeholder="请输入老师工号" />
        </el-form-item>
        <el-form-item label="枚举ID" prop="enumId">
          <el-input v-model="form.enumId" placeholder="请输入枚举ID" />
        </el-form-item>
        <el-form-item label="任务名称" prop="phyName">
          <el-input v-model="form.phyName" placeholder="请输入任务名称" />
        </el-form-item>
        <el-form-item label="任务描述" prop="phyDescript">
          <el-input v-model="form.phyDescript" placeholder="请输入任务描述" />
        </el-form-item>
        <el-form-item label="创建时间" prop="phyTime">
          <el-date-picker clearable
            v-model="form.phyTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="phyEndTime">
          <el-date-picker clearable
            v-model="form.phyEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-divider content-position="center">A2 A3 体能成绩信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddA2A3PhysicaScore">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteA2A3PhysicaScore">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="a2A3PhysicaScoreList" :row-class-name="rowA2A3PhysicaScoreIndex" @selection-change="handleA2A3PhysicaScoreSelectionChange" ref="a2A3PhysicaScore">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="学生学号" prop="stuId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.stuId" placeholder="请输入学生学号" />
            </template>
          </el-table-column>
          <el-table-column label="耐力" prop="physStamina2" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.physStamina2" placeholder="请输入耐力" />
            </template>
          </el-table-column>
          <el-table-column label="速度" prop="physSpeed2" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.physSpeed2" placeholder="请输入速度" />
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
import { listTest, getTest, delTest, addTest, updateTest } from "@/api/staminaTask/test";

export default {
  name: "Test",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedA2A3PhysicaScore: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // A2 A3体能测试表格数据
      testList: [],
      // A2 A3 体能成绩表格数据
      a2A3PhysicaScoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teaId: null,
        enumId: null,
        phyName: null,
        phyDescript: null,
        phyTime: null,
        phyEndTime: null
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
    /** 查询A2 A3体能测试列表 */
    getList() {
      this.loading = true;
      listTest(this.queryParams).then(response => {
        this.testList = response.rows;
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
        phyId: null,
        teaId: null,
        enumId: null,
        phyName: null,
        phyDescript: null,
        phyTime: null,
        phyEndTime: null
      };
      this.a2A3PhysicaScoreList = [];
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
      this.ids = selection.map(item => item.phyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加A2 A3体能测试";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const phyId = row.phyId || this.ids
      getTest(phyId).then(response => {
        this.form = response.data;
        this.a2A3PhysicaScoreList = response.data.a2A3PhysicaScoreList;
        this.open = true;
        this.title = "修改A2 A3体能测试";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.a2A3PhysicaScoreList = this.a2A3PhysicaScoreList;
          if (this.form.phyId != null) {
            updateTest(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTest(this.form).then(response => {
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
      const phyIds = row.phyId || this.ids;
      this.$modal.confirm('是否确认删除A2 A3体能测试编号为"' + phyIds + '"的数据项？').then(function() {
        return delTest(phyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** A2 A3 体能成绩序号 */
    rowA2A3PhysicaScoreIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** A2 A3 体能成绩添加按钮操作 */
    handleAddA2A3PhysicaScore() {
      let obj = {};
      obj.stuId = "";
      obj.physStamina2 = "";
      obj.physSpeed2 = "";
      this.a2A3PhysicaScoreList.push(obj);
    },
    /** A2 A3 体能成绩删除按钮操作 */
    handleDeleteA2A3PhysicaScore() {
      if (this.checkedA2A3PhysicaScore.length == 0) {
        this.$modal.msgError("请先选择要删除的A2 A3 体能成绩数据");
      } else {
        const a2A3PhysicaScoreList = this.a2A3PhysicaScoreList;
        const checkedA2A3PhysicaScore = this.checkedA2A3PhysicaScore;
        this.a2A3PhysicaScoreList = a2A3PhysicaScoreList.filter(function(item) {
          return checkedA2A3PhysicaScore.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleA2A3PhysicaScoreSelectionChange(selection) {
      this.checkedA2A3PhysicaScore = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('staminaTask/test/export', {
        ...this.queryParams
      }, `test_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
