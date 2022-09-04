<template>
    <div>
      <!-- 一列 -->
      <el-col >
      <el-menu
        :default-active="activatedItem"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
          <!-- 四个导航栏 -->
          <el-menu-item index="info" @click="toInformation">
            <i class="el-icon-user" ></i>
            <span slot="title">学生管理</span>
          </el-menu-item>
          <el-menu-item index="courses" @click="toCourses">
            <i class="el-icon-notebook-1" ></i>
            <span slot="title">课程信息</span>
          </el-menu-item>
          <el-menu-item index="grades" @click="toGrades">
            <i class="el-icon-edit-outline" ></i>
            <span slot="title">成绩管理</span>
          </el-menu-item>
          <el-menu-item index="analysis" @click="toAnalysis">
            <i class="el-icon-data-line"></i>
            <span slot="title">数据分析</span>
          </el-menu-item>
      </el-menu>
    </el-col>
    </div>
  </template>
  
  <script>
  export default {
      name:'Navi',
      data(){
        return {
          activatedItem:this.$route.name
        }
      },
      methods:{
        toInformation(){
            this.$router.push({
              name:"students"
            })
          },
        toCourses() {
          this.$router.push({
            name:"courses"
          })
        },
        toGrades() {
        this.$router.push({
          name:"grades"
        })
        },
        toAnalysis() {
          this.$router.push({
            name:"analysis"
          })
        },
      },
      // 钩子函数
      mounted() {
      },
      
  }
</script>
  
<style scoped>
  div{
    width: 200px
  }
</style>