<template>
    <div>
        <router-view></router-view>
    </div>
  </template>
  
  <script>

export default {
  components: {
    //Login
  },
  mounted() {
    // 跳转页面
    this.$router.push({
      name: 'login',
    })
  },
}
  </script>
  
  <style>
  
  </style>