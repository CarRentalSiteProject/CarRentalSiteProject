import React from 'react';

function Fleets() {
    return (
        <div>
            <section className="pb-5 pt-5"> 
                <div className="container pb-5 pt-5">
                    <div className="align-items-center mb-4 row">
                        <div className="col-md">
                            <h2 className="h5 text-primary">Our Fleet</h2>
                            <h3 className="fw-bold h1 mb-3 text-capitalize text-dark">Browse by Category</h3>
                            <div className="bg-primary col-2 d-inline-flex mb-3 pb-1"></div>
                        </div>
                        <div className="col-md-auto">
                            <a href="#" className="btn btn-primary pb-2 pe-4 ps-4 pt-2">View All</a>
                        </div>
                    </div>
                    <div className="g-4 justify-content-center row row-cols-lg-4 row-cols-md-2"> 
                        <div> 
                            <a href="#" className="bg-white border d-block link-dark shadow-sm text-decoration-none"><img src="https://images.unsplash.com/photo-1619767886558-efdc259cde1a?ixid=MnwyMDkyMnwwfDF8c2VhcmNofDMyM3x8c3V2fGVufDB8fHx8MTYzMTY4Njc4Nw&ixlib=rb-1.2.1q=85&fm=jpg&crop=faces&cs=srgb&w=600&h=450&fit=crop" className="img-fluid w-100" alt="..." width="600" height="450"/><div className="pb-3 pe-4 ps-4 pt-3">
                                    <h4 className="fw-bold h5 mb-0">Sedans</h4>
                                </div></a> 
                        </div>
                        <div> 
                            <a href="#" className="bg-white border d-block link-dark shadow-sm text-decoration-none"><img src="https://images.unsplash.com/photo-1511527844068-006b95d162c2?ixid=MnwyMDkyMnwwfDF8c2VhcmNofDQzfHxjYXIlMjBzdXZ8ZW58MHx8fHwxNjMxNjg0ODkw&ixlib=rb-1.2.1q=85&fm=jpg&crop=faces&cs=srgb&w=600&h=450&fit=crop" className="img-fluid w-100" alt="..." width="600" height="450"/><div className="pb-3 pe-4 ps-4 pt-3">
                                    <h4 className="fw-bold h5 mb-0">SUVs</h4>
                                </div></a> 
                        </div>
                        <div> 
                            <a href="#" className="bg-white border d-block link-dark shadow-sm text-decoration-none"><img src="https://images.unsplash.com/photo-1597210159614-966c9f632c89?ixid=MnwyMDkyMnwwfDF8c2VhcmNofDh8fGNhciUyMGNvbnZlcnRpYmxlfGVufDB8fHx8MTYzMTY4NTExMA&ixlib=rb-1.2.1q=85&fm=jpg&crop=faces&cs=srgb&w=600&h=450&fit=crop" className="img-fluid w-100" alt="..." width="600" height="450"/><div className="pb-3 pe-4 ps-4 pt-3">
                                    <h4 className="fw-bold h5 mb-0">Convertibles</h4>
                                </div></a> 
                        </div>
                        <div> 
                            <a href="#" className="bg-white border d-block link-dark shadow-sm text-decoration-none"><img src="https://images.unsplash.com/photo-1552519507-da3b142c6e3d?ixid=MnwyMDkyMnwwfDF8c2VhcmNofDMwfHxzcG9ydHMlMjBjYXJ8ZW58MHx8fHwxNjMxNjg3MzQ4&ixlib=rb-1.2.1q=85&fm=jpg&crop=faces&cs=srgb&w=600&h=450&fit=crop" className="img-fluid w-100" alt="..." width="600" height="450"/><div className="pb-3 pe-4 ps-4 pt-3">
                                    <h4 className="fw-bold h5 mb-0">Sports Cars</h4>
                                </div></a> 
                        </div>                         
                    </div>                     
                </div>                 
            </section>
        </div>
    );
}

export default Fleets;