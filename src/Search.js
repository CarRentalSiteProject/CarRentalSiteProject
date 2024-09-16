import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';
import Cookies from 'js-cookie';
import SideBar from './SideBar';
import MenuView from './MenuView';

function useQuery() {
  return new URLSearchParams(useLocation().search);
}

function Search() {
  const query = useQuery();
  const startPlace = query.get('place');
  const startDate = query.get('start');
  const endDate = query.get('end');
  const sortBy = query.get('sortBy')? query.get('sortBy') : "Price";
  const direction = query.get('direction')? query.get('direction') : "desc";
  const carType = query.get('carType')? query.get('carType') : null;
  const priceMin = query.get('priceMin')? query.get('priceMin') : 0;
  const priceMax = query.get('priceMax')? query.get('priceMax') : 0;
  const peopleNub = query.get('peopleNub')? query.get('peopleNub') : 0;

  const [cars, setCars] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(0); // Current page state
  const [totalPages, setTotalPages] = useState(1); // Total pages
  const pageSize = 9; // Page size (can be adjustable)

  const jwtToken = Cookies.get('jwt');
  console.log("JWT Token:", jwtToken);

  const searchCars = async (chplace, chdate, redate, page = 0, sortBy, direction, carType, priceMin, priceMax, peopleNub) => {
    setIsLoading(true);
    console.log("searcnCars, Query Params: ", { startPlace, startDate, endDate, sortBy, direction, carType, priceMin, priceMax, peopleNub });
    try {
      console.log('Sending request with params:', { chplace, chdate, redate, page, size: pageSize, sortBy, direction, carType, priceMin, priceMax, peopleNub });
      const response = await axios.post(`http://localhost:8080/car/queryPage`, {
        chplace,
        chdate,
        redate,
        page,
        size: pageSize,
        sortBy, // Include sortBy
        direction, // Include direction
        carType,
        priceMin,
        priceMax,
        peopleNub
      }, {
        headers: {
          Authorization: `Bearer ${jwtToken}`
        },
        withCredentials: true
      });
      console.log('Response:', response);
      if (response.data.cars.length === 0) {
        console.log('No cars found for the search criteria.');
      } else {
        setCars(response.data.cars);
      }
      setTotalPages(response.data.totalPages);
    } catch (error) {
      console.error('Error searching for cars:', error.response ? error.response.data : error.message);
    }
    setIsLoading(false);
  };
  

  useEffect(() => {
    console.log("useEffect Query Params: ", { startPlace, startDate, endDate, sortBy, direction, carType, priceMin, priceMax, peopleNub });

    // Call searchCars only if required parameters are present
    if (startPlace && startDate && endDate && sortBy && direction) {
        searchCars(
            startPlace,
            startDate,
            endDate,
            currentPage,
            sortBy,
            direction,
            carType || null,
            priceMin || 0,
            priceMax || null,
            peopleNub || null
        );
    }
  }, [startPlace, startDate, endDate, sortBy, direction, carType, priceMin, priceMax, peopleNub, currentPage]);


  const handlePageChange = (newPage) => {
    if (newPage >= 0 && newPage < totalPages) {
      setCurrentPage(newPage);
    }
  };

  const pages = [];
  for (let i = 0; i < totalPages; i++) {
    pages.push(
      <li className={`page-item ${i === currentPage ? 'active' : ''}`} key={i}>
        <a
          className={`page-link ${currentPage === 0 ? 'disabled' : ''}`}
          onClick={() => currentPage > 0 && handlePageChange(currentPage - 1)}
        >
          {i + 1} {/* Displaying 1-based page number */}
        </a>
      </li>
    );
  }


  return (
    <div className="wrapper">
      <section className="pb-5 position-relative pt-5">
        <div className="container-fluid">
          <div className="row ps-5 pe-5">
            <div className="col-xl-3 col-lg-5">
              <SideBar
                startPlace={startPlace}
                startDate={startDate}
                endDate={endDate}
                onSearch={searchCars}
              />
            </div>
            <div className="col-xl-7 col-lg-7">
              {isLoading ? <p>Loading...</p> : <MenuView cars={cars} />}
              <nav aria-label="Page navigation example">
                <ul className="pagination d-flex justify-content-center">
                  <li className="page-item">
                    <a className="page-link"
                      onClick={() => handlePageChange(currentPage - 1)}
                      disabled={currentPage === 0}>
                      Previous
                    </a>
                  </li>
                  {pages}
                  <li className="page-item">
                    <a className="page-link"
                      onClick={() => handlePageChange(currentPage + 1)}
                      disabled={currentPage + 1 >= totalPages}>
                      Next
                    </a>
                  </li>
                </ul>
              </nav>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Search;
