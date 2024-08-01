import TimeSlotService from "../services/TimeSlotService.ts";
import {Link, useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import CustomIcon from "../layouts/CustomIcon.tsx";
import {TimeSlot} from "../types/TimeSlot.ts";
import PatientService from "../services/PatientService.ts";
import {PatientRequest} from "../types/PatientRequest.ts";
import {AxiosError} from "axios";
import {Icon} from "semantic-ui-react";
import DoctorService from "../services/DoctorService.ts";
import {Doctor} from "../types/Doctor.ts";

const timeSlotService = new TimeSlotService();
const patientService = new PatientService();
const doctorService = new DoctorService();

function PatientSelect() {
    const { timeSlotId = ''} = useParams<string>();
    const [errorMessage, setErrorMessage] = useState<string>('');
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();
    const [patientRequest, setPatientRequest] = useState<PatientRequest>({
        name:'',
        contact:''
    });
    const [timeSlot, setTimeSlot] = useState<TimeSlot>({
        id: '',
        date: '',
        time: '',
        available: true,
        doctorId: '',
        patientId: '',
        verificationCode: ''
    });
    const [doctor, setDoctor] = useState<Doctor>({
        id:'',
        name:'',
        imageUrl:'',
        specializationId:'',
        specializationName:'',
        createdAt: new Date(),
        updatedAt: new Date(),
        active:true
    });

    const truncateText = (text: string, maxLength: number) => {
        if (text.length > maxLength) {
            return text.substring(0, maxLength);
        }
        return text;
    };

    const savePatient = async () => {
        try {
            const response = await patientService.addPatient(patientRequest);
            const patientId = response.data.id;
            console.log(patientId);
            return patientId;
        } catch (error) {
            console.log('Patient error:' + error);
            throw error;
        }
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const patientId = await savePatient();
            await timeSlotService.addPatientId(timeSlotId, patientId);
            setErrorMessage('');
            navigate(`/appointments/appointment-confirmation/${timeSlotId}`);
            console.log('Time slot updated successfully');
        } catch (error) {
            if (error instanceof AxiosError && error.response) {
                console.log(error.response.data);
                setErrorMessage(error.response.data.message);
            } else {
                console.log('Something went wrong:', error instanceof Error ? error.message : 'Unknown error');
                setErrorMessage('Something went wrong: ' + (error instanceof Error ? error.message : 'Unknown error'));
            }
        }
    };


    useEffect(() => {
        if (timeSlotId) {
            timeSlotService.getTimeSlotById(timeSlotId)
                .then((response) => {
                    setTimeSlot(response.data);
                    setLoading(false);
                })
                .catch((error) => {
                    console.error('Error fetching appointment:', error);
                    navigate('*');
                });
        }
    }, [timeSlotId, navigate]);

    useEffect(() => {
        if (timeSlot) {
            doctorService.getDoctorById(timeSlot.doctorId)
                .then((response) => {
                    setDoctor(response.data);
                    setLoading(false);
                })
        }
    }, [timeSlot]);

    if (loading) {
        return <div className={'container mt-3'}>
            <div className={'spinner-border text-primary'}>
                <span className={'visually-hidden'}></span>
            </div>
            <h5>Wird geledan...</h5>
        </div>;
    }

    return (
        <div className="container">
            <main>
                <div className="py-5 text-center">
                    <CustomIcon pageName='PatientSelect' />
                    <h2>Fast geschafft</h2>
                    <p className="lead">Nachdem Sie Ihre Termininformationen überprüft haben, geben Sie Ihren Namen und Ihre Handynummer ein.</p>
                </div>
                <div className="row g-5">
                    <div className="col-md-5 col-lg-4 order-md-last">
                        <h4 className="d-flex justify-content-between align-items-center mb-3">
                            <span className="text-primary">Ihr Termin</span>
                        </h4>
                        <ul className="list-group mb-3">
                            <li className="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 className="my-0">Fachgebiet</h6>
                                </div>
                                <span className="text-body-secondary">{doctor.specializationName}</span>
                            </li>
                            <li className="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 className="my-0">Arzt/Ärztin</h6>
                                </div>
                                <span className="text-body-secondary">{doctor.name}</span>
                            </li>
                            <li className="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 className="my-0">Datum</h6>
                                </div>
                                <span className="text-body-secondary">{timeSlot.date ? new Date(timeSlot.date).toLocaleDateString('de-DE') : "-"}</span>
                            </li>
                            <li className="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 className="my-0">Zeit</h6>
                                </div>
                                <span className="text-body-secondary">{truncateText(timeSlot.time,5)}</span>
                            </li>
                        </ul>
                    </div>

                    <div className="col-md-7 col-lg-8">
                        <h4 className="mb-3">Informationen zum Patienten</h4>
                        <form onSubmit={handleSubmit}>
                            <div className="row g-3">
                                <div className="col-sm-6">
                                    <label htmlFor="name" className="form-label">Name</label>
                                    <input type="text" className="form-control" id="name"
                                           onChange={(e) => setPatientRequest({
                                               ...patientRequest,
                                               name: e.target.value
                                           })}/>
                                </div>
                                <div className="col-sm-6">
                                    <label htmlFor="contact" className="form-label">Handynummer</label>
                                    <input type="text" className="form-control" id="contact"
                                           onChange={(e) => setPatientRequest({
                                               ...patientRequest,
                                               contact: e.target.value
                                           })}/>
                                </div>
                            </div>
                            <div className="row g-5">
                                <div className="col-md-5 col-lg-4">
                                    <Link className="w-100 btn btn-secondary btn-lg mt-3" to={`/appointments/date-select/${timeSlot.doctorId}`}>
                                        <Icon name='angle double left'/>
                                        Zurück
                                    </Link>
                                </div>
                                <div className="col-md-7 col-lg-8">
                                    <button className="w-100 btn btn-primary btn-lg mt-3" type="submit">
                                        Termin speichern
                                        <Icon name='check circle'/>
                                    </button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </main>
            {errorMessage && (
                <div className="alert alert-danger" role="alert">
                    {errorMessage}
                </div>
            )}
        </div>
    );
}

export default PatientSelect;