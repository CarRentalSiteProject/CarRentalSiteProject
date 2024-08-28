/*
在頁面加載時，立即檢查用戶是否已登入。
如果本地存儲中沒有 JWT token，直接重定向到登入頁面。
如果有 token，則向後端發送請求驗證 token 的有效性。
如果 token 有效，顯示菜單內容。
如果 token 無效或過期，清除本地存儲並重定向到登入頁面
*/

$(document).ready(function() {
    function checkAuth() {
        var token = localStorage.getItem("jwtToken");
        if (!token) {
            window.location.href = "/login";
            return;
        }

        // 驗證 token 有效性
        $.ajax({
            url: "/api/validate-token",
            type: "GET",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            },
            success: function(response) {
                // Token 有效，顯示菜單內容
                $("#menuContent").show();
            },
            error: function(xhr, status, error) {
                // Token 無效或過期，重定向到登入頁面
                localStorage.removeItem("jwtToken");
                localStorage.removeItem("username");
                window.location.href = "/login";
            }
        });
    }

    checkAuth();

    // 在這裡添加其他與菜單相關的功能
});