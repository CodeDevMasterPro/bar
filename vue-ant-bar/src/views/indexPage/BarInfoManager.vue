<template>
    <div>
        <div class="card-list" ref="content">
            <a-list
                    :dataSource="dataSource"
                    :grid="{gutter: 24, lg: 3, md: 2, sm: 1, xs: 1}"
                    :pagination="{
                 showSizeChanger: true,
                 showQuickJumper: true,
                 pageSize: pageSize,
                 defaultCurrent:current,
                 total: total,
                 pageSizeOptions:['6', '9', '12','15',],
                 onChange,onShowSizeChange
                }"
                    rowKey="id"


            >

                <a-list-item slot="renderItem" slot-scope="item">
                    <template v-if="!item || item.id === undefined">
                        <a-button @click="edit" class="new-btn" type="dashed">
                            <a-icon type="plus"/>
                            新增产品
                        </a-button>
                    </template>
                    <template v-else>
                        <a-card :loading="loading" size="small">
                            <a-card-meta>
                                <a-avatar class="card-avatar">状态</a-avatar>
                                <a-avatar :class="item.status%2==0?'start':'end'" :src="item.avatar" class="card-avatar"
                                          size="large" slot="avatar"/>
                                <div class="meta-content" slot="description">
                                    <h4 style="background: #e6fffb">项目编码：</h4>
                                    <p style="text-indent: 2em;margin-bottom: 5px">{{ item.projectCode }}</p>
                                </div>
                                <div class="meta-content" slot="description">
                                    <h4 style="background:  #b5f5ec">人员：</h4>
                                    <p style="text-indent: 2em;margin-bottom: 5px"> {{ item.personnel }}</p>
                                </div>
                                <div class="meta-content" slot="description">
                                    <h4 style="background: #87e8de">开始：</h4>
                                    <p style="text-indent: 2em;margin-bottom: 5px"> {{ item.start }}</p>
                                </div>
                                <div class="meta-content" slot="description">
                                    <h4 style="background: #5cdbd3">场景：</h4>
                                    <p style="text-indent: 2em;margin-bottom: 5px"> {{ item.scenes }}</p>
                                </div>
                                <div class="meta-content" slot="description">
                                    <h4 style="background: #36cfc9">描述：</h4>
                                    <template>
                                        <a-popover>
                                            <template slot="content">
                                                <p>{{item.content }}</p>
                                            </template>
                                            <p style="text-indent: 2em;margin-bottom: 5px; ">{{ item.content }}</p>
                                        </a-popover>
                                    </template>

                                </div>
                            </a-card-meta>

                            <template class="ant-card-actions" slot="actions">
                                <a-button @click="edit(item)" style="color: #52C41A" type="link ">编辑</a-button>
                                <a-button @click="del(item.id)" style="color: #52C41A" type="link ">删除</a-button>
                            </template>
                        </a-card>
                    </template>
                </a-list-item>
            </a-list>
        </div>
        <!--from表单-->
        <template>
            <div>
                <a-modal
                        :confirmLoading="confirmLoading"
                        :visible="visible"
                        @cancel="handleCancel"
                        @ok="handleOk"
                        size="small"
                        title="卡片详情"
                >
                    <a-card :hoverable="true" size="small">
                        <a-form :form="form" @submit="handleSubmit">
                            <!--:label-col="{ span: 5 }" :wrapper-col="{ span: 12 }"-->
                            <a-form-item label="项目编码" style="margin-bottom: 0">
                                <a-input :disabled="disable"
                                         v-decorator="['projectCode', { rules: [{ required: true, message: 'Please input your note!' }] }]"/>
                            </a-form-item>
                            <a-form-item label="人员" style="margin-bottom: 0">
                                <a-input
                                        v-decorator="['personnel', { rules: [{ required: true, message: 'Please input your note!' }] }]"/>
                            </a-form-item>
                            <a-form-item label="开始" style="margin-bottom: 0">
                                <a-date-picker style="width: 446px"
                                               v-decorator="['start', { rules: [{ required: true, message: 'Please input your note!' }] }]"/>
                                <!--  <a-input v-decorator="['start', { rules: [{ required: true, message: 'Please input your note!' }] }]"/>-->
                            </a-form-item>
                            <a-form-item label="场景" style="margin-bottom: 0">
                                <a-input
                                        v-decorator="['scenes', { rules: [{ required: true, message: 'Please input your note!' }] }]"/>
                            </a-form-item>
                            <a-form-item label="描述" style="margin-bottom: 0">
                                <a-input v-decorator="['content']"/>
                            </a-form-item>
                        </a-form>
                    </a-card>
                </a-modal>
            </div>
        </template>

    </div>
