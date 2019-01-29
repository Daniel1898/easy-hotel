<#import "common.ftl" as c>
<#import "forms.ftl" as f>

<@c.page>
<#if message?has_content>
    <p>${message}</p>
</#if>
<form action="/registration" method="post">
    <@f.forminput name="userLogin" label="Логин:"></@f.forminput>
    <@f.forminput name="name" label="Имя:"></@f.forminput>
    <@f.forminput name="surname" label="Фамилия:" ></@f.forminput>
    <@f.forminput type = "email" name="email" label="email:" ></@f.forminput>
    <@f.forminput name="phoneNumber" label="Номер телефона:" ></@f.forminput>
    <@f.forminput type="password" name="password" label="Пароль:"></@f.forminput>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <@f.forminput type="submit" value="Регистрация"></@f.forminput>
</form>
</@c.page>