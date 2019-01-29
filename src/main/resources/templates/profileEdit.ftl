<#import "common.ftl" as c>

<@c.page>
    <form action="/profile/edit" method="post">
        <p>Имя</p>
        <input type="text" name="name" value="${name}">
        <p>Фамилия</p>
        <input type="text" name="surname" value="${surname}">
        <p>email:</p>
        <input type="email" name="email" value="${email}">
        <p>Телефон:</p>
        <input type="text" name="phone" value="${phoneNumber}}"><br>
        <input type="submit" value="Сохранить">
        <input type="hidden" name="_csrf" value="${_csrf.token}}">
    </form>
</@c.page>