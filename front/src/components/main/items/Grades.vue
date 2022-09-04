<template>
    <div>
      <el-input class="w-25" v-model="searchName"></el-input>
      <el-button v-show="!isSearching" type="primary" @click=search>搜索</el-button>
      <el-button v-show="isSearching" type="info" @click=backBeforeSearch>返回</el-button>
      <el-button type="warning" @click="dialogVisible = true">添加</el-button>
  
       <el-dialog
        title="请先添加一个学生"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <span>学号:</span><el-input class="w-75" v-model="newGrades.sid"></el-input>
        <br>
        <span>姓名:</span><el-input class="w-75" v-model="newGrades.name"></el-input>
        <br>
        <span>性别:</span><el-input class="w-75" v-model="newGrades.gender"></el-input>
        <br>
        <span>年龄:</span><el-input class="w-75" v-model.number="newGrades.age"></el-input>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addGrades">确 定</el-button>
        </span>
        </el-dialog> 
       <el-table
      :data="tableForPage"
      border
      style="width: 100%">
      <!-- <el-table-column
        prop="sid"
        label="学号"
        width="100">
        
      </el-table-column> -->
      <el-table-column
        prop="name"
        label="姓名">
      </el-table-column>
      <el-table-column
        prop="java"
        sortable
        label="java">
        <template slot-scope="scope"> 
          <span v-show="!scope.row.isEdit">{{scope.row.java}}</span>
          <span v-show="scope.row.isEdit">
            <el-input v-model="scope.row.java" class=""></el-input>
          </span>
        </template>
      </el-table-column>
      <el-table-column
        prop="english"
        sortable
        label="英语">
        <template slot-scope="scope"> 
          <span v-show="!scope.row.isEdit">{{scope.row.english}}</span>
          <span v-show="scope.row.isEdit">
            <el-input v-model="scope.row.english" class=""></el-input>
          </span>
        </template>  
      </el-table-column>
      <el-table-column
        prop="mathematics"
        sortable
        label="高数">
        <template slot-scope="scope"> 
          <span v-show="!scope.row.isEdit">{{scope.row.mathematics}}</span>
          <span v-show="scope.row.isEdit">
            <el-input v-model="scope.row.mathematics" class=""></el-input>
          </span>
        </template>  
      </el-table-column>
      <el-table-column
        prop="game"
        sortable
        label="游戏">
        <template slot-scope="scope"> 
          <span v-show="!scope.row.isEdit">{{scope.row.game}}</span>
          <span v-show="scope.row.isEdit">
            <el-input v-model="scope.row.game" class=""></el-input>
          </span>
        </template>  
      </el-table-column>
      <el-table-column
        prop="algebra"
        sortable
        label="现代">
          <template slot-scope="scope"> 
          <span v-show="!scope.row.isEdit">{{scope.row.algebra}}</span>
          <span v-show="scope.row.isEdit">
            <el-input v-model="scope.row.algebra" class=""></el-input>
          </span>
        </template>
      </el-table-column>
      <el-table-column
        prop="military"
        sortable
        label="军事">
          <template slot-scope="scope"> 
          <span v-show="!scope.row.isEdit">{{scope.row.military}}</span>
          <span v-show="scope.row.isEdit">
            <el-input v-model="scope.row.military" class=""></el-input>
          </span>
        </template>
      </el-table-column>
      <el-table-column
        prop="modeling"
        sortable
        label="建模">
          <template slot-scope="scope"> 
          <span v-show="!scope.row.isEdit">{{scope.row.modeling}}</span>
          <span v-show="scope.row.isEdit">
            <el-input v-model="scope.row.modeling" class=""></el-input>
          </span>
        </template>
      </el-table-column>
      <el-table-column
        prop="grade"
        sortable
        width="100"
        label="绩点">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button 
            v-show="!scope.row.isEdit"
            size="mini"
            type="primary"
            icon="el-icon-edit"
            circle
            @click="handleEdit(scope.$index, scope.row)"></el-button>
          <el-button 
            v-show="scope.row.isEdit"
            size="mini"
            type="success"
            icon="el-icon-check"
            circle
            @click="handleSave(scope.$index, scope.row)"
          ></el-button>
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            circle
            @click="handleDelete(scope.$index, scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 6, 8, rows]"
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
          tableData: [],
          dialogVisible: false,
          newGrades: {},
          currentPage: 1,
          currentSize: 5,
          searchName: "",
          isSearching:false
        };
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
      async getGrades() {
        await  axios({
          url:"http://localhost:8080/getGrades"
        }).then(res => {
          this.tableData = res.data;
          console.log(res.data);
        })
      },
      handleEdit(index, row) {
        console.log(row);
        this.$set(row,"isEdit",true)
      },
     async handleSave(index, row) {
       await axios({
          url: "http://localhost:8080/updateGrades",
          method: "PUT",
          data:row
        })
       await this.getGrades();
        this.$set(row,"isEdit",false)
      },
      
     async addGrades() {
       await axios({
          url: "http://localhost:8080/addStudent",
          method: "POST",
          data:this.newGrades
        })
      await  axios({
          url: "http://localhost:8080/addGrades",
          method: "POST",
        data: {
          sid: this.newGrades.sid,
          name: this.newGrades.name,
          }
      })
       await this.getGrades();
       this.$message({
            type: 'success',
            message: '添加成功!'
        });
       this.dialogVisible = false;
      },
      async handleDelete(index, row) {
         this.$confirm('此操作将永久删除该学生及其成绩, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
             await axios({
              url: "http://localhost:8080/deleteGrades",
              method:"DELETE",
              data:row
            })
            await axios({
              url: "http://localhost:8080/deleteStudent",
              method:"DELETE",
              data:row
            })
            await this.getGrades();
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
     
      },
      handleCurrentChange(val) {
        console.log(val);
        this.currentPage = val;
      },
      handleSizeChange(val) {
        console.log(val);
        this.currentSize = val;
      },
      async search() {
       await axios({
          url: "http://localhost:8080/searchGrades",
          method: "POST",
          data: {
            name:this.searchName
          }
        }).then(res => {
          this.tableData = res.data;
        })
        this.currentPage = 1;
        this.isSearching = true;
      }, 
      async backBeforeSearch() {
        await this.getGrades();
        this.isSearching = false;
      }
    },
    mounted() {
        this.getGrades();
      },
    };
</script>
  
<style>
  
</style>