<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Заказы</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Гостиница «Россия»</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/users">Пользователи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/orders">Заказы</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/logout">Выход</a>
            </li>
        </ul>
    </div>
</nav>
<form method="get" action="/admin/orders">
    <div class="row">
        <div class="form-group col">
            <label for="fromDate">Дата заезда</label>
            <input type="date" class="form-control" id="fromDate" name="from">
        </div>
        <div class="to-group col">
            <label for="fromDate">Дата выезда</label>
            <input type="date" class="form-control" id="toDate" name="to">
        </div>
        <div class="button col">
            <label for="">  </label>
            <input type="submit" class="form-control btn btn-primary" id="button" value="Найти">
        </div>
    </div>
</form>
<table class="table">
    <tr>
        <th>Номер заказа</th>
        <th>Номер комнаты</th>
        <th>Дата заезда</th>
        <th>Дата выезда</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>email</th>
        <th>Номер телефона</th>
        <th>Услуги</th>
        <th>Цена</th>
        <th>Статус</th>
    </tr>
    {{#orders}}
        <tr>
            <td>{{id}}</td>
            <td>{{roomNumber}}</td>
            <td>{{fromDate}}</td>
            <td>{{toDate}}</td>
            <td>{{name}}</td>
            <td>{{surname}}</td>
            <td>{{email}}</td>
            <td>{{phoneNumber}}</td>
            <td> {{#services}}
                {{name}}<br>
            {{/services}}</td>
            <td>{{price}}</td>
            <td>
                {{status}}
            </td>

            {{^isAccepted}}
                <form action="/admin/orders/accept/{{id}}" method="post">
                    <td>
                        <button>принять</button>
                    </td>
                    <input type="hidden" name="_csrf" value="{{_csrf.token}}">
                </form>
            {{/isAccepted}}
            <form action="/admin/orders/delete/{{id}}" method="post">
                <td>
                    <button>удалить</button>
                </td>
                <input type="hidden" name="_csrf" value="{{_csrf.token}}">
            </form>
        </tr>
    {{/orders}}
</table>
</body>
</html>