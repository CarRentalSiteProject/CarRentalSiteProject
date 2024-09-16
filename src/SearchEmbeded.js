import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate  } from 'react-router-dom';
import { useAuth } from './AuthContext';

function SearchEmbeded() {
  const [chplace, setChplace] = useState('');
  const [chdate, setChdate] = useState('');
  const [redate, setRedate] = useState('');
  const [sortBy, setSortBy] = useState('Price');
  const [sortDirection, setSortDirection] = useState('desc');
  
  const {user} = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!user) {
      // 如果用戶未登入，將當前搜索參數存儲到 localStorage
      localStorage.setItem('lastSearch', JSON.stringify({ chplace, chdate, redate }));
      // 重定向到登入頁面
      navigate('/login');
    } else {
    try {
      navigate(`/search?place=${chplace}&start=${chdate}&end=${redate}&sortBy=${sortBy}&direction=${sortDirection}&carType=null&priceMin=${-1}&priceMax=${-1}&peopleNub=${-1}`);
    } catch (error) {
      console.error('Error submitting form:', error);
      }
    }
  };

  return (
    <div>
      <section className="bg-secondary pb-5 position-relative poster pt-5 text-white-50">
        <div className="container mt-5 pb-5 pt-5">
          <div className="mt-5 pt-5 row">
            <div className="col-md-10 col-xl-7 pt-5">
              <p className="fw-bold h4 text-white">Car Rentals</p>
              <h1 className="display-3 fw-bold mb-4 text-white">For Your <span className="text-primary">Everyday Commute</span> or <span className="text-primary">Leisure</span></h1>
              <div className="bg-white p-4">
                <h2 className="fw-bold h5 mb-3 text-dark">Let's find your ideal car</h2>
                <form onSubmit={handleSubmit}> 
                  <div className="align-items-center gx-2 gy-3 row">
                    <div className="col-sm"> 
                      <select 
                        className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0" 
                        value={chplace} 
                        onChange={(e) => setChplace(e.target.value)}
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
                    <div className="col-sm-4"> 
                      <input 
                        type="date" 
                        className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                        value={chdate}
                        onChange={(e) => setChdate(e.target.value)}
                        required
                      /> 
                    </div>
                    <div className="col-sm-4"> 
                      <input 
                        type="date" 
                        className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                        value={redate}
                        onChange={(e) => setRedate(e.target.value)}
                        required
                      /> 
                    </div>
                    <div className="col-sm-auto text-end"> 
                      <button type="submit" className="btn btn-primary pb-2 pe-4 ps-4 pt-2" onClick={handleSubmit}>Search</button>                       
                    </div>
                  </div>                   
                </form>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default SearchEmbeded;