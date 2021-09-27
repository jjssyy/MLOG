import { instance } from '@/util/index'

// 총 일기 작성글 수 조회 API
function fetchTotalCnt(data) {
  return instance.get(`/main/${data['id']}/totalcnt`)
}

// 해당 월의 차트 조회 API
function fetchChart(data) {
  return instance.get(`/main/${data['id']}/${data['month']}/chart`)
}

// 해당 월의 일기 정보 조회 API
function fetchMonthDiary(data) {
  return instance.get(`/main/${data['id']}/${data['month']}`)
}

export { fetchTotalCnt, fetchChart, fetchMonthDiary }
