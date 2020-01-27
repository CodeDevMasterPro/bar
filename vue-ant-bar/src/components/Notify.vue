<template>
    <div class="item">
        <a-popover placement="bottomRight" trigger="click">
            <template slot="content">
                <a-spin :spinning="loadding">
                    <a-tabs>
                        <a-tab-pane key="1" tab="通知">
                            <a-list>
                                <a-list-item>
                                    <a-list-item-meta description="一天前" title="你收到了 1 份新周报">
                                        <a-avatar slot="avatar"
                                                  src="https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png"
                                                  style="background-color: white"/>
                                    </a-list-item-meta>
                                </a-list-item>
                                <a-list-item>
                                    <a-list-item-meta description="一天前" title="你的申请已通过!">
                                        <a-avatar slot="avatar"
                                                  src="https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png"
                                                  style="background-color: white"/>
                                    </a-list-item-meta>
                                </a-list-item>
                                <a-list-item>
                                    <a-list-item-meta description="一天前" title="你发起的流程被打回!">
                                        <a-avatar slot="avatar"
                                                  src="https://gw.alipayobjects.com/zos/rmsportal/kISTdvpyTAhtGxpovNWd.png"
                                                  style="background-color: white"/>
                                    </a-list-item-meta>
                                </a-list-item>
                            </a-list>
                        </a-tab-pane>
                        <a-tab-pane key="2" tab="消息">
                            123
                        </a-tab-pane>
                        <a-tab-pane key="3" tab="待办">
                            123
                        </a-tab-pane>
                    </a-tabs>
                </a-spin>
            </template>
            <span @click="fetchNotice" class="header-notice">
      <a-badge count="12">
        <a-icon style="color:white; font-size: 24px;" type="bell"/>
      </a-badge>
    </span>
        </a-popover>
    </div>
</template>

<script>
    export default {
        name: "Notify",
        data() {
            return {
                loadding: false,
            };
        },
        methods: {
            fetchNotice() {
                if (this.loadding) {
                    this.loadding = false;
                    return;
                }
                this.loadding = true;
                setTimeout(() => {
                    this.loadding = false;
                }, 2000);
            }
        },
    }

</script>

<style scoped>

</style>