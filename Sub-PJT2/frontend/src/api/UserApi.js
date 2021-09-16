import http from '@/util/http-common';

const initProfile = (data,callback,errorCallback) => {
    http
    .put('/member/'+data.uid, {
        params: {
            nickname: data.nickname,
            file_path: data.profileImg,
        },
    })
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const UserApi = {
    initProfile:(data,callback,errorCallback)=>initProfile(data,callback,errorCallback)
}

export default UserApi
