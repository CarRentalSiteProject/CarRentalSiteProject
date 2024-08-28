    function addAuthHeader(xhr) {
        var token = localStorage.getItem("jwtToken");
        if (token) {
            xhr.setRequestHeader("Authorization", "Bearer " + token);
        }
    }

    $(document).ready(function() {
        $("#loginForm").submit(function(event) {
            event.preventDefault();
            var formData = {
                username: $("#username").val(),
                password: $("#password").val()
            };

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/login",
                data: JSON.stringify(formData),
                dataType: 'json',
                success: function(result) {
                    localStorage.setItem("jwtToken", result.token);
                    localStorage.setItem("username", result.username);

                    
                    alert("登錄成功！");
                    window.location.href = "/index";
                },
                error: function(e) {
                    alert("登錄失敗：" + e.responseText);
                }
            });
        });
    });