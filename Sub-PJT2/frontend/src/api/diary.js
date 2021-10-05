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
  return instance.post(
    `/diary/${data['diary_id']}/edit?content=${data['content']}&date=${data['date']}`,
  )
}

// 특정 일기 삭제하기 API
function deleteDiary(data) {
  return instance.delete(`/diary/${data['diary_id']}/delete`)
}

// 특정 일기 추천음악들 불러오기 API
function fetchRcdMusic(uid, data) {
  return instance.get(`/diary/music/${data}/${uid}`)
}

// 특정 일기의 선택된 음악 보내기 API
function submitMusic(uid, data) {
  return instance.get(`/music/${uid}/${data['diary_id']}/${data['mid']}`)
}

export {
  createDiary,
  fetchDiary,
  updateDiary,
  deleteDiary,
  fetchRcdMusic,
  submitMusic,
}
