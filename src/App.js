import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Header from './Header';
import MainContent from './MainContent';
import Signup from './Signup';
import Login from './Login';
// import Menu from './menu';
import Search from './Search';
import RentOrder from './rentOrder';
import UpdateInfo from './UpdateInfo';
import Membership from './Membership';
import ProtectedRoute from './ProtectedRoute';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/css/bootstrap.rtl.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import '@popperjs/core';
import PaymentResultPage from './paymentResultPage';
import PaymentResult from './paymentResult';
import ForOrder from './forOrder';
import ForOrderDetail from './forOderDetail';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Footer from './Footer';
import Fleets from './Fleets';
import { AuthProvider, useAuth } from './AuthContext';

function App() {
  const [error, setError] = useState(null);
  const baseUrl = process.env.REACT_APP_API_URL || "http://localhost:8080/api/login";

  const { checkAuth } = useAuth();

  useEffect(() => {
  checkAuth();
  }, []);

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
      <Route path="/signup" element={<Signup />} />
      <Route path="login" element={<Login />} />
      <Route path="index" element={<MainContent />} />
      <Route path="/search" element={<ProtectedRoute><Search /></ProtectedRoute>} />
      <Route path="/rentOrder" element={<RentOrder />} /> 
      <Route path="/fleets" element={<Fleets />} />
      <Route path="/updateinfo" element={<UpdateInfo />} />
      <Route path="/membership" element={<ProtectedRoute><Membership /></ProtectedRoute>} /> 
      <Route path="/carrent/paymentResultPage" element={<PaymentResultPage/>} />
      <Route path="/carrent/paymentResult" element={<PaymentResult/>} />
      <Route path="/forOrder" element={<ForOrder/>} />
      <Route path="/forOrderDetail" element={<ForOrderDetail/>} />
      {/* Add more routes as needed */}
    </Routes>
    <Footer />
    </Router>
  </div>
  );
}

function AppWrapper() {
  return (
  <AuthProvider>
    <App />
  </AuthProvider>
  );
}

export default AppWrapper;
