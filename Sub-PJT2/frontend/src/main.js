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

router.beforeEach((to, from, next) => {
  console.log(from)
  if (to.meta.auth && !store.state.token) {
    next('/')
    return
  } else if (!to.meta.auth && store.state.token) {
    router.push('/main')
  } else {
    next()
  }
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
