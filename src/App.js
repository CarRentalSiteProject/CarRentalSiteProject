import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Header from './Header';
import MainContent from './MainContent';
import Login from './login';
import { AuthProvider } from './AuthContext';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import '@popperjs/core';

function App() {
  // 錯誤狀態
  const [error, setError] = useState(null);
  // 用戶認證狀態
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  // API 基礎 URL
  const baseUrl = "http://localhost:8080/api";

  useEffect(() => {
    // 從本地存儲獲取 token
    const token = localStorage.getItem('token');
    if (token) {
      // 如果存在 token,驗證其有效性
      axios.get(`${baseUrl}/validate-token`, {
        headers: { Authorization: `Bearer ${token}` }
      })
      .then(() => setIsAuthenticated(true))  // token 有效,設置認證狀態為 true
      .catch(() => {
        // token 無效,移除 token 並設置認證狀態為 false
        localStorage.removeItem('token');
        setIsAuthenticated(false);
      });
    }
  }, []);  // 空依賴數組,僅在組件掛載時執行

  // 如果有錯誤,顯示錯誤信息
  if (error) return `Error: ${error.message}`;
  
  return (
    <AuthProvider>
      <Router>
        <div>
          {/* 傳遞認證狀態和設置函數到 Header 組件 */}
          <Header isAuthenticated={isAuthenticated} setIsAuthenticated={setIsAuthenticated} />
          <Routes>
            {/* 登錄路由 */}
            <Route path="/login" element={
              isAuthenticated ? <Navigate to="/" /> : <Login setIsAuthenticated={setIsAuthenticated} />
            } />
            {/* 主頁路由 */}
            <Route path="/" element={<MainContent isAuthenticated={isAuthenticated} />} />
          </Routes>
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;