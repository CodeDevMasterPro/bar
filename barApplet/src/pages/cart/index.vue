<template>
  <div v-if="shopList.length>0">
    <van-notify id="van-notify"></van-notify>
    <van-row>
      <van-col :span="12"><span style="padding-left: 10px">您选购的商品</span></van-col>
      <van-col :offset="9" :span="3"><span><a @click="edit"><span v-if="isEdit">编辑</span><span
        v-else>完成</span></a></span></van-col>
    </van-row>


    <van-checkbox-group :value="result" @change="change">
      <div>
        <van-row :key="item.id" v-for="item in shopList">
          <div style="float: left;margin-top:45px;padding-left: 9px">
            <van-checkbox :name="item.id"></van-checkbox>
          </div>
          <div style="width: 90%;float: right">
            <van-card
              :desc="item.name"
              :num="item.number"
              :origin-price="item.price*2"
              :price="item.price"
              :thumb="item.img"
              :title="item.name"
              thumb-mode="aspectFill"
            >
              <view slot="footer">
                <van-stepper :data-goods_id="item.id" :value="item.number" @change="onChange" @minus="item.number"
                             @plus="item.number" disable-input/>
              </view>
            </van-card>
          </div>
        </van-row>
      </div>
    </van-checkbox-group>

    <van-row>
      <van-submit-bar :price="totalMoney"
                      @submit="pay"
                      button-text="提交订单"
                      v-if="isEdit"
      >
        <view slot="-">
          您的收货地址不支持同城送,
          <text>修改地址</text>
        </view>
        <van-checkbox :value="checked" @change="changes" style="margin-left:5px">全选</van-checkbox>
      </van-submit-bar>
      <van-submit-bar @submit="onClickButton"
                      button-text="删除"
                      v-else
      >
        <view slot="-">
          您的收货地址不支持同城送,
          <text>修改地址</text>
        </view>
        <van-checkbox :value="checked" @change="changes" style="margin-right: 180px">全选</van-checkbox>
      </van-submit-bar>
    </van-row>
  </div>
  <div v-else>
    <van-row>
      <van-col :offset="10" :span="8">
        <div style="height:80px;width:80px;margin-top: 100px;margin-bottom: 20px;">
          <img src="/static/images/cart/icon-cart.png" style="height: 100%;width: 100%">
        </div>
      </van-col>
    </van-row>
    <van-row>
      <van-col :offset="9" :span="8">
        <div style="margin-bottom: 20px"><span style="font-size: 15px;color: gainsboro">购物车空空如也～</span></div>
      </van-col>
    </van-row>
    <van-row>
      <van-col :offset="4" :span="16">
        <van-button @click="goShop" size="large" type="danger">去选购美酒</van-button>
      </van-col>
    </van-row>
  </div>
</template>
<script>
  import Notify from "../../../static/vant/dist/notify/notify";

  export default {
    data() {
      return {
        shopList: [],
        shopCarInfo: {shopList: [], shopNumber: 0},//购物车信息
        is: true,
        totalMoney: 0,
        result: [],
        checked: false,
        isEdit: true,//控制是否显示删除
      }
    },
    methods: {
      //单选
      change(event) {
        this.result = event.mp.detail
        this.result.length != this.shopList.length ? this.checked = false && this.compute() : this.checked = true && this.compute()
        this.compute();

      },
      //全选
      changes() {
        //this.checked=event.mp.detail
        this.checked = !this.checked
        if (this.checked) {
          this.totalMoney = 0;
          this.result = []
          this.shopList.forEach(item => {
            this.result.push(`${item.id}`);
            this.totalMoney += item.number * item.price * 100;
          })
        } else {
          this.result = []
          this.totalMoney = 0
        }
      },
      //付款确认
      pay() {
        const data = []
        this.result.forEach(id => {
          this.shopList.filter(item => {
            if (id == item.id) {
              data.push(item)
            }
          })
        })
        if (data.length == 0) {
          Notify({type: 'danger', message: '提交订单未包含任何商品', duration: 1000});
        } else {
          wx.navigateTo({
            url: "../pay/main?shopList=" + JSON.stringify(data) + '&totalMoney=' + this.totalMoney
          })
        }
      },

      //价格计算
      compute() {
        this.totalMoney = 0
        this.result.forEach(item => {
          this.shopList.filter(a => {
            if (item == a.id) {
              this.totalMoney += a.price * a.number * 100
            }
          })
        })
      },
      edit() {
        this.isEdit = !this.isEdit
        if (!this.isEdit) {
          this.checked = false;
          this.result = []
        }
      },

      //删除
      onClickButton() {
        this.result.forEach(item => {
          this.shopList = this.shopList.filter(a => item != a.id)
        })
        this.shopCarInfo.shopList = this.shopList
        // 写入本地存储
        wx.setStorage({
          key: 'shopCarInfo',
          data: this.shopCarInfo
        })
        wx.showToast({
          title: '删除成功',
          icon: 'success',
        })
        this.compute()
      },

      //加减
      onChange(e) {
        const id = e.currentTarget.dataset.goods_id;
        const number = e.mp.detail
        this.shopList.filter(a => {
          if (a.id == id) {
            a.number = number;
            this.compute();
          }

        })

      },

      goShop() {
        wx.reLaunch({
          url: "../menu/main"
        });
      },
      setStorage() {
        const shop = wx.getStorageSync('shopCarInfo');
      }
    },
    create() {
      this.onLoad()
    },
    onLoad: function () {

      // 获取购物车数据
      const shopCarInfoMem = wx.getStorageSync('shopCarInfo');
      if (shopCarInfoMem && shopCarInfoMem.shopList) {
        this.shopList = shopCarInfoMem.shopList
      }
      /* //计算总金额
       this.totalMoney=0
       this.shopList.forEach(item=>{
           this.totalMoney+=item.price*item.number*100
       })*/
      this.changes();
    },
  }
</script>
<style scoped>

</style>

