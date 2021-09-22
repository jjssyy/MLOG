import Login from './views/user/Login.vue'
import InitNickname from './views/user/InitNickname.vue'
import InitProfileImg from './views/user/InitProfileImg.vue'
import Profile from './views/profile/Profile.vue'
import Components from './views/Components.vue'

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
    path: '/main',
    component: () => import('@/views/main/main.vue'),
  },
]
