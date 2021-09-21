export default {
  LOGOUT: function(state) {
    state.authToken = ''
    state.uid = ''
    state.filePath = ''
    state.nickname = ''
    state.email = ''
    state.emailCompany = ''
    state.hasSurveyed = false
  },
}
