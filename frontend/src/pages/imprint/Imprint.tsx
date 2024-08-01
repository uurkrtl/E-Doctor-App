import './Imprint.css'

function Imprint() {
    return (
        <body className="container imprint-body">
            <header className="header-type">
                <div>
                    <h1 className="text-black">Impressum</h1>
                </div>
            </header>
            <div className="mt-3">
                <h2>Anbieter</h2>
                <p>Uğur Kartal</p>

                <h2>Kontakt</h2>
                <p>E-Mail: info [at] ugurkartal.de</p>
                <p>Website: ugurkartal.de</p>

                <h2>Haftungsausschluss</h2>
                <p>Trotz sorgfältiger inhaltlicher Kontrolle übernehmen wir keine Haftung für die Inhalte externer
                    Links. Für den Inhalt der verlinkten Seiten sind ausschließlich deren Betreiber verantwortlich.</p>
            </div>
        </body>
    );
}

export default Imprint;