<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <a class="navbar-brand" href="#">Гостиница «Россия»</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/#">Об отеле</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/#numbers">Номера</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/#bron">Забронировать</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/order/cancellation">Отменить бронирование</a>
            </li>
            <#if auth>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Личный кабинет</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Выход</a>
            </li>
            <#else>
            <li class="nav-item">
                <a class="nav-link" href="/login">Вход</a>
            </li>
            </#if>
        </ul>
    </div>
</nav>
<div class="container">
<#nested>
</div>
<footer class="container-fluid">
</footer>
</body>
</html>
</#macro>