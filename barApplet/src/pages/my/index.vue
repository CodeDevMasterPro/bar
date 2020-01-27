<template>
  <div>
    <div class="indexContainer">
      <img :src="userInfo.avatarUrl" alt="" v-show="isShow">
      <button @getuserinfo="handleGetUserInfo" class="btn" open-type="getUserInfo" v-show="!isShow">获取用户信息</button>
      <p>hello {{userInfo.nickName}}</p>
    </div>

    <div>
      <van-panel :key="item.id" desc="快速配送哦........." status="0089" title="取餐号" use-footer-slot="true"
                 v-for="item in shopList">
        <view>
          <van-cell title="订单编号" value="123659985245"/>
          <van-cell :value="item.totalMoney" title="金额"/>
          <van-cell :value="item.status" title="订单状态"/>
          <van-cell :value="item.time" title="下单时间"/>
        </view>
        <view>
          <van-cell title="商品列表"/>
          <van-row>
            <div>
              <van-card
                :desc="item.name"
                :num="item.number"
                :origin-price="item.price*2"
                :price="item.price"
                :thumb="item.img"
                :title="item.name"
                thumb-mode="aspectFill"
              >
              </van-card>
            </div>
          </van-row>
        </view>

        <view slot="footer" style="margin-left: 190px">
          <van-button size="small">取消订单</van-button>
          <van-button size="small" type="danger"><span>{{item.status=="未付款"?'立即付款':'未付款'}}</span></van-button>
        </view>
        <!--<view slot="footer" >
          <van-divider  hairline customStyle="color:#ccc;border-color:#ccc;"></van-divider>
        </view>-->

        <!--  <view slot="footer" style="margin-left: 208px">
            <van-button size="small">已完成</van-button>
            <van-button size="small" type="danger">删除订单</van-button>
          </view>-->
      </van-panel>
    </div>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        totalMoney: 123,
        isGet: true,
        isShow: false,
        userInfo: {},
        shopList: [{
          "id": 1,
          "img": "/static/images/hot/11.png",
          'name': '鸡尾酒',
          'price': 123,
          number: 1,
          'totalMoney': 123,
          'time': '2019-12-08',
          'status': '未付款'
        },
          {
            "id": 2,
            "img": "/static/images/hot/22.png",
            'name': '五粮液',
            'price': 99.5,
            number: 1,
            'totalMoney': 200,
            'time': '2019-12-08',
            'status': '已付款'
          }]

      }
    },
    beforeMount() {
      console.log('组件挂载之前')
      // this.getUserInfo()
      this.getUserInfo();
    },

    methods: {
      getUserInfo(e) {
        console.log(e)
      },
      handleGetUserInfo(msg) {
        console.log(this);
        console.log(msg);
        if (msg.mp.detail.rawData) {
          this.getUserInfo();
        }
      },
      getUserInfo() {
        // 获取用户信息
        wx.getUserInfo({
          success: (data) => {
            this.isShow = true;
            this.userInfo = data.userInfo
          },
          fail: () => {

          }
        })
      },
    }
  }
</script>

<style scoped>
  page {
    background: gray;
    height: 100%;
  }

  .indexContainer {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .indexContainer img {
    width: 150 rpx;
    height: 150 rpx;
    border-radius: 100 rpx;
    margin: 50 rpx 0;
  }

  .btn {
    width: 300 rpx;
    height: 300 rpx;
    border-radius: 150 rpx;
    margin: 100 rpx 0;
    line-height: 300 rpx;
  }

  .indexContainer p {
    font-size: 30 rpx;
    font-weight: bold;
    margin: 20 rpx 0;
  }

</style>
