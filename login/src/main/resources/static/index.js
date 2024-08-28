$(document).ready(function() {
    var token = localStorage.getItem("jwtToken");
    if (token) {
        // 如果有 token，顯示用戶資訊
        $("#userInfo").hide();
        $("#content").show();
        // 這裡可以添加一個 API 調用來獲取用戶資訊
        // 暫時使用存儲的用戶名
        $("#username").text(localStorage.getItem("username"));
    }

    $("#logoutBtn").click(function() {
        localStorage.removeItem("jwtToken");
        localStorage.removeItem("username");
        window.location.reload();
    });
});