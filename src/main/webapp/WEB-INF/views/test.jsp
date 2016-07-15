<%--
  Created by IntelliJ IDEA.
  User: JeongHeon
  Date: 2016. 7. 14.
  Time: 오후 7:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<style>
    #modDiv{
        width:300px;
        height:100px;
        background-color: grey;
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -50px;
        margin-left: -150px;
        padding:10px;
        z-index: 1000;
    }
    .pagination {
        width: 100%;
    }

    .pagination li{
        list-style: none;
        float: left;
        padding: 3px;
        border: 1px solid blue;
        margin:3px;
    }

    .pagination li a{
        margin: 3px;
        text-decoration: none;
    }
</style>
<head>
    <title>Title</title>
</head>
<body>
<h2>Ajax Test Page</h2>
<div>
    <div>
        REPLYER <input type="text" name="replyer" id="newReplyWriter">
    </div>
    <div>
        REPLY TEXT <input type="text" name="replytext" id="newReplyText">
    </div>
    <button id="replyAddBtn">ADD REPLY</button>
</div>

<%--수정 삭제--%>
<div id="modDiv" style="display: none;">
    <div class="modal-title"></div>
    <div>
        <input type="text" id="replytext">
    </div>
    <div>
        <button type="button" id="replyModBtn">Modify</button>
        <button type="button" id="replyDelBtn">Delete</button>
        <button type="button" id="closeBtn">Close</button>
    </div>
</div>


<%--댓글--%>
<ul id="replies">

</ul>

<%--댓글 페이징--%>
<ul class="pagination">

</ul>

</body>
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script>
    var bno = 1769440;

    console.log("test1");
    getPageList(1);

    /*전체 목록에 대한 함수 처리*/
    function getAllList(){
        $.getJSON("/replies/all/" + bno, function(data){
            console.log(data.length);

            var str;
            $(data).each(
                    function(){
                        str += "<li data-rno='" + this.rno + " ' class='replyLi'>"
                                + this.rno + ":" + this.replytext +
                                "<button>MOD</button></li>";

                    });
            $("#replies").html(str);
        });
    }

    /*for the Paging*/
    function getPageList(page){
        $.getJSON("/replies/" + bno + "/" + page, function(data){
            console.log(data.list.length);
            var str = "";
            $(data.list).each(function(){
                str += "<li data-rno=' " + this.rno+" ' class='replyLi'>"
                +this.rno+":"+this.replytext+
                                "<button>MOD</button></li>";
            });
            $("#replies").html(str);
            printPaging(data.pageMaker);
        });
    }

    /**/
    function printPaging(pageMaker){

        var str = "";

        if(pageMaker.prev){
            str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";
        }

        for(var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++){
            var strClass= pageMaker.cri.page == i?'class=active':'';
            str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
        }

        if(pageMaker.next){
            str += "<li><a href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";
        }
        $('.pagination').html(str);
    }


    //댓글 추가 버튼
    $("#replyAddBtn").on("click", function(){
        console.log("click replyAddBtn")
        var replyer = $("#newReplyWriter").val();
        var replytext = $("#newReplyText").val();
        //jQuery를 이용하여 ajax를 통해 서버 호출
        $.ajax({
            type : 'post',
            url : '/replies',
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "POST"
            },
            dataType : 'text',
            data : JSON.stringify({
                bno : bno,
                replyer : replyer,
                replytext : replytext
            }),
            success : function(result){
                if(result == 'SUCCESS'){
                    alert("등록 되었습니다");
                    getAllList();
                }
            }
        });
    });

    //MODIFY
    $("#replies").on("click", ".replyLi button", function(){
        var reply = $(this).parent();

        var rno = reply.attr("data-rno");
        var replytext = reply.text();

        $(".modal-title").html(rno);
        $("#replytext").val(replytext);
        $("#modDiv").show("slow");


        getAllList();
        //alert(rno + " : " + replytext);
    });

    //삭제버튼
    $("#replyDelBtn").on("click", function(){
        var rno = $(".modal-title").html();
        var replytext = $("replytext").val();

        $.ajax({
            type : 'delete',
            url : '/replies/' + rno,
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "DELETE"
            },
            dataType : 'text',
            success : function(result){
                console.log("result: "  + result);
                if(result == 'SUCCESS'){
                    alert("delete !!");
                    $("#modDiv").hide("slow");
                    getAllList();
                }
            }
        });
    });

    //수정버튼
    $("#replyModBtn").on("click", function(){
        var rno = $(".modal-title").html();
        var replytext = $("#replytext").val();
        console.log("replytext : " + replytext);

        $.ajax({
            type : 'put',
            url : '/replies/' + rno,
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "PUT"
            },
            data:JSON.stringify({replytext:replytext}),
            dataType : 'text',
            success : function(result){
                console.log("result: "  + result);
                if(result == 'SUCCESS'){
                    alert("modify !!");
                    $("#modDiv").hide("slow");
                    //getAllList();
                    getPageList(replyPage);
                }
            }
        });
    });

    /*댓글 페이징 이벤트 처리*/
    //replyPage수정이나 삭제 작업에 다시 목록 페이지를 갱신할 때 필요하므로 변수 처리
    var replyPage = 1;
    $(".pagination").on("click", "li a", function(event){
        //<a href=">의 기본 동작인 페이지 전환을 막는 역할을 한다
        event.preventDefault();
        replyPage = $(this).attr("href");
        getPageList(replyPage);
    });



    /*
     post의 경우 사용 불가
     그 이유는 replycontroller에서 @requestBody에서 json으로 전송된 데이터를 해당객체 타입으로 변환시켜 주는데 얘는 그렇지 못함
     /*$("#replyAddBtn").on("click", function(){
     var replyer = $("#newReplyWriter").val();
     var replytext = $("#newReplyText").val();

     $.post("/replies",
     {replyer:replyer, replytext:replytext},
     function(result){
     alert(result);
     })
     });*/
</script>
</html>
