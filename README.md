# CarRentalSiteProject
## 專案簡介
這是一個租車網站的實作專案，
前端使用 React 架構實現互動式使用者界面，
後端則採用 Spring Boot 提供 REST API 服務，確保系統穩定性與可擴展性。
此外，專案也整合了第三方金流服務，為用戶提供便捷的線上支付功能。
## 功能
* 登入登出
* 註冊
* 條件查詢
* 第三方金流支付
* 查看訂單
* JWT Security
## 系統架構
![](/imgs/CarRent.jpg)
## 活動圖
![](/imgs/carRentalActivity.jpg)
## 安裝與使用
1. Clone本專案到本地端
```bash=
git clone https://github.com/CarRentalSiteProject/CarRentalSiteProject.git
```
2. application.properties中更改資料庫名稱
```bash=
spring.datasource.url=jdbc:mysql://localhost:3306/database_name
```
3. 執行CarRentTestApplication.java
