import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import search from '@/components/search'
import axios from 'axios'

Vue.use(Router)
Object.defineProperty(Vue.prototype, '$axnpm ios', {value: axios});

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/search',
      name: 'search',
      component: search
    }
  ]
})
