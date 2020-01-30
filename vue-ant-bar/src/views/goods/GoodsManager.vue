<template>
    <div>
        <a-row style="margin-bottom: 10px">
            <a-col :span="8">
                <a-input-search @search="onSearch" enterButton placeholder="请输入商品名称" style="width: 200px"/>
            </a-col>
            <a-col :span="8">
                <a-input-search @search="onSearch" enterButton placeholder="请输入库存临界值" style="width: 200px"/>
            </a-col>
            <a-col :span="8">
                <a-select
                        @change="selectSearch"
                        labelInValue
                        style="width: 200px"
                >
                    <a-icon slot="suffixIcon" type="search"/>
                    <a-select-option value="1">热门</a-select-option>
                    <a-select-option value="0">非热门</a-select-option>
                </a-select>
            </a-col>
        </a-row>

        <div class="operate">
            <a-button @click="add()" icon="plus" style="width: 100%" type="dashed">添加</a-button>
        </div>
        <a-table :columns="columns" :dataSource="data" @change="handleTableChange" bordered size="small">
            <template
                    slot="mainImageUrl"
                    slot-scope="text, record, index"
            >
                <div class="clearfix" id="mainImageUrl">
                    <a-upload
                            :fileList="fileList"
                            @change="handleChanges"
                            @preview="handlePreview"
                            action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                            listType="picture-card"
                            v-if="record.editable"
                    >
                        <div v-if="fileList.length < 3">
                            <a-icon type="plus"/>
                            <div class="ant-upload-text">Upload</div>
                        </div>
                    </a-upload>
                    <template v-else>
                        <div>
                            <img :src="text" height="55px" width="60px">
                            <a-divider type="vertical"/>
                            <img :src="text" height="55px" width="60px">
                        </div>
                    </template>
                    <a-modal :footer="null" :visible="previewVisible" @cancel="handleCancel">
                        <img :src="previewImage" alt="example" style="width: 100%"/>
                    </a-modal>

                </div>
            </template>

            <template
                    slot="imageUrl"
                    slot-scope="text, record, index"
            >
                <div class="clearfix" id="imageUrl">
                    <a-upload
                            :beforeUpload="beforeUpload"
                            :fileList="fileList"
                            @change="handleChanges"
                            @preview="handlePreview"
                            action="https:localhost:8888/upload"
                            listType="picture-card"
                            v-if="record.editable"
                    >
                        <div v-if="fileList.length < 3">
                            <a-icon type="plus"/>
                            <div class="ant-upload-text">Upload</div>
                        </div>
                    </a-upload>
                    <template v-else>
                        <div>
                            <img :src="text" height="55px" width="60px">
                            <a-divider type="vertical"/>
                            <img :src="text" height="55px" width="60px">
                        </div>
                    </template>
                    <a-modal :footer="null" :visible="previewVisible" @cancel="handleCancel">
                        <img :src="previewImage" alt="example" style="width: 100%"/>
                    </a-modal>

                </div>
            </template>

            <template
                    slot="isHot"
                    slot-scope="text, record, index"
            >
                <div id="isHot">
                    <a-select
                            :defaultValue="{ key: `${text}` }"
                            @change="e => handleChange(e, record.id, 'isHot')"
                            labelInValue
                            style="width: 120px"
                            v-if="record.editable"
                    >
                        <a-select-option value="1">热门</a-select-option>
                        <a-select-option value="0">非热门</a-select-option>
                    </a-select>
                    <template v-else>{{text==1?"热门":"非热门"}}</template>
                </div>
            </template>

            <template
                    :slot="col"
                    slot-scope="text, record, index"
                    v-for="col in [ 'name', 'price','store', 'description']">
                <div :key="col">
                    <a-input
                            :value="text"
                            @change="e => handleChange(e.target.value, record.id, col)"
                            style="margin: -5px 0"
                            v-if="record.editable"
                    />
                    <template v-else>{{text}}</template>
                </div>
            </template>


            <template slot="operation" slot-scope="text, record">
                <a-popconfirm
                        @confirm="() => onDelete(record.id)"
                        title="确定删除吗?"
                        v-if="data.length"
                >
                    <a href="javascript:;">删除</a>
                </a-popconfirm>

                <a-divider type="vertical"/>

                <span v-if="record.editable">
          <a @click="() => save(record.id)">保存</a>
             <a-divider type="vertical"/>
          <a-popconfirm @confirm="() => cancel(record.id)" title="确定取消吗?">
            <a>取消</a>
          </a-popconfirm>
        </span>
                <span v-else>
          <a @click="() => edit(record.id)">编辑</a>
        </span>
            </template>
        </a-table>
    </div>
</template>

