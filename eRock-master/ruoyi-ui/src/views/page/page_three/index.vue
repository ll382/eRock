<template>
  <div>
    <button @click="back" role="button" class="button-name">上一页</button>
    <div class="tle">{{ queryName }}同学个人技术分析</div>
    <div class="left">
      <div class="stu_url1">
        <video
          v-if="checkIfVideo(stu_url2)"
          :src="stu_url2"
          controls
          style="width: 100%; border-radius: 20px; margin-top: 4vh"
        ></video>
        <img v-else :src="stu_url2" style="width: 100%; height: 100%" />
      </div>
      <div class="stu_url2">
        <video
          v-if="checkIfVideo(stu_url1)"
          :src="stu_url1"
          controls
          style="width: 100%; border-radius: 20px; margin-top: 4vh"
        ></video>
        <img v-else :src="stu_url1" style="width: 100%; height: 100%" />
      </div>
    </div>
    <div id="rader" style="width: 40vw; height: 46vh; margin-top: 10vh"></div>
    <div class="rightbottom">
      <div class="top">
        <span style="font-size: 3vw">运动处方</span>
        <div class="content">技术分析：{{ stuName }}同学，您的动作{{ ms_class }}右边是改进建议</div>
      </div>
      <div class="right">
        <span class="cv1" style="margin-left: 1vw">处方1：{{ cv2 }}</span>
        <div class="url_1">
          <video
            v-if="checkIfVideo(url1)"
            :src="url1"
            controls
            style="width: 100%; border-radius: 20px; margin-top: 4vh"
          ></video>
          <img v-else :src="url1" style="width: 100%; height: 100%" />
        </div>
        <span class="cv2">处方2：{{ cv2 }}</span>
        <div class="url_2">
          <video
            v-if="checkIfVideo(url2)"
            :src="url2"
            controls
            style="width: 100%; border-radius: 20px; margin-top: 4vh"
          ></video>
          <img v-else :src="url2" style="width: 100%; height: 100%" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import {
  studentRader,
  studentRaderDetail,
  studentVideo,
} from "@/api/page_three/rader";

