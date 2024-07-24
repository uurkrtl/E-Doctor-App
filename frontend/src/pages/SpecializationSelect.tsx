import SpecializationService from "../services/SpecializationService.ts";
import {useEffect, useState} from "react";
import {Specialization} from "../types/Specialization.ts";
import DoctorService from "../services/DoctorService.ts";
import {Doctor} from "../types/Doctor.ts";

const specializationService = new SpecializationService();
const doctorService = new DoctorService();

function SpecializationSelect() {
    const [specializations, setSpecializations] = useState<Specialization[]>([]);
    const [doctors, setDoctors] = useState<Doctor[]>([]);
    const [loading, setLoading] = useState(true);
    const [errorMessage, setErrorMessage] = useState<string>('');
    const [selectedSpecialization, setSelectedSpecialization] = useState<Specialization | undefined>();

    const truncateText = (text: string, maxLength: number) => {
        if (text.length > maxLength) {
            return text.substring(0, maxLength - 3) + '...';
        }
        return text;
    };

    useEffect(() => {
        specializationService.getSpecializationByActive(true).then((response) => {
            setSpecializations(response.data);
            setLoading(false)
        }).catch((error) => {
            setErrorMessage(`Fehler beim Abrufen der Fachgebiete: ${error}`)
            setLoading(false)
        });
    }, []);

    useEffect(() => {
        if (selectedSpecialization) {
            doctorService.getActiveDoctorsBySpecializationId(selectedSpecialization.id).then((response) => {
                setDoctors(response.data);
                setLoading(false)
            }).catch((error) => {
                setErrorMessage(`Fehler beim Abrufen der Ärzte: ${error}`)
                setLoading(false);
            });
        }
    }, [selectedSpecialization]);

    if (loading) {
        return <div className={'container'}>
            <div className={'spinner-border text-primary'}>
                <span className={'visually-hidden'}></span>
            </div>
            <h5>Wird geledan...</h5>
        </div>;
    }

    return (
        <div className={'container'}>
            <div className="mt-5 mb-3">
                <select className="form-select"
                        id="specializationName"
                        onChange={(e) => setSelectedSpecialization(specializations.find(specialization => specialization.id === e.target.value))}
                >
                    <option defaultValue={0}>Fachgebiet auswählen</option>
                    {specializations.map((specialization) => {
                        return(
                            <option key={specialization.id} value={specialization.id}>{specialization.name}</option>
                        )
                    })}
                </select>
            </div>
            <div>
                <ul>
                    {doctors.map((doctor) => {
                        return (
                            <li key={doctor.id}>{truncateText(doctor.name, 30)}</li>
                        )
                    })}
                </ul>
            </div>
            {errorMessage && (
                <div className="alert alert-danger" role="alert">
                    {errorMessage}
                </div>
            )}
        </div>
    );
}

export default SpecializationSelect;