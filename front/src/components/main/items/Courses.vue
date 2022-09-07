<template>
  <div>
    <!-- 课程信息  -->
    <el-col style="margin-top:20px">
    <el-table :data="tableData" stripe style="width: 100% ">
      <el-table-column
        label="课程号"
        width="">
        <template slot-scope="scope">
          <span v-show="!scope.row.isEdit" style="margin-left: 10px">{{ scope.row.cid }}</span>
           <span v-show="scope.row.isEdit"><el-input v-model="scope.row.cid"></el-input></span>       
        </template>
      </el-table-column>

      <el-table-column
        label="课程名"
        width="">
        <template slot-scope="scope">
          <span v-show="!scope.row.isEdit" style="margin-left: 10px">{{ scope.row.cname }}</span>
          <span v-show="scope.row.isEdit"><el-input v-model="scope.row.cname"></el-input></span>
        </template>
      </el-table-column>
      <el-table-column label="课程学分">
        <template slot-scope="scope">
          <span v-show="!scope.row.isEdit" style="margin-left: 10px">{{ scope.row.credit }}</span>
          <span v-show="scope.row.isEdit"><el-input v-model.number="scope.row.credit"></el-input></span>
        </template>
      </el-table-column>
    </el-table>
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
            newCourse: {
                // sid: "", 
                // name: "", 
                // gender:""
            }
            }
        },
        methods: {
          getCourses() {
            axios({
              url: 'http://localhost:8080/courses',
            }).then(res => {
              this.tableData = res.data;
            })
          },
        },
        mounted() {
            this.getCourses();
        },
    
    }
</script>
  
<style>
  
</style>