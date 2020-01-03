<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
<script language="javascript">

    $(function () {
        //添加点击事件
        $("#checkBut").click(function () {
            //取值
            var uname = $("#inputname").val();
            //发送异步请求
            $.post("checkUname", {"uname": uname}, function (data) {
                if (data.result) {
                    $("#notice").html("用户名可用").css("color", "green");
                } else
                    $("#notice").html("用户名不可用").css("color", "red");
            }, "json");
        });
    });
</script>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT>新用户注册</DT>
            <DD class=past>填写个人信息</DD>
        </DL>
        <DIV class=box>
            <FORM action="reg" method="post" name="form1">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD class=field>用 户 名：</TD>
                            <TD><INPUT class=text id="inputname" type=text name=name><span id="notice"></span></span>
                                <input type="button" id="checkBut" value="检查用户是否存在"></TD>
                        </TR>
                        <TR>
                            <TD class=field>密　　码：</TD>
                            <TD><INPUT class=text type=password name=password></TD>
                        </TR>
                        <TR>
                            <TD class=field>确认密码：</TD>
                            <TD><INPUT class=text type=password name=repassword></TD>
                        </TR>
                        <TR>
                            <TD class=field>电　　话：</TD>
                            <TD><INPUT class=text type=text name=telephone></TD>
                        </TR>
                        <TR>
                            <TD class=field>年龄：</TD>
                            <TD><INPUT class=text type=text name=age></TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons>
                        <INPUT value=立即注册 type=submit>
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
