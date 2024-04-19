<template>
  <!-- The map container -->
  <div ref="map" class="map-container"></div>
</template>
<script>
import mapboxgl from "mapbox-gl";
mapboxgl.accessToken = import.meta.env.VITE_MAP_KEY;

export default {
  mounted() {
    this.mount();
    setTimeout(() => {
      this.reMount();
      this.recenter();
    }, 1000);
  },
  created() {},
  methods: {
    reMount() {
      if (this.map) {
        this.map.remove();
      }
      this.mount();
    },
    mount() {
      const map = new mapboxgl.Map({
        container: this.$refs.map,
        style: "mapbox://styles/mapbox/streets-v12", // Replace with your preferred map style
        center: [-87.6243, 41.8757],
        zoom: 12,
        attributionControl: false,
      });
      this.map = map;
    },
    recenter() {
      this.map.setCenter([-87.6243, 41.8757]);
      this.map.setZoom(12);
    },
  },
  data() {
    return {};
  },
  props: {
    landmarks: {
      type: Object,
    },
  },
};
</script>
<style>
.map {
  height: inherit;
  max-height: inherit;
  position: relative;
}

.map-container {
  flex: 1;
  height: 100%;
  width: 100%;
}

.mapboxgl-ctrl-attrib {
  font-size: 12px;
  /* Adjust the font size as needed */
  position: absolute;
  z-index: 3;
}

.mapboxgl-ctrl,
.mapboxgl-ctrl-attrib,
.mapboxgl-compact {
  position: absolute;
  z-index: 3;
}
</style>
