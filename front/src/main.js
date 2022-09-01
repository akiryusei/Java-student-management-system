//全局引入
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
// 引用包
import VueRouter from 'vue-router'
// 引用 router模块。。。
import router from './router'
// 引入bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'

Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});