export default {
  data() {
    return {
      queryValue: this.$route.query.queryValue,
      shotDribble: this.$route.query.shotDribble,
      queryName: this.$route.query.queryName,
      eId: this.$route.query.eId,
      stuId: this.$route.query.stuId,
      queryDate: this.$route.query.queryDate,
      enumId: this.$route.query.enumId,
      query: {
        msId: this.$route.query.queryMsId,
      },
      url1: null,
      url2: null,
      stu_url1: null,
      stu_url2: null,
      ms_class: null,
      mId: this.$route.query.mId,
      stuName: this.$route.query.stuName,
      cv1: null,
      cv2: null,
    };
  },
  methods: {
    back() {
      window.history.back();
    },
    checkIfVideo(url) {
      return /\.(mp4|mov|avi|wmv)$/i.test(url);
    },
  },
  mounted() {
    console.log(this.stuName);
    console.log(this.mId);
    console.log(this.enumId);
    console.log(this.queryValue);
    console.log(this.query.msId);
    console.log(this.shotDribble);
    console.log(this.queryName);
    console.log(this.shotDribble);
    let queryValue = this.queryValue;
    let queryName = this.queryName;
    let stuId = parseInt(this.stuId, 10);
    let queryDate = this.queryDate;
    let shotDribble = this.shotDribble;
    let msId = this.query.msId;
    console.log(stuId);
    console.log(msId);

    if (this.eId === "1") {
      studentRader(this.query).then((response) => {
        if (this.shotDribble === "ms_shooting") {
          var chartDom = document.getElementById("rader");
          var myChart = echarts.init(chartDom);
          var option;

          console.log(response.rows[0]);

          let arr = [
            response.rows[0].shoArc,
            response.rows[0].shoSpinner,
            response.rows[0].shoAngle,
          ];

          option = {
            title: {
              text: `投篮技术数据`,
              textStyle: {
                color: "black",
              },
            },
            legend: {
              data: [`评分：${queryValue}`],
              textStyle: {
                // 设置图例文本样式
                color: "black", // 图例字体颜色为绿色
                fontSize: 18, // 图例字体大小为18
                fontWeight: "bold", // 图例字体加粗
              },
            },
            tooltip: {
              trigger: "item",
            },
            radar: {
              // shape: 'circle',
              indicator: [
                { name: "弧线(标准53-55°)", max: 100 },
                { name: "后旋(标准110-140转每分钟)", max: 100 },
                { name: "偏角(标准0-7°)", max: 100 },
              ],
              center: ["60%", "60%"],
              name: {
                textStyle: {
                  // 设置雷达图指标名称的文本样式
                  color: "black", // 字体颜色为红色
                  fontSize: 20, // 字体大小为18
                  fontWeight: "bold", // 字体加粗
                },
              },
            },
            series: [
              {
                name: "Budget vs spending",
                type: "radar",
                data: [
                  {
                    value: arr,
                    name: `评分：${queryValue}`,
                    areaStyle: {
                      // 添加填充样式
                      color: "rgba(0, 191, 255, 0.5)", // 设置填充颜色和透明度
                    },
                  },
                ],
              },
            ],
          };
          option && myChart.setOption(option);
        } else {
          var chartDom = document.getElementById("rader");
          var myChart = echarts.init(chartDom);
          var option;

          let arr = [
            response.rows[0].driStability,
            response.rows[0].driPower,
            response.rows[0].driSpeed,
          ];

          option = {
            title: {
              text: `投篮技术数据`,
              textStyle: {
                color: "black",
              },
            },
            legend: {
              data: [`运球分：${queryValue}`],
              textStyle: {
                // 设置图例文本样式
                color: "black", // 图例字体颜色为绿色
                fontSize: 18, // 图例字体大小为18
                fontWeight: "bold", // 图例字体加粗
              },
            },
            tooltip: {
              trigger: "item",
            },
            radar: {
              // shape: 'circle',
              indicator: [
                { name: "稳定性", max: 100 },
                { name: "力量", max: 100 },
                { name: "速度", max: 100 },
              ],
              center: ["50%", "60%"],
              name: {
                textStyle: {
                  // 设置雷达图指标名称的文本样式
                  color: "black", // 字体颜色为红色
                  fontSize: 22, // 字体大小为18
                  fontWeight: "bold", // 字体加粗
                },
              },
            },
            series: [
              {
                name: "Budget vs spending",
                type: "radar",
                data: [
                  {
                    value: arr,
                    name: `运球分：${queryValue}`,
                    areaStyle: {
                      // 添加填充样式
                      color: "rgba(0, 191, 255, 0.5)", // 设置填充颜色和透明度
                    },
                  },
                ],
              },
            ],
          };
          option && myChart.setOption(option);
        }
      });
    } else if (this.eId === "2") {
      studentRaderDetail(1, stuId, shotDribble, queryDate).then((response) => {
        if (this.shotDribble === "ms_shooting") {
          var chartDom = document.getElementById("rader");
          var myChart = echarts.init(chartDom);
          var option;

          console.log(response.data.shoAngle);

          let arr = [
            response.data.shoArc,
            response.data.shoSpinner,
            response.data.shoAngle,
          ];

          let ms_shooting = response.data.msShooting;

          option = {
            title: {
              text: `投篮技术数据`,
              textStyle: {
                color: "black",
              },
            },
            legend: {
              data: [`评分：${ms_shooting}`],
              textStyle: {
                // 设置图例文本样式
                color: "black", // 图例字体颜色为绿色
                fontSize: 18, // 图例字体大小为18
                fontWeight: "bold", // 图例字体加粗
              },
            },
            tooltip: {
              trigger: "item",
            },
            radar: {
              // shape: 'circle',
              indicator: [
                { name: "弧线(标准53-55°)", max: 100 },
                { name: "后旋(标准110-140转每分钟)", max: 100 },
                { name: "偏角(标准0-7°)", max: 100 },
              ],
              center: ["60%", "60%"],
              name: {
                textStyle: {
                  // 设置雷达图指标名称的文本样式
                  color: "black", // 字体颜色为红色
                  fontSize: 20, // 字体大小为18
                  fontWeight: "bold", // 字体加粗
                },
              },
            },
            series: [
              {
                name: "Budget vs spending",
                type: "radar",
                data: [
                  {
                    value: arr,
                    name: `评分：${ms_shooting}`,
                    areaStyle: {
                      // 添加填充样式
                      color: "rgba(0, 191, 255, 0.5)", // 设置填充颜色和透明度
                    },
                  },
                ],
              },
            ],
          };
          option && myChart.setOption(option);
        } else {
          var chartDom = document.getElementById("rader");
          var myChart = echarts.init(chartDom);
          var option;

          let arr = [
            response.data.driStability,
            response.data.driPower,
            response.data.driSpeed,
          ];

          let ms_shooting = response.data.msDribble;

          option = {
            title: {
              text: `投篮技术数据`,
              textStyle: {
                color: "black",
              },
            },
            legend: {
              data: [`运球分：${ms_shooting}`],
              textStyle: {
                // 设置图例文本样式
                color: "black", // 图例字体颜色为绿色
                fontSize: 18, // 图例字体大小为18
                fontWeight: "bold", // 图例字体加粗
              },
            },
            tooltip: {
              trigger: "item",
            },
            radar: {
              // shape: 'circle',
              indicator: [
                { name: "稳定性", max: 100 },
                { name: "力量", max: 100 },
                { name: "速度", max: 100 },
              ],
              center: ["50%", "60%"],
              name: {
                textStyle: {
                  // 设置雷达图指标名称的文本样式
                  color: "black", // 字体颜色为红色
                  fontSize: 22, // 字体大小为18
                  fontWeight: "bold", // 字体加粗
                },
              },
            },
            series: [
              {
                name: "Budget vs spending",
                type: "radar",
                data: [
                  {
                    value: arr,
                    name: `运球分：${ms_shooting}`,
                    areaStyle: {
                      // 添加填充样式
                      color: "rgba(0, 191, 255, 0.5)", // 设置填充颜色和透明度
                    },
                  },
                ],
              },
            ],
          };
          option && myChart.setOption(option);
        }
      });
    } else if (this.eId === "3") {
      studentRaderDetail(3, stuId, shotDribble, queryDate).then((response) => {
        if (this.shotDribble === "ms_shooting") {
          var chartDom = document.getElementById("rader");
          var myChart = echarts.init(chartDom);
          var option;

          console.log(response.data.shoAngle);

          let arr = [
            response.data.shoArc,
            response.data.shoSpinner,
            response.data.shoAngle,
          ];

          let ms_shooting = response.data.msShooting;

          option = {
            title: {
              text: `投篮技术数据`,
              textStyle: {
                color: "black",
              },
            },
            legend: {
              data: [`评分：${ms_shooting}`],
              textStyle: {
                // 设置图例文本样式
                color: "black", // 图例字体颜色为绿色
                fontSize: 18, // 图例字体大小为18
                fontWeight: "bold", // 图例字体加粗
              },
            },
            tooltip: {
              trigger: "item",
            },
            radar: {
              // shape: 'circle',
              indicator: [
                { name: "弧线(标准53-55°)", max: 100 },
                { name: "后旋(标准110-140转每分钟)", max: 100 },
                { name: "偏角(标准0-7°)", max: 100 },
              ],
              center: ["60%", "60%"],
              name: {
                textStyle: {
                  // 设置雷达图指标名称的文本样式
                  color: "black", // 字体颜色为红色
                  fontSize: 20, // 字体大小为18
                  fontWeight: "bold", // 字体加粗
                },
              },
            },
            series: [
              {
                name: "Budget vs spending",
                type: "radar",
                data: [
                  {
                    value: arr,
                    name: `评分：${ms_shooting}`,
                    areaStyle: {
                      // 添加填充样式
                      color: "rgba(0, 191, 255, 0.5)", // 设置填充颜色和透明度
                    },
                  },
                ],
              },
            ],
          };
          option && myChart.setOption(option);
        } else {
          var chartDom = document.getElementById("rader");
          var myChart = echarts.init(chartDom);
          var option;

          let arr = [
            response.data.driStability,
            response.data.driPower,
            response.data.driSpeed,
          ];

          let ms_shooting = response.data.msDribble;

          option = {
            title: {
              text: `投篮技术数据`,
              textStyle: {
                color: "black",
              },
            },
            legend: {
              data: [`运球分：${ms_shooting}`],
              textStyle: {
                // 设置图例文本样式
                color: "black", // 图例字体颜色为绿色
                fontSize: 18, // 图例字体大小为18
                fontWeight: "bold", // 图例字体加粗
              },
            },
            tooltip: {
              trigger: "item",
            },
            radar: {
              // shape: 'circle',
              indicator: [
                { name: "稳定性", max: 100 },
                { name: "力量", max: 100 },
                { name: "速度", max: 100 },
              ],
              center: ["50%", "60%"],
              name: {
                textStyle: {
                  // 设置雷达图指标名称的文本样式
                  color: "black", // 字体颜色为红色
                  fontSize: 22, // 字体大小为18
                  fontWeight: "bold", // 字体加粗
                },
              },
            },
            series: [
              {
                name: "Budget vs spending",
                type: "radar",
                data: [
                  {
                    value: arr,
                    name: `运球分：${ms_shooting}`,
                    areaStyle: {
                      // 添加填充样式
                      color: "rgba(0, 191, 255, 0.5)", // 设置填充颜色和透明度
                    },
                  },
                ],
              },
            ],
          };
          option && myChart.setOption(option);
        }
      });
    }
    if (this.enumId === "2") {
      studentVideo(msId, this.enumId).then((response) => {
        console.log(response.data.erSource);
        this.url1 = response.data.content_video2;
        this.url2 = response.data.content_video;
        this.ms_class = response.data.ms_class;
        this.stu_url1 = response.data.mp4;
        this.stu_url2 = response.data.jsp;
        this.cv1 = response.data.cv1;
        this.cv2 = response.data.cv2;
      });
    } else {
      studentVideo(this.mId, this.enumId).then((response) => {
        console.log(response.data.erSource);
        this.url1 = response.data.content_video2;
        this.url2 = response.data.content_video;
        this.ms_class = response.data.ms_class;
        this.stu_url1 = response.data.mp4;
        this.stu_url2 = response.data.jsp;
        this.cv1 = response.data.cv1;
        this.cv2 = response.data.cv2;
      });
    }
  },
};
</script>

