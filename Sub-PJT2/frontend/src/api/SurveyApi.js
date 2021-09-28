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
        survey_list: data.survey_list,
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
