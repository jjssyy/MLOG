import { instance } from '@/util/index'

function getMyDiary(data) {
  return instance.get(`/profile/${data['id']}/diary`)
}

function getMyPlaylist(data) {
  return instance.get(`/profile/${data['id']}/playlist`)
}

export { getMyDiary, getMyPlaylist }
