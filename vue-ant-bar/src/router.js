import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

function route(path, file, name, children) {
    return {
        exact: true,
        path,
        name,
        children,
        component: () => import('./views' + file)
    }
}

export default new Router({
    mode: 'history',  //去掉url中的#
    routes: [
        /* route("/login",'/Login',"Login"),// /login路径，路由到登录组件*/
        {
            path: "/", // 根路径，路由到 Layout组件
            component: () => import('./views/Index'),
            redirect: "/index/content",
            children: [ // 其它所有组件都是 Layout的子组件
                route("/index/content", "/Content", "Content"),
                route("/order/orderManager", '/order/OderManager', "OderManager"),
                route("/indexPage/slideshowManager", '/indexPage/SlideshowManager', "SlideshowManager"),
                route("/indexPage/barInfoManager", '/indexPage/BarInfoManager', "BarInfoManager"),
                route("/goods/goodsManager", '/goods/GoodsManager', "GoodsManager"),
                route("/category/categoryManager", '/category/CategoryManager', "CategoryManager"),
            ]
        }
    ]
})
