import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from "./views/HelloWorld";
import Search from "./views/Search";

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/about',
            name: 'about',
            // route level code-splitting
            // this generates a separate chunk (about.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
        },
        {
            path: '/',
            name: 'HelloWorld',
            component: HelloWorld
        },
        {
            path: '/search',
            name: 'search',
            component: Search
        }
    ]
})
