<template>
  <div id="app">
    <div class = "col-8 offset-2">
      <table class="table caption-top table-hover">
  <caption class="text-center">
    <h1>
      学生管理系统
    </h1>
    <el-button type="primary" @click = "getStudent">获取学生信息</el-button>
    <el-button type="warning" @click="dialogVisible = true">添加学生</el-button>

<el-dialog
  title="提示"
  :visible.sync="dialogVisible"
  width="30%"
  :before-close="handleClose">
  <div>添加学生信息</div>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addStudent">添加</el-button>
  </span>
  <!-- 与newstudent绑定 -->
  <div><span>学号</span><input v-model="newStudent.number"/></div>
  <div><span>姓名</span><input v-model="newStudent.name"/></div>
  <div><span>年龄</span><input v-model.number="newStudent.age"/></div>
  <div><span>语文</span><input v-model.number="newStudent.chi"/></div>
  <div><span>数学</span><input v-model.number="newStudent.math"/></div>
  <div><span>英语</span><input v-model.number="newStudent.eng"/></div>
</el-dialog>
<el-button type="info" @click="dialogVisiblelogin = true">login</el-button>

<el-dialog
  title="登录"
  :visible.sync="dialogVisiblelogin"
  width="30%"
  :before-close="handleClose">
  <span>用户名</span><input type="text" v-model="user_for_login.username">
  <!-- v-model绑定 -->
  <br>
  <span>密码</span><input type="password" v-model="user_for_login.password">
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisiblelogin = false">取 消</el-button>
    <el-button type="primary" @click="login">登录</el-button>
  </span>
</el-dialog>
<el-button type="info" @click="dialogVisibleregister = true">register</el-button>
<!-- type颜色 -->
<el-dialog
  title="注册"
  :visible.sync="dialogVisibleregister"
  width="30%"
  :before-close="handleClose">
  <span>用户名</span><input type="text" v-model="user_for_register.username">
  <!-- v-model绑定 -->
  <br>
  <span>密码</span><input type="password" v-model="user_for_register.password">
  <br>
   <span>确认密码</span><input type="password" v-model="user_for_register.confirmPassword">
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisibleregister = false">取 消</el-button>
    <el-button type="primary" @click="register">注册</el-button>
  </span>
</el-dialog>
  </caption>
  <thead>
    <tr>
      <th scope="col">学号</th>
      <th scope="col">姓名</th>
      <th scope="col">年龄</th>
      <th scope="col">语文</th>
      <th scope="col">数学</th>
      <th scope="col">英语</th>
      <th scope="col">操作</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <Student v-for="stu in students_for_page" :key="stu.id" :student="stu"></Student>
  </tbody>
</table>
<div class = "text-center">
  <el-button-group>
  <el-button type="primary" icon="el-icon-arrow-left" @click="last_page">上一页</el-button>
  <el-button type="primary" @click="next_page">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
  </el-button-group>
</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import Student from './components/Student.vue'
export default {
  name: 'App',
  components: {
    Student
  },
  data() {
    return{
      user_for_login:{
        username:"",
        password:""
      },
      user_for_register:{
        username:"",
        password:"",
        confirmPassword:""
      },
      page :1,
      dialogVisible: false,
      dialogVisiblelogin: false,
      dialogVisibleregister: false,
      students:[],
      newStudent:{
        number:"",
        name:"",
        age:"",
        chi:"",
        math:"",
        eng:""
      }
    }
  },
  methods:{
    getStudent(){
      if(sessionStorage.getItem("islogined")=="true"){
        axios({
        url:"http://localhost:8080/students",
        method:'GET',
        }).then(res=>{
          console.log(res.data);
          this.students = res.data;
        })
      }else{
        this.$alert("请先登录")
      }
      
    },
    // 对话框
    handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
    addStudent(){
      axios({
        url:"http://localhost:8080/add",
        method:'POST',
        data:this.newStudent
      })
      this.dialogVisible = false
    },
    next_page(){
      this.page+=1;
    },
    last_page(){
      this.page-=1;
    },
    login(){
      axios({
        url:"http://localhost:8080/login",
        method:'POST',
        data:this.user_for_login
      }).then(res=>{
        console.log(res.data)
        if(res.data == "1"){
          sessionStorage.setItem("islogined","true");
          //sessionStorage.clear()清除
          //sessionStorage.getItem("")//拿取数据
          //存的key value
        }else{
          alert("用户名或密码错误")
        }
        // localStorage.setItem("login",res.data)
      })
      this.dialogVisiblelogin = false
      //登录状态的保持才是重点
    },
    register(){
      // 注册按钮绑定事件
      axios({
        url:"http://localhost:8080/register",
        method:"POST",
        data:this.user_for_register
      })
      this.dialogVisibleregister = false;
      this.$alert("注册成功")
    }
  },
  computed:{
    students_for_page(){
      return this.students.slice(this.page*5-5,this.page*5)
      //数组切片
    }
  }
}
</script>

<style>

</style>
