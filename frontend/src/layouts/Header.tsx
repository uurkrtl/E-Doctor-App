import { Link } from "react-router-dom";


function Header() {
    return (
        <div>
            <div className="px-4 pt-5 my-5 text-center border-bottom">
                <h1 className="display-4 fw-bold text-body-emphasis">Finden Sie Termine einfach</h1>
                <div className="col-lg-6 mx-auto">
                    <p className="lead mb-4">Willkommen bei Ihrer Arzttermin-Buchungsplattform! Finden Sie ganz
                        einfach verfügbare Termine und buchen Sie noch heute.</p>
                    <div className="d-grid gap-2 d-sm-flex justify-content-sm-center mb-5">
                        <Link to={"/appointments/doctor-select"} className="btn btn-primary btn-lg px-4 me-sm-3">Termin suchen</Link>
                        <button type="button" className="btn btn-outline-secondary btn-lg px-4">Über uns</button>
                    </div>
                </div>
                <div className="overflow-hidden" style={{maxHeight: '30vh'}}>
                    <div className="container px-5">
                        <img src="https://i.ibb.co/CWQ5JcK/doctors.jpg" className="img-fluid border rounded-3 shadow-lg mb-4"
                             alt="Example" width="700" height="500" loading="lazy"/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Header;