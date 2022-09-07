<template>
  <div>
    <el-col>
      <el-row class="score_one">
        <el-col :span="5" :offset="8">
          <el-input v-model="searchName" placeholder="请输入学生姓名" prefix-icon="el-icon-search"></el-input>
        </el-col>
        <el-col :span="2">
          <div style="margin-left:0%">
            <el-button v-show="!isSearching" type="primary" @click=search>搜索</el-button>
          </div>
          <div style="margin-left:0%">
          <el-button v-show="isSearching" type="info" @click=backBeforeSearch>返回</el-button>
          </div>
        </el-col>
        <el-col :span="2">
          <el-button type="warning" @click="dialogVisible = true">添加</el-button>
        </el-col>
      </el-row>
        <!-- 添加学生 -->
       <el-dialog
        title="请先添加一个学生"
        :visible.sync="dialogVisible"
        width="28%"
        :before-close="handleClose"
        class="dialog_add">
        <el-row>
          <span>学号:</span><el-input class="dia_input" v-model="newScores.sid"></el-input>
        </el-row>
        <el-row>
          <span>姓名:</span><el-input class="dia_input" v-model="newScores.name"></el-input>
        </el-row>
        <el-row>
          <span>性别:</span><el-input class="dia_input" v-model="newScores.gender"></el-input>
        </el-row>
        <el-row>
          <span>年龄:</span><el-input class="dia_input" v-model.number="newScores.age"></el-input>
        </el-row >
          <div style="margin-top:15px ; margin-bottom:20px">
          <!-- <span slot="footer" class="dialog-footer"> -->
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addScores" style="margin-left:17%">确 定</el-button>
        <!-- </span> -->
          </div>
        </el-dialog> 

        <!-- border 表格隔行 -->
       <el-table
      :data="tableForPage"
      border
      style="width: 100%">
      <!-- <el-table-column
        prop="sid"
        label="学号"
        width="100">
      </el-table-column> -->
      <!-- prop填入数据 -->
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
            @click="handleEdit(scope.$index, scope.row)">
          </el-button>
          <el-button 
            v-show="scope.row.isEdit"
            size="mini"
            type="success"
            icon="el-icon-check"
            circle
            @click="handleSave(scope.$index, scope.row)">
          </el-button>
          <!-- 删除 -->
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            circle
            @click="handleDelete(scope.$index, scope.row)"></el-button>
        </template>
      </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-row class="score_three">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 6, 8, rows]"
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
        dialogVisible: false,
        newScores: {},
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
      async getScores() {
        await axios({
          url:"http://localhost:8080/getScores"
        }).then(res => {
          this.tableData = res.data;
          console.log(res.data);
        })
      },
      //编辑
      handleEdit(index, row) {
        console.log(row);
        this.$set(row,"isEdit",true)
      },
      async handleSave(index, row) {
        await axios({
          url: "http://localhost:8080/updateScores",
          method: "PUT",
          data:row
        })
        await this.getScores();
        this.$set(row,"isEdit",false)
      },
      //添加成绩
      async addScores() {
        await axios({
          url: "http://localhost:8080/addStudent",
          method: "POST",
          data:this.newScores
        })
        await axios({
          url: "http://localhost:8080/addScores",
          method: "POST",
          data: {
            sid: this.newScores.sid,
            name: this.newScores.name,
          }
        })
        await this.getScores();
        //弹出会话框 添加成功
          this.$message({
            type: 'success',
            message: '添加成功!'
        });
        this.dialogVisible = false;
      },
      //删除成绩
      async handleDelete(index, row) {
         this.$confirm('此操作将永久删除该学生及其成绩, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
             await axios({
              url: "http://localhost:8080/deleteScores",
              method:"DELETE",
              data:row
            })
            await axios({
              url: "http://localhost:8080/deleteStudent",
              method:"DELETE",
              data:row
            })
            await this.getScores();
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
        //查询
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
          await this.getScores();
          this.isSearching = false;
        }
      },
    mounted() {
        this.getScores();
      },
  };
</script>
  
<style scoped>
.score_one{
  margin-bottom: 15px;
}
.score_three{
  margin-top: 15px;
}
.dia_input{
  width: 60%;
  padding-top: 2.5%;
  padding-left: 5%;
  padding-bottom:2.5%;
}

/* .el-dialog__body {
    padding: 10px 20px;
    color: #606266;
    font-size: 14px;
    word-break: break-all;
} */
.dialog_add /deep/.el-dialog__body{
  padding-top: 1%;
  padding-bottom: 4%;
}
</style>