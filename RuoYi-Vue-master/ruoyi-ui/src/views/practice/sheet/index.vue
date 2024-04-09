<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务ID" prop="etId">
        <el-input
          v-model="queryParams.etId"
          placeholder="请输入任务ID"
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
      <el-form-item label="老师工号" prop="teaId">
        <el-input
          v-model="queryParams.teaId"
          placeholder="请输入老师工号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评分时间" prop="msTime">
        <el-date-picker clearable
          v-model="queryParams.msTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择评分时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="运球分数" prop="msDribble">
        <el-input
          v-model="queryParams.msDribble"
          placeholder="请输入运球分数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投篮分数" prop="msShooting">
        <el-input
          v-model="queryParams.msShooting"
          placeholder="请输入投篮分数"
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
      <el-form-item label="erock评分" prop="msScore">
        <el-input
          v-model="queryParams.msScore"
          placeholder="请输入erock评分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="技能数值1" prop="ms1">
        <el-input
          v-model="queryParams.ms1"
          placeholder="请输入技能数值1"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="技能数值2" prop="ms2">
        <el-input
          v-model="queryParams.ms2"
          placeholder="请输入技能数值2"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="技能数值3" prop="ms3">
        <el-input
          v-model="queryParams.ms3"
          placeholder="请输入技能数值3"
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
          v-hasPermi="['practice:sheet:add']"
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
          v-hasPermi="['practice:sheet:edit']"
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
          v-hasPermi="['practice:sheet:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['practice:sheet:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sheetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评分ID" align="center" prop="msId" />
      <el-table-column label="任务ID" align="center" prop="etId" />
      <el-table-column label="学生学号" align="center" prop="stuId" />
      <el-table-column label="老师工号" align="center" prop="teaId" />
      <el-table-column label="评分时间" align="center" prop="msTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.msTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="运球分数" align="center" prop="msDribble" />
      <el-table-column label="投篮分数" align="center" prop="msShooting" />
      <el-table-column label="枚举ID" align="center" prop="enumId" />
      <el-table-column label="erock评分" align="center" prop="msScore" />
      <el-table-column label="技能数值1" align="center" prop="ms1" />
      <el-table-column label="技能数值2" align="center" prop="ms2" />
      <el-table-column label="技能数值3" align="center" prop="ms3" />
      <el-table-column label="运动处方" align="center" prop="msClass" />
      <el-table-column label="erock截图" align="center" prop="msImg" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['practice:sheet:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['practice:sheet:remove']"
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

    <!-- 添加或修改练习、测试评分表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务ID" prop="etId">
          <el-input v-model="form.etId" placeholder="请输入任务ID" />
        </el-form-item>
        <el-form-item label="学生学号" prop="stuId">
          <el-input v-model="form.stuId" placeholder="请输入学生学号" />
        </el-form-item>
        <el-form-item label="老师工号" prop="teaId">
          <el-input v-model="form.teaId" placeholder="请输入老师工号" />
        </el-form-item>
        <el-form-item label="评分时间" prop="msTime">
          <el-date-picker clearable
            v-model="form.msTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评分时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="运球分数" prop="msDribble">
          <el-input v-model="form.msDribble" placeholder="请输入运球分数" />
        </el-form-item>
        <el-form-item label="投篮分数" prop="msShooting">
          <el-input v-model="form.msShooting" placeholder="请输入投篮分数" />
        </el-form-item>
        <el-form-item label="枚举ID" prop="enumId">
          <el-input v-model="form.enumId" placeholder="请输入枚举ID" />
        </el-form-item>
        <el-form-item label="erock评分" prop="msScore">
          <el-input v-model="form.msScore" placeholder="请输入erock评分" />
        </el-form-item>
        <el-form-item label="技能数值1" prop="ms1">
          <el-input v-model="form.ms1" placeholder="请输入技能数值1" />
        </el-form-item>
        <el-form-item label="技能数值2" prop="ms2">
          <el-input v-model="form.ms2" placeholder="请输入技能数值2" />
        </el-form-item>
        <el-form-item label="技能数值3" prop="ms3">
          <el-input v-model="form.ms3" placeholder="请输入技能数值3" />
        </el-form-item>
        <el-form-item label="运动处方" prop="msClass">
          <el-input v-model="form.msClass" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="erock截图" prop="msImg">
          <el-input v-model="form.msImg" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">投篮运球表信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddABallExam">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteABallExam">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="aBallExamList" :row-class-name="rowABallExamIndex" @selection-change="handleABallExamSelectionChange" ref="aBallExam">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="稳定性" prop="driStability" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.driStability" placeholder="请输入稳定性" />
            </template>
          </el-table-column>
          <el-table-column label="力量" prop="driPower" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.driPower" placeholder="请输入力量" />
            </template>
          </el-table-column>
          <el-table-column label="速度" prop="driSpeed" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.driSpeed" placeholder="请输入速度" />
            </template>
          </el-table-column>
          <el-table-column label="分析" prop="driAnalysis" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.driAnalysis" placeholder="请输入分析" />
            </template>
          </el-table-column>
          <el-table-column label="弧线" prop="shoArc" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.shoArc" placeholder="请输入弧线" />
            </template>
          </el-table-column>
          <el-table-column label="后旋" prop="shoSpinner" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.shoSpinner" placeholder="请输入后旋" />
            </template>
          </el-table-column>
          <el-table-column label="偏角" prop="shoAngle" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.shoAngle" placeholder="请输入偏角" />
            </template>
          </el-table-column>
          <el-table-column label="评价" prop="shoAnalysis" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.shoAnalysis" placeholder="请输入评价" />
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
import { listSheet, getSheet, delSheet, addSheet, updateSheet } from "@/api/practice/sheet";

export default {
  name: "Sheet",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedABallExam: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 练习、测试评分表表格数据
      sheetList: [],
      // 投篮运球表表格数据
      aBallExamList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        etId: null,
        stuId: null,
        teaId: null,
        msTime: null,
        msDribble: null,
        msShooting: null,
        enumId: null,
        msScore: null,
        ms1: null,
        ms2: null,
        ms3: null,
        msClass: null,
        msImg: null
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
    /** 查询练习、测试评分表列表 */
    getList() {
      this.loading = true;
      listSheet(this.queryParams).then(response => {
        this.sheetList = response.rows;
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
        msId: null,
        etId: null,
        stuId: null,
        teaId: null,
        msTime: null,
        msDribble: null,
        msShooting: null,
        enumId: null,
        msScore: null,
        ms1: null,
        ms2: null,
        ms3: null,
        msClass: null,
        msImg: null
      };
      this.aBallExamList = [];
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
      this.ids = selection.map(item => item.msId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加练习、测试评分表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const msId = row.msId || this.ids
      getSheet(msId).then(response => {
        this.form = response.data;
        this.aBallExamList = response.data.aBallExamList;
        this.open = true;
        this.title = "修改练习、测试评分表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.aBallExamList = this.aBallExamList;
          if (this.form.msId != null) {
            updateSheet(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSheet(this.form).then(response => {
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
      const msIds = row.msId || this.ids;
      this.$modal.confirm('是否确认删除练习、测试评分表编号为"' + msIds + '"的数据项？').then(function() {
        return delSheet(msIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 投篮运球表序号 */
    rowABallExamIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 投篮运球表添加按钮操作 */
    handleAddABallExam() {
      let obj = {};
      obj.driStability = "";
      obj.driPower = "";
      obj.driSpeed = "";
      obj.driAnalysis = "";
      obj.shoArc = "";
      obj.shoSpinner = "";
      obj.shoAngle = "";
      obj.shoAnalysis = "";
      this.aBallExamList.push(obj);
    },
    /** 投篮运球表删除按钮操作 */
    handleDeleteABallExam() {
      if (this.checkedABallExam.length == 0) {
        this.$modal.msgError("请先选择要删除的投篮运球表数据");
      } else {
        const aBallExamList = this.aBallExamList;
        const checkedABallExam = this.checkedABallExam;
        this.aBallExamList = aBallExamList.filter(function(item) {
          return checkedABallExam.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleABallExamSelectionChange(selection) {
      this.checkedABallExam = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('practice/sheet/export', {
        ...this.queryParams
      }, `sheet_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
