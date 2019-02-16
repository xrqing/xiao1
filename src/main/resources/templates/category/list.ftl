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
                    <table class="table table-bordered table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>类目的编号</th>
                            <th>名字</th>
                            <th>类目的类型</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                    <#list productCategoryPage.content as productCategory>
                    <tr>
                        <td>${productCategory.categoryId}</td>
                        <td>${productCategory.categoryName}</td>
                        <td>${productCategory.categoryType}</td>
                        <td>${productCategory.createTime}</td>
                        <td>${productCategory.updateTime}</td>
                        <td><a href="/sell/seller/category/add?categoryId=${productCategory.categoryId}">修改</a></td>
                    </tr>
                    </#list>
                        </tbody>
                    </table>
                </div>

                <!--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disable"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/category/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                    </#if>
                    <#list 1..productCategoryPage.getTotalPages() as index>
                        <#if currentPage == index>
                            <li class="disabled"><a href="#">${index}</a></li>
                        <#else>
                            <li><a href="/sell/seller/category/list?page=${index}&size=${size}">${index}</a></li>
                        </#if>

                    </#list>

                    <#if currentPage gte productCategoryPage.getTotalPages()>
                        <li class="disable"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/sell/seller/category/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                    </#if>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>