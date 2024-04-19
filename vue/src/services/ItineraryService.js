import axios from "axios";


export default {

  /* GETTING THE ITINERARIES FOR A USER */
  //STEP 1
  list(userId) {
  // RETURNS A LIST OF ITIN IDS ASSOCIATED WITH THE USER 
    return axios.get("/itineraries/" + userId);
  },

  //STEP 2 GET THE LANDMARKS IN THE ITIN BY ITS ID
  getItinerary(itineraryId) {
    return axios.get("/itinerary/" + itineraryId);
  },

  //DIFFERENT METHODS TO ADD LANDMARKS
  /* ADDING LANDMARKS TO AN ITINERARY */
  //STEP 1
  addLandmarksToItinerary(itineraryId, dto){
    //SUCCESS CODE 201
    //SENDS A BODY/ARRAY OF landmarkIds to the server
    //ASSUMES THE ITINERARY IS BLANK
    return axios.post("/itinerary/" + itineraryId + "/landmark", dto)
  },

  /* ADDING LANDMARKS TO AN ITINERARY */
  //STEP 1
  updateItineraryLandmarksList(itineraryId, dto){
    //RETURNS NUMBER OF ROWS AFFECTED
    //THIS ACTUALLY DELETES THE EXISTING LIST OF LANDMARKS IN THE ITINERARY
    //AND REPLACES IT WITH A NEW ONE.
    //TO ADD LANDMARKS TO AN ITINERARY THAT HAS LANDMARKS ALREADY,
    //KEEP AN ARRAY OF THE EXISTING LANDMARKS YOU WANT TO KEEP TO USE THIS
    //THIS CAN ALSO BE USED TO REMOVE LANDMARKS
    //SUCCESS CODE 200
    return axios.put("/updateitinerarylandmarks/" + itineraryId, dto)
  },

  //DELETES ALL LANDMARKS IN THE ITINERARY
  //SUCCESS CODE 200
  deleteAllItineraryLandmarks(itineraryId){
    return axios.put("/itinerary/" + itineraryId + "/landmark/delete");
  },

  //SUCCESS CODE 200
  deleteItinerary(itineraryId){
    return axios.put("/itinerary/" + itineraryId + "/delete");
  },

  //SENDS AN OBJECT
  //{itineraryName, userId, itineraryDate}
  //GIVES BACK
  //{itineraryId, itineraryName, userId, startpoint:null, itineraryDate, landmarks[]}
  //SUCCESS CODE 201
  //DTO IS THE PLACEHOLDER TO SEND THE OBJECT
  createBlankItinerary(dto){
    return axios.post("/itinerary", dto);
  }

  //NEED JAMIE TO CLARIFY THE PROCESS OF CREATING AND EDITING
  //AN ITINERARY >.>
  //DON'T KNOW THE DIFF BETWEEN ADD A STARTPOINT TO AN ITINERARY
  //AND CREATE A STARTPOINT FOR AN ITINERARY SINCE THE JDBC METHOD
  //FOR ONE OF THEM ACTUALLY ONLY TAKES AN ITINERARY ID BUT UPDATES?

//   editItinerary(){
//     return axios.put("/itinerary/" + );
//   },
//   addItinerary(itinerary) {
//     return axios.post("itinerary", itinerary);
//   },
//   deleteItinerary(itineraryId) {
//     return axios.delete("/itinerary/" + itineraryId);
//   }
 
};
