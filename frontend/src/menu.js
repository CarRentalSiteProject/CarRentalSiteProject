import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Menu() {
    const location = useLocation();
    const navigate = useNavigate();
    const { cars, chdate, redate } = location.state;
    const [selectedCar, setSelectedCar] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/carrent/order2', {
                selectedCar,
                chdate,
                redate
            });
            console.log('API Response:', response.data);
            // Handle the response, maybe navigate to a confirmation page
            navigate('/rentOrder', { state: { carDetail: response.data, chdate, redate } });
        } catch (error) {
            console.error('Error submitting order:', error);
        }
    };

    return (
        <div>
            <h1>Available Cars</h1>
            <form onSubmit={handleSubmit}>
                <p>取車日期 : {chdate}</p>
                <p>還車日期 : {redate}</p>
                <table border="1">
                    <thead>
                        <tr>
                            <th>選擇</th>
                            <th>車型</th>
                            <th>地點</th>
                            <th>價格</th>
                            <th>可容納人數</th>
                        </tr>
                    </thead>
                    <tbody>
                        {cars.map((car) => (
                            <tr key={car.CarID}>
                                <td>
                                    <input 
                                        type="radio" 
                                        name="selectedCar" 
                                        value={car.CarID}
                                        onChange={(e) => setSelectedCar(e.target.value)}
                                    />
                                </td>
                                <td>{car.CarType}</td>
                                <td>{car.C_Location}</td>
                                <td>{car.Price}</td>
                                <td>{car.PeopleNub}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                <button type="submit">提交訂單</button>
            </form>
        </div>
    );
}

export default Menu;