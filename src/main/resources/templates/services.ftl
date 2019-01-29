<#import "common.ftl" as c>
<#import "forms.ftl" as f>

<@c.page>

<#if warning?has_content><H2>
    Сервис с таким именем уже существует!
</H2></#if>
<table class="table">
    <tr>
        <th>id</th>
        <th>Название</th>
        <th>Цена</th>
        <th>Удалить</th>
        <th>Редактировать</th>
    </tr>
<#list service as s>
    <tr>
        <td>${s.id}</td>
        <td>${s.name}</td>
        <td>${s.price}</td>
        <td>
            <form action="/admin/services/delete/${s.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="submit" value="Удалить">
            </form>
        </td>
        <td>
            <form action="/admin/services/edit/${s.id}" method="get">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Редактировать">
        </form>
        </td>
    </tr>
</#list>
</table>
<form action="/admin/services/" method="post">
    <p>Название</p>
    <input type="text" name="name" value="">
    <p>Цена</p>
    <input type="text" name="price" value="">
    <input type="submit" value="Добавить">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
</@c.page>