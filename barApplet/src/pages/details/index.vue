<template>
  <div>
    <van-notify id="van-notify"></van-notify>
    <div>
      <div style="margin: 0 0 10px 20px">
        <van-row><span style="font-size: 20px">{{details.name}}</span></van-row>
      </div>
      <div style="width:100%;height:200px;">
        <van-image
          :src="details.img"
          height="100%"
          width="100%"
        />
      </div>
    </div>

    <div style="margin:10px 20px">
      <van-row>
        <van-col span="12"><span style="color: red;line-height: 28px;">${{details.price}}</span></van-col>
        <van-col span="12"><span style="text-align: right;"><van-stepper :value="1" @change="onChange"
                                                                         disable-input/></span></van-col>
      </van-row>
    </div>

    <van-row>
      <p>
        我们应该都还记得前段时间我在音乐酒吧醉生梦死的经历吧，现在想想还很怀念呢！不得不说喝酒真的是一件美好的事情呀～</p>
    </van-row>

    <van-row>

      <van-goods-action>
        <van-goods-action-icon dot icon="chat-o" text="客服"/>
        <van-goods-action-icon :info="shopNumber" @click="goShopCar" icon="cart-o" text="购物车"/>
        <van-goods-action-button @click="addCar" text="加入购物车" type="warning"/>
        <van-goods-action-button @click="goPay" text="立即购买"/>
      </van-goods-action>
    </van-row>
  </div>
</template>
<script>
  import Notify from "../../../static/vant/dist/notify/notify";

  export default {
    data() {
      return {
        details: {},
        number: 1,
        shopList: [],//购物车中商品集合
        shopCarInfo: {shopList: [], shopNumber: 0},//购物车信息
        shopNumber: 0,//购物车商品数量
        totalMoney: 0,
      }
    },
    methods: {
      onChange(val) {
        console.log(val)
        this.number = val.mp.detail
      },
      addCar() {
        //  this.details.number=this.number
        if (this.shopList.length == 0) {
          console.log("00")
          this.details.number = this.number;
          this.shopList.push(this.details)
          this.compute();
        } else {
          console.log(">000")
          this.shopList.forEach(item => {
            if (item.id == this.details.id) {
              console.log("aaa")
              item.number += this.number
            }
          })

          const data = this.shopList.filter(item => item.id == this.details.id)
          if (data.length == 0) {
            this.details.number = this.number;
            this.shopList.push(this.details)
          }
          /* const data=  this.shopList.filter(a => a.id==this.details.id)
             this.details.number=this.number;
             data.length==0?this.shopList.push(this.details):''*/
          this.compute();
        }

        this.shopCarInfo.shopList = this.shopList
        // 写入本地存储
        wx.setStorage({
          key: 'shopCarInfo',
          data: this.shopCarInfo
        })
        /* wx.showToast({
             title: '加入购物车成功',
             icon: 'success',
         })*/
        Notify({type: 'success', message: '加入购物车成功', duration: 1000});
      },

      compute() {
        //计算购物车中商品数量
        this.shopNumber = 0
        this.shopList.forEach(b => this.shopNumber += b.number)
        this.shopCarInfo.shopNumber = this.shopNumber
      },

      goPay() {
        this.details.number = this.number
        this.totalMoney += this.details.number * this.details.price * 100
        const data = []
        data.push(this.details)
        wx.navigateTo({
          url: "../pay/main?shopList=" + JSON.stringify(data) + '&totalMoney=' + this.totalMoney
        });
      },
      //去购物车
      goShopCar() {
        wx.reLaunch({
          url: "../cart/main"
        });
      },
    },
    onLoad: function (options) {
      this.details = {}
      this.number = 1;
      this.details = JSON.parse(options.details)

      //获取购物车数据
      const that = this
      wx.getStorage({
        key: 'shopCarInfo',
        success: function (res) {
          that.shopList = res.data.shopList
          that.shopCarInfo.shopList = that.shopList
          that.compute()
        }
      })


    },
  }
</script>
<style scoped>

</style>

