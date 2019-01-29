<#import "common.ftl" as c>
<#import "forms.ftl" as f>

<@c.page>
<form action="/orderpage" method="post">
    <p>Тип номера: ${typename}</p>
    <p>Бронирование c ${from} по ${to}</p>
    <#if user>
        <#assign fname = "${u.name}" fsurname = "${u.surname}" femail = "${u.email}" fnum="${u.phoneNumber}">
    <#else>
        <#assign fname = "" fsurname = "" femail = "" fnum="">
    </#if>

    <@f.forminput name="name" label="Имя:" value="${fname}"></@f.forminput>
        <@f.forminput name="surname" label="Фамилия:" value="${fsurname}"></@f.forminput>
        <@f.forminput type = "email" name="email" label="email:" value="${femail}"></@f.forminput>
        <@f.forminput name="number" label="Номер телефона:" value="${fnum}"></@f.forminput>

    <p>Дополнительные услуги</p>
    <ul class="">
    <#list service as s>
    <label for="s1">${s.name} ${s.price?string["########"]}p в день</label>
    <input type="checkbox" id="s${s.id}" value="${s.id}" onchange="changeprice(${s.price?string["########"]},'s${s.id}')" name="myParam[]">
    </#list>
    </ul>
    <input type="date" class="hidden_inp" name="from" value="${from}">
    <input type="date" class="hidden_inp" name="to" value="${to}">
    <input type="text" class="hidden_inp" name="room_type" value="${typename}">
    <input type="submit" value="Забронировать">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    <p>Цена:</p>
    <input type="number" contenteditable="false" id="price" name="price" value="${price?string["########"]}">
</form>
<script>
    var price = document.getElementById("price");
    var days = parseInt(${days});
    function changeprice(p,name) {
        var r = document.getElementById(name);
        if (r.checked) {
            price.value = parseInt(price.value) + parseInt(p)*days;
        } else {
            price.value -= parseInt(p)*days;
        }
    }
</script>
</@c.page>