import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'vue-awesome/icons/'
import Icon from 'vue-awesome/components/Icon'

Vue.config.productionTip = false

window.Vue = Vue;

Object.defineProperty(Vue.prototype, '$axios', {value: axios});


Vue.component('icon', Icon)

Vue.use(BootstrapVue)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