<style>
html {
  overflow-y: scroll; /* 显示垂直滚动条 */
}

body {
  margin: 0; /* 重置 body 的默认边距 */
  padding: 0; /* 重置 body 的默认内边距 */
}

#rader {
  border-radius: 49px;
  box-shadow: 10px 10px 20px #b5b5b5, -10px -10px 20px #ffffff;
  margin-left: 56vw;
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
  position: absolute;
  top: 10px; /* 根据需要调整 */
  left: 10px; /* 根据需要调整 */
  z-index: 10; /* 确保按钮在最上层 */
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

.left {
  border-radius: 49px;
  box-shadow: 10px 10px 20px #b5b5b5, -10px -10px 20px #ffffff;
  width: 50vw;
  height: 46vh;
  position: absolute; /* 使用绝对定位 */
  top: 33%; /* 垂直居中 */
  left: 3%; /* 根据需要调整，使其靠左 */
  transform: translateY(-50%); /* 使用变换来精确垂直居中 */
}
.rightbottom {
  border-radius: 49px;
  box-shadow: 10px 10px 20px #b5b5b5, -10px -10px 20px #ffffff;
  width: 94vw;
  height: 36vh;
  position: absolute; /* 使用绝对定位 */
  top: 78%; /* 垂直居中 */
  left: 3%; /* 根据需要调整，使其靠左 */
  transform: translateY(-50%); /* 使用变换来精确垂直居中 */
  overflow: hidden;
}

