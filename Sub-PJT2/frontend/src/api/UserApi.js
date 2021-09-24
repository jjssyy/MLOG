import http from '@/util/http-common'

const initProfile = (data, callback, errorCallback) => {
  http
    .put('/member/' + data.uid, {
      params: {
        nickname: data.nickname,
        file_path: data.file_path,
      },
    })
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const kakaoLogin = (data, callback, errorCallback) => {
  http
    .get('/member/kakao?code=' + data.code)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const googleLogin = (data, callback, errorCallback) => {
  http
    .get('/member/google/' + data.id_token)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const UserApi = {
  initProfile: (data, callback, errorCallback) =>
    initProfile(data, callback, errorCallback),
  kakaoLogin: (data, callback, errorCallback) =>
    kakaoLogin(data, callback, errorCallback),
  googleLogin: (data, callback, errorCallback) =>
    googleLogin(data, callback, errorCallback),
}

export default UserApi
