<template>
  <!-- TODO: Fix Carousel covers nav bar when shrunk  -->
  <swiper
    :slidesPerView="1"
    :spaceBetween="0"
    :autoplay="{
      delay: 3500,
      disableOnInteraction: false,
    }"
    :loop="true"
    :effect="'fade'"
    :speed="1750"
    :navigation="true"
    :modules="modules"
    class="mySwiper"
  >
    <swiper-slide v-for="(photo, index) in photos" :key="index"
      ><img :src="photo" alt=""
    /></swiper-slide>
  </swiper>
</template>

<script>
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue";

// Import Swiper styles
import "swiper/css";

import "swiper/css/effect-fade";
import "swiper/css/pagination";
import "swiper/css/navigation";

// import required modules
import { Autoplay, Navigation, EffectFade } from "swiper/modules";

export default {
  props: {
    photos: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {};
  },
  components: {
    Swiper,
    SwiperSlide,
  },
  setup() {
    return {
      modules: [Autoplay, Navigation, EffectFade],
    };
  },
  methods: {},
};
</script>

<style>
.swiper {
  z-index: 2;
}

.swiper-slide {
  align-items: center;
  height: inherit;
}

.swiper-slide img {
  display: block;
  height: 100%;
  width: 100%;
  object-fit: cover;
}
</style>
