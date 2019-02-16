<html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
<#--边栏-->
        <#include "../common/nav.ftl">
<#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <input hidden type="text" name="categoryId" value="${(category.categoryId)!''}">
                        <div class="form-group">
                            <label>名字</label>
                            <input type="text" class="form-control" name="categoryName"
                                   value="${(category.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类别</label>
                            <input type="text" class="form-control" name="categoryType"
                                   value="${(category.categoryType)!''}"/>
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