</template>

<script>
    const dataSource = []
    dataSource.push({})
    for (let i = 0; i < 26; i++) {
        dataSource.push({
            id: i,
            projectCode: 'S-342499979',
            start: '2018-07-26',
            scenes: '新建' + `${i}`,
            personnel: '李白,大白',
            status: `${i}`,
            content: '这里是成都市高新区XX站点安装5G新设备,在点人员有5人这里是成都市高新区XX站点安装5G新设备,在点人员有5人'
        })
    }
    export default {
        name: 'BarInfoManager',
        data() {
            return {
                pageSize: 6,
                current: 1,
                total: 50,
                formLayout: 'horizontal',
                form: this.$form.createForm(this, {name: 'coordinated'}),
                disable: false,
                dataSource,
                //弹窗数据
                ModalText: 'Content of the modal',
                visible: false,
                confirmLoading: false,
                loading: false
            }
        },
        watch: {
            /* pageSize:function(val){
                 console.log('pageSize', val);
             },
             current(val) {
                 console.log('current', val);
             },
 */
        },
        methods: {

            //真正表单提交
            handleSubmit(e) {
                this.loading = true
                e.preventDefault();
                this.form.validateFields((err, values) => {
                    if (values.start != '') {
                        console.log(this.$moment(values.start, "YYYY-MM-DD"))
                        values.start = this.$moment(values.start, "YYYY-MM-DD")
                        values.start = this.$moment(values.start).format("YYYY-MM-DD")//moment转时间
                    }
                    if (!err) {
                        this.confirmLoading = true;
                        setTimeout(() => {
                            this.confirmLoading = false;
                            this.visible = false;
                            this.$message.error("提交失败!");
                            console.log('Received values of form: ', values);
                            this.form.resetFields();
                            this.disable = false
                            this.loading = false
                        }, 2000);
                    }
                });

            },

            //编辑
            edit(item) {
                console.log("编辑")
                if (item.start != '') {
                    item.start = this.$moment(item.start, "YYYY-MM-DD")

                }
                //console.log(item)
                if (item.constructor === Object) {
                    this.disable = true
                    setTimeout(() => {
                        this.form.setFieldsValue(item);
                    }, 0)

                }
                this.visible = true;
            },
            //删除
            del(index) {

                //const  newData = this.dataSource.filter(item => item.key !== index)
                this.$confirm({
                    title: '你确定要删除吗?',
                    content: '删除后不可恢复哟!',
                    okText: 'Yes',
                    okType: 'danger',
                    cancelText: 'No',
                    onOk() {
                        return new Promise((resolve, reject) => {
                            setTimeout(Math.random() > 0.5 ? resolve : reject, 1000);
                        }).then(() => {
                            console.log("success!")
                        }).catch(() => console.log('Oops errors!' + index));
                    },
                    onCancel() {
                        console.log('Cancel');
                    },
                });
            },
            //弹窗OK
            handleOk(e) {
                this.handleSubmit(e);
            },
            //弹窗Cancel
            handleCancel() {
                this.form.resetFields();
                this.visible = false;
                this.disable = false
            },
            //分页
            onChange(pageNumber, pagesize) {
                this.loading = true
                setTimeout(() => {
                    this.loading = false
                }, 2000)
                console.log('Page: ', pageNumber, 'pagesize:' + pagesize);
            },
            //每页显示数据条数
            onShowSizeChange(current, pageSize) {
                this.loading = true
                setTimeout(() => {
                    this.loading = false
                }, 2000)
                this.pageSize = pageSize;
                console.log(current, pageSize);
            },


            //搜索
            onSearch(value) {
                console.log(value);
            },
            created() {
                this.onChange(1);
            }
        }
    }
</script>
<style scoped>
    .ant-card-meta-detail .ant-card-meta-description .meta-content p {
        /*overflow: hidden;white-space: nowrap;text-overflow: ellipsis;*/
        overflow: hidden;
        -ms-text-overflow: ellipsis;
        -webkit-line-clamp: 1;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        text-overflow: ellipsis;
    }

    .new-btn {
        background-color: #fff;
        border-radius: 2px;
        width: 100%;
        height: 285px;
    }

    .ant-card-actions {
        height: 50px;
        margin: 0 0;
    }


    .card-avatar {
        width: 15px;
        height: 15px;
        border-radius: 48px;
        margin-right: -10px;
    }

    .start {
        background-color: #52C41A;
    }

    .end {
        background-color: red;
    }
</style>
