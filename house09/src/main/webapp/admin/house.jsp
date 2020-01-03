<%@page pageEncoding="utf-8" contentType="text/html; utf-8"  language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>区域管理</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
    <script src="js/jquery-1.8.3.js" language="JavaScript" type="text/javascript"></script>
    <!--jquery.easyui.min.js包含了easyUI中的所有插件-->
    <script src="js/jquery.easyui.min.js" language="JavaScript" type="text/javascript"></script>
    <script language="JavaScript" type="text/javascript" src="js/house.js"></script>
</head>
<body>
<!--显示区域的表格-->
<table id="dg"></table>

<!--工具栏-->
<div id="ToolBar">
    <div style="height: 60px;" >
        <a href="javascript:goAdd()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">批量审核</a>


        区域<SELECT class=text id="district_id" name=district_id>
        <option value="">请选择</option>
    </SELECT>---街道<SELECT class=text
                          name=streetId id="street_id">
        <option value="">请选择</option>
    </SELECT>---户型:<SELECT class=text name=typeId id="typeid">
        <option value="">请选择</option>
    </SELECT> -- 审核状态<select>
        <option value="0" >未审核</option> <option value="1">已审核</option>
    </select>:标题:<input id="a" type="text" name="tel">--价格:<input type="text" size="10">-<input type="text" size="10">
        <a id="btn" href="javascript:searchUser();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>

    </div>
</div>


</body>
</html>