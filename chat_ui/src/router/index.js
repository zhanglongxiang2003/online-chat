import { useUserStore } from '@/stores/user'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/login_register/index.vue')
    },
    {
      path: '',
      name: 'index',
      component: () => import('@/views/index.vue'),
      redirect: '/message',
      children: [
        {
          path: '/message',
          name: 'message',
          component: () => import('@/views/message/index.vue'),
          children: [
            {
              path: 'wrapper',
              name: 'wrapper',
              component: () => import('@/views/message/component/messageBoxItem.vue'),
            },
            // {
            //   path: 'addFriend',
            //   name: 'addFriend',
            //   component: () => import('@/views/contacts/component/addFriend.vue'),
            // },
            // {
            //   path: 'createGroup',
            //   name: 'createGroup',
            //   component: () => import('@/views/contacts/component/createGroup.vue'),
            // },
          ]
        },
        {
          path: '/contacts',
          name: 'contacts',
          component: () => import('@/views/contacts/index.vue'),
          children: [
            {
              path: 'notificationList',
              name: 'notificationList',
              component: () => import('@/views/contacts/component/notificationList.vue'),
            },
            {
              path: 'friendDetail',
              name: 'friendDetail',
              component: () => import('@/views/contacts/component/friendDetail.vue'),
            },
            {
              path: 'groupDetail',
              name: 'groupDetail',
              component: () => import('@/views/contacts/component/groupDetail.vue'),
            },
            {
              path: 'addFriend',
              name: 'addFriend',
              component: () => import('@/views/contacts/component/addFriend.vue'),
            },
            {
              path: 'createGroup',
              name: 'createGroup',
              component: () => import('@/views/contacts/component/createGroup.vue'),
            },
          ]
        },
        {
          path: '/circleOfFriends',
          name: 'circleOfFriends',
          component: () => import('@/views/CircleOfFriends/index.vue'),
        },
        {
          path: '/themeCenter',
          name: 'themeCenter',
          component: () => import('@/views/themeCenter/index.vue'),
        },
      ]
    },
  ]
})


// 路由前置守卫
router.beforeEach((to, from, next) => {

  // 判断是否有token
  const userStore = useUserStore()
  if (to.path === '/login' || userStore.token !== null && userStore.token !== '' && userStore.token !== undefined) {
    next()
  } else {
    //都不满足，跳转登录页面
    next('/login')
  }


})



export default router
