import axios from 'axios';

export default {

    getStartPointByItineraryId(id){
        return axios.get("/startpoint/" + id);
    }

}
