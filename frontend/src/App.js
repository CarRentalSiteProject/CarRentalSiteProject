import axios from 'axios';
import React, {useState, useEffect} from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './Header';
import MainContent from './MainContent';
import Menu from './menu';
import Search from './Search';
import RentOrder from './rentOrder';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import '@popperjs/core';
//import './styles/*';

function App() {
  const [error, setError] = useState(null);
  const baseUrl = "http://localhost:8080/carrent/test_api";

  useEffect(() => {
    axios.get(baseUrl)
    .then((response) => {
      console.log(response);
    })
    .catch(error => {
      setError(error);
    })
  }, []);

  if (error) return `Error: ${error.message+"API錯誤_APP"}`;
  
  return (
    <Router>
      <div>
        <Header />
        <Routes>
          <Route path="/" element={<MainContent />} />
          <Route path="/menu" element={<Menu />} />
          <Route path="/search" element={<Search />} />
          <Route path="/rentOrder" element={<RentOrder />} /> 
          {/* Add more routes as needed */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
