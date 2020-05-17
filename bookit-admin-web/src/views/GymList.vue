<template>
  <div class="gym-list">
    <h1 class="display-4">Gyms</h1>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Name</th>
          <th>Address</th>
          <th>Category</th>
          <th>&nbsp;</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="gym in gyms" :key="gym.id">
          <td>{{ gym.name }}</td>
          <td>{{ gym.address }}</td>
          <td>{{ getCategoryName(gym.categoryId) }}</td>
          <td>
            <router-link class="btn btn-dark"
              :to="{
                name: 'gym-edit',
                params: { gymId: gym.id },
              }"
            >
              Edit
            </router-link>
          </td>
        </tr>
      </tbody>
    </table>
    <hr />
    <router-link class="btn btn-primary" :to="{ name: 'gym-new' }">
      New Gym
    </router-link>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'gym-list',
  computed: mapState(['categories', 'gyms']),
  methods: {
    ...mapActions(['loadCategories', 'loadGyms']),
    getCategoryName(categoryId) {
      const category = this.categories.find(i => i.id === categoryId);
      return category && category.name;
    },
  },
  mounted() {
    this.loadCategories();
    this.loadGyms();
  },
};
</script>
