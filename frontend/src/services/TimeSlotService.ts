import axios from "axios";

export default class TimeSlotService {
    getTimeSlotsByDate(doctorId:string, slotDate:string) {
        return axios.get(`/api/time-slots/doctor/${doctorId}/${slotDate}`)
    }

    addPatientId(slotId:string, patientId:string) {
        return axios.put(`/api/time-slots/patient/${slotId}/${patientId}`)
    }

    getTimeSlotById(id:string) {
        return axios.get(`/api/time-slots/${id}`)
    }
}