import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import Router from 'vue-router'
import axios from 'axios'
import router from './router'

Vue.use(BootstrapVue)
Vue.use(Router)
Object.defineProperty(Vue.prototype, '$axnpm ios', {value: axios});

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
