import Vue from 'vue';
import Router from 'vue-router';

import Home from './views/Home.vue';
import GymList from './views/GymList.vue';
import GymNew from './views/GymNew.vue';
import GymEdit from './views/GymEdit.vue';
import TypeItems from './views/TypeItems.vue';
import CategoryList from './views/CategoryList.vue';
import CategoryNew from './views/CategoryNew.vue';
import RegionList from './views/RegionList.vue';
import RegionNew from './views/RegionNew.vue';
import UserList from './views/UserList.vue';
import ReviewList from './views/ReviewList.vue';
import Login from './views/Login.vue';

import store from './store';

Vue.use(Router);

const authenticated = (_to, _from, next) => {
  if (!store.state.accessToken) {
    next('/login');
    return;
  }

  next();
};

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      beforeEnter: authenticated,
    },
    {
      path: '/gyms',
      name: 'gym-list',
      component: GymList,
      beforeEnter: authenticated,
    },
    {
      path: '/gyms/new',
      name: 'gym-new',
      component: GymNew,
      beforeEnter: authenticated,
    },
    {
      path: '/gyms/:gymId',
      name: 'gym-edit',
      component: GymEdit,
      beforeEnter: authenticated,
    },
    {
      path: '/gyms/:gymId/typeitems',
      name: 'type-items',
      component: TypeItems,
      beforeEnter: authenticated,
    },
    {
      path: '/categories',
      name: 'category-list',
      component: CategoryList,
      beforeEnter: authenticated,
    },
    {
      path: '/categories/new',
      name: 'category-new',
      component: CategoryNew,
      beforeEnter: authenticated,
    },
    {
      path: '/regions',
      name: 'region-list',
      component: RegionList,
      beforeEnter: authenticated,
    },
    {
      path: '/regions/new',
      name: 'region-new',
      component: RegionNew,
      beforeEnter: authenticated,
    },
    {
      path: '/users',
      name: 'user-list',
      component: UserList,
      beforeEnter: authenticated,
    },
    {
      path: '/reviews',
      name: 'review-list',
      component: ReviewList,
      beforeEnter: authenticated,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
  ],
});
