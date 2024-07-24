import {Route, Routes} from "react-router-dom";
import HomePage from "../pages/HomePage.tsx";
import Footer from "./Footer.tsx";
import SpecializationSelect from "../pages/SpecializationSelect.tsx";


function UserPages() {
    return (
        <div>
            <Routes>
                <Route path={'/'} element={<HomePage/>}/>
                <Route path={'/appointments/add'} element={<SpecializationSelect/>}/>
            </Routes>
            <Footer/>
        </div>
    );
}

export default UserPages;