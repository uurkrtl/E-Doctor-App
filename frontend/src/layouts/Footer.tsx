import {Link} from "react-router-dom";

function Footer() {
    return (
        <div>
            <div className="container">
                <footer className="py-3 my-4">
                    <ul className="nav justify-content-center border-bottom pb-3 mb-3">
                        <li className="nav-item">
                            <Link to='/' className="nav-link px-2 text-body-secondary">
                                Startseite
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link to='/appointments/doctor-select' className="nav-link px-2 text-body-secondary">
                                Neuer Termin
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link to='/appointments/appointment-cancel' className="nav-link px-2 text-body-secondary">
                                Termin stornieren
                            </Link>
                        </li>
                    </ul>
                    <p className="text-center text-body-secondary">
                        <Link to={`http://ugurkartal.de/`} target="_blank"
                              className="text-muted text-decoration-none lh-1">
                            <span>© 2024 Ugur Kartal</span>
                        </Link>
                    </p>
                </footer>
            </div>
        </div>
    );
}

export default Footer;