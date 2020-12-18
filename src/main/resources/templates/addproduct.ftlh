<#import "macros/common.ftlh" as c>

<@c.page>
    <form action="/main">
        <button type="submit">Вернуться на главную страницу</button>
    </form>
    <form action="/product">
        <button type="submit">Вернуться к таблице изделий</button>
    </form>
    <div><b>Добавление записи в таблицу изделий</b></div>
    <div>Заполните данные:</div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <label for="name">Название изделия</label><br/>
            <input type="text" name="name" placeholder="Название" id="name"/><br/>
            <label for="type">Тип изделия</label><br/>
            <input type="text" name="type" placeholder="Тип" id="type"/><br/>
            <label for="material">Материал изделия</label><br/>
            <input type="text" name="material" placeholder="Материал" id="material"/><br/>
            <label for="availability">Количество изделий (число)</label><br/>
            <input type="text" name="availability" placeholder="Количество" id="availability"><br/>
            <label for="releaseDate">Дата производства (yyyy-mm-dd)</label><br/>
            <input type="text" name="releaseDate" placeholder="Дата (yyyy-mm-dd)" id="releaseDate"/><br/>
            <label for="cost">Стоимость (число)</label><br/>
            <input type="text" name="cost" placeholder="Стоимость" id="cost"/><br/><br/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    <b>${message!}</b><br/>
</@c.page>