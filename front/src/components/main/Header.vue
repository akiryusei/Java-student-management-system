<template>
  <div>
    <!-- 一行两列 -->
    <el-row>
        <el-col :span="4" :offset="10">
        <span class="info">学生管理系统 </span>
        </el-col>
        <el-col :span="4" :offset="6" class="user-info" >
            <el-dropdown >
            <span class="el-dropdown-link" >
                你好,{{username}}
                <i class="el-icon-arrow-down"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="toLogout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
    name:'Header',
    data(){
        return{
            username:sessionStorage.getItem("username")
        }
    },
    methods: {
      toLogout() {
        this.$confirm('确定退出系统？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$alert("您已退出系统!","温馨提示",{
                   confirmButtonText: '知道啦'
                });
          sessionStorage.clear();
          this.$router.push({
          name:"login"
        })
        })
      },
    },
}
</script>

<style scoped>
.info{
    text-align: center;
    font-size: 16px;
    line-height: 60px;
    /* 行高 */
}
.user-info{
    text-align: right;
    line-height: 60px;
}
</style>