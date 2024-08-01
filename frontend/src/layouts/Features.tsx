function Features() {
    return (
        <div>
            <div className="container px-4 py-4" id="hanging-icons">
                <div className="row g-4 py-3 row-cols-1 row-cols-lg-3">
                    <div className="col d-flex align-items-start">
                        <div
                            className="icon-square text-body-emphasis bg-body-secondary d-inline-flex align-items-center justify-content-center fs-4 flex-shrink-0 me-3">
                            <svg className="bi" width="1em" height="1em">
                            </svg>
                        </div>
                        <div>
                            <h3 className="fs-2 text-body-emphasis">Featured title</h3>
                            <p>Paragraph of text beneath the heading to explain the heading. We'll add onto it with
                                another sentence and probably just keep going until we run out of words.</p>
                            <a href="#" className="btn btn-primary">
                                Primary button
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    );
}

export default Features;