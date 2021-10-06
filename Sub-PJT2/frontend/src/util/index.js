import axios from 'axios'
import store from '@/vuex/store'

function createInstance() {
  return axios.create({
    baseURL: process.env.VUE_APP_API_URL,
    headers: {
      'Content-type': 'application/json',
      token: store.state.token,
      company: store.state.emailCompany,
    },
  })
}

export const instance = createInstance()
