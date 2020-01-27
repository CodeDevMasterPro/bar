import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css'
import axios from "axios";
import zhCN from 'ant-design-vue/lib/locale-provider/zh_CN';
import Moment from 'moment'

Vue.config.productionTip = false;
Vue.use(Antd);
Vue.prototype.$zh = zhCN
Vue.prototype.$axios = axios;
Vue.prototype.$moment = Moment,
    new Vue({
        render: h => h(App),
        router,
    }).$mount('#app');
