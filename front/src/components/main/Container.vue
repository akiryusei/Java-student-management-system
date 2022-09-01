<!-- 主界面  -->
<template>
  <div>
  <el-container>
    <!-- header -->
    <el-header><Header/></el-header>
    <Navi/>
    <el-container>
<!-- main -->
        <!-- <el-main>
            <router-view></router-view>
        </el-main>  -->
     
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
      background-color: #545c64;
      color: #fff;
      /* 字体颜色 */
    }
    
    .el-aside {
      background-color: #D3DCE6;
      color: #333;
      text-align: center;
      line-height: 200px;
    }
    
    .el-main {
      background-color: #E9EEF3;
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