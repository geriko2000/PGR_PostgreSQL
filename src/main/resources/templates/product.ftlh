<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    <br/>
    <div>
        Есть в наличии?
        <form method="get" action="/product">
            <input type="checkbox" name="availability">
            <button type="submit">Принять</button>
        </form>
    </div>
    <br/>
    Таблица ювелирных изделий
    <table>
        <thead>
        <tr>
            <th>Код изделия</th>
            <th>Название</th>
            <th>Тип</th>
            <th>Материал</th>
            <th>Наличие</th>
            <th>Дата выпуска</th>
            <th>Цена</th>
        </tr>
        </thead>
        <body>
        <#list products as product>
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.type}</td>
                <td>${product.material}</td>
                <td>${product.availability}</td>
                <td>${product.releaseDate}</td>
                <td>${product.cost}</td>
            </tr>
        </#list>
        </body>
    </table>
</@c.page>