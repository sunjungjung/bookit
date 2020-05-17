import {
  useAccessToken,
  get, post, patch,
  loginPost,
} from './http';

const { log: print } = console;

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
  async loadGyms({ commit }) {
    const gyms = await get('/gyms');
    commit('setGyms', gyms);
  },
  async loadGym ({ commit }, gymId) {
    const gym = await get(`/gyms/${gymId}`);
    commit('setGym', gym);
  },
  async saveGym ({ state }) {
    const { gym } = state;
    await patch(`/gyms/${gym.id}`, gym);
    // TODO: Show message.
    print('saveGym - Success!');
  },
  resetGym({ commit }) {
    commit('setGym', {
      name: '',
      address: '',
      categoryId: null,
    });
  },
  async createGym({ state }, callback) {
    const { gym } = state;
    await post('/gyms', gym);
    // TODO: Show message.
    print('createGym - Success!');
    callback();
  },
  async loadTypeItems({ commit }, gymId) {
    const typeItems = await get(`/gyms/${gymId}/typeItems`);
    commit('setTypeItems', typeItems);
  },
  async saveTypeItems({ state }, gymId) {
    const { typeItems } = state;
    await patch(`/gyms/${gymId}/typeItems`, typeItems);
    // TODO: Show message.
    print('saveTypeItems - Success!');
  },
  addTypeItem({ commit, state }) {
    const { typeItems } = state;
    commit('setTypeItems', [
      ...typeItems,
      { name: '' },
    ]);
  },
  async loadCategories({ commit }) {
    const categories = await get('/categories');
    commit('setCategories', categories);
  },
  async createCategory({ state }, callback) {
    const { category } = state;
    await post('/categories', category);
    // TODO: Show message.
    print('createCategory - Success!');
    callback();
  },
  resetCategory({ commit }) {
    commit('setCategory', { name: '' });
  },
  async loadRegions({ commit }) {
    const regions = await get('/regions');
    commit('setRegions', regions);
  },
  async createRegion({ state }, callback) {
    const { region } = state;
    await post('/regions', region);
    // TODO: Show message.
    print('createRegion - Success!');
    callback();
  },
  resetRegion({ commit }) {
    commit('setRegion', { name: '' });
  },
  async loadReviews({ commit }) {
    const reviews = await get('/reviews');
    commit('setReviews', reviews);
  },
  async loadUsers({ commit, dispatch }) {
    const users = await get('/users');
    commit('setUsers', users);
    users
      .filter(user => user.gymId)
      .forEach(user => dispatch('loadUserGym', user));
  },
  async loadUserGym({ state, commit }, user) {
    const { id, gymId } = user;
    const gym = await get(`/gyms/${gymId}`);
    const users = state.users
      .map(u => ((u.id === id) ? { ...u, gym } : u));
    commit('setUsers', users);
  },
};
