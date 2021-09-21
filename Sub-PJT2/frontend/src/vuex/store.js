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
  auth_token: '',
  uid: '',
  file_path: '',
  nickname: '',
  email: '',
  email_company: '',
  has_surveyed: false,
}

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
})
