<#import "common.ftl" as c>
<#import "forms.ftl" as f>

<@c.page>

<ul>
    <h2>Основная информация</h2>
        <h3>${user.name} ${user.surname}</h3>
        <p>email: ${user.email}</p>
        <p>Телефон: ${user.phoneNumber}</p>
    <h2>Мои заказы</h2>
    <table class="table">
        <tr>
            <th>Номер заказа</th>
            <th>Номер комнаты</th>
            <th>Дата заезда</th>
            <th>Дата выезда</th>
            <th>Статус</th>
            <th></th>
        </tr>
        <#list orders as o>
            <tr>
                <td>${o.id}</td>
                <td>${o.roomNumber}</td>
                <td>${o.fromDate}</td>
                <td>${o.toDate}</td>
                <td>${o.status}
                    <form action="/profile/orders/delete/${o.id}" method="post">
                <td>
                    <button>Отменить</button>
                </td>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                </form>
                </td>
            </tr>
        </#list>
    </table>

</ul>
</@c.page>