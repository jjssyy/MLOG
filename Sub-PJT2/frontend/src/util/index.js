import axios from 'axios'

function createInstance() {
  return axios.create({
    baseURL: process.env.VUE_APP_API_URL,
    headers: {
      'Content-type': 'application/json',
    },
  })
}

export const instance = createInstance()
