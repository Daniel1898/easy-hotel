<#macro roomlist>
    <#list rooms as room>
            <div class="col-3">
                <article class="nomer">
                    <img class="d-block w-100 nomer-img" src="${room.imgPath}" alt="First slide">
                    <h4 class="text-center">${room.name}</h4>
                    <p class="text-justify">${room.description}</p>
                </article>
            </div>
    <#else>
No rooms.
    </#list>
</#macro>