<template>
  <div class="max-w-7xl mx-auto ">
    <section>
      <!-- PLACEHOLDER BELOW -->
      <Accordion class="bg-background1 shadow-md rounded-md">
        <AccordionTab v-for="itinerary in itineraries" :key="itinerary.key"
          :header="itinerary.itineraryName + ' - ' + itinerary.itineraryDate" class="">
          <div class="w-11/12 mx-auto flex flex-col h-2/6 items-center my-12 mb-20 rounded-md bg-bahama">
            <div class="w-full flex justify-end mr-8 py-4 gap-4">
              
              <router-link :to="{name : 'landmarks'}">
                <Button label="Add Landmarks" raised></Button>
              </router-link>

              <router-link :to="{ name: 'EditTripView', params: { itineraryId: itinerary.itineraryId } }">
                <Button label="Edit" raised class="min-w-16"></Button>
              </router-link>

              <Button label="Delete" raised class="min-w-16" @click="deleteItinerary(itinerary.itineraryId)"></Button>

            </div>
            <div class="flex gap-6 flex-row w-full max-h-96 drop-shadow-md rounded-md">
              <div class="max-h-full w-1/2   text-center overflow-scroll rounded-3xl">
                <div class="">
                  <div class="p-4 pl-8">
                    <landmark-card v-for="landmark in itinerary.landmarks" :key="landmark.landmarkId"
                      :landmark="landmark" :itinerary="itinerary" class="my-6"></landmark-card>
                  </div>
                </div>
              </div>

              <div class="w-1/2 pr-8 rounded-lg">
                <div class="h-full w-full" refs="mapCon">
                  <map-box class="flex"></map-box>
                </div>

              </div>

            </div>
            <div class="h-12 bg-bahama">
              <!-- spacer -->
            </div>
          </div>
        </AccordionTab>
      </Accordion>

    </section>
  </div>
</template>

<script>

  import MapBox from './MapBox.vue';
  import Accordion from 'primevue/accordion';
  import AccordionTab from 'primevue/accordiontab';
  import Button from 'primevue/button';
  import LandmarkCard from './LandmarkCard.vue';
  import itineraryService from '../services/ItineraryService';

  export default {
    data() {
      return {
      }
    },
    components: {
      Accordion,
      AccordionTab,
      Button,
      MapBox,
      LandmarkCard

    },
    props: {
      itineraries: {
        type: Object
      }
    },
    methods: {
      deleteItinerary(itineraryId) {
        itineraryService
          .deleteItinerary(itineraryId)
          .then(response => {
            if (response == 200) {
              this.$store.commit("DELETE_ITINERARY", itineraryId);
              this.$forceUpdate();
            }
          })
          .catch(error =>{
            window.alert(error.response.status + 'Error, could not delete the itinerary!')
          })
      },
    }

  }

</script>

<style scoped>

  /* buttons {
  @apply 
}, */
  .map-repaint {
    opacity: 0.99;
  }
</style>