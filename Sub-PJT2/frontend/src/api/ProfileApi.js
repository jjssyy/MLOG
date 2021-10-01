import { instance } from '@/util/index'

function getMyDiary(data) {
  return instance.get(`/profile/${data['id']}/diary`)
}

export { getMyDiary }
