<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    <div>Добавление записи в таблицу изделий</div>
    <div>Заполните данные:</div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Название изделия"/>
            <input type="text" name="type" placeholder="Тип изделия"/>
            <input type="text" name="material" placeholder="Материал изделия"/>
            <input type="text" name="availability" placeholder="Количество изделий (число)">
            <input type="text" name="releaseDate" placeholder="Дата производства (yyyy-mm-dd)"/>
            <input type="text" name="cost" placeholder="Стоимость (число)"/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    ${message!}
    <br/>
    Таблица ювелирных изделий
    <div>
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
    </div>

</@c.page>