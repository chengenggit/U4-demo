﻿<%@page pageEncoding="utf-8" contentType="text/html;utf-8" language="java" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
    <script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
    <script language="JavaScript" type="text/javascript">
        $(function () {  //加载
            //1.发送异步请求获取类型，进行加载
            $.post("getTypeData", null, function (data) {
                //加载类型
                //[{"id":1000,"name":"一室一厅"},{"id":1001,"name":"一室两厅"},{"id":1002,"name":"两室一厅"},{"id":1003,"name":"两室两厅"},{"id":1004,"name":"三室一厅"},{"id":1005,"name":"三室两厅"},{"id":1006,"name":"四室一厅"},{"id":1007,"name":"四室两厅"}]
                for (var i = 0; i < data.length; i++) {
                    //创建option
                    var optionnode = $("<option value=" + data[i].id + ">" + data[i].name + "</option>");
                    //将option添加到下拉列表
                    $("#typeid").append(optionnode);
                }

                //设置类型的选中项
                $("#typeid").val(${house.typeId});
            }, "json");

            //2.发送异步请求加载区域信息
            $.post("getDistritctData", null, function (data) {
                //加载类型
                for (var i = 0; i < data.length; i++) {
                    //创建option
                    var optionnode = $("<option value=" + data[i].id + ">" + data[i].name + "</option>");
                    //将option添加到下拉列表
                    $("#district_id").append(optionnode);
                }

                //设置区域的选中项
                $("#district_id").val(${house.districtid});
                //加载对应的区域下的街道
                loadStreet();

            }, "json");

            //3.二级联动给区域下拉列拉添加选项改变事件
            $("#district_id").change(function () {
                loadStreet();
            });

        });

        //通过区域加载街道
        function loadStreet() {
            //取当前区域选项的id
            var did = $("#district_id").val();
            //清空原有选项
            $("#street_id>option:gt(0)").remove();
            //发异步请求加载街道
            $.post("getStreetByDid", {"did": did}, function (data) {
                //加载类型
                for (var i = 0; i < data.length; i++) {
                    //创建option
                    var optionnode = $("<option value=" + data[i].id + ">" + data[i].name + "</option>");
                    //将option添加到下拉列表
                    $("#street_id").append(optionnode);
                }
                //设置街道选中项
                $("#street_id").val(${house.streetId});
            }, "json");
        }
    </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT>新房屋信息发布</DT>
            <DD class=past>填写房屋信息</DD>
        </DL>
        <DIV class=box>
            <FORM id=add_action enctype="multipart/form-data" method=post name=add.action
                  action=upHouse>
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <input type="hidden" name="id" value="${house.id}">
                            <input type="hidden" name="oldPicPath" value="${house.path}">
                            <TD class=field>标　　题：</TD>
                            <TD><INPUT id=add_action_title class=text value="${house.title}" type=text name=title></TD>
                        </TR>
                        <TR>
                            <TD class=field>户　　型：</TD>
                            <TD><SELECT class=text name=typeId id="typeid">
                                <option value="">请选择</option>
                            </SELECT></TD>
                        </TR>
                        <TR>
                            <TD class=field>面　　积：</TD>
                            <TD><INPUT id=add_action_floorage class=text value="${house.floorage}" type=text
                                       name=floorage></TD>
                        </TR>
                        <TR>
                            <TD class=field>价　　格：</TD>
                            <TD><INPUT id=add_action_price class=text type=text value="${house.price}" name=price></TD>
                        </TR>
                        <TR>
                            <TD class=field>房产证日期：</TD>
                            <TD><INPUT
                                    value="<f:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></f:formatDate>"
                                    class=text type="date" name=pubdate></TD>
                        </TR>
                        <TR>
                            <TD class=field>位　　置：</TD>
                            <TD>区：
                                <SELECT class=text id="district_id" name=district_id>
                                    <option value="">请选择</option>
                                </SELECT>
                                街：${house.streetId}<SELECT class=text
                                                           name=streetId id="street_id">
                                    <option value="">请选择</option>
                                </SELECT></TD>
                        </TR>
                        <TR>
                            <TD class=field>联系方式：</TD>
                            <TD><INPUT id=add_action_contact class=text value="${house.contact}" type=text name=contact>
                            </TD>
                        </TR>
                        <TR>
                            <TD class=field>详细信息：</TD>
                            <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD>
                        </TR>
                        <TR>
                            <TD class=field>图片：</TD>
                            <TD><img width="100" height="100" src="http://localhost:80/${house.path}"><input type="file"
                                                                                                             name="pfile">
                            </TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons><INPUT value=更新 type="submit">
                    </DIV>
                </DIV>
            </FORM>
        </DIV>
    </DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>
            <a href="https://wh.58.com/chuzu/?utm_source=market&spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&PGTID=0d100000-0009-e186-50dd-94c3598b816f&ClickID=2/">关于我们</a>·
            <a href="https://e.58.com/contact.html?from=90008&PGTID=0d100000-0009-ee86-166c-ed29ed9fc481&ClickID=2">联系方式</a>·
            <a href="https://help.58.com/help_pc/wzjyym/index.html?PGTID=0d100000-0009-ee86-166c-ed29ed9fc481&ClickID=3">意见反馈</a>·
            <a href="https://help.58.com/help_pc/zcyh/index.html?PGTID=0d100000-0009-ee86-166c-ed29ed9fc481&ClickID=4">帮助中心</a>
        </DD>
    </DL>
</DIV>
</BODY>
</HTML>
