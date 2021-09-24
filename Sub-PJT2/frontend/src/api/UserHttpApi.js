import axios from 'axios'
import store from '@/vuex/store'

let url = 'http://localhost:8080'
// let url= "http://i5c105.p.ssafy.io/api";
function createInstance() {
    const instance = axios.create({
        baseURL: url,
        headers: {
            'Content-Type': 'application/json',
            token: store.state.token,
            company: store.state.emailCompany,
        },
    })
    return instance
}

export { createInstance, url }

// const instance = createInstance();
//             this.teamId = this.selectTeam.teamId;
//                 instance.post("/board", {
//                     teamId: this.teamId,
//                     title: this.title,
//                     contents: this.contents,
//                     writer:this.writer,
//                 })
//                     .then(({ data }) => {
//                         let msg = "등록 처리시 문제가 발생했습니다.";
//                         //console.log(data);
//                         if (data.data === "success") {
//                             msg = "등록이 완료되었습니다.";
//                         }
//                         alert(msg);
//                         this.moveList();
//                     })
//                     .catch(() => {
//                         alert("등록 처리시 에러가 발생했습니다.");
//                     });
