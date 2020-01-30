<template>
    <div>
        <a-list :pagination="{showSizeChanger: true, showQuickJumper: true, pageSize: 5, total: 50}" size="large">
            <a-list-item :key="index" v-for="(item, index) in data">
                <a-list-item-meta :description="item.description">
                    <a-avatar :src="item.avatar" shape="square" size="large" slot="avatar"/>
                    <a slot="title">{{ item.title }}</a>
                </a-list-item-meta>
                <div slot="actions">
                    <a>编辑</a>
                </div>
                <div slot="actions">
                    <a-dropdown>
                        <a-menu slot="overlay">
                            <a-menu-item><a>编辑</a></a-menu-item>
                            <a-menu-item><a>删除</a></a-menu-item>
                        </a-menu>
                        <a>更多
                            <a-icon type="down"/>
                        </a>
                    </a-dropdown>
                </div>
                <div class="list-content">
                    <div class="list-content-item">
                        <span>Owner</span>
                        <p>{{ item.owner }}</p>
                    </div>
                    <div class="list-content-item">
                        <span>开始时间</span>
                        <p>{{ item.startAt }}</p>
                    </div>
                    <div class="list-content-item">
                        <span>Owner</span>
                        <p>{{ item.owner }}</p>
                    </div>
                    <div class="list-content-item">
                        <span>开始时间</span>
                        <p>{{ item.startAt }}</p>
                    </div>
                    <div class="list-content-item">
                        <span>Owner</span>
                        <p>{{ item.owner }}</p>
                    </div>

                    <div class="list-content-item">
                        <a-progress :percent="item.progress.value"
                                    :status="!item.progress.status ? null : item.progress.status" style="width: 180px"/>
                    </div>
                </div>
            </a-list-item>
        </a-list>
    </div>
</template>

<script>
    const data = []
    data.push({
        title: 'Alipay',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png',
        description: '那是',
        owner: '付晓晓',
        startAt: '2018-07-26 22:44',
        progress: {
            value: 90
        }
    })
    data.push({
        title: 'Angular',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png',
        description: '希望是一个好东西，也许',
        owner: '曲丽丽',
        startAt: '2018-07-26 22:44',
        progress: {
            value: 54
        }
    })
    data.push({
        title: 'Ant Design',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png',
        description: '生命就像一盒巧克力，结果往往出人意料',
        owner: '林东东',
        startAt: '2018-07-26 22:44',
        progress: {
            value: 66
        }
    })
    data.push({
        title: 'Ant Design Pro',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png',
        description: '城镇中有那么多的酒馆的酒馆',
        owner: '周星星',
        startAt: '2018-07-26 22:44',
        progress: {
            value: 30
        }
    })
    data.push({
        title: 'Bootstrap',
        avatar: 'https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png',
        description: '那时候我只想自己拥有什么',
        owner: '吴加好',
        startAt: '2018-07-26 22:44',
        progress: {
            status: 'exception',
            value: 100
        }
    })

    export default {
        name: "content",
        data() {
            return {
                data,
            }
        }
    }
</script>

<style scoped>
    .ant-avatar-lg {
        width: 48px;
        height: 48px;
        line-height: 48px;
    }

    .list-content-item {
        color: rgba(0, 0, 0, .45);
        display: inline-block;
        vertical-align: middle;
        font-size: 14px;
        margin-left: 40px;

    span {
        line-height: 20px;
    }

    p {
        margin-top: 4px;
        margin-bottom: 0;
        line-height: 22px;
    }

    }
    .ant-list-item >>> .ant-list-item-meta {
        width: 150px
    }
</style>
