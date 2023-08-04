//最火视频推荐

$(function (){

    const hottestPath = document.getElementById("hottestPath");
    const hottestName = document.getElementById("hottestName");
    const hottestImg = document.getElementById("hottestImg");
    const hottestTag = document.getElementById("hottestTag");

    $.ajax({
        url:"/UstbTube/index",
        data:{"state":"hottest"},
        async:true,
        cache:false,
        type:"POST",
        dataType:"json",
        success:function (result){
            console.log(result);
            hottestPath.href = "detail?av=" + result.videoId;
            hottestPath.target = "_blank";
            hottestName.innerHTML = result.videoTitle;
            hottestImg.src = result.img;
            hottestTag.innerHTML = "人气❤" + result.tag;
        }
    })

})

//推荐四个视频
$(function (){

    const guessOnePath = document.getElementById("guessOne");
    const guessOneName = document.getElementById("guessOneName");
    const guessOneImg = document.getElementById("guessOneImg");
    const guessOneTag = document.getElementById("guessOneTag");

    const guessTwoPath = document.getElementById("guessTwo");
    const guessTwoName = document.getElementById("guessTwoName");
    const guessTwoImg = document.getElementById("guessTwoImg");
    const guessTwoTag = document.getElementById("guessTwoTag");

    const guessThreePath = document.getElementById("guessThree");
    const guessThreeName = document.getElementById("guessThreeName");
    const guessThreeImg = document.getElementById("guessThreeImg");
    const guessThreeTag = document.getElementById("guessThreeTag");

    const guessFourPath = document.getElementById("guessFour");
    const guessFourName = document.getElementById("guessFourName");
    const guessFourImg = document.getElementById("guessFourImg");
    const guessFourTag = document.getElementById("guessFourTag");

    $.ajax({
        url:"/UstbTube/index",
        data:{"state":"guess", "count":10},
        async:true,
        cache:false,
        type:"POST",
        dataType:"json",
        success:function (result){
            console.log(result);
            const guesses = result;

            guessOnePath.href = "detail?av=" + guesses[0].videoId;
            guessOnePath.target = "_blank";
            guessOneName.innerHTML = guesses[0].videoTitle;
            guessOneImg.src = guesses[0].img;
            guessOneTag.innerHTML = guesses[0].tag;

            guessTwoPath.href = "detail?av=" + guesses[1].videoId;
            guessTwoPath.target = "_blank";
            guessTwoName.innerHTML = guesses[1].videoTitle;
            guessTwoImg.src = guesses[1].img;
            guessTwoTag.innerHTML = guesses[1].tag;

            guessThreePath.href = "detail?av=" + guesses[2].videoId;
            guessThreePath.target = "_blank";
            guessThreeName.innerHTML = guesses[2].videoTitle;
            guessThreeImg.src = guesses[2].img;
            guessThreeTag.innerHTML = guesses[2].tag;

            guessFourPath.href = "detail?av=" + guesses[3].videoId;
            guessFourPath.target = "_blank";
            guessFourName.innerHTML = guesses[3].videoTitle;
            guessFourImg.src = guesses[3].img;
            guessFourTag.innerHTML = guesses[3].tag;

        }
    })

})

// 最新视频

