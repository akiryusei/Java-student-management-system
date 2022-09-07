<template>
    <div class="data-view mt-3">
      <el-card>
        <!-- <h3>各科平均分</h3> -->
          <div id="bar"></div>
      </el-card>
      <el-card>
          <div id="pie"></div>
      </el-card>
    </div>
</template>
  
<script>
import axios from 'axios'
  export default {
    name:"Analysis",
    data() {
      return {
        averageScores: [],
        countGradesNumbers:[],
      }
    },
    methods: {
      async getAverageScores() {
        await axios({
          url:"http://localhost:8080/getAverageScores"
        }).then(res => {
          // console.log(res.data);
          this.averageScores = res.data
          // console.log(this.averageScores);
      })
      },
      async getCountGradesNumbers() {
        await axios({
          url:"http://localhost:8080/getCountGradesNumbers"
        }).then(res => {
          this.countGradesNumbers = res.data
      })
      },
      // 使用echarts的方法，dom找到这个卡片，init初始化 将其放进
      // mybar是一张图
      drawBar() {
        console.log(this.scores);
        var myBar = this.$charts.init(document.getElementById("bar"))
        myBar.setOption(
          {
            title: {
              text: '各科成绩平均分'
          },
            xAxis: {
              type: 'category',
              data: ['java', '高数', '英语', '游戏', '线代', '军事', '建模']
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                data: [
                  this.averageScores.java,
                  this.averageScores.math,
                  this.averageScores.english,
                  this.averageScores.game,
                  this.averageScores.algebra,
                  this.averageScores.military,
                  this.averageScores.modeling,
                ],
                type:'bar'
              }]
          })
      },
      //饼图
      drawPie() {
        var myPie = this.$charts.init(document.getElementById("pie"))
        myPie.setOption(
        {
          title:{
            text:'各绩点分段人数统计图'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '88%',
            left: 'center'
          },
          series: [
            {
              name: '学生数量',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '30',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: this.countGradesNumbers
            }
          ]
        }
              )
            }
      
    },
    
     
   async mounted() {
    // 一开始就初始化去拿数据
     await this.getAverageScores();
      this.drawBar();
     await this.getCountGradesNumbers();
     this.drawPie();
      // console.log(this.averageScores); 
      // console.log(myBar);
    },
  }
</script>
<style>
  .data-view{
    width: 100%;
    display:flex;
    justify-content:space-between
  }
  #bar{
    width:450px;
    height:375px
  }
  #pie{
    width:480px;
    height:375px
  }
     
</style>