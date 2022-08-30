import VueRouter from 'vue-router'

import User from '../components/user/User'
import Login from '../components/user/Login'
import Register from '../components/user/Register'
import Main from '../components/main/Main'

const router = new VueRouter({
// 配置路由
    routes: [
        {
            path: "/user",
            component: User,
            name: "user",
            children: [
                {
                    // 登录页面
                    path: "login",
                    name: "login",
                    component: Login,
                },
                {
                    //首页 路由配置
                    path: "register",
                    name: "register",
                    component: Register,
                }
            ]
        },
        {
            path: "/main",
            name: "main",
            component:Main,
        },
    ]
})

export default router