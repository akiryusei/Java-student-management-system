//全局引入
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
// 引用包
import VueRouter from 'vue-router'
// 引用 router
import router from './router'
// 引入bootstrap
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
// 引入echarts
import * as charts from 'echarts';

Vue.use(ElementUI);
Vue.use(VueRouter);

Vue.prototype.$charts=charts
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});
