import * as moment from 'moment';

import {
  useAccessToken,
  get, post,
  loginPost,
} from './http';

export default {
  setAccessToken({ commit }, { accessToken }) {
    commit('setAccessToken', accessToken);

    useAccessToken(accessToken);

    localStorage.setItem('accessToken', accessToken);
  },
  clearAccessToken({ commit }) {
    commit('setAccessToken', '');

    useAccessToken('');

    localStorage.removeItem('accessToken');
  },
  async createSession({ commit }, { email, password, success }) {
    const { accessToken } = await loginPost('/session', { email, password });
    commit('setAccessToken', accessToken);

    useAccessToken(accessToken);
    localStorage.setItem('accessToken', accessToken);

    success();
  },
  async loadCategories({ commit }) {
    const categories = await get('/categories');
    commit('setCategories', categories);
  },
  async loadGyms({ commit }, { region, category }) {
    const gyms = await get('/gyms', { region, category });
    commit('setGyms', gyms);
  },
  async loadGym({ commit }, gymId) {
    const gym = await get(`/gyms/${gymId}`);
    commit('setGym', gym);
  },
  resetReview({ commit }) {
    commit('setReview', {
      score: 3,
      description: '',
    });
  },
  async createReview({ state, dispatch }, gymId) {
    const { review } = state;
    try {
      await post(`/gyms/${gymId}/reviews`, review);
      dispatch('resetReview');
      dispatch('loadGym', gymId);
    } catch (e) {
      // TODO: Show error message
    }
  },
  resetReservation({ commit }) {
    const time = moment().add(1.5, 'hour');
    commit('setReservation', {
      date: time.format('YYYY-MM-DD'),
      time: time.format('HH:00'),
      partySize: 1,
    });
  },
  async createReservation({ state, dispatch }, gymId) {
    const { reservation } = state;
    try {
      await post(`/gyms/${gymId}/gyms`, gym);
      dispatch('resetReservation');
    } catch (e) {
      // TODO: Show error message
    }
  },
};
