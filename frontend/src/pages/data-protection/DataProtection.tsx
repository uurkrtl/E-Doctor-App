import './DataProtection.css'

function DataProtection() {
    return (
        <body className="container">
            <header className="header-type">
                <div>
                    <h1 className="text-black">Datenschutzerklärung</h1>
                </div>
            </header>
            <div className="mt-3">
                <h2>1. Erhebung und Speicherung personenbezogener Daten sowie Art und Zweck von deren Verwendung</h2>
                <p>Wir erheben, speichern und verarbeiten personenbezogene Daten unserer Nutzer ausschließlich,
                    um die Funktionalität der Anwendung zu gewährleisten.
                    Hierbei handelt es sich insbesondere um die von Ihnen eingegebenen Daten.</p>
                <h3>Datenarten:</h3>
                <ul>
                    <li>Name</li>
                    <li>E-Mail-Adresse</li>
                    <li>Telefonnummer</li>
                    <li>Terminvereinbarungen</li>
                    <li>Andere von Ihnen zur Verfügung gestellte Daten</li>
                </ul>

                <h2>2. Zweck der Verarbeitung</h2>
                <p>Die Verarbeitung der von Ihnen eingegebenen Daten erfolgt zu folgenden Zwecken:</p>
                <ul>
                    <li>Verwaltung und Bearbeitung von Terminvereinbarungen</li>
                    <li>Kommunikation mit Nutzern</li>
                    <li>Sicherstellung der Funktionalität der Anwendung</li>
                </ul>

                <h2>3. Rechte der Nutzer</h2>
                <p>Sie haben das Recht, Auskunft über die von uns gespeicherten Daten zu erhalten und die Löschung Ihrer
                    Daten zu verlangen.</p>

                <h2>4. Kontakt</h2>
                <p>Bei Fragen zum Datenschutz können Sie uns unter der im Impressum angegebenen E-Mail-Adresse
                    kontaktieren.</p>
            </div>
        </body>
    );
}

export default DataProtection;