<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp"%>

<style>
    .fileDrop {
        width: 80%;
        height: 100px;
        border: 1px dotted gray;
        background-color: lightslategrey;
        margin: auto;

    }
</style>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id='registerForm' role="form" method="post">
    <div class="box-body">
        <div class="form-group">
            <label for="exampleInputEmail1">Title</label>
            <input type="text" name="title" class="form-control" placeholder="Enter Title">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Content</label>
            <textarea class="form-control" name="content" rows="3" placeholder="Enter ..."></textarea>
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">Writer</label>
            <input type="text" name="writer" class="form-control" placeholder="Enter Writer">
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">File DROP Here</label>
            <div class="fileDrop"></div>
        </div>
    </div>
    <%--Box body--%>

    <div class="box-footer">
        <div>
            <hr/>
        </div>

        <ul class="mailbox-attachments clearfix uploadedList">

        </ul>
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
</form>
</body>

<%--
1. 서버에 업로드 된 파일의 이름
2. getFileInfo()를 통한 정보 추출
3. JavaScript객체 생성
4. handlebars 템플릿에 적용
5. HTML생성
6. 화면 적용
--%>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script id="template" type="text/x-handlebars-template">
    <li>
        <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
        <div class="mailbox-attachment-info">
            <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
            <a href="{{fullName}}"
               class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
            </span>
        </div>
    </li>
</script>

<%--파일의 드래그 앤 드롭 기능--%>
<script>

    var template = Handlebars.compile($("#template").html());

    $(".fileDrop").on("dragenter dragover", function(event){
        event.preventDefault();
    });


    $(".fileDrop").on("drop", function(event){
        event.preventDefault();

        var files = event.originalEvent.dataTransfer.files;

        var file = files[0];

        var formData = new FormData();

        formData.append("file", file);


        $.ajax({
            url: '/uploadAjax',
            data: formData,
            dataType:'text',
            processData: false,
            contentType: false,
            type: 'POST',
            success: function(data){

                var fileInfo = getFileInfo(data);

                var html = template(fileInfo);

                $(".uploadedList").append(html);
            }
        });
    });

    /*사용자가 업로드 한 파일의 정보를 form태그의 내부로 포함시켜서 전송하는 방식*/
    $("#registerForm").submit(function(event){
        event.preventDefault();

        var that = $(this);

        var str ="";
        $(".uploadedList .delbtn").each(function(index){
            str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
        });
        console.log(str);
        that.append(str);

        //jQuery의 get(0)은 순수한 DOM객체를 얻기 위해 사용
        that.get(0).submit();
    });



</script>

</html>

<%@ include file="/WEB-INF/views/include/footer.jsp"%>
