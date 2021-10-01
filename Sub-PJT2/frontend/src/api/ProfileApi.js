import { instance } from '@/util/index'

function getMyDiary(data) {
  return instance.get(`/profile/${data['id']}/diary`)
}

function getMyPlaylist(data) {
  return instance.get(`/profile/${data['id']}/playlist`)
}

function getMyDiaryReport(data) {
  return instance.get(
    `/profile/${data['id']}/report/diary?start_date=${data['startDate']}&end_date=${data['endDate']}`,
  )
}

function getEmotionReport(data) {
  return instance.get(
    `/profile/${data['id']}/report/sentiment?start_date=${data['startDate']}&end_date=${data['endDate']}`,
  )
}

export { getMyDiary, getMyPlaylist, getMyDiaryReport, getEmotionReport }
