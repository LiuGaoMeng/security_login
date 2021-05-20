// $(document).ready(function(){
layui.use(['jquery','layer'],function(){
    var $ = layui.$, layer = layui.layer;
    // 获取元素和点击操作
    const signInBtn = document.getElementById("signIn");
    const signUpBtn = document.getElementById("signUp");
    const fistForm = document.getElementById("form1");
    const secondForm = document.getElementById("form2");
    const container = document.querySelector(".container");

    signInBtn.addEventListener("click", () => {
        container.classList.remove("right-panel-active");
    });
    signUpBtn.addEventListener("click", () => {
        container.classList.add("right-panel-active");
    });
    $("#register_user").click(function () {
        $.ajax({
            type: 'get',
            async:false,
            url: "/user/register",
            dataType:'json',
            data: {
                username: $('#form1 input[name=username]').val(),
                password:$('#form1 input[name=password]').val()
            },
            success:function (res) {
                if (res.status){
                    layer.msg(res.message, { icon: 1, area:['250px', '70px'], time:1000 },function(){
                        setTimeout('window.location.reload()',500);
                    });
                }
            },
            error : function(res) {
                layer.msg(res.message, { icon: 1, area:['250px', '70px'], time:1000 },function(){
                    setTimeout('window.location.reload()',500);
                });
            }
        })
    });
    $("#login_user").click((event)=>{
            $.ajax({
                url: "/login",
                dataType:"json",
                type:'post',
                async:false,
                data:{
                    username: $('#form2 input[name=username]').val(),
                    password:$('#form2 input[name=password]').val()
                },
                success:function (data) {
                    layer.msg(data.msg, { icon: 1, area:['250px', '70px'], time:1000 },function(){
                        setTimeout(window.location.href = "/main/main.html",500);
                    });
                },
                error:function (data){
                    layer.msg(data.responseJSON.msg, { icon: 1, area:['350px', '70px'], time:1500 },function(){
                        setTimeout(window.location.href = "/login.html",500);
                    });
                }
            })

    });
})