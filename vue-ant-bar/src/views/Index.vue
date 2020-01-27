<template>
    <a-layout id="components-layout-demo-fixed-sider">
        <a-layout-sider collapsible v-model="collapsed">
            <a-menu
                    :defaultOpenKeys="['sub1']"
                    :defaultSelectedKeys="['1']"
                    :style="{ height: '100%', borderRight: 0 }"
                    mode="inline"
                    theme="dark"
            >
                <a-sub-menu :key="item.title" v-for="item in items">
                    <span slot="title"><a-icon :type='item.action'/><span>{{ item.title }}</span></span>
                    <a-menu-item :key="subItem.title" :to="item.path + subItem.path" v-for="subItem in item.items">
                        {{ subItem.title }}
                        <router-link :to="item.path + subItem.path"></router-link>
                    </a-menu-item>
                </a-sub-menu>
            </a-menu>
        </a-layout-sider>
        <a-layout>

            <a-layout-header class="header">

                <a-menu
                        :defaultSelectedKeys="['2']"
                        :style="{ lineHeight: '64px'}"
                        mode="horizontal"
                        theme="dark"
                >
                    <a-menu-item class="logo"></a-menu-item>
                    <a-menu-item key="1" style="margin-right: 100px;margin-left: 25px">首页</a-menu-item>
                    <a-sub-menu style="margin-right: 100px">
                        <span class="submenu-title-wrapper" slot="title">菜单</span>
                        <a-menu-item key="setting:1"><a href="http://www.alipay.com/"></a>支付宝</a-menu-item>
                        <a-menu-item key="setting:2">淘宝</a-menu-item>
                        <a-menu-item key="setting:3">天猫</a-menu-item>
                    </a-sub-menu>
                    <a-menu-item key="3" style="margin-right: 100px">关于我们</a-menu-item>
                    <a-menu-item key="4" style="margin-right: 100px">
                        <a-dropdown :placement="placement">
                            <a class="ant-dropdown-link" href="#">商务合作</a>
                            <a-menu slot="overlay">

                                <a-menu-item>
                                    <a href="http://www.alipay.com/" rel="noopener noreferrer" target="_blank">支付宝</a>
                                </a-menu-item>
                                <a-menu-item>
                                    <a href="http://www.taobao.com/" rel="noopener noreferrer" target="_blank">淘宝</a>
                                </a-menu-item>
                                <a-menu-item>
                                    <a href="http://www.tmall.com/" rel="noopener noreferrer" target="_blank">天猫</a>
                                </a-menu-item>
                            </a-menu>
                        </a-dropdown>
                    </a-menu-item>
                    <a-sub-menu>
                        <span class="submenu-title-wrapper" slot="title">关于我们</span>
                        <a-menu-item-group title="Item 1">
                            <a-menu-item key="setting:1">Option 1</a-menu-item>
                            <a-menu-item key="setting:2">Option 2</a-menu-item>
                        </a-menu-item-group>
                        <a-menu-item-group title="Item 2">
                            <a-menu-item key="setting:3">Option 3</a-menu-item>
                            <a-menu-item key="setting:4">Option 4</a-menu-item>
                        </a-menu-item-group>
                    </a-sub-menu>
                </a-menu>

                <div style="float: right;margin-top: -65px;margin-right:100px">
                    <Notify></Notify>
                </div>
                <div style="float: right;margin-top: -65px;margin-right: -10px">
                    <User></User>
                </div>
            </a-layout-header>

            <a-breadcrumb style="margin: 8px 0">
                <a-breadcrumb-item>
                    <a-icon type="home"/>
                </a-breadcrumb-item>
                <a-breadcrumb-item>
                    <!-- <a-icon type="user"/>-->
                    <span>{{item1}}</span>
                </a-breadcrumb-item>
                <a-breadcrumb-item>
                    <!--    <a-icon type="user"/>-->
                    <span>{{item2}}</span>
                </a-breadcrumb-item>
            </a-breadcrumb>

            <a-layout-content :style="{ margin: '24px 16px 0' }">
                <div :style="{ padding: '24px', background: '#fff', minHeight: '530px' }">
                    <router-view></router-view>
                </div>
            </a-layout-content>

            <a-layout-footer style="textAlign: center; ">
                Zhun Da ©2019 Created by Ant UED
            </a-layout-footer>
        </a-layout>

    </a-layout>
</template>
<script>
    import Notify from "../components/Notify";
    import User from "../components/User";
    import menus from "../menu";

    export default {
        name: "Index",
        components: {User, Notify},
        computed: {
            items() {
                return menus;
            },
            item1() {
                // console.log(this.$route)
                const arr = this.$route.path.split("/");
                return this.menuMap[arr[1]].name;
            },
            item2() {
                // console.log(this.$route)
                const arr = this.$route.path.split("/");
                return this.menuMap[arr[1]][arr[2]];
            }
        },
        created() {
            menus.forEach(m => {
                const p1 = m.path.slice(1);
                this.menuMap[p1] = {name: m.title};
                m.items.forEach(i => {
                    this.menuMap[p1][i.path.slice(1)] = i.title;
                })
            })
        },
        data() {
            return {
                val: "",
                collapsed: false,
                content: true,
                oneMenu: "",
                twoMenu: "",
                placement: "bottomCenter",
                Path: {},
                menuMap: {},
            }
        },

        methods: {
            /*menu(s) {
                this.Path = {}
                this.Path = JSON.parse(JSON.stringify(s))
                console.log(this.Path)
                console.log(this.Path.name)
                this.$router.push(s.path)
                //console.log(this.val)
            }*/
        },
    }
</script>

<style>
    #components-layout-demo-top-side-2 .logo {
        width: 160px;
        height: 50px;
        background-image: url("../assets/logo.png");
        background-size: 100% 100%;

        margin-top: 15px;
        margin-left: -35px;
        float: left;
    }

</style>
