<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    <form action="/client">
        <button type="submit">Вернуться к таблице клиентов</button>
    </form>
    <div><b>Добавление записи в таблицу клиентов</b></div>
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
    <b>${message!}</b><br/>
</@c.page>