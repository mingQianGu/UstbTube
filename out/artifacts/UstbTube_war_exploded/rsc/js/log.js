$(function (){

    const send = document.getElementById("send");
    const reg = /^1[3-9]\d{9}$/;

    $("#sendCode").click(function () {
        const phone = $("#phone").val();
        $.ajax({
            url: "/UstbTube/log",
            data: {"state": "sendCode", "phone": phone},
            async: true,
            cache: false,
            type: "POST",
            dataType: "json",
            success: function (result) {
                console.log(result);
                if (result === true) {
                    send.innerHTML = "验证码发送成功";
                } else if(!reg.test(phone)) {
                    send.innerHTML = "手机号码格式不正确"
                }

            }
        })
    })
})

$(function (){

    const registerResult = document.getElementById("registerResult");
    const send = document.getElementById("send");
    const nameResult = document.getElementById("nameResult");
    const reg = /^1[3-9]\d{9}$/;

    $("#register").click(function () {
        const nickName = $("#nickName").val();
        const phone = $("#phone").val();
        const code = $("#code").val();
        const passWord = $("#passWord").val();

        if(!reg.test(phone)) {
            send.innerHTML = "手机号码格式不正确";
        } else {
            $.ajax({
                url: "/UstbTube/log",
                data: {"state": "register","nickName":nickName, "phone": phone,
                    "code": code, "passWord":passWord},
                async: true,
                cache: false,
                type: "POST",
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    if(result === "name") {
                        nameResult.innerHTML = "名字已被占用";
                        send.innerHTML = "";
                    } else if(result === "code") {
                        send.innerHTML = "验证码不正确";
                        nameResult.innerHTML = "";
                    } else {
                        registerResult.innerHTML = "注册成功 " + result.userId;
                        send.innerHTML = "";
                        nameResult.innerHTML = "";
                    }

                }
            })
        }


    })
})