.top {
  position: absolute;
  top: 5%;
  left: 3%;
}

.url_1 {
  width: 46%; /* 设置宽度为外部盒子的一半 */
  height: 100%; /* 设置高度为外部盒子的一半 */
  border-radius: 20px; /* 设置圆角边框 */
  position: absolute;
  overflow: hidden;
}

.url_2 {
  width: 46%; /* 设置宽度为外部盒子的一半 */
  height: 100%; /* 设置高度为外部盒子的一半 */
  border-radius: 20px; /* 设置圆角边框 */
  position: absolute;
  overflow: hidden;
  left: 50%;
}
.tle {
  font-size: 1.8vw;
  font-weight: bold; /* 字体变粗 */
  text-shadow: 2px 2px 2px rgba(0, 0, 0, 0.5);
  position: absolute;
  top: 1%;
  left: 38%;
}

.stu_url1 {
  width: 44%; /* 设置宽度为外部盒子的一半 */
  height: 80%; /* 设置高度为外部盒子的一半 */
  border-radius: 20px; /* 设置圆角边框 */
  border: 1px solid black;
  overflow: hidden;
  position: absolute;
  left: 3%;
  top: 10%;
}

.stu_url2 {
  width: 44%; /* 设置宽度为外部盒子的一半 */
  height: 80%; /* 设置高度为外部盒子的一半 */
  border-radius: 20px; /* 设置圆角边框 */
  overflow: hidden;
  position: absolute;
  left: 53%;
  top: 10%;
}

.right {
  width: 50%;
  height: 80%;
  /* border: 1px solid black; */
  position: absolute;
  top: 6%;
  left: 46%;
}
.cv2 {
  display: inline-block;
  margin-left: 14vw;
  margin-bottom: 1vh;
}

.content {
  width: 30vw;
  position: absolute;
  top: 100%;
  font-size: 2vw;
}
</style>