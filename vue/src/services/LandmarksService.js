import axios from "axios";


export default {
  // RETURNS A LIST OF LANDMARKS AND THEIR PROPERTIES
  list() {
    return axios.get("/landmarks");
  },
  getLandmark(landmarkId) {
    return axios.get("/landmarks/" + landmarkId);
  },
  getVenues(){
    return axios.get("/venues");
  }
  // getLandmarks() {
  //   return http.get("/landmarks");
  // },
};
