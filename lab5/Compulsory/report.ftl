<!DOCTYPE html>
<html>
<head>
    <title>Raport Imagini</title>
</head>
<body>
    <h1>Lista de imagini</h1>
    <ul>
        <#list imagini as imagine>
            <li>${imagine.nume} - ${imagine.data} - ${imagine.locatie}</li>
        </#list>
    </ul>
</body>
</html>
