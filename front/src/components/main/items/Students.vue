<template>
  <div>
    <el-col>
      <el-row class="student_one" >
        <el-col :span="5" :offset="8">
          <el-input v-model="searchName" placeholder="请输入学生姓名" prefix-icon="el-icon-search" ></el-input>
        </el-col>
        <el-col :span="2">
          <div style="margin-left:1%">
          <el-button v-show="!isSearching" type="primary" @click="search">搜索</el-button>
          </div>
          <div style="margin-left:1%">
          <el-button v-show="isSearching" type="info" @click="backBeforeSearch">返回</el-button>
          </div>
        </el-col>
        <el-col :span="2">
          <el-button type="warning" @click="dialogVisible = true">添加</el-button>
       </el-col>
      </el-row>
      <!-- 添加学生  -->
      <el-dialog
        title="添加学生"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
        class="dialog_add">
        <el-row>
          <span>学号:</span><el-input class="dia_input" v-model="newStudent.sid"></el-input>
        </el-row>
        <el-row>
          <span>姓名:</span><el-input class="dia_input" v-model="newStudent.name"></el-input>
        </el-row>
        <el-row>
          <span>性别:</span><el-input class="dia_input" v-model="newStudent.gender"></el-input>
        </el-row>
        <el-row>
          <span>年龄:</span><el-input class="dia_input" v-model.number="newStudent.age"></el-input>
        </el-row>
        <div style="margin-top:15px ; margin-bottom:20px">
        <!-- <span slot="footer" class="dialog-footer"> -->
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addStudent" style="margin-left:17%">确 定</el-button>
        </div>
        </el-dialog>
      <!--  -->
       <el-table
        :data="tableForPage"
        style="width: 100%">
        <!-- 五列 学号/姓名/性别/年龄/操作-->
        <el-table-column
          label="学号"
          width="">
          <!-- 拿到scope -->
          <template slot-scope="scope">
            {{ scope.row.sid }}
          </template>
        </el-table-column>
        <el-table-column label="姓名" width="">
          <template slot-scope="scope">
            <!-- isedit属性  true：覆盖原来的框！ -->
          <span v-show="!scope.row.isEdit" style="margin-left: 10px">{{ scope.row.name }}</span>
          <span v-show="scope.row.isEdit"><el-input v-model="scope.row.name"></el-input></span>
        </template>
        </el-table-column>
        
        <el-table-column label="性别">
        <template slot-scope="scope">
            <span v-show="!scope.row.isEdit" style="margin-left: 10px">{{ scope.row.gender }}</span>
            <span v-show="scope.row.isEdit"><el-input v-model="scope.row.gender"></el-input></span>
          </template>
        </el-table-column>

        <el-table-column label="年龄">
        <template slot-scope="scope">
            <span v-show="!scope.row.isEdit" style="margin-left: 10px">{{ scope.row.age }}</span>
            <span v-show="scope.row.isEdit"><el-input type="number" class="w-50" v-model="scope.row.age"></el-input></span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button 
              v-show="!scope.row.isEdit"
              size="mini"
              type="primary"
              @click="handleEdit(scope.$index, scope.row)"><i class="el-icon-edit"></i></el-button>
            <el-button 
              v-show="scope.row.isEdit"
              size="mini"
              type="success"
              @click="handleSave(scope.$index, scope.row)"
            ><i class="el-icon-check"></i>
            </el-button>
            <!-- 删除 -->
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"><i class="el-icon-delete"></i></el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <!-- 分页 
          第一个：条目 绑定 下拉菜单
          绑定当前页数 ，初始化 1
          total：row 的条数
      -->
      <el-row class="student_three">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 6, 8, 10]"
          :page-size="100"
          layout="total, sizes, prev, pager, next, jumper"
          :total="rows">
        </el-pagination>
      </el-row>
    </el-col>
  </div>
</template>
  
<script>
  import axios from 'axios'
    export default {
      data() {
        return {
          tableData: [],
          dialogVisible: false, //会话框显示
          newStudent: {
            // sid: "", 
            // name: "", 
            // gender:""
          },
          currentPage: 1,
          currentSize: 5,
          isSearching: false,
          searchName: "",
        }
      },
      // 计算属性
      computed: {
        rows() {
        return this.tableData.length;
        },
        tableForPage() {
          return this.tableData.slice(this.currentPage*this.currentSize-this.currentSize, this.currentPage*this.currentSize)
        }
      },
      methods: {
        getStudents(){
          axios({
            url: 'http://localhost:8080/students',
          }).then(res => {
              this.tableData = res.data;
              // 转换数据
          })
        },
  
        async addStudent() {
          console.log(this.newStudent);
          await axios({
            url: "http://localhost:8080/addStudent",
            method: 'POST',
            data:this.newStudent
          })
          await  axios({
            url: "http://localhost:8080/addScores",
            method: "POST",
            data: {
            sid: this.newStudent.sid,
            name: this.newStudent.name
          }
          })
          this.$message({
            type: 'success',
            message: '添加成功!'
          });
          await this.getStudents();
          this.dialogVisible = false
        },
        // 编辑操作
        handleEdit(index, row) {
          // 点击编辑后，给 row 增加一个isedit属性，设置为true vue自带的set方法！
          this.$set(row,"isEdit",true)
          console.log(index, row);
        },
        // 删除操作  index作为占位符存在
        handleDelete(index, row) {
          this.$confirm('此操作将永久删除该学生及其成绩, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            await axios({
              url: "http://localhost:8080/deleteStudent",
              method: "DELETE",
              data: row
            });
            await axios({
              url: "http://localhost:8080/deleteScores",
              method: "DELETE",
              data: row
            });
              await this.getStudents();
              this.$message({
                  type: 'success',
                  message: '删除成功!'
              });
              }).catch(() => {
              this.$message({
                  type: 'info',
                  message: '已取消删除'
              });          
              });
          // console.log(index, row);
        },
        // 保存
        async handleSave(index, row) {
          row.isEdit=false;
          console.log(index, row);
          await axios({
            url: "http://localhost:8080/updateStudent",
            method:"PUT",
            data:row
          })
          await axios({
           url: "http://localhost:8080/updateScores",
           method: "PUT",
          data:row
          })
          // console.log("===========");
          // console.log(row.sid);
        },
        // var参数 代表浏览器绑定的数据，参数：当前页数
        handleCurrentChange(val) {
          console.log(val);
          this.currentPage = val;
        },
        handleSizeChange(val) {
          console.log(val);
          this.currentSize = val;
        },
        // 查询
        async search() {
          await axios({
            url: "http://localhost:8080/searchStudents",
            method: "POST",
            data: {
              name:this.searchName
            }
          }).then(res => {
          console.log(res.data);
          this.tableData = res.data;
          //将表格数据 替换成 查询后的数据
          })
          this.currentPage = 1;
          this.isSearching=true;
        },
        // 返回
        async backBeforeSearch() {
          await this.getStudents();
          this.isSearching=false;
        }
      },
      mounted() {
        this.getStudents();
      },
    }
</script>
  
<style scoped>
.student_one{
  margin-bottom: 15px;

}
.student_three{
  margin-top: 15px;
}
.dia_input{
  width: 60%;
  padding-top: 2%;
  padding-left: 5%;
  padding-bottom:3%;
}
.dialog_add /deep/.el-dialog__body{
  padding-top: 1%;
  padding-bottom: 4%;
}

</style>