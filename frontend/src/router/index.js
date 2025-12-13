import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import HorariosView from '../views/HorariosView.vue'
import PiuView from '../views/PiuView.vue'
import InformacionGeneralView from '../views/InformacionGeneralView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/horarios',
      name: 'horarios',
      component: HorariosView
    },
    {
      path: '/piu',
      name: 'piu',
      component: PiuView
    },
    {
      path: '/informacion-general',
      name: 'informacion-general',
      component: InformacionGeneralView
    }
  ]
})

export default router