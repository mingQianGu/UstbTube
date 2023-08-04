$(function (){

    const welcome = document.getElementById("welcome");
    const submit = document.getElementById("submit");

    const log = document.getElementById("log");
    const mine = document.getElementById("mine");
    const favorites = document.getElementById("favorites");
    const history = document.getElementById("history");
    const exit = document.getElementById("exit");

    $.ajax({
        url:"/UstbTube/log",
        data:{"state":"get"},
        async:true,
        cache:false,
        type:"POST",
        dataType:"json",
        success:function (result){
            console.log(result);

            if(result === false) {
                welcome.innerHTML = "Welcome to USTBTUBE!";
                submit.style.visibility = 'hidden';

                mine.remove();
                favorites.remove();
                history.remove();
                exit.remove();
                mine.remove();

            } else{
                welcome.innerHTML = "Welcome to USTBTUBE! " + result.nickName;
                submit.style.visibility = '';

                log.remove();

            }

        }
    })


})