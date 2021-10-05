import { instance } from '@/util/index'

const getSurveyMusicList = (callback, errorCallback) => {
  instance
    .get('/survey')
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const enrollSurvey = (data, callback, errorCallback) => {
  instance
    .post(`/survey/${data['id']}`, data)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const resetSurvey = (data, callback, errorCallback) => {
  instance
    .post(`/survey/reset/${data['id']}`)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const SurveyApi = {
  getSurveyMusicList: (callback, errorCallback) =>
    getSurveyMusicList(callback, errorCallback),
  enrollSurvey: (data, callback, errorCallback) =>
    enrollSurvey(data, callback, errorCallback),
  resetSurvey: (data, callback, errorCallback) =>
    resetSurvey(data, callback, errorCallback),
}

export default SurveyApi
