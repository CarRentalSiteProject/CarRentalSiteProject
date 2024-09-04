import axios from 'axios';
import React, { useState, useEffect } from 'react';
import Header from './Header';
import MainContent from './MainContent';
import 'bootstrap/dist/css/bootstrap.min.css';
import '@popperjs/core';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './Login';


function App() {
  const [error, setError] = useState(null);
  const baseUrl = process.env.REACT_APP_API_URL || "http://localhost:8080/api/login";

  useEffect(() => {
    axios.get(baseUrl)
      .then((response) => {
        console.log(response);
      })
      .catch(error => {
        setError(error);
      });
  }, [baseUrl]);

  if (error) {
    return (
      <div className="alert alert-danger" role="alert">
        Error: {error.message}
      </div>
    );
  }

  return (
    <div>
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<MainContent />} />
          <Route path="login" element={<Login />} />
          <Route path="index" element={<MainContent />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
