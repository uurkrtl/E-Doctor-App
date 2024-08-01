import TimeSlotService from "../services/TimeSlotService.ts";
import {useEffect, useState} from "react";
import {TimeSlot} from "../types/TimeSlot.ts";
import {Link, useNavigate, useParams} from "react-router-dom";
import {Icon} from "semantic-ui-react";

const timeSlotService = new TimeSlotService();

function CancelAppointmentConfirmation() {
    const [timeSlot, setTimeSlot] = useState<TimeSlot>({
        id: '',
        date: '',
        time: '',
        available: true,
        doctorId: '',
        patientId: '',
        verificationCode: ''
    });
    const { timeSlotId = ''} = useParams<string>();
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();
    const [errorMessage, setErrorMessage] = useState<string>('');

    const truncateText = (text: string, maxLength: number) => {
        if (text.length > maxLength) {
            return text.substring(0, maxLength);
        }
        return text;
    };

    const handleRemovePatient = () => {
        timeSlotService.removePatientId(timeSlotId)
            .then(() => {
                setErrorMessage('')
            }).catch((error) => {
            if (error.response) {
                setErrorMessage(error.response.data.message)
            } else {
                setErrorMessage('Error: ' + error.message);
            }
        });
    }

    const handleGoHome = () => {
        navigate('/');
    }

    useEffect(() => {
        if (timeSlotId) {
            timeSlotService.getTimeSlotById(timeSlotId)
                .then((response) => {
                    setTimeSlot(response.data);
                    setLoading(false);
                })
                .catch((error) => {
                    console.error('Fehler beim Abrufen des Termins:', error);
                    setErrorMessage(`Fehler beim Abrufen des Termins: ${error}`)
                    navigate('*');
                });
        }
    }, [timeSlotId, navigate]);

    if (loading) {
        return <div className={'container mt-5'}>
            <div className={'spinner-border text-primary'}>
                <span className={'visually-hidden'}></span>
            </div>
            <h5>Wird geledan...</h5>
        </div>;
    }

    return (
        <div className="container py-5 text-center">
            <div className="row g-5">
                <div className="col-md-5 col-lg-4">
                    <h4 className="d-flex justify-content-between align-items-center mb-3">
                        <span className="text-primary">Ihr Termin</span>
                    </h4>
                    <ul className="list-group mb-3">
                        <li className="list-group-item d-flex justify-content-between lh-sm">
                            <div>
                                <h6 className="my-0">Datum</h6>
                            </div>
                            <span
                                className="text-body-secondary">{timeSlot.date ? new Date(timeSlot.date).toLocaleDateString('de-DE') : "-"}</span>
                        </li>
                        <li className="list-group-item d-flex justify-content-between lh-sm">
                            <div>
                                <h6 className="my-0">Zeit</h6>
                            </div>
                            <span className="text-body-secondary">{truncateText(timeSlot.time, 5)}</span>
                        </li>
                    </ul>
                </div>

                <div className="col-md-7 col-lg-8">
                    <h4 className="mb-3">Möchten Sie Ihren Termin absagen?</h4>
                    <div className="row g-5">
                        <div className="col-md-6 col-lg-6">
                            <Link className="w-100 btn btn-secondary btn-lg mt-3"
                                  to={`/`}>
                                <Icon name='home'/>
                                Zur Startseite
                            </Link>
                        </div>
                        <div className="col-md-6 col-lg-6">
                            <button type="button" className="w-100 btn btn-danger btn-lg mt-3" data-bs-toggle="modal"
                                    data-bs-target="#exampleModal" onClick={handleRemovePatient}>
                                <Icon name='cancel'/>
                                Absagen
                            </button>
                        </div>

                        <div className="modal fade" id="exampleModal" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <h1 className="modal-title fs-5" id="exampleModalLabel">Bestätigung</h1>
                                    </div>
                                    <div className="modal-body">
                                        Ihr Termin wurde abgesagt!
                                    </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-primary"
                                                data-bs-dismiss="modal" onClick={handleGoHome}>Okay
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            {errorMessage && (
                <div className="alert alert-danger" role="alert">
                    {errorMessage}
                </div>
            )}
        </div>
    );
}

export default CancelAppointmentConfirmation;