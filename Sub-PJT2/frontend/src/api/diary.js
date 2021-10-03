import { instance } from '@/util/index'

// 일기 작성 API
function createDiary(uid, data) {
  return instance.post(
    `/diary/${uid}?content=${data.content}&date=${data.date}`,
  )
}

// 특정 일기 불러오기 API
function fetchDiary(uid, data) {
  return instance.get(`/diary/${uid}/${data['date']}`)
}

// 특정 일기 수정하기 API
function updateDiary(data) {
  return instance.put(
    `/diary/${data['diary_id']}/edit?content=${data['content']}&date=${data['date']}`,
  )
}

// 특정 일기 삭제하기 API
function deleteDiary(data) {
  return instance.delete(`/diary/${data['diary_id']}/delete`)
}
export { createDiary, fetchDiary, updateDiary, deleteDiary }
