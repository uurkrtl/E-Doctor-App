import axios from "axios";

export default class DoctorService {
    getActiveDoctorsBySpecializationId(specializationId:string) {
        return axios.get(`/api/doctors/specialization/${specializationId}`);
    }
}