<template>
    <a-dropdown>
        <div class="item">

            <div class="d-flex align-items-center px-sm">
                <a-avatar class="mr-sm" icon="user" nzSize="small"></a-avatar>
                <span style="color:white"> Admin</span>
            </div>
        </div>
        <a-menu slot="overlay">
            <a-menu-item>
                <i class="fa fa-user-o"></i>
                个人中心
            </a-menu-item>
            <a-menu-item>
                <i class="fa fa-cog"></i>
                个人设置
            </a-menu-item>
            <a-menu-divider/>
            <a-menu-item divided key="logout">
                <i class="fa fa-sign-out"></i>
                退出登录
            </a-menu-item>
        </a-menu>
    </a-dropdown>
</template>

<script>
    export default {
        name: "User",
        data() {
            return {
                loadding: false,
            };
        },
        methods: {},
    }
</script>

<style scoped>

</style>