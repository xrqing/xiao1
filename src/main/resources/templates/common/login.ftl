<html>
<head>
    <meta charset="utf-8">
    <title>登录页面</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/sell/css/style.css">
</head>
<body>

<div id="wrapper" class="toggled">
<#--边栏-->
<#--<#include "../common/nav.ftl">-->
<#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/user/dologin">
                        <div class="form-group">
                            <label>用户名</label>
                            <input type="text" class="form-control" name="username"/>
                        </div>
                        <div class="form-group">
                            <label>密码</label>
                            <input type="text" class="form-control" name="password"/>
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>