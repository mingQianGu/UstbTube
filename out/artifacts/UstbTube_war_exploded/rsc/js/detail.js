$(function (){

    const videoElement = document.getElementById("myVideo");
    const titleElement = document.getElementById("videoName");
    const userNameElement = document.getElementById("userName");
    const videoIntroduction = document.getElementById("videoIntroduction");
    const videoCreateTime = document.getElementById("videoCreateTime");
    const videoStar = document.getElementById("videoStar");

    const urlParams = new URLSearchParams(window.location.search);
    const av = urlParams.get("av");
    $.ajax({
        url:"/UstbTube/detail",
        data:{"state":"select", "av":av},
        async:true,
        cache:false,
        type:"POST",
        dataType:"json",
        success:function (result){
            console.log(result);
            videoElement.src = result.path;
            titleElement.innerHTML = result.videoTitle;
            videoIntroduction.innerHTML = result.introduction;

            const createTime = document.createElement("span");
            createTime.innerHTML = result.createTime;
            videoCreateTime.appendChild(createTime);

            userNameElement.innerText = "By ";
            const userLink = document.createElement('a');
            userLink.href = "#";
            userLink.textContent = result.userName;
            userLink.target = "_parent";
            userNameElement.appendChild(userLink);

            const Star = document.createTextNode(result.star);
            videoStar.appendChild(Star);
        }
    })

    $.ajax({
        url: "/UstbTube/comment",
        type: 'GET',
        async:true,
        cache:false,
        data: { videoId: av },
        dataType: 'json',
        success: function(data) {
            // 解析JSON数据
            // var comments = JSON.parse(data);

            // 获取评论容器
            var commentContainer = document.getElementById('commentContainer');

            // 清空评论容器
            commentContainer.innerHTML = '';

            // 遍历评论数据并添加到HTML中
            data.forEach(function(comment) {
                // 创建评论元素
                // var mediaDiv = document.createElement('div');
                // mediaDiv.className = 'media';
                //
                // var mediaLeftDiv = document.createElement('div');
                // mediaLeftDiv.className = 'media-left';
                // var mediaLeftLink = document.createElement('a');
                // mediaLeftLink.href = '#';
                // var mediaLeftImg = document.createElement('img');
                // mediaLeftImg.src = "rsc/images/7.jpg";
                // mediaLeftImg.className = 'media-object';
                // mediaLeftLink.appendChild(mediaLeftImg);
                // mediaLeftDiv.appendChild(mediaLeftLink);
                //
                // var mediaBodyDiv = document.createElement('div');
                // mediaBodyDiv.className = 'media-body';
                // var mediaHeading = document.createElement('h4');
                // mediaHeading.className = 'media-heading';
                // mediaHeading.textContent = comment.userName;
                // var mediaParagraph = document.createElement('p');
                // mediaParagraph.textContent = comment.text;
                // mediaBodyDiv.appendChild(mediaHeading);
                // mediaBodyDiv.appendChild(mediaParagraph);
                var mediaDiv = document.createElement("div");
                // commentElement.textContent = comment.text+comment.userName;
                var styledText = document.createElement("span");
                // styledText.textContent = comment.text + comment.userName;
                styledText.innerHTML = `</div>
                <div class="media-body">
                    <h4 class="media-heading">${comment.userName}(${comment.createTime}):</h4>
                        <p>${comment.text}</p>
                        </div>
                        </div> `;
                styledText.classList.add("highlight"); // 添加自定义的CSS类名


                mediaDiv.appendChild(styledText);


                // 将评论元素添加到容器中
                commentContainer.appendChild(mediaDiv);})

            console.log(data);
        },
        error: function(xhr, status, error) {
            // 处理错误情况
            console.error('Error:', error);
        }
    });

})

