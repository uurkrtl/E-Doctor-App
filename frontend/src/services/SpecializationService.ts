import axios from "axios";

export default class SpecializationService {
    getSpecializationByActive(isActive: boolean) {
        return axios.get(`/api/specializations/status/${isActive}`);
    }
}