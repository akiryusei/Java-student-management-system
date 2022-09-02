import VueRouter from 'vue-router'

import User from '../components/user/User'
import Login from '../components/user/Login'
import Register from '../components/user/Register'
import Container from '../components/main/Container'
// 导航组件
import Students from '../components/main/items/Students'
import Courses from '../components/main/items/Courses'


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
            name: "container",
            component:Container,
            children:[
                {
                    path: "students",
                    name: "students",
                    component: Students,
                },
                {
                    path: "courses",
                    component: Courses,
                    name: "courses",
                }
            ]
        },
    ]
})

export default router