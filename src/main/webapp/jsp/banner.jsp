<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
    $(function () {

        //初始化表单
        $("#bannerTable").jqGrid({
            url : "${path}/banner/showAll",
            editurl:"${path}/banner/edit",
            datatype : "json",
            rowNum : 3,  //每页展示条数   page   rows
            rowList : [ 2,3,5],  //可选展示条数
            styleUI:"Bootstrap",
            pager : '#bannerPage',  //分页工具栏
            viewrecords : true,  //是否显示总条数
            autowidth:true,
            height : "500px",
            colNames : [ 'Id', '名字', '图片', '描述', '状态','上传时间'],
            colModel : [
                {name : 'id'},
                {name : 'title',editable:true,},
                {name : 'url',editable:true,align : "center",edittype:"file",
                    formatter:function(data){
                        return "<img src='"+data+"' style='width:90px;height:150px' >";
                    }
                },
                {name : 'description',editable:true,align : "center"},
                {name : 'status',align : "center",
                    formatter:function (data) {
                        if (data == "1"){
                            return "展示";
                        }
                        else {
                            return "冻结";
                        }
                    },editable:true,editrules:{required:true},edittype:"select",editoptions: {value:"1:展示;2:冻结"}
            },
                {name : 'createDate',align:"center",editable:true,editrules:{required:true},edittype: "date"},

            ]
        });

        //处理增删改查操作
        $("#bannerTable").jqGrid('navGrid', '#bannerPage', {edit:true, add:true, del:true, addtext:"添加",edittext:"修改",deltext:"删除"},
            {
                closeAfterEdit:true,
                afterSubmit:function (response,postData) {
                    var bannerId = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/upload",
                        type:"post",
                        datatype: "json",
                        data:{bannerId:bannerId},
                        fileElementId:"url",
                        success:function (data) {
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    })
                    return postData;
                }
            },  //执行修改操作的额外配置
            {
                //关闭对话框
                closeAfterAdd:true,
                afterSubmit:function (response,postData) {
                    var bannerId = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/upload",
                        type:"post",
                        datatype: "json",
                        data:{bannerId:bannerId},
                        fileElementId:"url",
                        success:function (data) {
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    })
                    return postData;
                }
            }, //执行添加操作的额外配置
            {}
        );
    });
</script>


<!--初始化面板-->
<div class="panel panel-info">
    <!--面板头-->
    <div class="panel panel-heading">
        <h2>轮播图信息</h2>
    </div>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#"></a></li>
    </ul>

    <!--初始化表单-->
    <table id="bannerTable"></table>
    <!--分页工具栏-->
    <div id="bannerPage" style="height: 80px"></div>
</div>