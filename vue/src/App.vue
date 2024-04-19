<template>
  <div id="capstone-app">
    <!-- <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div> -->
    <nav-bar></nav-bar>
    <router-view />
  </div>
</template>

<script>
  import NavBar from './components/NavBar.vue';
  import landmarksService from './services/LandmarksService';
  import itineraryService from './services/ItineraryService';

  export default {
    data() {
      return {
        itineraryIds: [],
        itinDTO: []
      }
    },
    components: {
      NavBar
    },
    created() {
      // Wrap popLandmarks in a promise
      const popLandmarksPromise = new Promise((resolve) => {
        this.popLandmarks(); // Call the void method
        resolve(); // Resolve the promise immediately
      });

      // Wait for popLandmarks to complete
      popLandmarksPromise.then(() => {
        // After popLandmarks completes, wait for 100 milliseconds before calling getUserItineraryIds
        setTimeout(() => {
          if (this.$store.state.user !== null) {
            this.getUserItineraryIds();
          }
        }, 100);
      });

    },
    watch: {
      itineraryIds() {
        this.populateItineraries();
        this.$store.commit("SET_USER_ITINERARY_LIST", this.itinDTO);
        this.itineraryList = this.$store.state.itineraryList;
      }
    },
    methods: {
      // POPULATES THE LANDMARKS ARRAY IN THE STORE BY PULLING FROM BACKEND 
      // It should only do this once for the entire application so refresh if you made changes to the backend database
      popLandmarks() {
        landmarksService
          .list()
          .then(response => {
            if (response.status == 200) {
              this.$store.commit("SET_LANDMARKS_LIST", response.data);
              this.$store.commit("SET_FILTER_FOR_LANDMARKS", response.data);
            }
          })
          .catch(error => {
            const response = error.response;
            window.alert(response.status + ' ERROR! COULD NOT LOAD LANDMARKS FROM SERVER')
          })
      },

      // METHODS TO POPULATE THE USER'S ITINERARIES
      getUserItineraryIds() {

        itineraryService
          .list(this.$store.state.user.id)
          .then(response => {
            // window.alert(response.status + "ERROR GETTING ITIN IDS 1");
            if (response.status == 200) {
              this.itineraryIds = response.data;
            }
          })
          .catch(error => {
            const response = error.response;
            // window.alert(response.status + " ERROR GETTING ITIN IDS")
          });

      },
      populateItineraries() {
        let itinArray = [];
        this.itineraryIds.forEach((o) => {
          itineraryService
            .getItinerary(o.itineraryId)
            .then(response => {
              if (response.status == 200) {
                itinArray.push(response.data);
              }
            })
            .catch(error => {
              const response = error.response;
              window.alert(response.status + "\nError! Populating the Itineraries 3 ")
            })
          this.itinDTO = itinArray;

        });

      },


    }
  };

</script>

<style>
  * {
    @apply font-body
  }

  h2 {
    @apply font-extrabold
  }
</style>
