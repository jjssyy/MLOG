import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import routes from './routes'
import store from './vuex/store'
import VueYoutube from 'vue-youtube'
import Donut from 'vue-css-donut-chart'
import VueSweetalert2 from 'vue-sweetalert2'
import 'sweetalert2/dist/sweetalert2.min.css'

Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(VueYoutube)
Vue.use(Donut)
Vue.use(VueSweetalert2)
const router = new VueRouter({
  routes,
  mode: 'history',
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