$(function (){

    const newOneTag = document.getElementById("newOneTag");
    const newOnePath = document.getElementById("newOnePath");
    const newOneName = document.getElementById("newOneName");
    const newOneImg = document.getElementById("newOneImg");
    const newOnePathName = document.getElementById("newOnePathName");
    const newOneUserName = document.getElementById("newOneUserName");
    const newOneCreateTime = document.getElementById("newOneCreateTime");
    const newOneStar = document.getElementById("newOneStar");

    const newTwoTag = document.getElementById("newTwoTag");
    const newTwoPath = document.getElementById("newTwoPath");
    const newTwoName = document.getElementById("newTwoName");
    const newTwoImg = document.getElementById("newTwoImg");
    const newTwoPathName = document.getElementById("newTwoPathName");
    const newTwoUserName = document.getElementById("newTwoUserName");
    const newTwoCreateTime = document.getElementById("newTwoCreateTime");
    const newTwoStar = document.getElementById("newTwoStar");

    const newThreeTag = document.getElementById("newThreeTag");
    const newThreePath = document.getElementById("newThreePath");
    const newThreeName = document.getElementById("newThreeName");
    const newThreeImg = document.getElementById("newThreeImg");
    const newThreePathName = document.getElementById("newThreePathName");
    const newThreeUserName = document.getElementById("newThreeUserName");
    const newThreeCreateTime = document.getElementById("newThreeCreateTime");
    const newThreeStar = document.getElementById("newThreeStar");

    $.ajax({
        url:"/UstbTube/index",
        data:{"state":"new"},
        async:true,
        cache:false,
        type:"POST",
        dataType:"json",
        success:function (result){
            console.log(result);
            const news = result;

            //One
            newOneTag.innerHTML = news[0].tag;
            newOnePath.href = "detail?av=" + news[0].videoId;
            newOnePath.target = "_parent";
            newOneName.innerHTML = news[0].videoTitle;
            newOneImg.src = news[0].img;

            const PathNameOne = document.createElement('a');
            PathNameOne.href = "detail?av=" + news[0].videoId;
            PathNameOne.textContent = news[0].videoTitle;
            newOnePathName.appendChild(PathNameOne);

            const UserNameOne = document.createElement('a');
            UserNameOne.href = "#";
            UserNameOne.textContent = news[0].userName;
            newOneUserName.innerHTML = "By";
            newOneUserName.appendChild(UserNameOne);

            const newTextOne = document.createTextNode(news[0].createTime);
            newOneCreateTime.appendChild(newTextOne);

            const TextOne = document.createTextNode(news[0].star);
            newOneStar.appendChild(TextOne);

            //Two
            newTwoTag.innerHTML = news[1].tag;
            newTwoPath.href = "detail?av=" + news[1].videoId;
            newTwoPath.target = "_parent";
            newTwoName.innerHTML = news[1].videoTitle;
            newTwoImg.src = news[1].img;

            const PathNameTwo = document.createElement('a');
            PathNameTwo.href = "detail?av=" + news[1].videoId;
            PathNameTwo.textContent = news[1].videoTitle;
            newTwoPathName.appendChild(PathNameTwo);

            const UserNameTwo = document.createElement('a');
            UserNameTwo.href = "#";
            UserNameTwo.textContent = news[1].userName;
            newTwoUserName.innerHTML = "By";
            newTwoUserName.appendChild(UserNameTwo);

            const newTextTwo = document.createTextNode(news[1].createTime)
            newTwoCreateTime.appendChild(newTextTwo);

            const TextTwo = document.createTextNode(news[1].star);
            newTwoStar.appendChild(TextTwo);

            //Three
            newThreeTag.innerHTML = news[2].tag;
            newThreePath.href = "detail?av=" + news[2].videoId;
            newThreePath.target = "_parent";
            newThreeName.innerHTML = news[2].videoTitle;
            newThreeImg.src = news[2].img;

            const PathNameThree = document.createElement('a');
            PathNameThree.href = "detail?av=" + news[2].videoId;
            PathNameThree.textContent = news[2].videoTitle;
            newThreePathName.appendChild(PathNameThree);

            const UserNameThree = document.createElement('a');
            UserNameThree.href = "#";
            UserNameThree.textContent = news[2].userName;
            newThreeUserName.innerHTML = "By";
            newThreeUserName.appendChild(UserNameThree);

            const newTextThree = document.createTextNode(news[2].createTime)
            newThreeCreateTime.appendChild(newTextThree);

            const TextThree = document.createTextNode(news[2].star);
            newThreeStar.appendChild(TextThree);

        }
    })

})

// 最新资讯

$(function (){

    $.ajax({
        url:"/UstbTube/index",
        data:{"state":"topNews"},
        async:true,
        cache:false,
        type:"POST",
        dataType:"json",
        success:function (result){
            const  topNews = $('#topNews');
            result.forEach(function (item) {
                const videoDiv = $('<div>').addClass('wrap-vid');
                const zoomContainer = $('<div>').addClass('zoom-container');
                const zoomCaption = $('<div>').addClass('zoom-caption');
                const tagSpan = $('<span>').text(item.tag);
                const link = $('<a>').attr('href', "detail?av=" + item.videoId);
                const playIcon = $('<i>').addClass('fa fa-play-circle-o fa-5x').css('color', '#fff');
                const videoName = $('<p>').text(item.videoTitle);
                const img = $('<img>').attr('src', item.img);
                const videoNameHeader = $('<h3>').addClass('vid-name').append($('<a>').attr('href', "detail?av=" + item.videoId).text(item.videoTitle));
                const infoDiv = $('<div>').addClass('info');
                const uploadedBy = $('<h5>').text('By ').append($('<a>').attr('href', '#').text(item.userName));
                const uploadDate = $('<span>').append($('<i>').addClass('fa fa-calendar')).text(item.createTime);
                const likesCount = $('<span>').append($('<i>').addClass('fa fa-heart')).text(item.star);

                uploadDate.prepend($('<i>').addClass('fa fa-calendar'));
                likesCount.prepend($('<i>').addClass('fa fa-heart'));

                zoomCaption.append(tagSpan, link.append(playIcon), videoName);
                zoomContainer.append(zoomCaption, img);
                infoDiv.append(uploadedBy, uploadDate, likesCount);
                videoDiv.append(zoomContainer, videoNameHeader, infoDiv);

                topNews.append(videoDiv);
                }
            )

        }
    })

})