<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
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