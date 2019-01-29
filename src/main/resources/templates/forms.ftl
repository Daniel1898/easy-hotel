<#macro bron>
<div class="row justify-content-center">

    <div class="col-6">
        <h2 class="text-center ">Бронирование номеров</h2>
        <form method="get" action="/rooms">
            <div class="row">
                <div class="form-group col">
                    <label for="fromDate">Дата заезда</label>
                    <input type="date" class="form-control" id="fromDate" name="from">
                </div>
                <div class="to-group col">
                    <label for="fromDate">Дата выезда</label>
                    <input type="date" class="form-control" id="toDate" name="to">
                </div>
            </div>
            <div class="button">
                <input type="submit" class="form-control btn btn-primary" id="button" value="Найти номера">
            </div>
        </form>
    </div>
</div>
</#macro>

<#macro forminput type="text" name="" label="" value="" class="">
    <div class="form-group">
     <label for="${name}">${label}</label>
        <input type="${type}" name="${name}" value="${value}" class="form-control ${class}">
    </div>
</#macro>