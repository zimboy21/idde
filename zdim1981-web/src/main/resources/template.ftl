<html>
<head>
    <link href="./style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <h1>Available items in the shop:</h1>

    <h3>CPUs:</h3>
    <#list CPUs as cpu>
    <div class='bordered inlinediv'>
        ${cpu.getName()} <br/>
        Price: ${cpu.getPrice()}$ <br/>
        Clock speed: ${cpu.getClockSpeed()}Hz <br/>
        ${cpu.getCoreCount()} Cores <br/>
        Overclocking : ${cpu.getOverClocking()}Hz <br/>
    </div>
    </#list>

    <h3>Motherboards:</h3>
    <#list mbs as mb>
    <div class='bordered inlinediv'>
            ${mb.getName()} <br/>
            Price: ${mb.getPrice()}$ <br/>
            Front Side Bus: ${mb.getFsb()} MHz <br/>
            Bios: ${mb.getBios()} <br/>
            Memory: ${mb.getMemory()} GB <br/>
    </div>
    </#list>


    <form method="POST" action="/logout">
        <button type="submit" class='button' >Logout</button>
    </form>
</body>
</html>