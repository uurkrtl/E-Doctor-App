# E-Doctor-App

E-Doctor-App ist eine Webanwendung, die es Nutzern ermöglicht, Arzttermine einfach zu buchen und zu verwalten.

## Funktionen

- Erstellung und Verwaltung von Arztterminen
- Benutzerfreundliche Oberfläche
- Erhalt eines Bestätigungscodes für Termine

## Installation

1. **Projektklon:** Klonen Sie das Projekt von GitHub:
    ```bash
    git clone https://github.com/uurkrtl/E-Doctor-App.git
    ```
2. **Abhängigkeiten:** Navigieren Sie zum Projektverzeichnis und installieren Sie die Abhängigkeiten:
    ```bash
    cd E-Doctor-App/backend
    mvn install
    ```

   ```bash
    cd E-Doctor-App/frontend
    npm install
    ```
3. **DB URI:** Fügen Sie Ihren Mongo DB URI in die `application.properties`-Datei ein:
    ```properties
    spring.data.mongodb.uri=YOUR_MONGODB_URI
    ```
4. **Anwendung starten:**
    ```bash
    mvn spring-boot:run
    npm start
    ```

## Nutzung

- Wählen Sie einen Arzt und buchen Sie einen Termin.
- Verwalten Sie Ihre Termine und erhalten Sie Ihren Bestätigungscode.

## Technologien

- **Backend:** Java 21, Spring Boot 3
- **Frontend:** React 18, Typescript 5, Bootstrap 5, Semantic UI React 2
- **Database** MongoDB Atlas

## Datenschutzrichtlinie

- **Cookies:** Die Anwendung verwendet keine Cookies.

## Rechtliche Informationen

- **AGB:** Allgemeine Geschäftsbedingungen der Anwendung.
- **Datenschutzerklärung:** Datenschutz- und Datenverwendungsrichtlinien.
- **Impressum:** Gesetzliche Kontaktinformationen.

## Kontakt

Für Fragen senden Sie bitte eine E-Mail an [info at ugurkartal.de](mailto:info@ugurkartal.de).

## Beitrag

Um einen Beitrag zu leisten, senden Sie bitte einen Pull-Request.

## Lizenz

Dieses Projekt ist unter der MIT-Lizenz lizenziert. Weitere Informationen finden Sie in der `LICENSE`-Datei.

---

Besuchen Sie das Projekt: [E-Doctor-App](https://filmempfehlungen.com)