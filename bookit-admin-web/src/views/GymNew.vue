<template>
  <div class="gym-new">
    <h1 class="display-4">Gym</h1>
    <table class="table table-striped">
      <tbody>
        <tr>
          <th>Name</th>
          <td>
            <input type="text" class="form-control" v-model="gym.name">
          </td>
        </tr>
        <tr>
          <th>Address</th>
          <td>
            <input type="text" class="form-control" v-model="gym.address">
          </td>
        </tr>
        <tr>
          <th>Category</th>
          <td>
            <select class="form-control" v-model="gym.categoryId">
              <option v-for="category in categories"
                :key="category.id"
                :value="category.id"
              >
                {{ category.name }}
              </option>
            </select>
          </td>
        </tr>
      </tbody>
    </table>
    <hr>
    <div>
      <button type="button" class="btn btn-primary" @click="submit">
        Create
      </button>
      |
      <router-link class="btn btn-secondary" :to="{ name: 'gym-list' }">
        Cancel
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'gym-new',
  computed: {
    ...mapState(['categories', 'gym']),
    gymId() {
      const { gymId } = this.$route.params;
      return gymId;
    },
  },
  methods: {
    ...mapActions(['resetGym', 'createGym', 'loadCategories']),
    submit() {
      this.createGym(() => {
        this.$router.push({ name: 'gym-list' });
      });
    },
  },
  mounted() {
    this.resetGym();
    this.loadCategories();
  },
};
</script>
