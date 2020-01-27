<template>
  <div>
    <van-dialog id="van-dialog"/>
    <van-row><span style="margin-left: 5px;margin-bottom: 5px">商品列表</span></van-row>
    <van-row :key="item.id" v-for="item in shopList">
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

    <van-row>
      <van-submit-bar
        :price="totalMoney"
        @submit="onClickButton"
        button-text="提交订单">

      </van-submit-bar>
    </van-row>
    <van-row>
      <van-cell-group style="margin-left: 5px;margin-top: 5px">
        <van-field
          @blur="blur"
          autosize
          label="备注"
          placeholder="如需备注请输入..."
          type="textarea"
        />
      </van-cell-group>
    </van-row>
  </div>
</template>

<script>
  import Dialog from "../../../static/vant/dist/dialog/dialog";

  export default {
    name: "pay",
    data() {
      return {
        message: '',
        totalMoney: 0,
        shopList: [],
        shopCarInfo: {shopList: [], shopNumber: 0}

      }
    },
    methods: {
      blur(e) {
        this.message = e.mp.detail.value
        console.log(e.mp.detail.value)
      },
      onClickButton() {
        Dialog.confirm({
          title: '请确认支付',
          message: '订单金额: ' + this.totalMoney + '元',
          asyncClose: true
        })
          .then(() => {
            setTimeout(() => {
              Dialog.close();
            }, 1000);
            const that = this
            wx.getStorage({
              key: 'shopCarInfo',
              success: function (res) {
                const data = []

                that.shopList.forEach(item => {
                  res.data.shopList.forEach(a => {
                    if (item.id != a.id) {
                      data.push(a)
                    }
                  })
                })
                that.shopCarInfo.shopList = data
                that.shopCarInfo.totalMoney = that.totalMoney
                // 写入本地存储
                wx.setStorage({
                  key: 'shopCarInfo',
                  data: that.shopCarInfo
                })
              }
            })
          })
          .catch(() => {
            Dialog.close();
          });
      },
    },
    onLoad: function (options) {
      console.log(options)
      this.shopList = JSON.parse(options.shopList)
      this.totalMoney = Number(options.totalMoney)

    }

  }
</script>

<style scoped>

</style>
