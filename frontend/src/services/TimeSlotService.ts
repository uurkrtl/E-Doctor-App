import axios from "axios";

export default class TimeSlotService {
    getTimeSlotsByDate(doctorId:string, slotDate:string) {
        return axios.get(`/api/time-slots/doctor/${doctorId}/${slotDate}`)
    }
}