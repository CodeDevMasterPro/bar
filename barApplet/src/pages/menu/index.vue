<template>
  <van-tree-select
    :items="items"
    :main-active-index="mainActiveIndex"
    @clickNav="onClickNav"
    height="450px"
  >
    <van-row :key="item.id" slot="content" v-for="item in sortGoods">
      <van-card
        :desc="item.name"
        :num="item.number"
        :origin-price="item.price*2"
        :price="item.price"
        :thumb="item.img"
        :title="item.name"
        @click="goDetails(item)"
        thumb-mode="aspectFill"

      >
      </van-card>
    </van-row>

  </van-tree-select>
</template>
<script>
  export default {
    data() {
      return {
        items: [],
        mainActiveIndex: 0,
        goods: [],
        sortGoods: [],

      }
    },
    methods: {
      goDetails(item) {
        console.log(item)
        wx.navigateTo({
          url: "../details/main?details=" + JSON.stringify(item)
        })
      },
      onClickNav(event) {
        event != 0 ? this.mainActiveIndex = event.mp.detail.index : ''
        this.sortGoods = this.goods.filter(item => item.sort == this.mainActiveIndex)
      },
    },

    onLoad: function () {
      this.goods = []
      this.items = []
      this.items.push({text: '啤酒', id: 1},
        {text: '红酒', id: 2, children: []},
        {text: '精酿', id: 3, children: []},
        {text: '鸡尾酒', id: 4, children: []},
        {text: '小吃', id: 5, children: []})

      this.goods.push({"id": 1, 'sort': 1, "img": "/static/images/hot/11.png", 'name': '鸡尾酒', 'price': 123, number: 1},
        {"id": 2, 'sort': 2, "img": "/static/images/hot/22.png", 'name': '五粮液', 'price': 99.5, number: 1},
        {"id": 4, 'sort': 4, "img": "/static/images/hot/44.png", 'name': '洋酒', 'price': 100.99, number: 1},
        {"id": 25, 'sort': 2, "img": "/static/images/hot/22.png", 'name': '五粮液', 'price': 99.5, number: 1},
        {"id": 7, 'sort': 4, "img": "/static/images/hot/44.png", 'name': '洋酒', 'price': 100.99, number: 1},
        {"id": 15, 'sort': 0, "img": "/static/images/hot/44.png", 'name': '洋酒', 'price': 100.99, number: 1},
        {"id": 6, 'sort': 3, "img": "/static/images/hot/33.png", 'name': '拉菲999', 'price': 333, number: 1},
        {"id": 8, 'sort': 3, "img": "/static/images/hot/33.png", 'name': '拉菲999', 'price': 333, number: 1},
        {"id": 9, 'sort': 3, "img": "/static/images/hot/44.png", 'name': '拉菲999', 'price': 333, number: 1},
        {"id": 10, 'sort': 3, "img": "/static/images/hot/33.png", 'name': '拉菲999', 'price': 333, number: 1},
        {"id": 11, 'sort': 3, "img": "/static/images/hot/11.png", 'name': '拉菲999', 'price': 333, number: 1},
        {"id": 12, 'sort': 3, "img": "/static/images/hot/22.png", 'name': '拉菲999', 'price': 333, number: 1},
        {"id": 13, 'sort': 3, "img": "/static/images/hot/33.png", 'name': '拉菲999', 'price': 333, number: 1},
        {"id": 14, 'sort': 3, "img": "/static/images/hot/22.png", 'name': '拉菲999', 'price': 333, number: 1},
      )
      this.onClickNav(0)
    },
  }
</script>
<style scoped>

  .element.style {
    height: 600px !important;
  }
</style>

