<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    Поиск заказа: <br/>
    <form method="get">
        <select name="filter">
            <option name="filterId" value="id">Код заказа</option>
            <option name="filterClient" value="client">Клиент</option>
            <option name="filterProduct" value="product">Изделие</option>
        </select>
        <input type="text" name="search">
        <button type="submit">Поиск</button>
        <br/>
    </form>
    <br/>
    <form action="/addorder">
        <button type="submit">Добавить запись в таблицу заказов</button>
    </form>
    <b>${message!}</b><br/>
    Таблица заказов
    <table border="1">
        <thead>
        <tr>
            <th>Код заказа</th>
            <th>Покупатель</th>
            <th>Товар</th>
            <th>Кол-во</th>
            <th>Дата заказа</th>
        </tr>
        </thead>
        <body>
        <#list orders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.client}</td>
                <td>${order.product}</td>
                <td>${order.quantity}</td>
                <td>${order.date}</td>
            </tr>
        </#list>
        </body>
    </table>
</@c.page>