<template>
  <div class="type-items">
    <h1 class="display-4">Type Items</h1>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Name</th>
          <th>&nbsp;</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(typeItem, index) in typeItems" :key="typeItem.id">
          <td>
            <input type="text" class="form-control" v-model="typeItem.name"
              :disabled="typeItem.destroy"
            >
          </td>
          <td>
            <input type="checkbox" class="form-check-input"
              :id="`checkbox-destroy-item-${index}`"
              v-model="typeItem.destroy"
            >
            <label class="form-check-label"
              :for="`checkbox-destroy-item-${index}`"
            >
              Delete
            </label>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <button type="button" class="btn btn-dark" @click="addTypeItem">
              Add Item
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <hr>
    <div>
      <button type="button" class="btn btn-primary" @click="submit">
        Save
      </button>
      |
      <router-link class="btn btn-secondary"
        :to="{
          name: 'gym-edit',
          params: { gymId },
        }"
      >
        Back
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'gym-edit',
  computed: {
    ...mapState(['gym', 'typeItems']),
    restaurantId() {
      const { gymId } = this.$route.params;
      return gymId;
    },
  },
  methods: {
    ...mapActions(['loadTypeItems', 'addTypeItems', 'saveTypeItems']),
    submit() {
      this.saveTypeItems(this.gymId);
    },
  },
  mounted() {
    this.loadTypeItems (this.gymId);
  },
};
</script>
