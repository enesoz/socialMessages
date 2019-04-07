import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
import {library} from '@fortawesome/fontawesome-svg-core'
import {faCoffee} from '@fortawesome/free-solid-svg-icons'

import Router from 'vue-router'
import axios from 'axios'
import router from './router'

library.add(faCoffee)

Vue.component('icon', FontAwesomeIcon)
Vue.use(BootstrapVue)
Vue.use(Router)
Object.defineProperty(Vue.prototype, '$axnpm ios', {value: axios});

Vue.config.productionTip = false

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
