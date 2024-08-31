import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Search() {
    const [chplace, setChplace] = useState('');
    const [chdate, setChdate] = useState('');
    const [redate, setRedate] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/carrent/searchPlace', {
                chplace,
                chdate,
                redate
            });
            console.log('API Response:', response.data);
            navigate('/menu', { state: { cars: response.data, chdate, redate } });
        } catch (error) {
            console.error('Error submitting form:', error);
        }
    };

    return (
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
                                            <option value="">請選擇縣市</option>
                                            <option value="Taipei">台北</option>
                                            <option value="NewTaipei">新北</option>
                                            <option value="Keelung">基隆</option>
                                            <option value="Taoyuan">桃園</option>
                                            <option value="Hsinchu">新竹</option>
                                            <option value="Taichung">台中</option>
                                            <option value="Changhua">彰化</option>
                                            <option value="Yunlin">雲林</option>
                                            <option value="Chiayi">嘉義</option>
                                            <option value="Tainan">台南</option>
                                            <option value="Kaohsiung">高雄</option>
                                            <option value="Pingtung">屏東</option>
                                            <option value="Hualien">花蓮</option>
                                            <option value="Taitung">台東</option>
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
                                        <button type="submit" className="btn btn-primary pb-2 pe-4 ps-4 pt-2">Search</button>                                             
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

export default Search;