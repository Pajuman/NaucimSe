# NaucimSe
Pavlův projekt v rámci rekvalifikace Java v ITnetwork

Účel - 
Aplikace nabízí možnost vyzkoušet se z okruhu otázek nebo slovíček a získat zpětnou vazbu o tom, jak jsme byli úspěšní. 
Aplikace nám častěji "předhazuje" slovíčka, která nám nešla. Uživatel může použít existující okruhy nebo si nějaké sám vytvořit.

Spuštění - 
Pro správný chod aplikace je potřeba mít připojené MySQL na localhost. 
Při spuštění Spring Boot se automaticky založí databáze "Naucim_se" a do ní se nahrají 3 okruhy se španělskými slovíčky.

Používání - 
Přes jednoduchý interface je možné vytvářet a editovat "okruhy", ve kterých jsou slovíčka (případně otázky a odpovědi). Jádrem je však samotné zkoušení. 
Najedeme-li kurzorem na slovíčko, objeví se nám správná odpověď. My pak vybereme, jestli jsme odpověď znali nebo ne. Poté se podle toho upraví jakýsi rating slovíčka. 
Při dalším náhodném výběru zkoušeného slovíčka se vybírá z poloviny slovíček s nejmenším "ratingem".

Skutečný účel aplikace :) - 
Jako začínající programátor jsem si zde poprvé vyzkoušel Spring Boot (MVC), Bootstrap, Git a propojení s databází. 
Aplikace slouží také jako finální projekt v rámci rekvalifikačního distančního kurzu Java - webové aplikace u společnosti ITNetwork, který mi velkoryse financuje Pracovní úřad v Českých Budějovicích. 
V projektu jsem využil i svých předchozích zkušeností s Javascriptem.
