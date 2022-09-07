<template>
    <div>
    <!-- rules:表单验证/prop设置校验字段名 -->
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="register-box">
      <h4 class="register-title">注 册</h4>
      <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username" placeholder="请输入用户名" class="w-75"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off" placeholder="请输入密码" class="w-75"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" placeholder="请再次输入密码" class="w-75"></el-input>
      </el-form-item>
    <!-- button -->
      <div class="register_button">
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')" style="margin-left:17%">重置</el-button>
      </div>
    </el-form>
    </div>
</template>
  
<script>
import axios from 'axios';
    export default {
        name:"Login",
        data() {
        var checkusername = (rule, value, callback) => {
          if (!value) {
            return callback(new Error('用户名不能为空'));
          }
          // setTimeout(() => {
              if (value.length >18 || value.length<6) {
                callback(new Error('用户名长度必须在6-18位之间'));
              } else {
                callback();
              
            }
          // }, 10);
        };
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'));
          } else {
            if (this.ruleForm.checkPass !== '') {
              this.$refs.ruleForm.validateField('checkPass');
            }
            callback();
          }
        };
        var validatePass2 = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请再次输入密码'));
          } else if (value !== this.ruleForm.pass) {
            callback(new Error('两次输入密码不一致!'));
          } else {
            callback();
          }
        };
        return {
          ruleForm: {
            pass: '',
            checkPass: '',
            username: ''
          },
          // 表单验证
          rules: {
            pass: [
              { validator: validatePass, trigger: 'blur' }
            ],
            checkPass: [
              { validator: validatePass2, trigger: 'blur' }
            ],
            username: [
              { validator: checkusername, trigger: 'blur' }
            ]
          }
        };
      },
      methods: {
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              axios({
                url: "http://localhost:8080/register",
                method: "POST",
                data:{
                  username:this.ruleForm.username,
                  password:this.ruleForm.pass
                }
              }).then(res=>{
                sessionStorage.setItem("username",res.data.username);
                sessionStorage.setItem("token",res.data.token);
              })
              this.$alert("注册成功快去登录吧！","温馨提示",{
                      confirmButtonText: '知道啦'
                  })
              this.$router.push({
                name:"login"
              })
            } else {
              console.$alert('error submit!!');
              return false;
            }
          });
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
</script>

<style scoped>
.register-box{
    width:350px;
    margin:120px auto;
    /* //左右150居中 */
    border: 1px solid #DCDFE6 ;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px #DCDFE6;
}
.register-title{
    line-height:40px;
    text-align:center;
}
.register_button{
  margin-bottom: 10px;
  margin-left:20%;
}

</style>