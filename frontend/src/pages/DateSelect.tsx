import TimeSlotService from "../services/TimeSlotService.ts";
import {Link, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {TimeSlot} from "../types/TimeSlot.ts";
import DoctorService from "../services/DoctorService.ts";
import {Doctor} from "../types/Doctor.ts";
import {
    CardMeta,
    CardHeader,
    CardGroup,
    CardContent,
    Card,
    Button,
    Icon
} from 'semantic-ui-react';
import DatePicker from "react-datepicker";
import CustomIcon from "../layouts/CustomIcon.tsx";

const timeSlotService = new TimeSlotService();
const doctorService = new DoctorService();

function DateSelect() {
    const { doctorId = '' } = useParams<string>();
    const [loading, setLoading] = useState(true);
    const [errorMessage, setErrorMessage] = useState<string>('');
    const [timeSlots, setTimeSlots] = useState<TimeSlot[]>([]);
    const [selectedDate, setSelectedDate] = useState<Date | null>(new Date());
    const [selectedTimeSlot, setSelectedTimeSlot] = useState<TimeSlot | undefined>();
    const [doctor, setDoctor] = useState<Doctor>({
        id:'',
        name:'',
        imageUrl:'',
        specializationId:'',
        specializationName:'',
        createdAt: new Date(),
        updatedAt: new Date(),
        active:true
    })

    const truncateText = (text: string, maxLength: number) => {
        if (text.length > maxLength) {
            return text.substring(0, maxLength);
        }
        return text;
    };

    const handleTimeSlotChange = (e: React.MouseEvent<HTMLInputElement>) => {
        const selectedSlotId = (e.target as HTMLInputElement).id;
        setSelectedTimeSlot(timeSlots.find(timeSlot => timeSlot.id === selectedSlotId));
    }

    const handleDateFilter = (handleDate: Date | null) => {
        setSelectedDate(handleDate);
        setSelectedTimeSlot(undefined);
    }

    useEffect(() => {
        doctorService.getDoctorById(doctorId).then((response) => {
            setDoctor(response.data);
            setLoading(false)
        }).catch((error) => {
            setErrorMessage(`Fehler beim Abrufen vom Arzt: ${error}`)
            setLoading(false)
        });
    }, [doctorId]);

    useEffect(() => {
        if (selectedDate) {
            timeSlotService.getTimeSlotsByDate(doctorId, `${selectedDate.toISOString().split('T')[0]}`).then((response) => {
                setTimeSlots(response.data);
                setLoading(false)
            }).catch((error) => {
                setErrorMessage(`Fehler beim Abrufen der Zeitfenster: ${error}`)
                setLoading(false)
            });
        }
    }, [doctorId, selectedDate]);

    if (loading) {
        return <div className={'container mt-3'}>
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
                    <CustomIcon pageName='DateSelect'/>
                    <h2>Terminauswahl</h2>
                    <p className="lead">Unten können Sie das Datum und die Uhrzeit für Ihren Termin auswählen.</p>
                </div>
                <div className="row g-3">
                    <div className="col-md-5 col-lg-4">
                        <CardGroup>
                        <Card>
                                <CardContent>
                                    <CardMeta>Fachgebiet</CardMeta>
                                    <CardHeader>{doctor.specializationName}</CardHeader>
                                    <CardMeta>Arzt/Ärztin</CardMeta>
                                    <CardHeader>{doctor.name}</CardHeader>
                                    <CardMeta>Datum</CardMeta>
                                    <CardHeader>{selectedDate ? new Date(selectedDate).toLocaleDateString('de-DE') : "-"}</CardHeader>
                                    <CardMeta>Zeit</CardMeta>
                                    {selectedTimeSlot && (
                                        <CardHeader>{truncateText(selectedTimeSlot.time, 5)}</CardHeader>
                                    )}
                                </CardContent>
                            </Card>
                        </CardGroup>
                    </div>
                    <div className="col-md-5 col-lg-8">
                        <div className="form-check-inline mb-3">
                            <label className="form-check-label" htmlFor="purchaseStartDate">Datum vom Termin:</label>
                            <DatePicker className="form-control mx-2" id="purchaseEndDate"
                                        selected={selectedDate}
                                        onChange={(date: Date | null) => handleDateFilter(date)}
                                        dateFormat={'dd.MM.yyyy'}
                                        minDate={new Date()}
                                        maxDate={new Date('2024-10-01')}/>
                        </div>
                        <p>Wählen Sie die Zeit:</p>
                        {timeSlots.map((timeSlot) => {
                            return (
                                <div className="form-check-inline" key={timeSlot.id}>
                                    <input
                                        className="form-check-input mt-2"
                                        type="radio"
                                        name="flexRadioDefault"
                                        id={timeSlot.id}
                                        onClick={handleTimeSlotChange}
                                        disabled={timeSlot.status != 'AVAILABLE'}
                                    />
                                    <label className="form-check-label" htmlFor={timeSlot.id}>
                                        <span
                                            className={`badge rounded-pill mx-1 mt-2 text-bg-${timeSlot.status == 'AVAILABLE' ? "success" : "danger"}`}>
                                            {truncateText(timeSlot.time, 5)}
                                        </span>
                                    </label>
                                </div>
                            )
                        })}
                    </div>
                    <div className="mt-4">
                        <Button as={Link} to={`/appointments/doctor-select/${doctor.specializationId}`} icon labelPosition='left'>
                            <Icon name='arrow left'/>
                            Zurück
                        </Button>
                        <Button as={Link} to={`/appointments/name-enter/${selectedTimeSlot?.id}`} primary icon
                                labelPosition='right' disabled={selectedTimeSlot==null}>
                            Nächste
                            <Icon name={"arrow right"}/>
                        </Button>
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

export default DateSelect;