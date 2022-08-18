<template>
  <div id="app">
    <div class = "col-8 offset-2">
      <table class="table caption-top table-hover">
  <caption class="text-center">
    <h1>
      学生管理系统
    </h1>
    <el-button type="primary" @click = "getStudent">获取学生信息</el-button>
    <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>

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
    </tr>
  </thead>
  <tbody>
    <Student v-for="stu in students" :key="stu.id" :student="stu"></Student>
  </tbody>
</table>
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
      dialogVisible: false,
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
      axios({
        url:"http://localhost:8080/students",
        method:'GET',
      }).then(res=>{
        console.log(res.data);
        this.students = res.data;
      })
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
    }
  },
}
</script>

<style>

</style>
