<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    <form action="/order">
        <button type="submit">Вернуться к таблице заказов</button>
    </form>
    <div><b>Добавление записи в таблицу заказов</b></div>
    <div>Заполните данные:</div>
    <form method="post" enctype="multipart/form-data">
        <label for="client">Имя клиента</label><br/>
        <select name="client" id="client">
            <#list clients as clients>
                <option value="${clients.name}">${clients.name}</option>
            </#list>
        </select><br/>
        <label for="product">Изделие</label><br/>
        <select name="product" id="product">
            <#list products as products>
                <option value="${products.name}">${products.name}</option>
            </#list>
        </select><br/>
        <label for="quantity">Количество (число)</label><br/>
        <input type="text" name="quantity" placeholder="Количество" id="quantity"><br/><br/>
        <button type="submit">Добавить</button>
    </form>
    <b>${message!}</b><br/>
</@c.page>