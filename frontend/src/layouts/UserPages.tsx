import {Route, Routes} from "react-router-dom";
import HomePage from "../pages/HomePage.tsx";
import Footer from "./Footer.tsx";
import DoctorSelect from "../pages/DoctorSelect.tsx";
import DateSelect from "../pages/DateSelect.tsx";
import PatientSelect from "../pages/PatientSelect.tsx";
import AppointmentConfirmation from "../pages/appointment-confirmation/AppointmentConfirmation.tsx";
import PageNotFound404 from "../pages/not-found-404/PageNotFound404.tsx";
import CancelAppointmentSelect from "../pages/CancelAppointmentSelect.tsx";
import CancelAppointmentConfirmation from "../pages/CancelAppointmentConfirmation.tsx";
import Imprint from "../pages/imprint/Imprint.tsx";
import DataProtection from "../pages/data-protection/DataProtection.tsx";
import GeneralTermsAndConditions from "../pages/general-terms-and-conditions/GeneralTermsAndConditions.tsx";

function UserPages() {
    return (
        <div>
            <Routes>
                <Route path={'/'} element={<HomePage/>}/>
                <Route path={'/appointments/doctor-select/:specializationId?'} element={<DoctorSelect/>}/>
                <Route path={'/appointments/date-select/:doctorId'} element={<DateSelect/>}/>
                <Route path={'/appointments/name-enter/:timeSlotId'} element={<PatientSelect/>}/>
                <Route path={'/appointments/appointment-confirmation/:timeSlotId'} element={<AppointmentConfirmation/>}/>
                <Route path={'/appointments/appointment-cancel'} element={<CancelAppointmentSelect/>}/>
                <Route path={'/appointments/appointment-cancel/:timeSlotId'} element={<CancelAppointmentConfirmation/>}/>
                <Route path={'/general-terms-and-conditions'} element={<GeneralTermsAndConditions/>}/>
                <Route path={'/data-protection'} element={<DataProtection/>}/>
                <Route path={'/imprint'} element={<Imprint/>}/>
                <Route path={'*'} element={<PageNotFound404/>}/>
            </Routes>
            <Footer/>
        </div>
    );
}

export default UserPages;