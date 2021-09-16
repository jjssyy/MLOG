

import Login from './views/user/Login.vue'
import InitNickname from './views/user/InitNickname.vue'
import Components from './views/Components.vue'

export default [


    {
        path : '/',
        name : 'Login',
        component : Login
    },
    {
        path : '/components',
        name : 'Components',
        component : Components
    },
    {
        path : '/user/nickname',
        name : 'InitNickname',
        component : InitNickname
    },
]
