export default {
  LOGOUT: function(state) {
    state.auth_token = ''
    state.uid = ''
    state.file_path = ''
    state.nickname = ''
    state.email = ''
    state.email_company = ''
    state.has_surveyed = false
  }
}