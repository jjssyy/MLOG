import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import routes from './routes'
import store from './vuex/store'
import Donut from 'vue-css-donut-chart'

Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(Donut)

const router = new VueRouter({
  routes,
  mode: 'history',
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
