<template>
  <div class="search">
    <div class="border-dashed p-4 m-5">
      <!-- Float Label doesn't currently work and I suspect it's styling reasons? -->
      <FloatLabel>
        <!--
          the InputText binded to populate the search.landmarkName AND to execute the filteredList() method
          upon sensing user input (@input event) @ is shorthand to bind to events
        -->
        <InputText id="searchBox" v-model="search.landmarkName" @input="filteredList()" class="" />
        <label for="searchBox">Search Landmarks</label>
      </FloatLabel>
      <section class="bg-background px-8 py-4 rounded-md shadow-md">
        <div v-for="venue of venues" :key="venue.key" class="flex items-center gap-4 my-2">
          <Checkbox v-model="search.venueTypeName" :inputId="venue.venueId" name="venueType"
            v-model:value="venue.venueName"></Checkbox>
          <label :for="venue.venueId">{{ venue.venueName }}</label>
        </div>
      </section>
    </div>
  </div>
</template>
<script>
  import InputText from "primevue/inputtext";
  import Checkbox from 'primevue/checkbox';
  import FloatLabel from 'primevue/floatlabel';
  import landmarksService from '../services/LandmarksService';
  // Search function
  export default {
    components: {
      InputText,
      Checkbox,
      FloatLabel
    },
    data() {
      return {
        venues: {},
        search: {
          landmarkName: "",
          venueTypeName: [],
        }
      };
    },
    methods: {
      filteredList() {
        //Sets the 'filteredLandmark' to be the store's landmarks array (which is ALL the landmarks)
        let filteredLandmark = this.$store.state.landmarks;
        //if the search.landmark is NOT EMPTY, begin the filter
        if (this.search.landmarkName !== "") {
          filteredLandmark = filteredLandmark.filter((landmark) =>
            //if the landmark's name (landmarkName) includes the search.landmark, keep it in the filteredLandmark array
            landmark.landmarkName.toLowerCase().includes(this.search.landmarkName.toLowerCase())
          )
        }
        if (this.search.venueTypeName.length > 0) {
          filteredLandmark = filteredLandmark.filter(landmark => {
            landmark.venueTypeName.includes(this.search.venueTypeName) && !filteredLandmark.includes(landmark)
          })
        }
        //Calls the SET_FILTER_FOR_LANDMARKS Mutation in the store, and passes the newly filtered list of landmarks
        this.$store.commit('SET_FILTER_FOR_LANDMARKS', filteredLandmark);
      },
    },
    created() {
      landmarksService
        .getVenues()
        .then(response => {
          if (response.status == 200 && response.data != null) {
            this.venues = response.data;
          } else {
            throw new Error('Venue list returned was null')
          }
        })
        .catch(error => {
          if (error.response) {
            this.$toast.add({
              severity: 'danger',
              summary: 'GET Error!',
              detail:
                'CODE: ' + error.response.status +
                '\n Could not get venues list from server!',
              life: 5000
            })
          }
        })
    }
  };
</script>

<style scoped></style>