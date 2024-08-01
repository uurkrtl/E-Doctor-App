import './GeneralTermsAndConditions.css'
import {Link} from "react-router-dom";

function GeneralTermsAndConditions() {
    return (
    <body className="container">
        <header className="header-type">
            <div>
                <h1 className="text-black">AGB (Allgemeine Geschäftsbedingungen)</h1>
            </div>
        </header>
        <div className="mt-3">
            <h2>1. Geltungsbereich</h2>
            <p>Diese Allgemeinen Geschäftsbedingungen (AGB) gelten für die Nutzung der E-Doctor-App und die damit
                verbundenen Dienstleistungen.</p>

            <h2>2. Vertragspartner</h2>
            <p>Vertragspartner sind der Betreiber der E-Doctor-App und der Nutzer der Anwendung.</p>

            <h2>3. Leistungen der E-Doctor-App</h2>
            <p>Die E-Doctor-App ermöglicht es Nutzern, Arzttermine zu vereinbaren und zu verwalten.
                Die Nutzung der App ist kostenlos.</p>

            <h2>4. Registrierung</h2>
            <p>Für die Nutzung der E-Doctor-App ist eine Registrierung erforderlich.
                Die bei der Registrierung angegebenen Daten müssen wahrheitsgemäß und vollständig sein.</p>

            <h2>5. Pflichten des Nutzers</h2>
            <p>Der Nutzer verpflichtet sich, die App nur in Übereinstimmung mit diesen AGB und den geltenden Gesetzen zu
                nutzen.
                Es ist untersagt, die App für illegale oder missbräuchliche Zwecke zu verwenden.</p>
            <h2>6. Datenschutz</h2>
            <p>Der Schutz der personenbezogenen Daten des Nutzers hat höchste Priorität.
                Weitere Informationen finden Sie in unserer <Link to={'/data-protection'}>Datenschutzerklärung</Link>.</p>

            <h2>7. Haftung</h2>
            <p>Der Betreiber haftet nicht für direkte oder indirekte Schäden,
                die durch die Nutzung der E-Doctor-App entstehen,
                außer im Falle von Vorsatz oder grober Fahrlässigkeit.</p>

            <h2>8. Änderungen der AGB</h2>
            <p>Der Betreiber behält sich das Recht vor, diese AGB jederzeit zu ändern.
                Änderungen werden dem Nutzer rechtzeitig mitgeteilt.</p>

            <h2>9. Schlussbestimmungen</h2>
            <p>Sollte eine Bestimmung dieser AGB unwirksam sein oder werden,
                so bleibt die Wirksamkeit der übrigen Bestimmungen unberührt.
                Es gilt das Recht der Bundesrepublik Deutschland.</p>
        </div>
    </body>
    );
}

export default GeneralTermsAndConditions;