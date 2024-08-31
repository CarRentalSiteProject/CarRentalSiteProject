import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

function RentOrder() {
    const location = useLocation();
    const navigate = useNavigate();

    // 從location.state獲取傳遞的數據
    const { carDetail, chdate, redate } = location.state;

    const cDetails = carDetail.selectedCar;

    const handleSubmit = (e) => {
        e.preventDefault();
        // 提交表單，跳轉到payment頁面
        navigate('http://localhost:8080/carrent/payment', { state: { carId: cDetails.carID, chdate, redate } });
    };

    return (
        <div>
            <p>取車日期 : {chdate}</p>
            <p>還車日期 : {redate}</p>
            <p>Debug: selectedCar = {JSON.stringify(carDetail)}</p>
            <p>您選擇的車輛:</p>
            <table border="1">
                <thead>
                    <tr>
                        <th>車型</th>
                        <th>地點</th>
                        <th>價格</th>
                        <th>可容納人數</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{cDetails.CarType}</td>
                        <td>{cDetails.C_Location}</td>
                        <td>{cDetails.Price}</td>
                        <td>{cDetails.PeopleNub}</td>
                    </tr>
                </tbody>
            </table>
            <form onSubmit={handleSubmit}>
                <button type="submit">Confirm Order</button>
            </form>
        </div>
    );
}
export default RentOrder;