import Footer from './Footer';
import axios from 'axios';
import { useState } from 'react';
import Header from './Header';
import { Link, useNavigate } from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [mes, setMes] = useState('');
  const navigate = useNavigate(); // 使用 useNavigate 來進行頁面跳轉

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/login', {
        username: username,
        password: password
      });

      if (response.data.success) {
        // 成功處理
        navigate('/'); // 使用 useNavigate 來跳轉首頁
      } else {
        setMes(response.data.message);
        alert(response.data.message); // 顯示彈出視窗
      }
    } catch (error) {
      console.error('Error:', error);
      setMes('伺服器錯誤');
      alert('伺服器錯誤'); // 顯示彈出視窗
    }
  };

  return (
    <div className="text-muted">
      <main>
        <section className="bg-secondary pb-5 position-relative poster pt-5 text-white-50">
          <div className="container" />
          <div className="row justify-content-center">
            <div className="col-md-6 col-lg-4">
              <div className="mt-5 pt-5 row">
                <div className="login-form">
                  {mes && <div className="alert alert-danger">{mes}</div>}
                  <form onSubmit={handleSubmit}>
                    <h1 className="display-3 fw-bold mb-4 text-white">
                      <span className="text-primary">Log in</span>
                    </h1>

                    <div className="form-group">
                      <input type="text" className="form-control" name="id" placeholder="Username"
                        required="required"
                        value={username} onChange={(e) => setUsername(e.target.value)} />
                    </div>
                    <div className="form-group">
                      <input type="password" className="form-control" name="paswd" placeholder="Password"
                        required="required"
                        value={password} onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    <div className="card-group">
                      <button type="submit" name="login" value="login" className="btn btn-primary btn-block">Log in</button>
                    </div>
                    <div className="form-group text-center">
                      New friend?<Link to="/">Sign up</Link>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </section>
        <div>
          <Footer />
        </div>
      </main>
    </div>
  );
}

export default Login;
