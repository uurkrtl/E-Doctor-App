import axios from "axios";
import {PatientRequest} from "../types/PatientRequest.ts";

export default class PatientService {
    addPatient(patientRequest:PatientRequest) {
        return axios.post('/api/patients', patientRequest);
    }
}