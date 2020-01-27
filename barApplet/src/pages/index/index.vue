<template>
  <div>
    <div class="scroll">
      <swiper autoplay="true" indicator-color="white" indicator-dots="true">
        <swiper-item>
          <img src="../../images/title/title1.jpg">
        </swiper-item>
        <swiper-item>
          <img src="../../images/title/title2.jpg">
        </swiper-item>
        <swiper-item>
          <img src="../../images/title/title3.jpg">
        </swiper-item>
      </swiper>
    </div>
    <div>
      <van-row>
        <van-cell size="large" title="莱茵春天店"/>
      </van-row>
      <van-row>
        <van-cell @click="openMap" icon="location-o" is-link title="四川宜宾莱茵河畔666号" title-width="50%" value="地图"/>
      </van-row>
      <van-row>
        <van-cell icon="clock-o" title="营业时间" value="9:00-23:30"/>
      </van-row>
      <van-row>
        <van-cell @click="callPhone" data-tel='18384761' icon="phone-o" is-link title="联系电话" value="18384761"/>
      </van-row>
    </div>

    <div style="margin: 10px">
      <van-row span="24">
        <van-col offset="10" span="8"><span style="font-size: 18px">酒家推荐</span></van-col>
      </van-row>
    </div>
    <div>
      <van-row>
        <van-notice-bar
          left-icon="volume-o"
          mode="closeable"
          text="新店开业,全场9折,好酒好菜,尽在深夜酒吧.........................."
        />
      </van-row>

      <van-grid :border="true" column-num="2" quare="true">
        <van-grid-item :data-item="item" :key="item" @click="toDetails" use-slot v-for="item in hotList">
          <image
            :src="item.img"
            style="width: 100%; height:120px;"
          />
          <!-- <div style="margin-top: 10px;">
             <van-row>
               <span style="margin-top: 20px">{{item.name}}</span>
             </van-row>
             <van-row>
               <span style="color: red;margin-bottom: -20px">${{item.price}}</span>
             </van-row>
           </div>-->
        </van-grid-item>
      </van-grid>
      <van-divider contentPosition="center">没有更多了</van-divider>
    </div>

  </div>
</template>

<script>

  export default {
    data() {
      return {
        hotList: [{"id": 1, "img": "/static/images/hot/11.png", 'name': '鸡尾酒', 'price': 123, number: 1}, {
          "id": 2,
          "img": "/static/images/hot/22.png",
          'name': '五粮液',
          'price': 99.5,
          number: 1
        }, {"id": 3, "img": "/static/images/hot/33.png", 'name': '拉菲999', 'price': 333, number: 1}, {
          "id": 4,
          "img": "/static/images/hot/44.png",
          'name': '洋酒',
          'price': 100.99,
          number: 1
        }]
      }
    },
    methods: {
      //打开地图
      openMap(e) {
        const id = e.currentTarget.dataset.id
        const item = {}
        item['name'] = '宜宾'
        item['address'] = '宜宾地址'
        item['latitude'] = 30.13694
        item['longitude'] = 120.219925
        wx.openLocation({
          latitude: item.latitude,
          longitude: item.longitude,
          name: item.name,
          address: item.address
        })
      },
      //拨打电话
      callPhone(e) {
        console.log(e)
        const tel = e.currentTarget.dataset.tel
        wx.makePhoneCall({
          phoneNumber: tel
        })
      },
      toDetails(e) {
        let data = e.currentTarget.dataset.item
        wx.navigateTo({
          url: "../details/main?details=" + JSON.stringify(data)
        })
      }
    }
  }
</script>

<style scoped>
  .scroll swiper {
    width: 100%;
    height: 400 rpx;
  }

  .scroll swiper img {
    width: 100%;
    height: 100%;
  }
</style>
