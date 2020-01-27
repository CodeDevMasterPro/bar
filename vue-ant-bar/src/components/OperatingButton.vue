<template>
    <div>
        <a-button icon="plus-circle" style="margin:5px 10px" type="primary">新增</a-button>
        <a-button icon="edit" style="margin: 5px 10px" type="primary">修改</a-button>
        <a-button click="delete" icon="minus-circle" style="margin: 5px 10px" type="primary">删除</a-button>
        <a-button icon="download" style="margin: 5px 10px" type="primary">Download</a-button>
        <a-input-search style="width: 300px;margin: 5px 10px;">查询</a-input-search>
    </div>
</template>

<script>
    export default {
        name: "OperatingButton",
        methods: {}
    }
</script>

<style scoped>

</style>