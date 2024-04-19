<template>
    <div class="bg-gradient-to-b from-boxes from-40% via-background1 via-70% to-lightgray">
    <LandmarkDetails v-bind:landmark="viewedLandmark" />
    </div>
</template>

<script>
import LandmarkDetails from '../components/LandmarkDetails.vue';
import landmarksService from '../services/LandmarksService';
//import LandmarkCard from '../components/LandmarkCard.vue';
import ImgCarousel from '../components/ImgCarousel.vue';

export default {
    components: {
        // ImgCarousel,
        LandmarkDetails 
        //LandmarkCard
    },
    computed: {
        viewedLandmark() {
            let thisLandmark = this.$store.state.landmarks.filter((landmark) => {
                return landmark.landmarkId == this.$route.params.landmarkId;
            });
            if (thisLandmark.length > 0) {
                return thisLandmark[0];
            } else {
                return {
                    landmarkName: '',
                    venueTypeName: '',
                    description: '',
                    dayTimes: []
                }
            }
        }
    }, 
    created() {
    let landmarkId = parseInt(this.$route.params.landmarkId);
    landmarksService.getLandmark(landmarkId).then((response) => {
      this.landmark = response.data;
    });
  },
}
</script>

<style scoped>
#carousel {
  height: 20rem;
  display: flex;
}
</style>