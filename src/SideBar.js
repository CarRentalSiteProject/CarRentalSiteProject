import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function SideBar({ startPlace, startDate, endDate }) {
  const [chplace, setChPlace] = useState(startPlace || ''); 
  const [chdate, setChDate] = useState(startDate || '');
  const [redate, setReDate] = useState(endDate || '');
  const [sortBy, setSortBy] = useState('sortByPrice');
  const [sortDirection, setSortDirection] = useState('desc');
  const [type, setType] = useState('type');
  const [min, setPriceMin] = useState('min');
  const [max, setPriceMax] = useState('max');
  const [peopleNub, setPeopleNub] = useState(2);
  const navigate = useNavigate();

  const handleSearching = async (e) => {
    e.preventDefault();
    try {
      console.log({ chplace, chdate, redate, sortBy });
      navigate(`/search?place=${chplace}&start=${chdate}&end=${redate}&sortBy=${sortBy}&direction=${sortDirection}&carType=${type}&priceMin=${min}&priceMax=${max}&peopleNub=${peopleNub}`);
    } catch (error) {
      console.error('Error submitting form:', error);
    }
  };

  return (
    <div id="sidebar" className="sidebar sticky-lg-top">
      <div className="card bg-white border border-3 border-primary rounded col-sm p-4">
        <div className="cardbody">
          <form onSubmit={handleSearching}>
            <h2 className="h5 mb-4 pt-1 pb-3 border-bottom fw-bold text-dark" data-bs-toggle="collapse" data-bs-target="#collapse-search-1" aria-expanded="false" aria-controls="collapse-search-1">
              <span>
              Location & Date
              {/* https://www.svgrepo.com/ */}
              <svg className="float-end" width="30" height="30" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path fillRule="evenodd" clipRule="evenodd" d="M12.7071 14.7071C12.3166 15.0976 11.6834 15.0976 11.2929 14.7071L6.29289 9.70711C5.90237 9.31658 5.90237 8.68342 6.29289 8.29289C6.68342 7.90237 7.31658 7.90237 7.70711 8.29289L12 12.5858L16.2929 8.29289C16.6834 7.90237 17.3166 7.90237 17.7071 8.29289C18.0976 8.68342 18.0976 9.31658 17.7071 9.70711L12.7071 14.7071Z" fill="#000000"/>
              </svg>
              </span>
            </h2>
            <div className="align-items-center gx-2 gy-3 collapse" id="collapse-search-1">
              <div className="col-sm p-2"> 
                <select 
                  className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" 
                  value={chplace} 
                  onChange={(e) => setChPlace(e.target.value)}
                  required
                >
                  <option value="">Locations</option>
                  <option value="Taipei">Taipei</option>
                  <option value="NewTaipei">NewTaipei</option>
                  <option value="Keelung">Keelung</option>
                  <option value="Taoyuan">Taoyuan</option>
                  <option value="Hsinchu">Hsinchu</option>
                  <option value="Taichung">Taichung</option>
                  <option value="Changhua">Changhua</option>
                  <option value="Yunlin">Yunlin</option>
                  <option value="Chiayi">Changhua</option>
                  <option value="Tainan">Tainan</option>
                  <option value="Kaohsiung">Kaohsiung</option>
                  <option value="Pingtung">Pingtung</option>
                  <option value="Hualien">Hualien</option>
                  <option value="Taitung">Taitung</option>
                </select>
              </div>
              <div className="col-sm p-2"> 
                <input 
                  type="date" 
                  className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                  value={chdate}
                  onChange={(e) => setChDate(e.target.value)}
                  required
                /> 
              </div>
              <div className="col-sm p-2"> 
                <input 
                  type="date" 
                  className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                  value={redate}
                  onChange={(e) => setReDate(e.target.value)}
                  required
                /> 
              </div>
            </div>
            <h2 className="h5 mb-4 pt-1 pb-3 border-bottom fw-bold text-dark" data-bs-toggle="collapse" data-bs-target="#collapse-search-2" aria-expanded="false" aria-controls="collapse-search-2">
              <span>
              Sort by
              <svg className="float-end" width="30" height="30" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path fillRule="evenodd" clipRule="evenodd" d="M12.7071 14.7071C12.3166 15.0976 11.6834 15.0976 11.2929 14.7071L6.29289 9.70711C5.90237 9.31658 5.90237 8.68342 6.29289 8.29289C6.68342 7.90237 7.31658 7.90237 7.70711 8.29289L12 12.5858L16.2929 8.29289C16.6834 7.90237 17.3166 7.90237 17.7071 8.29289C18.0976 8.68342 18.0976 9.31658 17.7071 9.70711L12.7071 14.7071Z" fill="#000000"/>
              </svg>
              </span>
            </h2>
            <div className="collapse" id="collapse-search-2">
              {/* Sort by selection */}
              <div className="col-sm p-2">
                <div className="form-check">
                  <input 
                    className="form-check-input" 
                    type="radio" 
                    value="price" 
                    checked={sortBy === "price"} 
                    onChange={() => setSortBy('price')} 
                  />
                  <label className="form-check-label">Price</label>
                </div>
                <div className="form-check">
                  <input 
                    className="form-check-input" 
                    type="radio" 
                    value="peopleNub" 
                    checked={sortBy === "peopleNub"} 
                    onChange={() => setSortBy('peopleNub')} 
                  />
                  <label className="form-check-label">Passengers</label>
                </div>
              </div>
            
              {/* Sort direction */}
              <div className="col-sm p-2">
                <div className="form-check">
                  <input 
                    className="form-check-input" 
                    type="radio" 
                    checked={sortDirection === "asc"} 
                    onChange={() => setSortDirection('asc')} 
                  />
                  <label className="form-check-label">Increasing</label>
                </div>
                <div className="form-check">
                  <input 
                    className="form-check-input" 
                    type="radio" 
                    checked={sortDirection === "desc"} 
                    onChange={() => setSortDirection('desc')} 
                  />
                  <label className="form-check-label">Decreasing</label>
                </div>
              </div>
            </div>
            <h2 className="h5 mb-4 pt-1 pb-3 border-bottom fw-bold text-dark" data-bs-toggle="collapse" data-bs-target="#collapse-search-3" aria-expanded="false" aria-controls="collapse-search-3">
              <span>
                Conditions
                {/* https://www.svgrepo.com/ */}
                <svg className="float-end" width="30" height="30" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path fillRule="evenodd" clipRule="evenodd" d="M12.7071 14.7071C12.3166 15.0976 11.6834 15.0976 11.2929 14.7071L6.29289 9.70711C5.90237 9.31658 5.90237 8.68342 6.29289 8.29289C6.68342 7.90237 7.31658 7.90237 7.70711 8.29289L12 12.5858L16.2929 8.29289C16.6834 7.90237 17.3166 7.90237 17.7071 8.29289C18.0976 8.68342 18.0976 9.31658 17.7071 9.70711L12.7071 14.7071Z" fill="#000000"/>
                </svg>
              </span>
            </h2>
            <div className="align-items-center gx-2 gy-3 collapse" id="collapse-search-3">
              <div className="col-sm p-2"> 
                <label className="h6 mb-3 pt-1 pb-3 border-bottom fw-bold text-dark">Brands</label>
                <select 
                  className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" 
                  value={type} 
                  onChange={(e) => setType(e.target.value)}
                >
                  <option value="">select the brand</option>
                  <option value="Hyundai">Hyundai</option>
                  <option value="Honda">Honda</option>
                  <option value="BMW">BMW</option>
                  <option value="Chevrolet">Chevrolet</option>
                  <option value="Ferrari">Ferrari</option>
                  <option value="Ford">Ford</option>
                </select>
              </div>
              <div className="col-sm p-2">
                <label htmlFor="priceRange" className="h6 mb-3 pt-1 pb-3 border-bottom fw-bold form-label text-dark">Price</label>
                <div className="row ps-3 pe-3">
                  <input
                    type="text"
                    className="form-control col-sm"
                    value={min}
                    onChange={(e) => setPriceMin(e.target.value)}
                    aria-label="Amount (to the nearest dollar)"
                    id="min"
                    placeholder="min"
                  />
                  <span className="col-sm text-center fs-4">~</span>
                  <input
                    type="text"
                    className="form-control col-sm"
                    value={max}
                    onChange={(e) => setPriceMax(e.target.value)}
                    aria-label="Amount (to the nearest dollar)"
                    id="max"
                    placeholder="max"
                  />
                </div>
              </div>
              <div className="col-sm p-2">
                <label htmlFor="passengersRange" className="h6 mb-3 pt-1 pb-3 border-bottom fw-bold text-dark">Passengers</label>
                <input 
                  type="range" 
                  className="form-range" 
                  value={peopleNub} 
                  min="2" 
                  max="6" 
                  step="1" 
                  id="passengersRange" 
                  onChange={(e) => setPeopleNub(e.target.value)}
                />
                <div className="text-center fw-bold">{peopleNub} Passengers</div>
              </div>
            </div>
            <div className="col-sm-auto mt-3 p-2 text-end">
              <button type="submit" className="btn btn-primary pb-2 pe-4 ps-4 pt-2">Search</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default SideBar;