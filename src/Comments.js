import React from 'react';
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

function Comments() {
    const testimonials = [
        {
            imgSrc: "https://images.unsplash.com/photo-1537151625747-768eb6cf92b2?ixid=MXwyMDkyMnwwfDF8c2VhcmNofDE5fHxkb2d8ZW58MHx8fA&ixlib=rb-1.2.1q=85&fm=jpg&crop=faces&cs=srgb&w=360&h=360&fit=crop",
            altText: "Slide 1",
            text: "Aliqua id fugiat nostrud irure ex duis ea quis id quis ad et. Sunt qui esse pariatur duis deserunt mollit dolore cillum minim tempor enim. Elit aute irure tempor cupidatat incididunt sint deserunt ut voluptate aute id deserunt nisi.",
            name: "Kathryn Murphy",
            title: "Chief Technology Officer"
        },
        {
            imgSrc: "https://images.unsplash.com/photo-1574158622682-e40e69881006?ixid=MXwyMDkyMnwwfDF8c2VhcmNofDd8fGNhdHxlbnwwfHx8&ixlib=rb-1.2.1q=85&fm=jpg&crop=faces&cs=srgb&w=360&h=360&fit=crop",
            altText: "Slide 2",
            text: "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. Exercitation veniam consequat sunt nostrud amet.",
            name: "Dianne Russell",
            title: "Founder"
        },
        {
            imgSrc: "https://images.unsplash.com/photo-1517101724602-c257fe568157?ixid=MXwyMDkyMnwwfDF8c2VhcmNofDZ8fHBhcnJvdHxlbnwwfHx8&ixlib=rb-1.2.1q=85&fm=jpg&crop=faces&cs=srgb&w=360&h=360&fit=crop",
            altText: "Slide 3",
            text: "Aliqua id fugiat nostrud irure ex duis ea quis id quis ad et. Sunt qui esse pariatur duis deserunt mollit dolore cillum minim tempor enim. Elit aute irure tempor cupidatat incididunt sint deserunt ut voluptate aute id deserunt nisi.",
            name: "Darrell Steward",
            title: "Administrative Manager"
        }
    ];

    // Slick settings
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 5000,
    };

    return (
        <div>
            <section className="bg-dark pb-5 pt-5 text-white-50">
                <div className="container pb-5 pt-5">
                    <div className="mb-4 row">
                        <div className="col-md">
                            <h2 className="h5 text-primary">Testimonials</h2>
                            <h3 className="fw-bold h1 mb-3 text-white">What Our Customers Say About Us</h3>
                            <div className="bg-primary col-2 d-inline-flex mb-3 pb-1"></div>
                        </div>
                    </div>
                    <Slider {...settings}>
                        {testimonials.map((testimonial, index) => (
                            <div key={index} className="carousel-item">
                                <div className="align-items-center row">
                                    <div className="col-lg-4 pb-3 pt-3">
                                        <img src={testimonial.imgSrc} className="img-fluid" alt={testimonial.altText} width="360" height="360" />
                                    </div>
                                    <div className="col-lg-8 pb-3 pt-3">
                                        <svg viewBox="0 0 24 24" fill="currentColor" height="64" width="64" className="mb-3 text-primary">
                                            <path d="M23 1V5.06504C21.9136 5.67931 21.0807 6.43812 20.5012 7.34146C19.958 8.24481 19.5416 9.20235 19.2519 10.2141C18.9621 11.2258 18.8173 12.346 18.8173 13.5745H23V22.4634H14.0914V16.935C14.0914 13.6107 14.3992 11.0813 15.0148 9.34688C15.6667 7.61246 16.6444 6.02258 17.9481 4.57723C19.2519 3.09575 20.9358 1.90334 23 1ZM9.90864 1V5.06504C8.82222 5.67931 7.9893 6.43812 7.40988 7.34146C6.83045 8.24481 6.39588 9.20235 6.10617 10.2141C5.81646 11.2258 5.67161 12.346 5.67161 13.5745H9.90864V22.4634H1V16.935C1 13.6107 1.30782 11.0813 1.92346 9.34688C2.57531 7.61246 3.55309 6.02258 4.85679 4.57723C6.16049 3.09575 7.84444 1.90334 9.90864 1Z" />
                                        </svg>
                                        <p className="fs-5 mb-4">{testimonial.text}</p>
                                        <h4 className="fw-bold mb-1 text-primary">{testimonial.name}</h4>
                                        <p className="mb-0 text-white">{testimonial.title}</p>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </Slider>
                </div>
            </section>
        </div>
    );
}

export default Comments;
