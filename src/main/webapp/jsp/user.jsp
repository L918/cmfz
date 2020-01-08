<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../boot/css/back.css">
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" href="../jqgrid/css/jquery-ui.css">
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="../boot/js/bootstrap.min.js"></script>
    <script src="../jqgrid/js/trirand/src/jquery.jqGrid.js"></script>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <script src="../echarts/echarts.min.js"></script>
    <!-- 将https协议改为http协议 -->
    <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.3.js"></script>
<script type="text/javascript">
    $(function(){
        $("#userTable").jqGrid(
            {
                url: '${pageContext.request.contextPath}/user/showUser',
                datatype: "json",
                height: 300,
                colNames : [ 'ID', '手机号', '密码', '盐值', '头像','签名', '法号','状态','姓名','性别','地区','注册时间','最后登录时间' ],
                colModel : [
                    {name : 'id',hidden:true,align:"center"},
                    {name : 'phone',align:"center",editable:true},
                    {name : 'password',align:"center",editable:true},
                    {name : 'salt',align:"center",editable:true},
                    {name : 'photo',align:"center",formatter:function (data) {
                            return "<img style='height: 80px;width: 180px' src='"+data+"'>";
                        },editable:true,edittype: "file",editoptions:{enctype:"multipart/form-data"}},
                    {name : 'sign',align:"center",editable:true},
                    {name : 'name',align:"center",editable:true},

                    {name : 'status',align:"center",formatter:function (data) {
                            if (data=="1"){
                                return "展示";
                            } else return "冻结";
                        },editable:true,edittype:"select",editoptions: {value:"1:展示;2:冻结"}},
                    {name : 'nickName',align:"center",editable:true},
                    {name : 'sex',align:"center",formatter:function (data) {
                            if (data=="1"){
                                return "男";
                            } else return "女";
                        },editable:true,edittype:"select",editoptions: {value:"1:男;0:女"}},

                    {name : 'location',align:"center",editable:true},
                    {name : 'rigestDate',align:"center",editable:true,edittype:"date"},
                    {name : 'lostLogin',align:"center",editable:true,edittype:"date"},

                ],
                rowNum: 5,
                rowList: [5, 10, 15, 20],
                pager : '#userPage',
                sortname: 'id',
                viewrecords: true,//是否要显示总记录数
                sortorder: "desc",//排序方式
                mtype: "post",
                autowidth: true,//自动列宽
                height: '45%',//设置表格高度
                hidegrid: true,
                caption: "用户列表",
                editurl: "${pageContext.request.contextPath}/user/edit",
            });
        $("#albumTable").jqGrid('navGrid', '#userPage', {
                add: true,
                edit: true,
                del: false,
                edittext: "修改",
                addtext: "添加",
                deltext: "删除"
            },
            {
                closeAfterEdit: true,
                afterSubmit: function (response, postData) {
                    var userId = response.responseJSON.userId;
                    $.ajaxFileUpload({
                        // 指定上传路径
                        url: "${pageContext.request.contextPath}/user/upload",
                        type: "post",
                        datatype: "json",
                        data: {userId: userId},// 指定上传的input框id
                        fileElementId: "photo",
                        success:function (data) {
                            $("#userTable").trigger("reloadGrid")
                        }
                    });
                    // 防止页面报错
                    return postData;
                }
            },
            {
                closeAfterAdd: true,
                // 数据库添加轮播图后 进行上传 上传完成后需更改url路径 需要获取添加轮播图的Id
                afterSubmit: function (response, postData) {
                    var userId = response.responseJSON.userId;
                    $.ajaxFileUpload({
                        // 指定上传路径
                        url: "${pageContext.request.contextPath}/user/upload",
                        type: "post",
                        datatype: "json",
                        data: {userId: userId},// 指定上传的input框id
                        fileElementId: "cover",
                        success:function (photo) {
                            $("#userTable").trigger("reloadGrid")
                        }
                    });
                    // 防止页面报错
                    return postData;
                }
            },
            {closeAfterDel: true});
    });

</script>
</head>
<!--初始化面板-->
<div class="panel panel-info">
    <!--面板头-->
    <div class="panel panel-heading">
        <h2>用户信息---</h2>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#"></a></li>
    </ul>

    <!--初始化表单-->
    <table id="userTable"></table>
    <!--分页工具栏-->
    <div id="userPage" style="height: 80px"></div>
</div>