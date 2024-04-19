import axios from "axios";


export default {
    getImagesForLandmark(landmarkId){
        return axios.get("/images/" + landmarkId);
    }
};
