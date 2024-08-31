import React from 'react';

function Mobile() {
    return (
        <div>
            <section className="bg-primary pb-5 pt-5 text-center text-white-50">
                <div className="container pb-3 pt-3"> 
                    <h2 className="fw-bold h2 mb-4 text-capitalize text-white">Download Our App &amp; Get Started</h2>
                    <div className="d-inline-flex flex-wrap gap-2 py-1"> 
                        <button type="submit" className="btn btn-outline-light pb-2 pe-3 ps-3 pt-2"> 
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1.5em" height="1.5em" fill="currentColor" className="me-1">
                                <path d="M11.624 7.222c-.876 0-2.232-.996-3.66-.96-1.884.024-3.612 1.092-4.584 2.784-1.956 3.396-.504 8.412 1.404 11.172.936 1.344 2.04 2.856 3.504 2.808 1.404-.06 1.932-.912 3.636-.912 1.692 0 2.172.912 3.66.876 1.512-.024 2.472-1.368 3.396-2.724 1.068-1.56 1.512-3.072 1.536-3.156-.036-.012-2.94-1.128-2.976-4.488-.024-2.808 2.292-4.152 2.4-4.212-1.32-1.932-3.348-2.148-4.056-2.196-1.848-.144-3.396 1.008-4.26 1.008zm3.12-2.832c.78-.936 1.296-2.244 1.152-3.54-1.116.048-2.46.744-3.264 1.68-.72.828-1.344 2.16-1.176 3.432 1.236.096 2.508-.636 3.288-1.572z"/>
                            </svg>
                            <span className="align-middle">App Store</span>
                        </button>
                        <button type="submit" className="btn btn-outline-light pb-2 pe-3 ps-3 pt-2"> 
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="1.5em" height="1.5em" fill="currentColor" className="me-1">
                                <path d="M3.609 1.814L13.792 12 3.61 22.186a.996.996 0 0 1-.61-.92V2.734a1 1 0 0 1 .609-.92zm10.89 10.893l2.302 2.302-10.937 6.333 8.635-8.635zm3.199-3.198l2.807 1.626a1 1 0 0 1 0 1.73l-2.808 1.626L15.206 12l2.492-2.491zM5.864 2.658L16.802 8.99l-2.303 2.303-8.635-8.635z"/>
                            </svg>
                            <span className="align-middle">Google Play</span>
                        </button>
                    </div>                     
                </div>                 
            </section>
        </div>
    );
}

export default Mobile;