<#import "macros/common.ftlh" as c>

<@c.page>
    <div>РГР Архипенко АА-77 по дисциплине "Информационные технологии"</div>
    <div>Выберите, что хотите сделать:</div>
    <br/>
    <form action="/product">
        <button type="submit">Показать таблицу ювелирных изделий</button>
    </form>
    <br/>
    <form action="/client">
        <button type="submit">Показать таблицу клиентов</button>
    </form>
    <br/>
    <form action="/order">
        <button type="submit">Показать таблицу заказов</button>
    </form>
</@c.page>