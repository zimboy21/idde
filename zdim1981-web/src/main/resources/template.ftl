<html>
<head>
    <link href="./style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <h1>Available CPUs</h1>
    <#list CPUs as cpu>
    <div class='bordered inlinediv'>
        ${cpu.id}: ${cpu.getName()} <br/>
        ${cpu.getPrice()}$ <br/>
        ${cpu.getClockSpeed()}Hz <br/>
        ${cpu.getCoreCount()} Cores <br/>
        Overclocking : ${cpu.getOverClocking()}Hz <br />
    </div>
    </#list>
    <form method="POST" action="/logout">
        <button type="submit" class='button' >Logout</button>
    </form>
</body>
</html>