import { instance } from '@/util/index'

function getSurveyMusicList() {
  return instance.get(`/survey`)
}

function enrollSurvey(data) {
  return instance.post(`/survey/${data['id']}`)
}

export { getSurveyMusicList, enrollSurvey }
