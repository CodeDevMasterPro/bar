var menus = [
    {
        action: "home",
        title: "首页",
        path: "/index",
        items: [
            {title: "例子", path: "/content"},
        ],

    },
    {
        action: "file-done",
        title: "小程序首页管理",
        path: "/indexPage",
        items: [
            {title: "轮播图管理", path: "/slideshowManager"},
            {title: "店铺信息管理", path: "/barInfoManager"},
        ]
    },
    {
        action: "file-done",
        title: "商品管理",
        path: "/goods",
        items: [
            {title: "商品管理", path: "/goodsManager"},
        ]
    },
    {
        action: "file-done",
        title: "订单管理",
        path: "/order",
        items: [
            {title: "订单管理", path: "/orderManager"},
        ]
    },
    {
        action: "file-done",
        title: "分类管理",
        path: "/category",
        items: [
            {title: "分类管理", path: "/categoryManager"},
        ]
    },
]

export default menus;
