//zasoba je pole slovíček uložených ve formě objektů se stejnou strukturou jako třída Slovo v Javě
let zasoba;

    try {

        //proměnná "slovicka" je získána z jiného scriptu umístěného v HTML
        zasoba = JSON.parse(slovicka.replace(/&quot;/g,'"'));

    } catch (ex) {
        console.error(ex);
    }

let slovo;
let stareSlovo;
let pocet = 0;

//řazení na základě znalosti
function seradZnalost (){
    zasoba.sort(function (a, b){return a.znalost - b.znalost});
}


//losování z první poloviny seřazených slovíček (těch s nejnižší znalostí)
function vylosuj (){
    do {
        stareSlovo = slovo;
        let i = Math.floor(Math.random() * (zasoba.length / 2));
        slovo = zasoba[i];
    } while (slovo === stareSlovo);
    return slovo;
}

//renderování slova pro zkoušení
function vyberSlovo(){
    seradZnalost();
    vylosuj();
    document.getElementById("otazka").textContent = slovo.otazka;
    document.getElementById("odpoved").textContent = slovo.odpoved;
    document.getElementById("pocet").textContent = "Počítadlo " + pocet;
}

window.onload = function (){
    vyberSlovo();
}

//úprava znalosti++
function vedel (){
    pocet++;
    slovo.znalost++;
    vyberSlovo();
}

//úprava znalosti--
function nevedel (){
    pocet++;
    slovo.znalost--;
    vyberSlovo();
}

//renderování okruhu po ukončení zkoušení (včetně nových hodnot znalostí)
function zobrazKonec (){
    const zkouseniHTML = document.getElementById("zkouseni");
    zkouseniHTML.innerHTML = "";

    const hlavaTabulky = document.getElementById("hlavaTabulky");
    hlavaTabulky.innerHTML = "<th>ID</th>\n" +
        "        <th>otázka</th>\n" +
        "        <th>odpověď</th>\n" +
        "        <th>znalost</th>";

    const tabulka = document.getElementById("tabulka");

    zasoba.forEach((element) => {
        const radek = document.createElement("tr");
        Object.values(element).forEach((value) =>{
            const bunka = document.createElement("td");
            const bunkaText = document.createTextNode(value);
            bunka.appendChild(bunkaText);
            radek.appendChild(bunka);
        })
        tabulka.appendChild(radek);
    });

    const zpet = document.getElementById("zpet");
    zpet.innerText = "Zpět na hlavní stranu";
}