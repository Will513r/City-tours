<template>
  <section
    class="flex mx-auto max-w-4xl min-h-12 shadow font-medium text-gray-900 bg-slate-200 rounded-md items-center">
    <div class="flex flex-row justify-evenly gap-8 m-10 min-h-8 shrink">
      <!-- img container -->
      <!-- 
        :src="landmark.imageURLs[1].imageUrl"
        only 3 landmarks have images
      -->
      <div class="flex min-h-12 min-w-12 max-w-40 shrink">
        <img :src="checkImage" class="shrink object-cover">

      </div>

      <!-- description container -->
      <div class="flex flex-col overflow-hidden max-w-md flex-grow gap-2">
        <h2>{{ landmark.landmarkName }}</h2>

        <h3>{{ landmark.address }}</h3>
        <Tag class="w-fit text-center align-middle">{{ landmark.venueTypeName }}</Tag>

        <p class="line-clamp-2">{{ landmark.description }}</p>
      </div>

      <!-- buttons container -->
      <div class="flex items-center flex-col gap-4 shrink max-w-32">

        <Button label="Add" raised class="min-w-16" v-if="$route.name === 'landmarks' && addBoolean == false"
          @click="addToItinerary()"></Button>

        <Button label="In Your Itinerary" v-if="$route.name === 'landmarks' && addBoolean == true" disabled
          severity="secondary"></Button>

        <Button label="Remove"
          v-if="($route.name === 'landmarks' && addBoolean == true) || $route.name === 'ItineraryView'"
          severity="danger" @click="removeFromItinerary()"></Button>


        <router-link :to="{ name: 'LandmarkDetailsView', params: { landmarkId: landmark.landmarkId } }">
          <Button label="Details" raised class="min-w-16"></Button>
        </router-link>
        <p>Likes / Dislikes Here</p>
      </div>
    </div>

  </section>
</template>

<script>
  import Tag from 'primevue/tag';
  import Button from 'primevue/button';
  import itineraryService from '../services/ItineraryService'

  export default {

    components: {
      Button,
      Tag
    },
    props: {
      landmark: {
        type: Object,
        required: true
      },
      itinerary: {
        type: Object
      }
    },
    data() {
      return {
        tracker: this.$store.state.itinLandmarksTracker,
        landmarkId: this.landmark.landmarkId,
        itineraryId: null,
        itinLandmarkList: null,
        addBoolean: false,
        itinIdsList: [],

      }
    },
    methods: {
      addToItinerary() {
        this.itinIdsList.push(this.landmarkId);

        //Uses the http call that assumes that the itinerary has no landmarks
        if (this.itinLandmarkList.length == 0) {
          itineraryService
            .addLandmarksToItinerary(this.itineraryId, this.itinIdsList)
            .then(response => {
              if (response.status == 201) {

                //updates the store
                this.$store.commit("ADD_TO_ITINERARY", this.landmark, this.itineraryId);

                this.$store.commit("SET_TRACKER", this.itinIdsList);

                //flips the addBoolean to turn off the add button
                this.checkItinerary();

              } else {
                this.checkItinerary();
              }
            })
            .catch(error => {
              window.alert(error.response.status + 'ERROR! COULD NOT ADD TO ITINERARY!');
            })
        } else {
          //same as above, but instead assumes that the itinerary has landmarks already in it
          itineraryService
            .updateItineraryLandmarksList(this.itineraryId, this.itinIdsList)
            .then(response => {
              if (response.status == 200) {

                this.$store.commit("ADD_TO_ITINERARY", this.landmark, this.itineraryId);

                this.$store.commit("SET_TRACKER", this.itinIdsList);

                this.checkItinerary();

              } else {
                this.itinIdsList = [];
              }
            })
            .catch(error => {
              window.alert(error.response.status + 'ERROR! COULD NOT ADD TO ITINERARY!');
            })
        }
      },
      removeFromItinerary() {

        let oRemoval = this.itinIdsList.indexOf(this.landmarkId);
        this.itinIdsList.splice(oRemoval, 1);

        if (this.itinIdsList.length != 1) {
          itineraryService
            .updateItineraryLandmarksList(this.itineraryId, this.itinIdsList)
            .then(response => {
              if (response.data == 200) {
                this.$store.commit("REMOVE_FROM_ITINERARY", this.landmarkId, this.itineraryId);

                this.$store.commit("SET_TRACKER", this.itinIdsList);

                this.checkItinerary();
              } else {
                this.checkItinerary();
              }
            })
        } else {
          if (this.itinIdsList.length == 1) {
            itineraryService
              .deleteAllItineraryLandmarks(this.itineraryId)
              .then(response => {
                if (response.data == 200) {

                  this.$store.commit("REMOVE_FROM_ITINERARY", this.landmarkId, this.itineraryId);

                  this.$store.commit("SET_TRACKER", this.itinIdsList);

                  this.checkItinerary();
                } else {
                  this.checkItinerary();
                }

              })
              .catch(error => {
                window.alert(error.response.status + "ERROR WITH REMOVING ITEM! COULD NOT REMOVE!")
              })
          }
        }
      },
      //checks if the selected itinerary has the current landmark (card) in it already, if it does the add button isn't rendered. Also populates the itinLandmarksList so we have a reference for the itinerary's list of landmarks
      checkItinerary() {
        this.itinLandmarkList = this.itinerary.landmarks;
        let check = false;

        //checks if the store's tracker is 0. If it is, repopulate it to equal the selected itinerary's current state of landmarks (and their associated ids). This mimics the landmarks in the itinerary via the landmark ids as an array for us to pass through to the end point (axios call).
        if (this.$store.state.itinLandmarksTracker.length == 0) {
          for (let landmark of this.itinLandmarkList) {
            this.itinIdsList.push(landmark.landmarkId);
          }
        }
        for (let o of this.itinerary.landmarks) {
          if (o.landmarkId == this.landmark.landmarkId) {
            check = true;
            break;
          }
        }
        this.addBoolean = check;
      },
      
    },
    computed:{
      checkImage() {
        if (this.landmark.imageURLs.length > 0) {
          let url = this.landmark.imageURLs[0].imageUrl;
          return url;
        } else {
          return "src/assets/imgs/bunkinghamfountain/BuckinghamFountain-1.jpg";
        }
      }
    },
    watch: {
      itinerary() {
        // this.isThereAnItinerary();
        this.itineraryId = this.itinerary.itineraryId;
        setTimeout(() => {
          this.checkItinerary();
        }, 100);
      },
      addBoolean() {
        this.checkItinerary();
      },
      itineraryId() {
        //resets the tracker in the store when the selected itineraryId is changed
        this.$store.commit("RESET_TRACKER");
      }

    }
  };
</script>

<style scoped>
  h2 {
    @apply font-bold text-3xl
  }

  h3 {
    @apply font-semibold text-xl
  }
</style>