<script>
    export default {
        name: "GoodsManager",
        data() {
            return {
                data: [],
                previewVisible: false,
                previewImage: '',
                fileList: [
                    {
                        uid: '-1',
                        name: 'xxx.png',
                        status: 'done',
                        url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
                    },
                ],
                columns: [
                    {
                        title: 'id',
                        dataIndex: 'id',
                    },
                    {
                        title: 'name',
                        dataIndex: 'name',
                        scopedSlots: {customRender: 'name'},
                    },
                    {
                        title: 'price',
                        dataIndex: 'price',
                        scopedSlots: {customRender: 'price'},
                    },
                    {
                        title: 'store',
                        dataIndex: 'store',
                        scopedSlots: {customRender: 'store'},
                    },
                    {
                        title: 'isHot',
                        dataIndex: 'isHot',
                        scopedSlots: {customRender: 'isHot'},

                    },
                    {
                        title: 'description',
                        dataIndex: 'description',
                        scopedSlots: {customRender: 'description'},

                    },
                    {
                        title: 'mainImageUrl',
                        dataIndex: 'mainImageUrl',
                        width: 200,
                        scopedSlots: {customRender: 'mainImageUrl'},

                    },
                    {
                        title: 'imageUrl',
                        dataIndex: 'imageUrl',
                        width: 200,
                        scopedSlots: {customRender: 'imageUrl'},

                    },
                    {
                        title: 'operation',
                        dataIndex: 'operation',
                        width: 200,
                        scopedSlots: {customRender: 'operation'},
                    },
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
            handleChanges(data) {
                console.log(data)
                this.fileList = data.fileList;
            },


            beforeUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                if (!isJPG) {
                    this.$message.error('You can only upload JPG file!');
                }
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isLt2M) {
                    this.$message.error('Image must smaller than 2MB!');
                }
                return isJPG && isLt2M;
            },

            //删除
            onDelete(id) {
                const dataSource = [...this.data];
                this.data = dataSource.filter(item => item.id !== id);
            },
            //添加
            handleAdd() {
                const {count, dataSource} = this;
                const newData = {
                    id: count,
                    name: `Edward King ${count}`,
                    age: 32,
                    address: `London, Park Lane no. ${count}`,
                };
                this.dataSource = [...dataSource, newData];
                this.count = count + 1;
            },

            //修改赋值
            handleChange(value, id, column) {
                if (column == 'isHot') {
                    value = value.key;
                }
                const newData = [...this.data];
                const target = newData.filter(item => id === item.id)[0];
                if (target) {
                    target[column] = value;
                    this.data = newData;
                }
            },

            //编辑
            edit(id) {
                const newData = [...this.data];
                const target = newData.filter(item => id === item.id)[0];
                if (target) {
                    target.editable = true;
                    this.data = newData;
                }
            },

            //保存
            save(id) {
                const newData = [...this.data];
                const target = newData.filter(item => id === item.id)[0];
                if (target) {
                    delete target.editable;
                    this.data = newData;
                    this.cacheData = newData.map(item => ({...item}));
                }
            },

            //取消
            cancel(id) {
                const newData = [...this.data];
                const target = newData.filter(item => id === item.id)[0];
                if (target) {
                    Object.assign(target, this.data.filter(item => id === item.id)[0]);
                    delete target.editable;
                    this.data = newData;
                }
            },

            //分页
            handleTableChange(pagination, filters) {
                console.log(pagination);
                const pager = {...this.pagination};
                pager.current = pagination.current;
                this.pagination = pager;
                this.fetch({
                    results: pagination.pageSize,
                    page: pagination.current,
                    ...filters,
                });
            },
            fetch(params = {}) {
                console.log('params:', params);
                this.loading = true;
                this.$axios.get("/get", params).then((res) => {
                    console.log(res)
                })
                /* reqwest({
                     url: 'https://randomuser.me/api',
                     method: 'get',
                     data: {
                         results: 10,
                         ...params,
                     },
                     type: 'json',
                 }).then(data => {
                     const pagination = { ...this.pagination };
                     // Read total count from server
                     // pagination.total = data.totalCount;
                     pagination.total = 200;
                     this.loading = false;
                     this.data = data.results;
                     this.pagination = pagination;
                 });*/
            },
            onSearch(value) {
                console.log(value)
            },
            add() {
                const length = this.data.length
                const tempData = [];
                tempData.push(
                    {
                        id: length === 0 ? '1' : (parseInt(this.data[length - 1].id) + 1).toString(),
                        name: '',
                        price: '',
                        store: '',
                        isHot: '',
                        mainImageUrl: '',
                        imageUrl: '',
                        description: '',
                        editable: true,
                    }
                )
                this.data = tempData.concat(this.data)
            },
            selectSearch(value) {
                console.log(value)
            }
        },
        created() {
            const data = [];
            for (let i = 0; i < 100; i++) {
                data.push({
                    id: i.toString(),
                    name: `Edrward ${i}`,
                    price: 32,
                    store: i,
                    isHot: i % 2 == 0 ? 1 : 0,
                    mainImageUrl: 'https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png',
                    imageUrl: 'https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png',
                    description: `London Park no. ${i}`,
                });
            }
            this.data = data;
        }
    }
</script>

<style scoped>
    .ant-table-wrapper >>> .ant-upload {
        width: 58px;
        height: 48px;
        padding: 0;
        margin: 0;
    }

    .ant-table-wrapper >>> .ant-upload-list-item {
        width: 58px;
        height: 48px;
        padding: 0;
        margin: 0;
        margin-right: 5px;
    }
</style>

