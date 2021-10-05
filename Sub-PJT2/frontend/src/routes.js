import Login from './views/user/Login.vue'
import InitNickname from './views/user/InitNickname.vue'
import InitProfileImg from './views/user/InitProfileImg.vue'
import Profile from './views/profile/Profile.vue'
import MyInfo from './views/profile/MyInfo.vue'
import MyInfoUpdate from './views/profile/MyInfoUpdate.vue'
import MyDiary from './views/profile/MyDiary.vue'
import MyPlaylist from './views/profile/MyPlaylist.vue'
import DiaryReport from './views/profile/DiaryReport.vue'
import EmotionReport from './views/profile/EmotionReport.vue'
import SurveyStart from './views/survey/SurveyStart.vue'
import Survey from './views/survey/Survey.vue'
import Components from './views/Components.vue'
import Main from './views/main/Main.vue'
import CreateDiary from './views/diary/CreateDiary.vue'
import ReadDiary from './views/diary/ReadDiary.vue'
import UpdateDiary from './views/diary/UpdateDiary.vue'
import Music from './views/diary/Music.vue'

export default [
  {
    path: '/',
    name: 'Login',
    component: Login,
  },
  {
    path: '/components',
    name: 'Components',
    component: Components,
  },
  {
    path: '/user/nickname',
    name: 'InitNickname',
    component: InitNickname,
  },
  {
    path: '/user/profileimg',
    name: 'InitProfileImg',
    component: InitProfileImg,
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
  },
  {
    path: '/profile/myinfo',
    name: 'MyInfo',
    component: MyInfo,
  },
  {
    path: '/profile/myinfo/update',
    name: 'MyInfoUpdate',
    component: MyInfoUpdate,
  },
  {
    path: '/profile/mydiary',
    name: 'MyDiary',
    component: MyDiary,
  },
  {
    path: '/profile/myplaylist',
    name: 'MyPlaylist',
    component: MyPlaylist,
  },
  {
    path: '/profile/mydiaryreport',
    name: 'DiaryReport',
    component: DiaryReport,
  },
  {
    path: '/profile/emotionreport',
    name: 'EmotionReport',
    component: EmotionReport,
  },
  {
    path: '/survey',
    name: 'SurveyStart',
    component: SurveyStart,
  },
  {
    path: '/survey/:survey_num',
    name: 'Survey',
    component: Survey,
    props: true,
  },
  {
    path: '/main',
    name: 'Main',
    component: Main,
  },
  {
    path: '/diary/:date/create',
    name: 'CreateDiary',
    component: CreateDiary,
  },
  {
    path: '/diary/:date',
    name: 'ReadDiary',
    component: ReadDiary,
  },
  {
    path: '/diary/:date/update',
    name: 'UpdateDiary',
    component: UpdateDiary,
  },
  {
    path: '/diary/:date/music',
    name: 'MusicDiary',
    component: Music,
  },
]
