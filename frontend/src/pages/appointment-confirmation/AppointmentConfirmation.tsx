import './AppointmentConfirmation.css'
import {useNavigate, useParams} from "react-router-dom";
import TimeSlotService from "../../services/TimeSlotService.ts";
import {useEffect, useState} from "react";
import {TimeSlot} from "../../types/TimeSlot.ts";

const timeSlotService = new TimeSlotService();

function AppointmentConfirmation() {
    const { timeSlotId = ''} = useParams<string>();
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);
    const [timeSlot, setTimeSlot] = useState<TimeSlot>();

    useEffect(() => {
        if (timeSlotId) {
            timeSlotService.getTimeSlotById(timeSlotId)
                .then((response) => {
                    setTimeSlot(response.data);
                    setLoading(false);
                })
                .catch((error) => {
                    console.error('Error fetching product:', error);
                    navigate('*');
                });
        }
    }, [timeSlotId, navigate]);

    if (loading) {
        return <div className={'container mt-3'}>
            <div className={'spinner-border text-primary'}>
                <span className={'visually-hidden'}></span>
            </div>
            <h5>Wird geledan...</h5>
        </div>;
    }

    return (
        <div className="body">
            <div className="confirmation-container">
                <div className="checkmark-circle">
                    <div className="checkmark"></div>
                </div>
                <h2 className="confirm-h2">Ihr Termin wurde erfolgreich gespeichert!</h2>
                <h3>Ihr Bestätigungscode ist <strong className="text-bg-success">{timeSlot?.verificationCode}</strong></h3>
            </div>
        </div>
    );
}

export default AppointmentConfirmation;