<template>
  <div class="gym-edit">
    <h1 class="display-4">Gym</h1>
    <table class="table table-striped" v-if="gym.id">
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
        <tr>
          <th>Type</th>
          <td>
            <ul v-if="typeItems.length">
              <li v-for="typeItem in typeItems" :key="typeItem.id">
                {{ typeItem.name }}
              </li>
            </ul>
            <hr>
            <router-link class="btn btn-dark"
              :to="{
                name: 'type-items',
                params: {
                  restaurantId: gym.id,
                },
              }"
            >
              Edit Menu
            </router-link>
          </td>
        </tr>
      </tbody>
    </table>
    <hr>
    <div>
      <button type="button" class="btn btn-primary" @click="saveGym">
        Save
      </button>
      |
      <router-link class="btn btn-secondary" :to="{ name: 'gym-list' }">
        List
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'gym-edit',
  computed: {
    ...mapState(['categories', 'gym', 'typeItems']),
    restaurantId() {
      const { gymId } = this.$route.params;
      return gymId;
    },
  },
  methods: {
    ...mapActions([
      'loadCategories', 'loadGym', 'loadTypeItems', 'saveGym',
    ]),
  },
  mounted() {
    this.loadCategories();
    this.loadGym(this.gymId);
    this.loadTypeItems(this.gymId);
  },
};
</script>
