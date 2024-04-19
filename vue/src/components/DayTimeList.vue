<template>
    <div>
        <h2 class="ml-3 mt-3 font-bold overline decoration-bahama text-xl">Hours of Operation:</h2>
        <div class="day" v-for="day in dayTimes" v-bind:key="day.landmarkId">
            <p class="ml-9 mt-3 overline text-lg decoration-bahama">{{ day.dayName }}: {{ day.openTime }} - {{ day.closeTime }}</p>
        </div>
    </div>
</template>

<!-- how do I access the day and times and stuff here??? -->

<script>
export default {
    props: [
      'dayTimes'
    ]
}
</script>
