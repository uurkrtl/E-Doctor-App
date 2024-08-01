import TimeSlotService from "../services/TimeSlotService.ts";
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {AxiosError} from "axios";

const timeSlotService = new TimeSlotService();

function CancelAppointmentSelect() {
    const [contact, setContact] = useState("");
    const [errorMessage, setErrorMessage] = useState<string>('');
    const [verificationCode, setVerificationCode] = useState<string>('');
    const navigate = useNavigate();

    const getTimeSlot = async () => {
        try {
            const response = await timeSlotService.getTimeSlotByVerificationCode(contact, verificationCode);
            const timeSlotId = response.data.id;
            console.log(timeSlotId);
            return timeSlotId;
        } catch (error) {
            console.log('Patient error:' + error);
            throw error;
        }
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const timeSlotId = await getTimeSlot();
            setErrorMessage('');
            navigate(`/appointments/appointment-cancel/${timeSlotId}`);
        } catch (responseError) {
            if (responseError instanceof AxiosError && responseError.response) {
                console.log(responseError.response.data);
                setErrorMessage(responseError.response.data.message);
            } else {
                console.log('Something went wrong:', responseError instanceof Error ? responseError.message : 'Unknown error');
                setErrorMessage('Something went wrong: ' + (responseError instanceof Error ? responseError.message : 'Unknown error'));
            }
        }
    };

    return (
        <div className="container py-5 text-center">

            <form onSubmit={handleSubmit}>
                <div className="row g-3">
                    <div className="col-sm-6">
                        <label htmlFor="contact" className="form-label">Handynummer</label>
                        <input
                            type="text"
                            className="form-control"
                            id="basic-url"
                            aria-describedby="basic-addon3 basic-addon4"
                            onChange={(e) => setContact(e.target.value)}
                        />
                    </div>
                    <div className="col-sm-6">
                        <label htmlFor="contact" className="form-label">Bestätigungscode</label>
                        <input
                            type="text"
                            className="form-control"
                            id="basic-url"
                            aria-describedby="basic-addon3 basic-addon4"
                            onChange={(e) => setVerificationCode(e.target.value)}
                        />
                    </div>
                    <button className="w-100 btn btn-primary btn-lg mt-3" type="submit">
                        Termin ansehen
                    </button>
                </div>
            </form>


            {errorMessage && (
                <div className="alert alert-danger mt-3" role="alert">
                    {errorMessage}
                </div>
            )}

        </div>
    );
}

export default CancelAppointmentSelect;