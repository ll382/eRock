<template>
  <div style="height: 100vh; ">
    <button @click="back" role="button" class="button-name">上一页</button>
    <div id="main" style="width: 95vw; height: 26vh"></div>
    <div id="top" style="width: 95vw; height: 26vh"></div>
    <div id="foot" style="width: 95vw; height: 26vh"></div>
    <!-- <button @click="hyperx">test</button> -->
  </div>
</template>


<script>
import * as echarts from "echarts";
import {
  studentClassBefore,
  studentClassMid,
  studentClassAfter,
} from "@/api/page_two/student";

export default {
  name: "page_two",
  data() {
    return {
      id: this.$route.query.id,
      stuName: this.$route.query.name,
      queryDate: this.$route.query.queryDate,
      queryHour: this.$route.query.queryHour,
      shotDribble: this.$route.query.shotDribble,
      xAxis: [],
      whichClass: [],
      series: [],
      EROCKList: [],
      ms_shooting: [],
      date: [],
    };
  },
  mounted() {
    let self = this;

    studentClassMid(this.id, this.queryDate, this.shotDribble).then(
      (response) => {
        console.log(response.rows[0]);
        this.ms_shooting = response.rows[0];
        const result = this.ms_shooting.map((item, index) => {
          const times = index + 1;
          return {
            msId: item.ms_id,
            value: item.ms_shooting,
            xAxis: `第${times}次成绩`,
            yAxis: item.ms_shooting,
          };
        });
        const xAxis = result.map((item) => item.xAxis);
        const series = result.map((item) => item.yAxis);
        console.log(result);
        console.log(xAxis);
        console.log(series);

        var chartDom = document.getElementById("main");
        var myChart = echarts.init(chartDom);
        var option;

        let self = this;

        option = {
          title: {
            text: this.stuName + "的课堂练习成绩",
          },
          tooltip: {
            trigger: "axis",
          },
          legend: {},
          xAxis: {
            type: "category",
            boundaryGap: false,
            data: xAxis,
          },
          yAxis: {
            type: "value",
            position: "left",
            axisLabel: {
              formatter: "{value} 分",
            },
            offset: 14,
            // 控制分割段数
            splitNumber: 4, // 可以根据需要调整这个值
          },
          series: [
            {
              name: "Erock分",
              type: "line",
              data: series,
              markPoint: {
                data: result,
              },
            },
          ],
        };

        option && myChart.setOption(option);
        // 监听 'click' 事件
        myChart.on("click", function (params) {
          console.log(this.stuName);
          if (params.componentType === "markPoint" && params.data.coord) {
            var value = params.data.value; // 获取点击的标记点的分数值
            var msId = params.data.msId; // 获取对应的ID
            console.log("分数：" + value + ", ID: " + msId);
            self.$router.push({
              path: "page_three",
              query: {
                queryValue: value,
                queryMsId: msId,
                shotDribble: self.shotDribble,
                queryName: self.stuName,
                eId: 1,
                enumId: 2,
              },
            });
          }
        });
      }
    );

    studentClassBefore(this.id, 1, this.queryHour).then((response) => {
      // console.log(response.rows);
      this.xAxis = response.rows[0].xAxis;
      this.whichClass = response.rows[0].whichClass;
      this.series = response.rows[0].series;
      console.log(response.rows[0]);

      // console.log(this.$route.query.id);
      // console.log(this.$route.query.name);
      var chartDom = document.getElementById("top");
      var myChart = echarts.init(chartDom);
      var option;

      let self = this;
      let whichClass = this.whichClass;
      let xAxis = this.xAxis;
      let time = null;
      let mId = response.rows[0].msId;

      option = {
        title: {
          text: this.stuName + "的课前练习情况",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
          formatter: function (params) {
            var whichClassIndex = params[0].dataIndex; // 获取当前柱状图的索引
            var whichClass = self.whichClass[whichClassIndex]; // 根据索引获取whichClass的值
            return (
              params[0].name +
              "第" +
              whichClass +
              "<br />" +
              params[0].seriesName +
              ": " +
              params[0].value
            );
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: [
          {
            type: "category",
            data: this.xAxis,
            axisTick: {
              alignWithLabel: true,
            },
          },
        ],
        yAxis: [
          {
            type: "value",
            // 设置 Y 轴的最小值为数据中的最小值减去一个单位
            min: Math.min.apply(null, this.series) - 1,
            // 设置 Y 轴的最大值为数据中的最大值加上一个单位
            max: Math.max.apply(null, this.series) + 1,
            axisLabel: {
              formatter: function (value) {
                return Math.round(value);
              },
            },
          },
        ],
        series: [
          {
            name: "训练次数",
            type: "bar",
            barWidth: "60%",
            data: this.series,
          },
        ],
      };
      option && myChart.setOption(option);
      myChart.on("click", function (params) {
        if (params.componentType === "series") {
          // 点击了柱状图的某个系列
          var value = params.value; // 获取点击的柱状图数据值
          console.log("柱状图数据值：" + value);
          var dataIndex = params.dataIndex; // 获取点击的柱状图数据的索引
          console.log("柱状图数据索引：" + dataIndex);
          if (whichClass[dataIndex] === "一二节课") {
            time = " 08:00:00";
          } else {
            time = " 14:00:00";
          }
          let queryDate = xAxis[dataIndex] + time;
          console.log(mId[dataIndex]);
          console.log(queryDate);
          // 在这里可以执行跳转或其他操作
          console.log(response.rows[0]);
          self.$router.push({
            path: "page_three",
            query: {
              queryValue: value,
              shotDribble: self.shotDribble,
              queryName: self.stuName,
              eId: 2,
              stuId: self.id,
              queryDate: queryDate,
              enumId: 1,
              mId: mId[dataIndex],
            },
          });
        }
      });
      // 设置图表宽高
    });

    studentClassAfter(this.id, 3, this.queryHour).then((response) => {
      // console.log(response.rows);
      this.xAxis = response.rows[0].xAxis;
      this.whichClass = response.rows[0].whichClass;
      this.series = response.rows[0].series;

      // console.log(this.$route.query.id);
      // console.log(this.$route.query.name);
      var chartDom = document.getElementById("foot");
      var myChart = echarts.init(chartDom);
      var option;

      let self = this;
      let whichClass = this.whichClass;
      let xAxis = this.xAxis;
      let time = null;
      let mId = response.rows[0].msId;

      option = {
        title: {
          text: this.stuName + "的课后练习情况",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
          formatter: function (params) {
            var whichClassIndex = params[0].dataIndex; // 获取当前柱状图的索引
            var whichClass = self.whichClass[whichClassIndex]; // 根据索引获取whichClass的值
            return (
              params[0].name +
              "第" +
              whichClass +
              "<br />" +
              params[0].seriesName +
              ": " +
              params[0].value
            );
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: [
          {
            type: "category",
            data: this.xAxis,
            axisTick: {
              alignWithLabel: true,
            },
          },
        ],
        yAxis: [
          {
            type: "value",
          },
        ],
        series: [
          {
            name: "训练次数",
            type: "bar",
            barWidth: "60%",
            data: this.series,
          },
        ],
      };

      option && myChart.setOption(option);
      myChart.on("click", function (params) {
        if (params.componentType === "series") {
          // 点击了柱状图的某个系列
          var value = params.value; // 获取点击的柱状图数据值
          console.log("柱状图数据值：" + value);
          var dataIndex = params.dataIndex; // 获取点击的柱状图数据的索引
          console.log("柱状图数据索引：" + dataIndex);
          if (whichClass[dataIndex] === "一二节课") {
            time = " 08:00:00";
          } else {
            time = " 14:00:00";
          }
          let queryDate = xAxis[dataIndex] + time;
          console.log(mId[dataIndex]);
          console.log(queryDate);
          // 在这里可以执行跳转或其他操作
          console.log(response.rows[0]);
          self.$router.push({
            path: "page_three",
            query: {
              queryValue: value,
              shotDribble: self.shotDribble,
              queryName: self.stuName,
              eId: 3,
              stuId: self.id,
              queryDate: queryDate,
              enumId: 3,
              mId: mId[dataIndex],
            },
          });
        }
      });
    });
  },
  methods: {
    back() {
      window.history.back();
    },
    hyperx() {
      this.$router.push({
        path: "page_three",
        query: {},
      });
    },
  },
};
</script>

<style>
#top,
#main,
#foot {
  margin: 3vw 3vh;
  border-radius: 49px;
  box-shadow: 10px 10px 20px #b5b5b5, -10px -10px 20px #ffffff;
}
.button-name {
  align-items: center;
  appearance: none;
  background-color: #fcfcfd;
  border-radius: 4px;
  border-width: 0;
  box-shadow: rgba(45, 35, 66, 0.2) 0 2px 4px,
    rgba(45, 35, 66, 0.15) 0 7px 13px -3px, #d6d6e7 0 -3px 0 inset;
  box-sizing: border-box;
  color: #36395a;
  cursor: pointer;
  display: inline-flex;
  font-family: "JetBrains Mono", monospace;
  height: 28px;
  justify-content: center;
  line-height: 1;
  list-style: none;
  overflow: hidden;
  padding-left: 16px;
  padding-right: 16px;
  position: relative;
  text-align: left;
  text-decoration: none;
  transition: box-shadow 0.15s, transform 0.15s;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  white-space: nowrap;
  will-change: box-shadow, transform;
  font-size: 18px;
}

.button-name:focus {
  box-shadow: #d6d6e7 0 0 0 1.5px inset, rgba(45, 35, 66, 0.4) 0 2px 4px,
    rgba(45, 35, 66, 0.3) 0 7px 13px -3px, #d6d6e7 0 -3px 0 inset;
}

.button-name:hover {
  box-shadow: rgba(45, 35, 66, 0.3) 0 4px 8px,
    rgba(45, 35, 66, 0.2) 0 7px 13px -3px, #d6d6e7 0 -3px 0 inset;
  transform: translateY(-2px);
}

.button-name:active {
  box-shadow: #d6d6e7 0 3px 7px inset;
  transform: translateY(2px);
}
</style>