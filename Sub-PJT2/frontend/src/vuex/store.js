import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
  isUser: false,
  initNickname: '',
  initProfileimg: '',
  token: '',
  uid: '',
  filePath: '',
  nickname: '',
  email: '',
  emailCompany: '',
  hasSurveyed: false,
}

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
})
