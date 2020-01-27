<template>
  <div class="indexContainer">
    <img :src="userInfo.avatarUrl" alt="" v-show="isShow">
    <button @getuserinfo="handleGetUserInfo" class="btn" open-type="getUserInfo" v-show="!isShow">获取用户信息</button>
    <p>hello {{userInfo.nickName}}</p>
    <button @tap="toDetail">开启酒与故事之旅</button>
  </div>

</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        isShow: false,
        userInfo: {}

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
        const that = this
        wx.getUserInfo({
          success: function (res) {
            that.isShow = true;
            that.userInfo = res.userInfo
            /*            var nickName = userInfo.nickName
                        var avatarUrl = userInfo.avatarUrl
                        var gender = userInfo.gender //性别 0：未知、1：男、2：女
                        var province = userInfo.province
                        var city = userInfo.city
                        var country = userInfo.country*/
          }
        })
      },
      toDetail() {
        console.log('xxx');
        wx.switchTab({
          url: '../index/main'
        })
      }

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
    width: 200 rpx;
    height: 200 rpx;
    border-radius: 100 rpx;
    margin: 100 rpx 0;
  }

  .btn {
    width: 300 rpx;
    height: 300 rpx;
    border-radius: 150 rpx;
    margin: 100 rpx 0;
    line-height: 300 rpx;
  }

  .indexContainer p {
    font-size: 40 rpx;
    font-weight: bold;
    margin: 100 rpx 0;
  }

  .indexContainer button {
    background: #009ee0;
    font-size: 32 rpx;
  }

</style>
