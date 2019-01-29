<#import "common.ftl" as c>
<#import "forms.ftl" as f>

<@c.page>

<table class="table">
    <tr>
        <th>id</th>
        <th>login</th>
        <th>edit</th>

    </tr>
<#list users as u>
    <tr>
        <td>${u.id}</td>
        <td>${u.userLogin}</td>
        <td>
            <form action="/admin/users/delete/${u.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="submit" value="Удалить">
            </form>
        </td>
    </tr>
</#list>
</table>
</@c.page>