package de.earzt.backend.services.messages;

public class PatientMessage {
    private PatientMessage() {}

    public static final String PATIENT_NOT_FOUND = "Patient not found";
    public static final String PATIENT_PASSIVE = "Patient is passive. You cannot choose a passive patient";
    public static final String PATIENT_HAVE_ACTIVE_APPOINTMENT = "Patient have active appointment(s)";
}
