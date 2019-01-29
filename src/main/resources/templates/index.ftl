<#import "common.ftl" as c>
<#import "forms.ftl" as f>
<#import "roomlist.ftl" as r>

<@c.page>
<div class="container">
    <div class="row">
        <div class="col-6">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="img/hotel.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="img/hall.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="img/hall2.jpg" alt="Third slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="img/reception.jpg" alt="Third slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

        <article class="col-6">
            <h3 class="text-center">Гостиница «Россия»</h3>
            <p class="text-justify">Гостиничный комплекс «Россия» – это современный отель, предлагающий своим гостям
                высокий уровень
                обслуживания, основывающийся на развитой инфраструктуре, превосходно организованной технологии
                обслуживания
                гостей и профессионализме обслуживающего персонала. Доступные цены на размещение,
                широкие возможности отдыхать с комфортом и с удовольствием, привлекают в наш
                отель гостей со всей России и из-за рубежа. Гостиничный комплекс «Россия» способен не только разместить
                более 70 человек, но и обеспечить проведение семинаров, конференций и других мероприятий.</p>
        </article>
    </div>
    <a id="numbers"></a>
    <hr>
    <h2 class="text-center">Наши номера</h2>

    <div class="row">

        <@r.roomlist></@r.roomlist>
    </div>
    <hr id="bron">

    <@f.bron></@f.bron>
</div>
</div>
</@c.page>