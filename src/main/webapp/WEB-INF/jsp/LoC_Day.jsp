<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="searchPlace" method="post">
		<label for="places">取車點 :</label>
        <select name="chplace" id = "place">
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
        </select><br/>
        <p>輸入訂車日期</p>
		<input type="date" name = "chdate" /><br/>
		<p>輸入還車日期</p>		
		<input type="date" name = "redate" /><br/>
		<br/><input type="submit"name="set"value="送出日期與地點"/>
	</form>
</body>
</html>