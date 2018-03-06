import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false

window.Vue = Vue;

Object.defineProperty(Vue.prototype, '$axios', {value: axios});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
