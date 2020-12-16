<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    <div>Добавление записи в таблицу клиентов</div>
    <div>Заполните данные:</div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Имя клиента"/>
            <input type="text" name="email" placeholder="E-mail клиента"/>
            <input type="text" name="phone" placeholder="Телефон клиента"/>
            <input type="text" name="address" placeholder="Адрес клиента">
            <input type="text" name="city" placeholder="Город проживания клиента"/>
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