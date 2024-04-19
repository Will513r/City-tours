<template>
  <div
    class="bg-gradient-to-b from-boxes from-40% via-background1 via-70% to-lightgray min-h-svh gap-y-8 pt-10 flex flex-col">

    <div class="self-center">
      <Button class="text-xl font-bold min-h-16 min-w-48" label="Create New Itinerary" raised
        @click="showForm = !showForm"></Button>
    </div>

    <!-- <itinerary-form></itinerary-form> -->

    <section>
      <ItineraryList :itineraries="$store.state.itineraryList" />
    </section>
  </div>
</template>

<script>
  import ItineraryList from '../components/ItineraryList.vue';
  import Button from 'primevue/button';
  import ItineraryForm from '../components/ItineraryForm.vue';

  export default {
    name: 'Itinerary',
    components: {
      ItineraryList,
      Button,
      
    },
    data() {
      return {
        showForm: false,
      }
    },


  }

</script>