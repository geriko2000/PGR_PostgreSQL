<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    <form method="get">
        Поиск по:<br/>
        <input type="radio" name="filter" id="name" value="name" checked>
        <label for="name">имени</label><br/>
        <input type="radio" name="filter" id="address" value="address">
        <label for="address">адресу</label><br/>
        <input type="radio" name="filter" id="city" value="city">
        <label for="city">городу</label><br/>
        <input type="text" name="search">
        <button type="submit">Поиск</button><br/>
    </form>
    Таблица клиентов
    <table>
        <thead>
        <tr>
            <th>Код клиента</th>
            <th>Имя клиента</th>
            <th>E-mail</th>
            <th>Телефон</th>
            <th>Адрес</th>
            <th>Город</th>
        </tr>
        </thead>
        <body>
        <#list clients as client>
            <tr>
                <td>${client.id}</td>
                <td>${client.name}</td>
                <td>${client.email}</td>
                <td>${client.phone}</td>
                <td>${client.address}</td>
                <td>${client.city}</td>
            </tr>
        </#list>
        </body>
    </table>
</@c.page>