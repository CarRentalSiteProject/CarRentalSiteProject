function validateLogin(event) {
            event.preventDefault(); // 阻止表单提交

//            // 预设的账号和密码
//            const presetUsername = "admin";//"your_username"
//            const presetPassword = "admin123";//"your_password"
//
//            // 获取用户输入的账号和密码
//            const username = document.getElementById("username").value;
//            const password = document.getElementById("password").value;

            // 验证账号和密码
            if (username === presetUsername && password === presetPassword) {
                // 验证通过，跳转到 index.jsp
                window.location.href = "index.jsp";
            } else {
                // 验证失败，显示错误消息
                alert("Incorrect username or password.");
            }
        }