<template>
    <div class="back">
      <div v-show="!isLogin">
        <!-- <div style="margin: 80px;"></div> -->
        <!-- 登录界面表单 -->
      <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign" class="login-box">
        <h4 class = "login-title">欢 迎 登 录</h4>
        <el-form-item label="用户名:">
            <el-input v-model="formLabelAlign.username" placeholder="请输入用户名" class="w-75"></el-input>
        </el-form-item>

        <el-form-item label="密码:">
            <el-input type="password" v-model="formLabelAlign.password" placeholder="请输入密码" class="w-75"></el-input>
        </el-form-item>
        <div class="login_button">
          <el-button type="warning" icon="" @click="login">登录</el-button>
          <el-button type="primary" @click="register"  style="margin-left:17%">注册</el-button>
        </div>
      </el-form>
    </div>
    <!-- 登录提示 -->
      <div v-show="isLogin">
        <el-col class="text-center" >
          <h3>
            亲爱的<span class="text-warning h2">{{ formLabelAlign.username }}</span>!欢迎使用学生管理系统!
          </h3>       
        </el-col>
      </div>
    </div>
</template>
  
<script>
import axios from 'axios'
  export default{
    name:"Login",
    data() {
      return {
        labelPosition: 'right',
        formLabelAlign: {
        username:"", 
        password:"",
        },
        isLogin:false
      };
    },
    methods:{
      async login(){
        await axios({
          url: "http://localhost:8080/login",
          method: "POST",
          data:{
            username:this.formLabelAlign.username,
            password:this.formLabelAlign.password
          }
        }).then(res => {
          if (res.data<0) {
            this.$alert("用户名或密码错误!","温馨提示",{
              confirmButtonText: '知道啦'
            });
          }
          else{
            sessionStorage.setItem("username",this.formLabelAlign.username)
            this.$alert("登录成功！  尊敬的"+sessionStorage.getItem("username")+"欢迎您！","温馨提示",{
              confirmButtonText: '知道啦'
            });
            this.isLogin = true;
            this.$router.push({
              name:"container"
            })
            }
            })
        },
        register(){
          this.$router.push({
            name:"register",
          })
        },
      },
      mounted() {
        this.$bus.$on("logout",()=>{
          this.isLogin=false
        })
      },
    }
  </script>
<style scoped>
.login-box{
    width:350px;
    margin:120px auto;
    /* //左右150居中 */
    border: 1px solid #DCDFE6 ;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px #DCDFE6;
}
.login-title{
    line-height:40px;
    text-align: center;
    margin-bottom: 25px;
    margin-top:5px;
}
.login_button{
  margin-top: 25px;
  margin-bottom: 10px;
  margin-left:20%;
}

</style>