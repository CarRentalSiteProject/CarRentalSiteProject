import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

function Modal({ car }) {
  const navigate = useNavigate();

  const handleRental = car => {
  navigate('/fororder', car);
  }
  if (!car) return null; // If no car is selected, don't render the modal

  return (
  <div className="modal fade" id="carModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div className="modal-dialog modal-dialog-centered">
    <div className="modal-content">
      <div className="modal-header">
      <h5 className="modal-title" id="exampleModalLabel">{car.carType}</h5>
      <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div className="modal-body">
      <div className="bg-white border shadow-sm">
        <img
        src={car.imgs}
        className="img-fluid w-100"
        alt={car.carType}
        width="600"
        height="450"
        />
        <div className="p-4">
        <h4 className="fw-bold h5">
          <a href="#" className="link-secondary text-decoration-none">{car.carType}</a>
        </h4>
        <p className="small">description</p>
        <hr />
        <div className="align-items-center d-flex justify-content-between">
          <div className="align-items-center d-inline-flex gap-1 pb-1 pt-1">
          <span>{car.rating}</span>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="currentColor"
            width="1.125em"
            height="1.125em"
            className="text-primary"
          >
            <g>
            <path fill="none" d="M0 0h24v24H0z"></path>
            <path d="M12 18.26l-7.053 3.948 1.575-7.928L.587 8.792l8.027-.952L12 .5l3.386 7.34 8.027.952-5.935 5.488 1.575 7.928z"></path>
            </g>
          </svg>
          </div>
          <span className="fw-bold pb-1 pt-1 text-dark">${car.price}/day</span>
        </div>
        </div>
      </div>
      </div>
      <div className="modal-footer">
      <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={handleRental(car)}>Rent It Now</button>
      </div>
    </div>
    </div>
  </div>
  );
}

export default Modal;
