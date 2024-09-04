import React, { createContext, useState, useContext } from 'react';
import { validateToken } from './api';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = (userData) => {
    setUser(userData);
  };

  const logout = () => {
    setUser(null);
  };

  const checkAuth = async () => {
    try {
      const isValid = await validateToken();
      if (isValid) {
        // 如果 token 有效，可以從後端獲取用戶信息
        // 這裡假設 validateToken 返回用戶信息
        setUser(isValid);
        return true;
      } else {
        setUser(null);
        return false;
      }
    } catch (error) {
      console.error('驗證失敗:', error);
      setUser(null);
      return false;
    }
  };

  return (
    <AuthContext.Provider value={{ user, login, logout, checkAuth }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);