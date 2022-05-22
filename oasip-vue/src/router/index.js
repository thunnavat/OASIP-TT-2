import { createRouter, createWebHistory } from 'vue-router'
import  Home  from  '../views/Home.vue'
import AboutUs from '../views/AboutUs.vue'
import EventCategory from '../views/EventCategory.vue'
import  NotFound  from '../views/NotFound.vue'


const history = createWebHistory(import.meta.env.BASE_URL)
const routes = [  {path:'/' , name:'Home' , component:Home} ,
                  {path:'/about_us' , name:'AboutUs' , component:AboutUs},
                  {path:'/eventcategory' , name:'EventCategory' , component:EventCategory},
                  {path: '/:catchNotMatchPath(.*)' , name: 'NotFound' , component: NotFound}]


const router = createRouter({ history, routes })
export default router