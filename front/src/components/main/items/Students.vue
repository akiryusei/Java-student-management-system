<template>
    <div>
      <el-input v-model="searchName" class="w-25" placeholder="请输入学生姓名"></el-input>
      <el-button v-show="!isSearching" type="primary" @click="search">搜索</el-button>
      <el-button v-show="isSearching" type="info" @click="backBeforeSearch">返回</el-button>
      <el-button  type="warning" @click="dialogVisible = true">添加</el-button>

      <!-- 添加学生  -->
      <el-dialog
        title="添加学生"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <span>学号:</span><el-input class="w-75" v-model="newStudent.sid"></el-input>
        <br>
        <span>姓名:</span><el-input class="w-75" v-model="newStudent.name"></el-input>
        <br>
        <span>性别:</span><el-input class="w-75" v-model="newStudent.gender"></el-input>
        <br>
        <span>年龄:</span><el-input class="w-75" v-model.number="newStudent.age"></el-input>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addStudent">确 定</el-button>
        </span>
        </el-dialog>
      <!--  -->
       <el-table
        :data="tableForPage"
        style="width: 100%">
        <!-- 五列 学号/姓名/性别/年龄/操作 -->
        <el-table-column
          label="学号"
          width="">
          <template slot-scope="scope">
            {{ scope.row.sid }}
          </template>
        </el-table-column>
        <el-table-column label="姓名" width="">
          <template slot-scope="scope">
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
            ><i class="el-icon-check"></i></el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"><i class="el-icon-delete"></i></el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <!-- 分页 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 6, 8, 10]"
        :page-size="100"
        layout="total, sizes, prev, pager, next, jumper"
        :total="rows">
      </el-pagination>
    </div>
  </template>
  
<script>
  import axios from 'axios'
    export default {
      data() {
        return {
          tableData: [],// 表格数据
          dialogVisible: false, //添加会话框
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
              // 转换数据  res 响应对象
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
            url: "http://localhost:8080/addGrades",
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
          this.$set(row,"isEdit",true)
          console.log(index, row);
        },
        // 删除操作
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
              url: "http://localhost:8080/deleteGrades",
              method: "DELETE",
              data: row
            });
              await this.getStudents();
              // this.$emit('reloadTable');
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
           url: "http://localhost:8080/updateGrades",
           method: "PUT",
          data:row
          })
          // console.log("===========");
          // console.log(row.sid);
        },
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
          })
          this.currentPage = 1;
          this.isSearching=true;
        },
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
  
<style>
  
</style>