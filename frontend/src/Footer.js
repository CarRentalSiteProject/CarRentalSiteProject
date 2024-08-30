import React from 'react';

function Footer() {
    return (
        <div>
            <footer className="bg-dark pt-5 text-white"> 
                <div className="container"> 
                    <div className="row"> 
                        <div className="col-lg-4 py-3"> 
                            <a className="align-items-center d-inline-flex fw-bold gap-2 h3 lh-1 link-primary mb-3 text-decoration-none text-uppercase" href="#">
                                <svg version="1.0" xmlns="http://www.w3.org/2000/svg" width="3em" xmlSpace="preserve" viewBox="0 0 100 100" height="3em" className="me-1" fill="currentColor">
                                    <path d="M38.333 80a11.571 11.571 0 0 1-7.646-2.883A11.724 11.724 0 0 1 26.834 70H10V46.667L43.333 40l20-20H90v26.667H43.995l-27.328 5.465v11.2h11.166a11.787 11.787 0 0 1 4.212-4.807 11.563 11.563 0 0 1 12.577 0 11.789 11.789 0 0 1 4.213 4.807h7.833V70h-6.837a11.719 11.719 0 0 1-3.853 7.117A11.571 11.571 0 0 1 38.333 80Zm0-16.667a5 5 0 1 0 5 5 5.006 5.006 0 0 0-5.001-5Zm27.761-36.666L52.762 40h30.571V26.667Z"/>
                                    <path d="M56.667 63.333h-7.833a11.6 11.6 0 0 0-21 0H16.667v-11.2l27.328-5.465h12.672Z" opacity="0.2"/>
                                    <path d="M90 63.333H80v-10h-6.667v10h-10V70h10v10H80V70h10Z"/>
                                    <path d="M52.762 40h30.571V26.667H66.094Z" opacity="0.2"/>
                                </svg>
                                <span>Prime Drive</span>
                            </a> 
                            <ul className="list-unstyled">
                                <li className="mb-1">9056 Fairground Ave., New York, USA</li>
                                <li className="mb-1">
                                    <a href="#" className="link-info">+0 123 456 7890</a>
                                </li>
                                <li className="mb-1">
                                    <a href="#" className="link-info">info@company.com</a>
                                </li>
                            </ul>
                            <div className="d-inline-flex flex-wrap gap-2 py-1"> 
                                <a href="#" aria-label="facebook" className="link-light"> <svg viewBox="0 0 24 24" fill="currentColor" width="20" height="20"> 
                                        <path d="M14 13.5h2.5l1-4H14v-2c0-1.03 0-2 2-2h1.5V2.14c-.326-.043-1.557-.14-2.857-.14C11.928 2 10 3.657 10 6.7v2.8H7v4h3V22h4v-8.5z"/> 
                                    </svg> </a> 
                                <a href="#" aria-label="twitter" className="link-light"> <svg viewBox="0 0 24 24" fill="currentColor" width="20" height="20"> 
                                        <path d="M22.162 5.656a8.384 8.384 0 0 1-2.402.658A4.196 4.196 0 0 0 21.6 4c-.82.488-1.719.83-2.656 1.015a4.182 4.182 0 0 0-7.126 3.814 11.874 11.874 0 0 1-8.62-4.37 4.168 4.168 0 0 0-.566 2.103c0 1.45.738 2.731 1.86 3.481a4.168 4.168 0 0 1-1.894-.523v.052a4.185 4.185 0 0 0 3.355 4.101 4.21 4.21 0 0 1-1.89.072A4.185 4.185 0 0 0 7.97 16.65a8.394 8.394 0 0 1-6.191 1.732 11.83 11.83 0 0 0 6.41 1.88c7.693 0 11.9-6.373 11.9-11.9 0-.18-.005-.362-.013-.54a8.496 8.496 0 0 0 2.087-2.165z"/> 
                                    </svg> </a> 
                                <a href="#" aria-label="instagram" className="link-light"> <svg viewBox="0 0 24 24" fill="currentColor" width="20" height="20"> 
                                        <path d="M12 2c2.717 0 3.056.01 4.122.06 1.065.05 1.79.217 2.428.465.66.254 1.216.598 1.772 1.153a4.908 4.908 0 0 1 1.153 1.772c.247.637.415 1.363.465 2.428.047 1.066.06 1.405.06 4.122 0 2.717-.01 3.056-.06 4.122-.05 1.065-.218 1.79-.465 2.428a4.883 4.883 0 0 1-1.153 1.772 4.915 4.915 0 0 1-1.772 1.153c-.637.247-1.363.415-2.428.465-1.066.047-1.405.06-4.122.06-2.717 0-3.056-.01-4.122-.06-1.065-.05-1.79-.218-2.428-.465a4.89 4.89 0 0 1-1.772-1.153 4.904 4.904 0 0 1-1.153-1.772c-.248-.637-.415-1.363-.465-2.428C2.013 15.056 2 14.717 2 12c0-2.717.01-3.056.06-4.122.05-1.066.217-1.79.465-2.428a4.88 4.88 0 0 1 1.153-1.772A4.897 4.897 0 0 1 5.45 2.525c.638-.248 1.362-.415 2.428-.465C8.944 2.013 9.283 2 12 2zm0 5a5 5 0 1 0 0 10 5 5 0 0 0 0-10zm6.5-.25a1.25 1.25 0 0 0-2.5 0 1.25 1.25 0 0 0 2.5 0zM12 9a3 3 0 1 1 0 6 3 3 0 0 1 0-6z"/> 
                                    </svg> </a> 
                                <a href="#" aria-label="linkedin" className="link-light"> <svg viewBox="0 0 24 24" fill="currentColor" width="20" height="20"> 
                                        <path d="M6.94 5a2 2 0 1 1-4-.002 2 2 0 0 1 4 .002zM7 8.48H3V21h4V8.48zm6.32 0H9.34V21h3.94v-6.57c0-3.66 4.77-4 4.77 0V21H22v-7.93c0-6.17-7.06-5.94-8.72-2.91l.04-1.68z"/> 
                                    </svg> </a> 
                                <a href="#" aria-label="youtube" className="link-light"> <svg viewBox="0 0 24 24" fill="currentColor" width="20" height="20"> 
                                        <path d="M21.543 6.498C22 8.28 22 12 22 12s0 3.72-.457 5.502c-.254.985-.997 1.76-1.938 2.022C17.896 20 12 20 12 20s-5.893 0-7.605-.476c-.945-.266-1.687-1.04-1.938-2.022C2 15.72 2 12 2 12s0-3.72.457-5.502c.254-.985.997-1.76 1.938-2.022C6.107 4 12 4 12 4s5.896 0 7.605.476c.945.266 1.687 1.04 1.938 2.022zM10 15.5l6-3.5-6-3.5v7z"/> 
                                    </svg> </a> 
                            </div>                             
                        </div>
                        <div className="col-md col-sm-6 py-3"> 
                            <h2 className="fw-bold h5 mb-0 text-info">Company</h2>
                            <hr className="d-inline-block mb-4 w-25"/>
                            <ul className="list-unstyled"> 
                                <li className="mb-3"> 
                                    <a href="#" className="link-light">FAQ</a> 
                                </li>
                                <li className="mb-3"> 
                                    <a href="#" className="link-light">News</a> 
                                </li>
                                <li className="mb-3"> 
                                    <a href="#" className="link-light">Careers</a> 
                                </li>
                                <li className="mb-3"> 
                                    <a href="#" className="link-light">About Us</a> 
                                </li>                                 
                                <li className="mb-3"> 
                                    <a href="#" className="link-light">Contact Us</a> 
                                </li>                                 
                            </ul>                             
                        </div>
                        <div className="col-md col-sm-6 py-3"> 
                            <h2 className="fw-bold h5 mb-0 text-info">Vehicles</h2>
                            <hr className="d-inline-block mb-4 w-25"/>
                            <ul className="list-unstyled"> 
                                <li className="mb-3"> 
                                    <a href="#" className="link-light">SUVs</a> 
                                </li>
                                <li className="mb-3"> 
                                    <a href="#" className="link-light">Sedans</a> 
                                </li>
                                <li className="mb-3"> 
                                    <a href="#" className="link-light">Mini Vans</a> 
                                </li>
                                <li className="mb-3"> 
                                    <a href="#" className="link-light">Sports Cars</a> 
                                </li>
                                <li className="mb-3"> 
                                    <a href="#" className="link-light">Convertibles</a> 
                                </li>                                 
                            </ul>                             
                        </div>
                        <div className="col-lg-4 col-md-5 py-3"> 
                            <h2 className="fw-bold h5 mb-0 text-info">Top Cities</h2>
                            <hr className="d-inline-block mb-4 w-25"/>
                            <div className="gy-3 row row-cols-sm-2">
                                <div> 
                                    <ul className="list-unstyled"> 
                                        <li className="mb-3"> 
                                            <a href="#" className="link-light">Taxes</a> 
                                        </li>
                                        <li className="mb-3"> 
                                            <a href="#" className="link-light">Boston</a> 
                                        </li>
                                        <li className="mb-3"> 
                                            <a href="#" className="link-light">Colorado</a> 
                                        </li>
                                        <li className="mb-3"> 
                                            <a href="#" className="link-light">California</a> 
                                        </li>                                         
                                        <li className="mb-3"> 
                                            <a href="#" className="link-light">Manhattan</a> 
                                        </li>                                         
                                    </ul>
                                </div>
                                <div> 
                                    <ul className="list-unstyled"> 
                                        <li className="mb-3"> 
                                            <a href="#" className="link-light">Dallas</a> 
                                        </li>
                                        <li className="mb-3"> 
                                            <a href="#" className="link-light">Huston</a> 
                                        </li>
                                        <li className="mb-3"> 
                                            <a href="#" className="link-light">Seattle</a> 
                                        </li>
                                        <li className="mb-3"> 
                                            <a href="#" className="link-light">Denver</a> 
                                        </li>
                                        <li className="mb-3"> 
                                            <a href="#" className="link-light">Phoenix</a> 
                                        </li>                                         
                                    </ul>
                                </div>
                            </div>                             
                        </div>                         
                    </div>                     
                    <div className="pb-3 pt-3"> 
                        <hr className="border-secondary mt-0"/> 
                        <div className="align-items-center row">
                            <div className="col-md pb-2 pt-2">
                                <p className="mb-0">&copy; 2002 - 2021. All Rights Reserved - Company Name</p>
                            </div>
                            <div className="col-md-auto pb-2 pt-2">
                                <a href="#" className="link-light">Privacy Policy</a> | 
                                <a href="#" className="link-light">Terms of Use</a>
                            </div>
                        </div>                         
                    </div>                     
                </div>                 
            </footer>
        </div>
    );
}

export default Footer;