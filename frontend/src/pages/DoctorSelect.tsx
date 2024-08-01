import SpecializationService from "../services/SpecializationService.ts";
import React, {useEffect, useState} from "react";
import {Specialization} from "../types/Specialization.ts";
import DoctorService from "../services/DoctorService.ts";
import {Doctor} from "../types/Doctor.ts";
import {Link, useParams} from "react-router-dom";

import {
    CardMeta,
    CardHeader,
    CardGroup,
    CardDescription,
    CardContent,
    Card,
    Image,
} from 'semantic-ui-react';
import CustomIcon from "../layouts/CustomIcon.tsx";

const specializationService = new SpecializationService();
const doctorService = new DoctorService();

function DoctorSelect() {
    const { specializationId = '' } = useParams<string>();
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

    const handleSpecializationChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const selectedSpecializationId = e.target.value;
        setSelectedSpecialization(specializations.find(specialization => specialization.id === selectedSpecializationId));
    }

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
        }else if (specializationId) {
            doctorService.getActiveDoctorsBySpecializationId(specializationId).then((response) => {
                setDoctors(response.data);
                setLoading(false)
            }).catch((error) => {
                setErrorMessage(`Fehler beim Abrufen der Ärzte: ${error}`)
                setLoading(false);
            });
        }
    }, [specializationId, selectedSpecialization]);

    if (loading) {
        return <div className={'container mt-5'}>
            <div className={'spinner-border text-primary'}>
                <span className={'visually-hidden'}></span>
            </div>
            <h5>Wird geledan...</h5>
        </div>;
    }

    return (
        <div className={'container'}>
            <div className="mb-3">
                <div className="py-5 text-center">
                    <CustomIcon pageName='DoctorSelect'/>
                    <h2>Arztauswahl</h2>
                    <p className="lead">Unten können Sie Fachgebiet und Arzt auswählen.</p>
                </div>
                <select className="form-select"
                        id="specializationName"
                        onChange={handleSpecializationChange}
                        defaultValue={specializationId || ''}
                >
                    <option value="0">Fachgebiet auswählen</option>
                    {specializations.map((specialization) => {
                        return (
                            <option key={specialization.id} value={specialization.id}>{specialization.name}</option>
                        )
                    })}
                </select>
            </div>
            <div>
                <CardGroup className='mt-3'>
                    {doctors.map((doctor) => {
                        return (
                            <Card key={doctor.id}>
                                <CardContent>
                                    <Image
                                        floated='right'
                                        size='tiny'
                                        src={doctor.imageUrl}
                                    />
                                    <CardHeader>{truncateText(doctor.name, 30)}</CardHeader>
                                    <CardMeta>{truncateText(doctor.specializationName, 30)}</CardMeta>
                                    <CardDescription>
                                        Frühester Termin am <strong>1.10.2024</strong>
                                    </CardDescription>
                                </CardContent>
                                <CardContent extra>
                                    <Link to={`/appointments/date-select/${doctor.id}`} className="ui basic green button fluid">
                                        Wählen
                                    </Link>
                                </CardContent>
                            </Card>
                        )
                    })}
                </CardGroup>

            </div>
            {errorMessage && (
                <div className="alert alert-danger mt-3" role="alert">
                    {errorMessage}
                </div>
            )}
        </div>
    );
}

export default DoctorSelect;