<template>
    <div class="card-list" ref="content">
        <a-list
                :dataSource="dataSource"
                :grid="{gutter: 24, lg: 3, md: 2, sm: 1, xs: 1}"
                rowKey="id"
        >
            <a-list-item slot="renderItem" slot-scope="item">
                <template v-if="!item || item.id === undefined">
                    <a-button @click="add" class="new-btn" type="dashed">
                        <a-icon type="plus"/>
                        新增产品
                    </a-button>
                </template>
                <template v-else>
                    <a-card :hoverable="true">
                        <a-card-meta>
                            <div slot="description" style="height: 190px">
                                <a-avatar class="card-avatar" size="large" slot="avatar" style="background: red"
                                          v-if="item.status==1||item.status==5"/>
                                <a-avatar class="card-avatar" size="large" slot="avatar" style="background: lightgreen"
                                          v-else/>
                                <img
                                        :src=item.img
                                        alt="example"
                                        height="100%"
                                        slot="cover"
                                        width="100%"
                                />
                            </div>
                        </a-card-meta>
                        <template class="ant-card-actions" slot="actions">
                            <a-popconfirm @confirm="del(item)" title="确定删除吗？">
                                <a-icon slot="icon" style="color: red" type="question-circle-o"/>
                                <a href="#">删除</a>
                            </a-popconfirm>
                            <a @click="edit(item)">编辑</a>
                            <a-switch @click="enabled(item)" checkedChildren="开" defaultChecked unCheckedChildren="关"/>
                            <!-- <a v-if="item.status==1||item.status==5" @click="enabled(item)">启用</a>
                             <a v-else @click="disable(item)">禁用</a>-->
                        </template>
                    </a-card>
                </template>
            </a-list-item>
        </a-list>

        <div>
            <a-modal @ok="handleOk" title="图片上传" v-model="visible">
                <div class="clearfix">
                    <a-upload
                            :fileList="fileList"
                            @change="handleChange"
                            @preview="handlePreview"
                            action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                            listType="picture-card"
                    >
                        <div v-if="fileList.length < 1">
                            <a-icon type="plus"/>
                            <div class="ant-upload-text">Upload</div>
                        </div>
                    </a-upload>
                    <a-modal :footer="null" :visible="previewVisible" @cancel="handleCancel">
                        <img :src="previewImage" alt="example" style="width: 100%;height:100%"/>
                    </a-modal>
                </div>
            </a-modal>
        </div>
    </div>
</template>

<script>
    const dataSource = []
    dataSource.push({})
    for (let i = 0; i < 8; i++) {
        dataSource.push({
            id: i,
            status: i,
            img: 'https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png'
        })
    }
    export default {
        name: 'Slideshow',
        data() {
            return {
                visible: false,
                extraImage: 'https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png',
                dataSource,
                previewVisible: false,
                previewImage: '',
                fileList: [
                    {
                        uid: '-1',
                        name: 'xxx.png',
                        status: 'done',
                        url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
                        thumbUrl:
                            'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
                    }
                ],
            }
        },
        methods: {
            //上传
            handleCancel() {
                this.previewVisible = false;
            },
            handlePreview(file) {
                this.previewImage = file.url || file.thumbUrl;
                this.previewVisible = true;
            },
            handleChange({fileList}) {
                this.fileList = fileList;
            },

            showModal() {
                this.visible = true;
            },
            handleOk(e) {
                console.log(e);
                this.visible = false;
            },

            del(item) {
                console.log(item)
            },
            edit(item) {
                console.log(item)
                this.showModal();
            },
            enabled(item) {
                console.log(item)
            },
            disable(item) {
                console.log(item)
            },
            add() {
                this.showModal();
            }
        }
    }
</script>

<style scoped>
    .new-btn {
        background-color: #fff;
        border-radius: 2px;
        width: 100%;
        height: 188px;
    }

    .ant-card-bordered >>> .ant-card-body {
        padding: 0;
    }


    .card-avatar {
        width: 20px;
        height: 20px;
        border-radius: 20px;
        position: absolute;
        top: 0;
    }
</style>


