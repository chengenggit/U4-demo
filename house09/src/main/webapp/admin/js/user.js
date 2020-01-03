$(function(){
    //1.使用datagrid组件绑定数据
    $('#dg').datagrid({
        url:'getUserData',
        pagination:true,  //开启分页
        pageSize:5,  //初始化页大小
        pageList:[5,10,15,20,30],  //页大小选项
        toolbar:'#ToolBar', //指定工具栏
        columns:[[
            {checkbox:true,width:100,align:'right'},
            {field:'id',title:'编号',width:100,align:'right'},
            {field:'name',title:'用户名',width:100,align:'right'},
            {field:'telephone',title:'电话',width:100,align:'right'},
            {field:'isadmin',title:'是否管理员',width:100,align:'right'},
            {field:'age',title:'年龄',width:100,align:'right'},
            {field:'opt',title:'编辑|操作',width:100,align:'right',
                formatter: function(value,row,index){
                    //返回的内容就是呈现在单元格的内容
                    //value 表示当前字段的值， row当前行的值 index表示索引
                    return "<a href='javascript:goEdit("+row.id+");'>修改</a> <a href='javascript:delDistrict("+row.id+")'>删除</a>";
                }
            }
        ]]
    });
});

//实现datagrid绑定查询条件
function searchUser(){
    var inputName=$("#inputName").val();
    var inputTel=$("#inputTel").val();
    //设置条件查询
    $("#dg").datagrid("load",{ //设置查询条件
            name: inputName,
            tel: inputTel
        }
    );
}