$(function (){

    const aboutOneTag = document.getElementById("aboutOneTag");
    const aboutOnePath = document.getElementById("aboutOnePath");
    const aboutOneName = document.getElementById("aboutOneName");
    const aboutOneImg = document.getElementById("aboutOneImg");
    const aboutOnePathName = document.getElementById("aboutOnePathName");
    const aboutOneUserName = document.getElementById("aboutOneUserName");
    const aboutOneCreateTime = document.getElementById("aboutOneCreateTime");
    const aboutOneStar = document.getElementById("aboutOneStar");

    const aboutTwoTag = document.getElementById("aboutTwoTag");
    const aboutTwoPath = document.getElementById("aboutTwoPath");
    const aboutTwoName = document.getElementById("aboutTwoName");
    const aboutTwoImg = document.getElementById("aboutTwoImg");
    const aboutTwoPathName = document.getElementById("aboutTwoPathName");
    const aboutTwoUserName = document.getElementById("aboutTwoUserName");
    const aboutTwoCreateTime = document.getElementById("aboutTwoCreateTime");
    const aboutTwoStar = document.getElementById("aboutTwoStar");

    const aboutThreeTag = document.getElementById("aboutThreeTag");
    const aboutThreePath = document.getElementById("aboutThreePath");
    const aboutThreeName = document.getElementById("aboutThreeName");
    const aboutThreeImg = document.getElementById("aboutThreeImg");
    const aboutThreePathName = document.getElementById("aboutThreePathName");
    const aboutThreeUserName = document.getElementById("aboutThreeUserName");
    const aboutThreeCreateTime = document.getElementById("aboutThreeCreateTime");
    const aboutThreeStar = document.getElementById("aboutThreeStar");



    const urlParams = new URLSearchParams(window.location.search);
    const av = urlParams.get("av");
    $.ajax({
        url:"/UstbTube/detail",
        data:{"state":"about", "av":av},
        async:true,
        cache:false,
        type:"POST",
        dataType:"json",
        success:function (result){
            console.log(result);
            const abouts = result;

            //One
            aboutOneTag.innerHTML = abouts[0].tag;
            aboutOnePath.href = "detail?av=" + abouts[0].videoId;
            aboutOnePath.target = "_self";
            aboutOneName.innerHTML = abouts[0].videoTitle;
            aboutOneImg.src = abouts[0].img;

            const PathNameOne = document.createElement('a');
            PathNameOne.href = "detail?av=" + abouts[0].videoId;
            PathNameOne.textContent = abouts[0].videoTitle;
            aboutOnePathName.appendChild(PathNameOne);

            const UserNameOne = document.createElement('a');
            UserNameOne.href = "#";
            UserNameOne.textContent = abouts[0].userName;
            aboutOneUserName.innerHTML = "By";
            aboutOneUserName.appendChild(UserNameOne);

            const newTextOne = document.createTextNode(abouts[0].createTime)
            aboutOneCreateTime.appendChild(newTextOne);

            const StarOne = document.createTextNode(abouts[0].star);
            aboutOneStar.appendChild(StarOne);

            //Two
            aboutTwoTag.innerHTML = abouts[1].tag;
            aboutTwoPath.href = "detail?av=" + abouts[1].videoId;
            aboutTwoPath.target = "_self";
            aboutTwoName.innerHTML = abouts[1].videoTitle;
            aboutTwoImg.src = abouts[1].img;

            const PathNameTwo = document.createElement('a');
            PathNameTwo.href = "detail?av=" + abouts[1].videoId;
            PathNameTwo.textContent = abouts[1].videoTitle;
            aboutTwoPathName.appendChild(PathNameTwo);

            const UserNameTwo = document.createElement('a');
            UserNameTwo.href = "#";
            UserNameTwo.textContent = abouts[1].userName;
            aboutTwoUserName.innerHTML = "By";
            aboutTwoUserName.appendChild(UserNameTwo);

            const newTextTwo = document.createTextNode(abouts[1].createTime)
            aboutTwoCreateTime.appendChild(newTextTwo);

            const StarTwo = document.createTextNode(abouts[1].star);
            aboutTwoStar.appendChild(StarTwo);

            //Three
            aboutThreeTag.innerHTML = abouts[2].tag;
            aboutThreePath.href = "detail?av=" + abouts[2].videoId;
            aboutThreePath.target = "_self";
            aboutThreeName.innerHTML = abouts[2].videoTitle;
            aboutThreeImg.src = abouts[2].img;

            const PathNameThree = document.createElement('a');
            PathNameThree.href = "detail?av=" + abouts[2].videoId;
            PathNameThree.textContent = abouts[2].videoTitle;
            aboutThreePathName.appendChild(PathNameThree);

            const UserNameThree = document.createElement('a');
            UserNameThree.href = "#";
            UserNameThree.textContent = abouts[2].userName;
            aboutThreeUserName.innerHTML = "By";
            aboutThreeUserName.appendChild(UserNameThree);

            const newTextThree = document.createTextNode(abouts[2].createTime)
            aboutThreeCreateTime.appendChild(newTextThree);

            const StarThree = document.createTextNode(abouts[2].star);
            aboutThreeStar.appendChild(StarThree);

        }
    })

})


// 检测用户对视频的状态
$(function (){

    const star = document.getElementById("star");
    const favorite = document.getElementById("favorite");
    const view = document.getElementById("view");
    const support = document.getElementById("support");


    const urlParams = new URLSearchParams(window.location.search);
    const av = urlParams.get("av");
    $.ajax({
        url:"/UstbTube/detail",
        data:{"state":"userAbout", "av":av},
        async:true,
        cache:false,
        type:"POST",
        dataType:"json",
        success:function (result){
            console.log(result);

            if(result === false) {
                star.innerHTML = "喜欢";
                favorite.innerHTML = "收藏";
                view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234567";
                support.innerHTML = "关注";
            } else if(result === 0) {
                star.innerHTML = "喜欢";
                favorite.innerHTML = "收藏";
                view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234567";
                support.innerHTML = "关注";
            } else if(result === 100) {
                star.innerHTML = "❤ 喜欢";
                favorite.innerHTML = "收藏";
                view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234";
                support.innerHTML = "关注";
            } else if(result === 10) {
                star.innerHTML = "喜欢";
                favorite.innerHTML = "🔴 收藏";
                view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234";
                support.innerHTML = "关注";
            } else if(result === 110) {
                star.innerHTML = "❤ 喜欢";
                favorite.innerHTML = "🔴 收藏";
                view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901";
                support.innerHTML = "关注";
            }

        }
    })

})

