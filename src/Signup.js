import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from './AuthContext';
import { signup as apiSignup } from './api';

const Signup = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [age, setAge] = useState('');
    const [gender, setGender] = useState('');
    const [email, setEmail] = useState('');
    const [licenseNub, setLicenseNub] = useState('');
    const [address, setAddress] = useState('');
    const [phone, setPhone] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const { login } = useAuth();
  
    const handleSignup = async (e) => {
      e.preventDefault();
      if (password !== confirmPassword) {
        setError('密碼不匹配');
        return;
      }
      try {
        const userData = await apiSignup(username, password,  age , gender , email , licenseNub , address , phone);
        if (userData) {
          login(userData);
          navigate('/');
        }
      } catch (error) {
        setError('註冊失敗: ' + (error.response?.data || error.message));
      }
    };
    

    return (
        <section className="bg-secondary pb-5 position-relative poster pt-5 text-white-50">
            <div className="container mt-5 pb-5 pt-5">
                <div className="mt-5 pt-5 row">
                    <div className="col-md-10 col-xl-7 pt-5">
                        <p className="fw-bold h4 text-white">Car Rentals</p>
                        <h1><span className="display-3 fw-bold mb-4 text-primary">Sign </span><span className="text-white">Up</span></h1>
                        <div className="bg-white p-4">
                            <h2 className="fw-bold h5 mb-3 text-dark">Create an Account</h2>
                            <form onSubmit={handleSignup}>
                                <div className="align-items-center gx-2 gy-3 row">
                                    <div className="col-6">
                                        <label className="text-primary">Name: </label>
                                        <input
                                            type="text"
                                            className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                                            name="name"
                                            value={username}
                                            onChange={(e) => setUsername(e.target.value)}
                                            placeholder="Tom"
                                        />
                                    </div>
                                    <div className="col-6">
                                        <label className="text-primary">Age: </label>
                                        <input
                                            type="text"
                                            className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                                            name="age"
                                            value={age}
                                            onChange={(e) => setAge(e.target.value)}
                                            placeholder="18"
                                        />
                                    </div>
                                    <div className="col-6">
                                        <label className="text-primary">Gender: </label>
                                        <div className="text-primary">
                                            <input
                                                id="genderMale"
                                                type="radio"
                                                name="gender"
                                                value="male"
                                                className="form-check-input"
                                                checked={gender === 'male'}
                                                onChange={(e) => setGender(e.target.value)}
                                            />
                                            <label className="form-check-label" htmlFor="genderMale">Male</label>
                                        </div>
                                        <div className="text-primary">
                                            <input
                                                id="genderFemale"
                                                type="radio"
                                                name="gender"
                                                value="female"
                                                className="form-check-input"
                                                checked={gender === 'female'}
                                                onChange={(e) => setGender(e.target.value)}
                                            />
                                            <label className="form-check-label" htmlFor="genderFemale">Female</label>
                                        </div>
                                        <div className="text-primary">
                                            <input
                                                id="genderOther"
                                                type="radio"
                                                name="gender"
                                                value="other"
                                                className="form-check-input"
                                                checked={gender === 'other'}
                                                onChange={(e) => setGender(e.target.value)}
                                            />
                                            <label className="form-check-label" htmlFor="genderOther">Other</label>
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <label className="text-primary">Phone: </label>
                                        <input
                                            type="text"
                                            className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                                            name="phone"
                                            value={phone}
                                            placeholder="09XX-XXX-XXX"
                                            onChange={(e) => setPhone(e.target.value)}
                                        />
                                    </div>
                                    <div className="col-12">
                                        <label className="text-primary">Email: </label>
                                        <input
                                            type="email"
                                            className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                                            name="email"
                                            value={email}
                                            placeholder="tom@test.com"
                                            onChange={(e) => setEmail(e.target.value)}
                                        />
                                    </div>
                                    <div className="col-6">
                                        <label className="text-primary">Password: </label>
                                        <input
                                            type="password"
                                            id="password"
                                            className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                                            name="password"
                                            value={password}
                                            onChange={(e) => setPassword(e.target.value)}
                                        />
                                    </div>
                                    <div className="col-6">
                                        <label className="text-primary">Confirm Password: </label>
                                        <input
                                            type="password"
                                            id="confirmPassword"
                                            className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                                            name="confirmPassword"
                                            value={confirmPassword}
                                            onChange={(e) => setConfirmPassword(e.target.value)}
                                        />
                                    </div>
                                    <div className="col-12">
                                        <label className="text-primary">Address: </label>
                                        <input
                                            type="text"
                                            className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                                            name="address"
                                            value={address}
                                            placeholder="Taipei"
                                            onChange={(e) => setAddress(e.target.value)}
                                        />
                                    </div>
                                    <div className="col-6">
                                        <label className="text-primary">License Number: </label>
                                        <input
                                            type="text"
                                            className="form-control pb-2 pe-3 ps-3 pt-2 rounded-0"
                                            name="licenseNub"
                                            value={licenseNub}
                                            placeholder="A1XXXXXXXX"
                                            onChange={(e) => setLicenseNub(e.target.value)}
                                        />
                                    </div>
                                    <div className="col-12 text-end">
                                        <button type="submit" className="btn btn-primary pb-2 pe-4 ps-4 pt-2">Create</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Signup;