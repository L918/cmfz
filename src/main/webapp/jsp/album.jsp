<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<script type="text/javascript">
    $(function () {
        $("#albumTable").jqGrid(
            {
                url: '${pageContext.request.contextPath}/album/showAll',
                datatype: "json",
                height: 300,
                colNames : [ 'ID', '标题', '分数', '作者', '播音','章节数', '描述','状态','创建时间','封面' ],
                colModel : [
                    {name : 'id',hidden:true,align:"center"},
                    {name : 'title',align:"center",editable:true},
                    {name : 'score',align:"center",editable:true},
                    {name : 'author',align:"center",editable:true},
                    {name : 'broadcast',align:"center",editable:true},
                    {name : 'ccount',align:"center",editable:true},
                    {name : 'description',align:"center",editable:true},
                    {name : 'status',align:"center",formatter:function (data) {
                            if (data=="1"){
                                return "展示";
                            } else return "冻结";
                        },editable:true,edittype:"select",editoptions: {value:"1:展示;2:冻结"}},
                    {name : 'createdate',align:"center",editable:true,edittype:"date"},
                    {name : 'cover',align:"center",formatter:function (data) {
                            return "<img style='height: 80px;width: 180px' src='"+data+"'>";
                        },editable:true,edittype: "file",editoptions:{enctype:"multipart/form-data"}}
                ],
                rowNum: 5,
                rowList: [5, 10, 15, 20],
                pager : '#albumPage',
                sortname: 'id',
                viewrecords: true,//是否要显示总记录数
                sortorder: "desc",//排序方式
                mtype: "post",
                autowidth: true,//自动列宽
                height: '45%',//设置表格高度
                hidegrid: true,
                // 开启子表格支持
                subGrid: true,
                caption: "专辑列表",
                editurl: "${pageContext.request.contextPath}/album/edit",
                // subgrid_id:父级行的Id  row_id:当前的数据Id
                subGridRowExpanded: function (subgrid_id, row_id) {
                    // 调用生产子表格的方法
                    // 生成表格 | 生产子表格工具栏
                    addSubgrid(subgrid_id, row_id);
                },
                // 删除表格的方法
                subGridRowColapsed: function (subgrid_id, row_id) {
                }
            });
        $("#albumTable").jqGrid('navGrid', '#albumPage', {
                add: true,
                edit: true,
                del: true,
                search: false,
                edittext: "修改",
                addtext: "添加",
                deltext: "删除"
            },
            {
                closeAfterEdit: true,
                afterSubmit: function (response, postData) {
                    var albumId = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        // 指定上传路径
                        url: "${pageContext.request.contextPath}/album/upload",
                        type: "post",
                        datatype: "json",
                        data: {albumId: albumId},// 指定上传的input框id
                        fileElementId: "cover",
                        success:function (data) {
                            $("#albumTable").trigger("reloadGrid")
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
                    var albumId = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        // 指定上传路径
                        url: "${pageContext.request.contextPath}/album/upload",
                        type: "post",
                        datatype: "json",
                        data: {albumId: albumId},// 指定上传的input框id
                        fileElementId: "cover",
                        success:function (data) {
                            $("#albumTable").trigger("reloadGrid")
                        }
                    });
                    // 防止页面报错
                    return postData;
                }
            },
            {closeAfterDel: true});
    });

    // subgrid_id: 行id  row_id: 数据id
    function addSubgrid(subgrid_id,row_id){
        var subgrid_table_id, pager_id;
        subgrid_table_id = subgrid_id + "_t";
        pager_id = "p_" + subgrid_table_id;
        $("#" + subgrid_id).html(
            "<table id='" + subgrid_table_id + "' class='scroll'></table><div id='"+pager_id + "' class='scroll'></div>");
        $("#" + subgrid_table_id).jqGrid(
            {
                url : "${pageContext.request.contextPath}/chapter/showAll?albumId="+row_id,
                datatype : "json",
                colNames : [ 'Id', '标题', '大小','时长','创建时间','操作'],
                colModel : [
                    {name : "id",align:"center",hidden: true},
                    {name : "title",align:"center",editable:true},
                    {name : "size",align:"center"},
                    {name : "time",align:"center"},
                    {name : "createTime",align:"center",editable:true,edittype:"date"},
                    {name : "url",formatter:function (cellvalue, options, rowObject) {
                            var button = "<button type=\"button\" class=\"btn btn-primary\" onclick=\"download('"+cellvalue+"')\">下载</button>&nbsp;&nbsp;";
                            //                                                                声明一个onPlay方法 --> 显示模态框 ---> 为audio标签添加src  需要url路径作为参数传递
                            //                                                              'onPlay(参数)' ---> \"onPlay('"+cellvalue+"')\"
                            button+= "<button type=\"button\" class=\"btn btn-danger\" onclick=\"onPlay('"+cellvalue+"')\">在线播放</button>";
                            return button;
                        },editable:true,edittype:"file",editoptions:{enctype:"multipart/form-data"}}
                ],
                rowNum : 20,
                pager : pager_id,
                sortname : 'num',
                sortorder : "asc",
                height : '100%',
                styleUI:"Bootstrap",
                autowidth: true,
                editurl: '${pageContext.request.contextPath}/chapter/edit?albumId='+row_id
            });
        $("#" + subgrid_table_id).jqGrid('navGrid',"#" + pager_id, {
                edit: true,
                add: true,
                del: true,
                search: false,
                edittext: "修改",
                addtext: "添加",
                deltext: "删除"
            },
            {
                closeAfterEdit:true,
            },{
                closeAfterAdd:true,
                afterSubmit:function (response,postData) {
                    var chapterId = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/chapter/upload",
                        type:"post",
                        // datatype:"json",
                        data:{chapterId:chapterId},
                        fileElementId:"url",
                        success:function (data) {
                            $("#" + subgrid_table_id).trigger("reloadGrid");
                        }
                    });
                    return postData;
                }
            },{
                closeAfterDel:true,
            });
    }
    function onPlay(cellValue) {
        $("#music").attr("src",cellValue);
        $("#myModal").modal("show");
    }
    function download(cellValue) {
        location.href = "${pageContext.request.contextPath}/chapter/download?url="+cellValue;
    }
</script>
<div class="page-header">
    <h4>专辑管理</h4>
</div>
<ul class="nav nav-tabs">
    <li><a>专辑信息</a></li>
</ul>
<div class="panel">
    <table id="albumTable"></table>
    <div id="albumPage" style="height: 50px"></div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <audio id="music" src="" controls="controls">
        </audio>
    </div><!-- /.modal -->
</div>