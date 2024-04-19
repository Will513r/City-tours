import { createStore as _createStore } from "vuex";
import axios from "axios";

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || "",
      user: currentUser || {},
      /** 
        "id": ,
        "username": "username",
        "fName": null,
        "lName": null,
        "emailAddress": null,
        "address": null,
        "phoneNumber": null,
        "authorities": 

       * 
      */

      landmarks: [
        {
          //JSON structure for individual landmarks
          landmarkId: null,
          landmarkName: '',
          description: '',
          venueTypeName: '',
          dayTimes: null,
          longitudeLM: '',
          latitudeLM: '',
          imageURLs: null,
          address: ''
        },
      ],
      //FILTERED LANDMARKS FROM THE SEARCH BOX GOES HERE
        filterLandmarks: [

        ],

        //USER'S LIST OF ITINERARIES
        itineraryList:[
  
        ],
        //USED IN ADDING/REMOVING LANDMARKS
        selectedItinerary: {
          // {
          //   itineraryId: null,
          //   itineraryName: '',
          //   itineraryDate: null,
          //   userId: null,
          //   startPoint: ''
          // }
        },
        //NEEDS A WATCHER
        itinLandmarksTracker:[

        ],
      homePhotos: [
        "src/assets/imgs/home/Chicago-1.webp",
        "src/assets/imgs/bunkinghamfountain/BuckinghamFountain-1.jpg",
        "src/assets/imgs/culturecenter/ChicagoCulturalCenter1.jpg",
        "src/assets/imgs/milleniumpark/MilleniumPark1.png",
        "src/assets/imgs/navypier/NavyPier3.png"
      ],
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem("token", token);
        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem("user", JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        state.token = "";
        state.user = {};
        axios.defaults.headers.common = {};
      },
      //USED TO SET ALL THE LANDMARKS FROM THE BACKEND TO THE STORE
      SET_LANDMARKS_LIST(state, list) {
        state.landmarks = list;
        localStorage.setItem("landmarks", list);
      },
      //A FILTERED LIST FOR THE SEARCH BOX IN THE LM VIEW
      SET_FILTER_FOR_LANDMARKS(state, list){
        state.filterLandmarks = list;
      },
      //SETS THE USER'S LIST OF ITINERARIES IN THE STORE
      SET_USER_ITINERARY_LIST(state, list){
        state.itineraryList = list;
        localStorage.setItem("itineraryList", list);
      },
      //USED FOR DROPDOWN IN THE LM LIST VIEW
      SET_SELECTED_ITINERARY(state, itinerary){
        state.selectedItinerary = itinerary;
      },
      DELETE_ITINERARY(state, itineraryId){
        let oRemoval = state.itineraryList.findIndex(o => o.itineraryId == itineraryId);

        state.itineraryList.splice(oRemoval, 1);
      },
      //ADDS THE LANDMARK TO THE SPECIFIED ITINERARY BY THE ID
      ADD_TO_ITINERARY(state, landmark, itineraryId){
        for(let itinerary of state.itineraryList){
          if(itinerary.itineraryId == itineraryId){
            itinerary.landmarks.push(landmark);
          }
        }
        state.selectedItinerary.landmarks.push(landmark);
      },
      //REMOVES THE LANDMARK FROM THE ITINERARY SPECIFIED BY THE IDs
      REMOVE_FROM_ITINERARY(state, landmarkId, itineraryId ){
        for(let itinerary of state.itineraryList){
          if(itinerary.itineraryId == itineraryId){
            let oRemoval = itinerary.landmarks.findIndex(o => o.landmarkId == landmarkId);
            itinerary.landmarks.splice(oRemoval, 1);
            break;
          }
        }
        let oRemoval = state.selectedItinerary.landmarks.findIndex(o => o.landmarkId == landmarkId);
        state.selectedItinerary.landmarks.splice(oRemoval, 1);
      },
      //USED TO TRACK THE Landmark IDS THAT'LL BE PUSHED TO THE BACKEND
      SET_TRACKER(state, landmarkIdsList){
        state.itinLandmarksTracker = landmarkIdsList;
      },
      RESET_TRACKER(state){
        state.itinLandmarksTracker = [];
      }

      //
    },
  });
  return store;
}
