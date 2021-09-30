import { instance } from '@/util/index'

const getSurveyMusicList = (callback, errorCallback) => {
  instance
    .get('/survey')
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const enrollSurvey = (data, callback, errorCallback) => {
  instance
    .put(`/survey/${data['id']}`, {
      params: {
        neutralList: data.neutral,
        joyList: data.joy,
        sadnessList: data.sadness,
        angerList: data.anger,
        fearList: this.fear,
      },
    })
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const SurveyApi = {
  getSurveyMusicList: (callback, errorCallback) =>
    getSurveyMusicList(callback, errorCallback),
  enrollSurvey: (data, callback, errorCallback) =>
    enrollSurvey(data, callback, errorCallback),
}

export default SurveyApi
