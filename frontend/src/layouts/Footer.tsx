import {Link} from "react-router-dom";

function Footer() {
    return (
        <div>
            <div className="container">
                <footer className="py-3 my-4">
                    <ul className="nav justify-content-center border-bottom pb-3 mb-3">
                        <li className="nav-item"><a href="#" className="nav-link px-2 text-body-secondary">Home</a></li>
                        <li className="nav-item"><a href="#" className="nav-link px-2 text-body-secondary">Features</a>
                        </li>
                        <li className="nav-item"><a href="#" className="nav-link px-2 text-body-secondary">Pricing</a>
                        </li>
                        <li className="nav-item"><a href="#" className="nav-link px-2 text-body-secondary">FAQs</a></li>
                        <li className="nav-item"><a href="#" className="nav-link px-2 text-body-secondary">About</a>
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