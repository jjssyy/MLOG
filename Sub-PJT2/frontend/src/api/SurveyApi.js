import http from '@/util/http-common'

const getSurveyMusicList = (callback, errorCallback) => {
  http
    .get('/survey')
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const SurveyApi = {
  getSurveyMusicList: (data, callback, errorCallback) =>
    getSurveyMusicList(data, callback, errorCallback),
}

export default SurveyApi
