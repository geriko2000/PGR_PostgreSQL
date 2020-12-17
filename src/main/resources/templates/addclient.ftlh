<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    <div>Добавление записи в таблицу клиентов</div>
    <div>Заполните данные:</div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <label for="name">Имя клиента</label><br/>
            <input type="text" name="name" placeholder="Иванов" id="name"/><br/>
            <label for="email">E-mail</label><br/>
            <input type="text" name="email" placeholder="example@example.ru" id="email"/><br/>
            <label for="phone">Телефон</label><br/>
            <input type="text" name="phone" placeholder="88005553535" id="phone"/><br/>
            <label for="address">Адрес</label><br/>
            <input type="text" name="address" placeholder="Ленина 1" id="address"><br/>
            <label for="city">Город</label><br/>
            <input type="text" name="city" placeholder="Кибердянск" id="city"/><br/><br/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    ${message!}
    <br/>
    Таблица клиентов
    <div>
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
    </div>

</@c.page>