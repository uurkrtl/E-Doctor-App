import {Route, Routes} from "react-router-dom";
import HomePage from "../pages/HomePage.tsx";
import Footer from "./Footer.tsx";
import DoctorSelect from "../pages/DoctorSelect.tsx";
import DateSelect from "../pages/DateSelect.tsx";
import PatientSelect from "../pages/PatientSelect.tsx";
import AppointmentConfirmation from "../pages/appointment-confirmation/AppointmentConfirmation.tsx";
import PageNotFound404 from "../pages/not-found-404/PageNotFound404.tsx";

function UserPages() {
    return (
        <div>
            <Routes>
                <Route path={'/'} element={<HomePage/>}/>
                <Route path={'/appointments/doctor-select/:specializationId?'} element={<DoctorSelect/>}/>
                <Route path={'/appointments/date-select/:doctorId'} element={<DateSelect/>}/>
                <Route path={'/appointments/name-enter/:timeSlotId'} element={<PatientSelect/>}/>
                <Route path={'/appointments/appointment-confirmation/:timeSlotId'} element={<AppointmentConfirmation/>}/>
                <Route path={'*'} element={<PageNotFound404/>}/>
            </Routes>
            <Footer/>
        </div>
    );
}

export default UserPages;