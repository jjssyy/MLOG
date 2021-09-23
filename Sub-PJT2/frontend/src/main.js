import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import routes from './routes'
import store from './vuex/store'
import VueYoutube from 'vue-youtube'

Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(VueYoutube)

const router = new VueRouter({
  routes,
  mode: 'history',
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
