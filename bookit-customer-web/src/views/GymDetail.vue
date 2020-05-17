<template>
  <div class="gym-detail">
    <div v-if="gym.id">
      <h2>{{ gym.name }}</h2>
      <p>{{ gym.address }}</p>
      <h3>메뉴</h3>
      <ul v-if="gym.typeItems.length" class="type-items">
        <li v-for="typeItem in gym.typeItems" :key="typeItem.id">
          {{ typeItem.name }}
        </li>
      </ul>
      <h3>리뷰</h3>
      <ul v-if="gym.reviews.length" class="reviews">
        <li v-for="review in gym.reviews" :key="review.id">
          [
          <span v-for="i in review.score" :key="i">★</span>
          <span v-for="i in (5 - review.score)" :key="5 + i">☆</span>
          ]
          {{ review.name }}:
          {{ review.description }}
        </li>
      </ul>
      <div class="review-form">
        <textarea class="form-control" v-model="review.description"></textarea>
        <select class="form-control" v-model="review.score">
          <option v-for="i in 5" :key="i" :value="i">{{ i }}</option>
        </select>
        <button type="button" class="btn btn-primary"
          @click="createReview(gymId)"
        >
          리뷰 남기기
        </button>
      </div>
      <h3>예약</h3>
      <div class="gym-form">
        날짜
        <input type="date" class="form-control" :value="gym.date">
        시간
        <input type="time" class="form-control" v-model="gym.time">
        인원
        <input type="number" class="form-control" v-model="gym.partySize">
        <button type="button" class="btn btn-primary"
          @click="createReservation(gymId)"
        >
          예약
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'gym-detail',
  computed: {
    ...mapState(['gym', 'review', 'reservation']),
    gymId() {
      const { gymId } = this.$route.params;
      return gymId;
    },
  },
  methods: {
    ...mapActions([
      'loadGym',
      'resetReview', 'resetReservation',
      'createReview', 'createReservation',
    ]),
  },
  async mounted() {
    this.loadGym(this.gymId);
    this.resetReview();
    this.resetReservation();
  },
};
</script>

<style scoped>
h2 {
  margin-bottom: 20px;
  color: #555;
}

h3 {
  margin-top: 40px;
  padding-top: 40px;
  border-top: 1px solid #CCC;
}

.type-items,
.reviews {
  margin: 0;
  padding: 0;
  list-style: none;
}

.type-items li,
.reviews li {
  margin-bottom: 5px;
  color: #555;
}

.review-form {
  margin-top: 40px;
}

.review-form textarea {
  margin-bottom: 5px;
}

.review-form select {
  display: inline-block;
  width: 50px;
  margin-right: 5px;
}

.reservation-form {
  max-width: 20em;
}

.reservation-form input {
  margin-bottom: 5px;
}
</style>