// star

$(function (){

    const star = document.getElementById("star");
    const favorite = document.getElementById("favorite");
    const view = document.getElementById("view");
    const support = document.getElementById("support");


    const urlParams = new URLSearchParams(window.location.search);
    const av = urlParams.get("av");

    $("#star").click(function () {

            $.ajax({
                url:"/UstbTube/detail",
                data:{"state":"star", "av":av},
                async:true,
                cache:false,
                type:"POST",
                dataType:"json",
                success:function (result){
                    console.log(result);

                    if(result === false) {
                        alert("您尚未登录，请登录");
                    } else {
                        $.ajax({
                            url: "/UstbTube/detail",
                            data: {"state": "userAbout", "av": av},
                            async: true,
                            cache: false,
                            type: "POST",
                            dataType: "json",
                            success: function (result) {
                                console.log(result);

                                if (result === false) {
                                    star.innerHTML = "喜欢";
                                    favorite.innerHTML = "收藏";
                                    view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234567";
                                    support.innerHTML = "关注";
                                } else if (result === 0) {
                                    star.innerHTML = "喜欢";
                                    favorite.innerHTML = "收藏";
                                    view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234567";
                                    support.innerHTML = "关注";
                                } else if (result === 100) {
                                    star.innerHTML = "❤ 喜欢";
                                    favorite.innerHTML = "收藏";
                                    view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234";
                                    support.innerHTML = "关注";
                                } else if (result === 10) {
                                    star.innerHTML = "喜欢";
                                    favorite.innerHTML = "🔴 收藏";
                                    view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234";
                                    support.innerHTML = "关注";
                                } else if (result === 110) {
                                    star.innerHTML = "❤ 喜欢";
                                    favorite.innerHTML = "🔴 收藏";
                                    view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901";
                                    support.innerHTML = "关注";
                                }

                            }
                        })
                    }
                }
            })
    }
    )
})

// favorites

$(function (){

    const star = document.getElementById("star");
    const favorite = document.getElementById("favorite");
    const view = document.getElementById("view");
    const support = document.getElementById("support");


    const urlParams = new URLSearchParams(window.location.search);
    const av = urlParams.get("av");

    $("#favorite").click(function () {

            $.ajax({
                url:"/UstbTube/detail",
                data:{"state":"favorite", "av":av},
                async:true,
                cache:false,
                type:"POST",
                dataType:"json",
                success:function (result){
                    console.log(result);

                    if(result === false) {
                        alert("您尚未登录，请登录");
                    } else {
                        $.ajax({
                            url: "/UstbTube/detail",
                            data: {"state": "userAbout", "av": av},
                            async: true,
                            cache: false,
                            type: "POST",
                            dataType: "json",
                            success: function (result) {
                                console.log(result);

                                if (result === false) {
                                    star.innerHTML = "喜欢";
                                    favorite.innerHTML = "收藏";
                                    view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234567";
                                    support.innerHTML = "关注";
                                } else if (result === 0) {
                                    star.innerHTML = "喜欢";
                                    favorite.innerHTML = "收藏";
                                    view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234567";
                                    support.innerHTML = "关注";
                                } else if (result === 100) {
                                    star.innerHTML = "❤ 喜欢";
                                    favorite.innerHTML = "收藏";
                                    view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234";
                                    support.innerHTML = "关注";
                                } else if (result === 10) {
                                    star.innerHTML = "喜欢";
                                    favorite.innerHTML = "🔴 收藏";
                                    view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901234";
                                    support.innerHTML = "关注";
                                } else if (result === 110) {
                                    star.innerHTML = "❤ 喜欢";
                                    favorite.innerHTML = "🔴 收藏";
                                    view.innerHTML = "1234567890123456789012345678901234567890123456789012345678901";
                                    support.innerHTML = "关注";
                                }

                            }
                        })
                    }
                }
            })
        }
    )
})


$(function (){

    const urlParams = new URLSearchParams(window.location.search);
    const av = urlParams.get("av");
    const commentResult = document.getElementById("commentResult");
    $("#btnSend").click(function () {


        const comment = $("#message").val();
        $.ajax({
            url: "/UstbTube/comment",
            data: {"av": av, "comment": comment},
            async: true,
            cache: false,
            type: "POST",
            dataType: "json",
            success: function (result) {
                console.log(result);
                if(result === false) {
                    alert("您尚未登录，请登录");
                } else if(result === 1){
                    commentResult.innerHTML = "评论成功";
                } else if(result === 0) {
                    commentResult.innerHTML = "评论失败";
                } else if(result === 2) {
                    alert("评论不能为空");
                }
            }
        })
    })
})
