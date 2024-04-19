<template>
  <!-- Image Carousel What pictures does the team want shown first?
  Make the Carousel smaller???????  -->
  <main class="home bg-gradient-to-b from-boxes from-40% via-background1 via-70% to-lightgray">
    <div id="carousel" class="flex">
      <img-carousel :photos="$store.state.homePhotos" />
    </div>

    <!-- About Section????????  We can put what ever here like a welcome statement or how to use our service?????  -->
    <div class="about m-10">
      <H1 class="mt-10 text-center text-3xl leading-9 tracking-tight text-gray-900">Welcome to City Tours</H1>
      <p class="m-9 text-center text-lg text-gray-900">
        Plan your next trip to Chicago with a click of a button. We help you plan the best route for your trip, so you can spend more time enjoying Chicago!
        Discover the convenience of effortless trip planning to Chicago with just a click!
         Our intuitive platform streamlines the entire process, from mapping out the optimal route to providing
          insider tips on must-see attractions and hidden gems. Spend less time fretting over logistics and more time
           immersing yourself in the vibrant culture, delicious cuisine, and iconic landmarks of the Windy City.
             Start your journey now and experience the thrill of exploration with ease!
      </p>
    </div>

    <div class="landmarkcards">
      <landmark-card :landmark="$store.state.landmarks[0]" />
    </div>
  </main>
</template>

<script>
  import LandmarkCard from "../components/LandmarkCard.vue";
  import ImgCarousel from "../components/ImgCarousel.vue";
  import itineraryService from "../services/ItineraryService.js";


  export default {
    components: {
      ImgCarousel,
      LandmarkCard
    },
    data() {
      return {

        itineraryList: [],
        itineraryIds: [],
        itinDTO : []
      }
    },

    //upon opening the Home page, do the setItineraries method
    created() {
      this.setItineraries();
    },

    //Watch, watches for changes in data and triggers the following methods
    //When the itineraryIds experience any change, do the populateItineraries() method which populates the itinDTO which we can commit to the store.
    //We use watch since we can't be running multiple asynchronous processes at once and this staggers their processes. 
    //The $store.commit could not be put in the populateItineraries() method as it cannot be performed during an asychronous call which means we needed a DTO (Data Transfer Object) outside of the method to hold the result/response data from the axios call. 
    watch: {
      itineraryIds() {
        this.populateItineraries();
        this.$store.commit("SET_USER_ITINERARY_LIST", this.itinDTO);
        this.itineraryList = this.$store.state.itineraryList;
      }
    },
    methods: {
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
      setItineraries() {
        if (this.$store.state.user !== null) {
          this.getUserItineraryIds();
          this.populateItineraries();
        }
      },

    }
  }

</script>

<style scoped>
  #carousel {
    height: 40rem;
    display: flex;
  }
</style>
