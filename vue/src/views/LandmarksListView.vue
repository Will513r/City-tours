<template>
  <div class="bg-gradient-to-b from-boxes from-40% via-background1 via-70% to-lightgray min-h-svh">
    <section class="flex w-full pt-6 ">
      <div class="w-6/12 flex flex-row mx-auto justify-evenly">
        <div class="">
          <RouterLink :to="{ name: 'ItineraryView' }">
            <Button class="text-xl font-bold min-h-16 min-w-48" label="Create Itinerary"></Button>
          </RouterLink>

        </div>
        <div class="">
          <Dropdown v-model="itineraryName" placeholder="Select Your Itinerary" :options="listNames()" class="w-46">
          </Dropdown>
        </div>
      </div>
    </section>
    <section class="flex flex-row mx-auto max-w-7xl">
      <side-filter />
      <!-- 
                landmarkslist component is set to expect an array to be binded to the landmarksList prop 
                which you can find in its component file. It then loops through the array.
            -->
      <landmarks-list :landmarksList="$store.state.filterLandmarks" :selectedItinerary="$store.state.selectedItinerary"></landmarks-list>
    </section>
  </div>
</template>

<script>
  import { RouterLink } from "vue-router";
  import LandmarksList from "../components/LandmarksList.vue";
  import SideFilter from "../components/SideFilter.vue";
  import landmarksService from "../services/LandmarksService";
  import Button from "primevue/button";
  import Dropdown from "primevue/dropdown"
  export default {
    components: {
      LandmarksList,
      SideFilter,
      RouterLink,
      Button,
      Dropdown
    },
    data() {
      return {
        landmarksList: {
          // landmarkName: '',
          // landmarkAddress: '',
          // landmarkDescription: '',
          // landmarkVenueType: '',
          type: Object,
          required: true
        },
        itineraryName: {},
        itineraryObject: {},
      };
    },
    computed: {

    },
    methods: {
      // created() {
      //   let landmarkId = parseInt(this.$route.params.id);
      //   landmarksService.getLandmark(landmarkId).then((response) => {
      //     this.landmark = response.data;
      //   });
      // },
      listNames() {
        let arr = []
        this.$store.state.itineraryList.forEach(o => {
          arr.push(o.itineraryName);
        })
        return arr;
      },
      getItinerary() {
        let arr = this.$store.state.itineraryList;
        let dto = arr.filter(o => {
          return o.itineraryName == this.itineraryName;
        })
        this.itineraryObject = dto[0];
        this.$store.commit('SET_SELECTED_ITINERARY', this.itineraryObject);

        //set the store's tracker
        let dtoArr = [];
         for(let landmark of this.itineraryObject.landmarks){
            dtoArr.push(landmark.landmarkId);
         }
         this.$store.commit('SET_TRACKER', dtoArr);
      }
    },
    watch: {
      itineraryName() {
        this.getItinerary();
      }
    },
  };
</script>

<style scoped></style>
