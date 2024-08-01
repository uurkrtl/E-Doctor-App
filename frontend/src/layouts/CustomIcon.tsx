function CustomIcon({pageName}: Readonly<{ pageName:string }>) {
    let enableBackground;
    let viewBox;
    let id;
    let height;
    let width;
    let path1;
    let path2;
    let path3;

    switch (pageName) {
        case 'PatientSelect':
            enableBackground = 'new 0 0 68 68';
            viewBox = '0 0 68 68';
            id = 'prescription';
            height = '72';
            width = '57';
            path1 = 'M27.1 24.4h30.4c.6 0 1-.4 1-1 0-.6-.4-1-1-1H27.1c-.6 0-1 .4-1 1C26.1 23.9 26.5 24.4 27.1 24.4zM37.3 17h20.2c.6 0 1-.4 1-1 0-.6-.4-1-1-1H37.3c-.6 0-1 .4-1 1C36.3 16.5 36.7 17 37.3 17zM37.3 9.6h20.2c.6 0 1-.4 1-1 0-.6-.4-1-1-1H37.3c-.6 0-1 .4-1 1C36.3 9.2 36.7 9.6 37.3 9.6zM27.1 31.6h30.4c.6 0 1-.4 1-1 0-.6-.4-1-1-1H27.1c-.6 0-1 .4-1 1C26.1 31.1 26.5 31.6 27.1 31.6zM27.1 38.8h30.4c.6 0 1-.4 1-1 0-.6-.4-1-1-1H27.1c-.6 0-1 .4-1 1C26.1 38.3 26.5 38.8 27.1 38.8zM27.1 46h30.4c.6 0 1-.4 1-1 0-.6-.4-1-1-1H27.1c-.6 0-1 .4-1 1C26.1 45.5 26.5 46 27.1 46zM27.1 53.2h30.4c.6 0 1-.4 1-1 0-.6-.4-1-1-1H27.1c-.6 0-1 .4-1 1C26.1 52.8 26.5 53.2 27.1 53.2zM27.1 60.4h30.4c.6 0 1-.4 1-1 0-.6-.4-1-1-1H27.1c-.6 0-1 .4-1 1C26.1 60 26.5 60.4 27.1 60.4z';
            path2 = 'M12.6,66.9c2.4,0,4.7-0.8,6.5-2.2c1,1.4,2.7,2.2,4.4,2.2h37.2c3.1,0,5.5-2.5,5.5-5.5V6.6c0-3.1-2.5-5.5-5.5-5.5\n' +
                '\t\tC20.6,1,32.6,0.9,30.3,1c-0.3,0-0.5,0.1-0.7,0.3L18.2,12.7c-0.2,0.2-0.3,0.4-0.3,0.7c0,0.3,0,7.6,0,10.2c-1.6-0.9-3.4-1.4-5.4-1.4\n' +
                '\t\tc-5.9,0-10.8,4.8-10.8,10.8v23.3C1.8,62.1,6.6,66.9,12.6,66.9z M29.3,4.4v8h-8L29.3,4.4z M19.9,14.4h10.4c0.6,0,1-0.4,1-1V3h29.3\n' +
                '\t\tc2,0,3.5,1.6,3.5,3.5v54.9c0,2-1.6,3.5-3.5,3.5H23.5c-1.2,0-2.3-0.6-3-1.6c1.8-1.9,2.8-4.5,2.8-7.3V32.9c0-3.1-1.3-5.9-3.4-7.8\n' +
                '\t\tV14.4z M12.6,24.1c4.8,0,8.8,3.9,8.8,8.8v10.6H3.8V32.9C3.8,28,7.7,24.1,12.6,24.1z M3.8,45.5h17.5v10.6c0,4.8-3.9,8.8-8.8,8.8\n' +
                '\t\tc-4.8,0-8.8-3.9-8.8-8.8V45.5z'
            path3 = 'M12.6,28.1c2.6,0,4.8,2.1,4.8,4.8c0,0.6,0.4,1,1,1c0.6,0,1-0.4,1-1c0-3.7-3-6.8-6.8-6.8c-0.6,0-1,0.4-1,1\n' +
                '\t\tS12,28.1,12.6,28.1z';
            break;

        case 'DoctorSelect':
            enableBackground = '';
            viewBox = '0 0 100 100';
            id = 'doctor';
            height = '72';
            width = '57';
            path1 = 'M18.75 94.75h62.5a1 1 0 0 0 1-1V75c0-15.19-9.41-25.8-22.87-25.8a1 1 0 0 0-.8.38C57 51.67 53.8 53 50.31 53.07c-3.68.07-7-1.24-8.92-3.52a1 1 0 0 0-.76-.35C27.16 49.2 17.75 59.81 17.75 75V93.75A1 1 0 0 0 18.75 94.75zm31.6-39.68a14.52 14.52 0 0 0 6.76-1.72L50 72.17l-7.14-18.9A14.63 14.63 0 0 0 50.35 55.07zM19.75 75c0-11.26 5.26-19.58 13.63-22.58v9.26a6.54 6.54 0 0 0-5.53 6.44V78.6a1 1 0 0 0 1 1h2.4v-2h-1.4V68.12a4.53 4.53 0 0 1 9 0V77.6H37.5v2h2.4a1 1 0 0 0 1-1V68.12a6.54 6.54 0 0 0-5.52-6.44V51.83a20.91 20.91 0 0 1 4.56-.61l9.12 24.13a1 1 0 0 0 1.88 0l9.12-24.13a21.2 21.2 0 0 1 5.44.85V64.76a4.17 4.17 0 1 0 2 0v-12C75.36 56 80.25 64.14 80.25 75V92.75H19.75zM66.5 66.63a2.17 2.17 0 1 1-2.17 2.17A2.17 2.17 0 0 1 66.5 66.63zM33.27 30.83h.35a16.6 16.6 0 0 0 32.76 0h.35a6.15 6.15 0 0 0 6.15-6.14v-1a3.82 3.82 0 0 0-3.82-3.81H66.63V14.69a9.45 9.45 0 0 0-9.44-9.44H42.81a9.44 9.44 0 0 0-9.43 9.44v5.19H30.94a3.82 3.82 0 0 0-3.81 3.81v1A6.14 6.14 0 0 0 33.27 30.83zm33.36-2.7V21.88h2.43a1.82 1.82 0 0 1 1.82 1.81v1a4.15 4.15 0 0 1-4.15 4.14h-.1zM50 42.75A14.63 14.63 0 0 1 36.08 32.59c.22-1.69 5.75-3.46 13.92-3.46a40.87 40.87 0 0 1 10 1.1c2.36.65 3.8 1.52 3.91 2.36A14.63 14.63 0 0 1 50 42.75zM35.38 20.88V14.69a7.44 7.44 0 0 1 7.43-7.44H57.19a7.45 7.45 0 0 1 7.44 7.44v8.14A6.17 6.17 0 0 1 60 21c-1.76-2-1.23-5.19-1.22-5.21a1 1 0 0 0-.58-1.1A1 1 0 0 0 57 15c-4.66 6.08-18.29 7.5-21.64 7.76zm0 3.91c3-.21 15.15-1.37 21.4-6.66a7.63 7.63 0 0 0 1.72 4.18 8.05 8.05 0 0 0 6.13 2.52v3.3a16 16 0 0 1-.15 2c-2.77-2.15-9-3-14.48-3s-11.71.81-14.48 3a16 16 0 0 1-.14-2zm-6.25-1.1a1.81 1.81 0 0 1 1.81-1.81h2.44v7h-.11a4.14 4.14 0 0 1-4.14-4.14z';
            path2 = ''
            path3 = '';
            break;

        case 'DateSelect':
            enableBackground = '';
            viewBox = '0 0 24 24';
            id = 'date';
            height = '72';
            width = '57';
            path1 = 'M19,4H17V3a1,1,0,0,0-2,0V4H9V3A1,1,0,0,0,7,3V4H5A3,3,0,0,0,2,7V19a3,3,0,0,0,3,3H19a3,3,0,0,0,3-3V7A3,3,0,0,0,19,4Zm1,15a1,1,0,0,1-1,1H5a1,1,0,0,1-1-1V12H20Zm0-9H4V7A1,1,0,0,1,5,6H7V7A1,1,0,0,0,9,7V6h6V7a1,1,0,0,0,2,0V6h2a1,1,0,0,1,1,1Z';
            path2 = ''
            path3 = '';
            break;

        case 'CancelAppointmentSelect':
            enableBackground = '';
            viewBox = '0 0 29 29';
            id = 'cancel';
            height = '72';
            width = '57';
            path1 = 'M14.637 27.065a12.457 12.457 0 0 1-8.838-3.655c-4.874-4.874-4.874-12.804 0-17.678a12.419 12.419 0 0 1 8.839-3.662c3.339 0 6.478 1.3 8.838 3.662 2.361 2.361 3.662 5.5 3.662 8.839s-1.3 6.478-3.662 8.839a12.46 12.46 0 0 1-8.839 3.655zm.001-22.995a10.428 10.428 0 0 0-7.425 3.076c-1.983 1.983-3.075 4.62-3.075 7.425s1.092 5.441 3.075 7.425c4.094 4.094 10.756 4.095 14.849 0 1.983-1.983 3.076-4.62 3.076-7.425s-1.092-5.441-3.076-7.425a10.432 10.432 0 0 0-7.424-3.076z';
            path2 = 'M10.395 19.813a.999.999 0 0 1-.707-1.707l8.485-8.485a.999.999 0 1 1 1.414 1.414l-8.485 8.485a.993.993 0 0 1-.707.293z'
            path3 = 'M18.88 19.813a.997.997 0 0 1-.707-.293l-8.485-8.485a.999.999 0 1 1 1.414-1.414l8.485 8.485a.999.999 0 0 1-.707 1.707z';
            break;

        case 'CancelAppointmentConfirmation':
            enableBackground = '';
            viewBox = '0 0 24 24';
            id = 'cancel';
            height = '72';
            width = '57';
            path1 = 'M14.1 10.9c-.4-.4-1-.4-1.4 0l-.7.7-.7-.7c-.4-.4-1-.4-1.4 0s-.4 1 0 1.4l.7.7-.7.7c-.4.4-.4 1 0 1.4.2.2.5.3.7.3s.5-.1.7-.3l.7-.7.7.7c.2.2.5.3.7.3s.5-.1.7-.3c.4-.4.4-1 0-1.4l-.7-.7.7-.7c.4-.4.4-1 0-1.4z';
            path2 = 'M18 5V4c0-.6-.4-1-1-1s-1 .4-1 1v1H8V4c0-.6-.4-1-1-1s-1 .4-1 1v1C3.8 5 2 6.8 2 9v7c0 2.2 1.8 4 4 4h12c2.2 0 4-1.8 4-4V9c0-2.2-1.8-4-4-4zm2 11c0 1.1-.9 2-2 2H6c-1.1 0-2-.9-2-2V9c0-1.1.9-2 2-2 0 .6.4 1 1 1s1-.4 1-1h8c0 .6.4 1 1 1s1-.4 1-1c1.1 0 2 .9 2 2v7z'
            path3 = '';
            break;
    }

    return (
        <svg xmlns="http://www.w3.org/2000/svg" enableBackground={enableBackground} viewBox={viewBox}
             id={id} height={height} width={width}>
            <path
                d={path1}></path>
            <path d={path2}></path>
            <path d={path3}></path>
        </svg>
    );
}

export default CustomIcon;