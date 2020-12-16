<#import "macros/common.ftlh" as c>

<@c.page>
    <div>РГР Архипенко АА-77 по дисциплине "Информационные технологии"</div>
    <div>Выберите, что хотите сделать:</div>
    <div>
        <form action="/order">
            <button type="submit">Показать таблицу заказов</button>
        </form>
        <form action="/product">
            <button type="submit">Показать таблицу ювелирных изделий</button>
        </form>
        <form action="/client">
            <button type="submit">Показать таблицу клиентов</button>
        </form>
        <form action="/addorder">
            <button type="submit">Добавить запись в таблицу заказов</button>
        </form>
        <form action="/addproduct">
            <button type="submit">Добавить запись в таблицу ювелирных изделий</button>
        </form>
        <form action="/addclient">
            <button type="submit">Добавить запись в таблицу клиентов</button>
        </form>
    </div>
</@c.page>