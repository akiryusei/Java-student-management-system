<!-- 主界面  -->
<template>
  <div>
  <el-container>
    <!-- header -->
    <el-header><Header/></el-header>
    <el-container>
    <!-- main -->
        <el-aside width="200px">
          <Navi/>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main> 
    </el-container>
  </el-container>
  </div>
</template>
  
<script>
import Header from './Header'
import Navi from './Navi'

  export default {
      name:'Container',
      components:{
        Header,
        Navi,
        // 注册一下
    },
    data() {
      return {
        username:sessionStorage.getItem("username")
      }
    },
      methods: {
      }
  }
  </script>
  
  <style>
    .el-header {
      background-color:#545c64;
      color: #fff;
      /* padding: 20px; */
      /* box-shadow: 0 0 6px rgba(0,0,0,.04); */
    }
    
    .el-aside {
      background-color: #f9f4dc;
      color: #333;
      text-align:left;
      /* line-height: 200px; */
      overflow: hidden;
    }
    
    .el-main {
      background-color:#DCDFE6;
      color: #333;
      text-align: center;
      line-height: 40px;
    }
    .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
    .el-icon-arrow-down {
        font-size: 12px;
    }

  </style>