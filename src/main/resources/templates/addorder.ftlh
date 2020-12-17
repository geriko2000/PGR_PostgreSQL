<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    <div>Добавление записи в таблицу заказов</div>
    <div>Заполните данные:</div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <select name="client">
                <#list clients as clients>
                    <option value="${clients.name}">${clients.name}</option>
                </#list>
            </select>
            <select name="product">
                <#list products as products>
                    <option value="${products.name}">${products.name}</option>
                </#list>
            </select>
            <input type="text" name="quantity" placeholder="Количество">
            <button type="submit">Добавить</button>
        </form>
    </div>
    ${message!}
    <br/>
    Таблица заказов
    <div>
        <table>
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
    </div>

</@c.page>