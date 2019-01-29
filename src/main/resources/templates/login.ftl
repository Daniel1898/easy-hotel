<#import "common.ftl" as c>
<#import "forms.ftl" as f>

<@c.page>
<h1>Вход</h1>
<#if error?has_content>
<#else >
<p>Неверный логин или пароль.</p>
</#if>
<#if logout?has_content>
<#else >
    <p>Выход успешно произведен.</p>
</#if>
<form action="/auth" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <div><input type="submit" value="Sign In"/></div>
</form>

<a href="/registration">Регистрация</a>
</@c.page>