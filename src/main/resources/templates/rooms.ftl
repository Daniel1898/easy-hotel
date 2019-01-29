<#import "common.ftl" as c>

<@c.page>
<div class="container">

    <#list rooms as room>
        <row justify-content-md-center>
            <div class="col-8">
                <div class="row">
                    <div class="col-6">
                        <img class="d-block w-100 nomer-img" src="${room.imgPath}" alt="First slide">
                    </div>
                    <div class="col-6">
                        <h3>${room.name}</h3>
                        <p>${room.description}</p>
                        <form action="/orderpage" method="get">
                            <input type="date" name="from" class="hidden_inp" value="${from}">
                            <input type="date" name="to" class="hidden_inp" value="${to}">
                            <input type="text" name="type_id" class="hidden_inp" value="${room.id}">
                            <input type="submit" value="Забронировать">
                        </form>
                    </div>
                </div>
            </div>
        </row>
        <hr>

    </#list>
</@c.page>