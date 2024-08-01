import axios from "axios";

export default class TimeSlotService {
    getTimeSlotsByDate(doctorId:string, slotDate:string) {
        return axios.get(`/api/time-slots/doctor/${doctorId}/${slotDate}`)
    }

    addPatientId(slotId:string, patientId:string) {
        return axios.put(`/api/time-slots/patient/${slotId}/${patientId}`)
    }

    removePatientId(slotId:string) {
        return axios.put(`/api/time-slots/patient-remove/${slotId}`)
    }

    getTimeSlotById(id:string) {
        return axios.get(`/api/time-slots/${id}`)
    }

    getTimeSlotByVerificationCode(contact:string, verificationCode:string) {
        return axios.get(`/api/time-slots/verification-code/${contact}/${verificationCode}`)
    }
}