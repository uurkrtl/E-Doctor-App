import {Route, Routes} from "react-router-dom";
import HomePage from "../pages/HomePage.tsx";
import Footer from "./Footer.tsx";
import DoctorSelect from "../pages/DoctorSelect.tsx";
import DateSelect from "../pages/DateSelect.tsx";
import PatientSelect from "../pages/PatientSelect.tsx";

function UserPages() {
    return (
        <div>
            <Routes>
                <Route path={'/'} element={<HomePage/>}/>
                <Route path={'/appointments/doctor-select'} element={<DoctorSelect/>}/>
                <Route path={'/appointments/date-select/:doctorId'} element={<DateSelect/>}/>
                <Route path={'/appointments/name-enter/:timeSlotId'} element={<PatientSelect/>}/>
            </Routes>
            <Footer/>
        </div>
    );
}

export default UserPages;