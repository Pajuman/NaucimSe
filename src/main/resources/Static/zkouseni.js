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

function seradZnalost (){
    zasoba.sort(function (a, b){return a.znalost - b.znalost});
}

function seradId (){
    zasoba.sort(function (a, b){return a.id - b.id});
}

function vylosuj (){
    do {
        stareSlovo = slovo;
        let i = Math.floor(Math.random() * (zasoba.length / 2));
        slovo = zasoba[i];
    } while (slovo === stareSlovo);
    return slovo;
}

function vyberSlovo(){
    seradZnalost();
    vylosuj();
    document.getElementById("otazka").textContent = slovo.cesky;
    document.getElementById("odpoved").textContent = slovo.spanelsky;
    document.getElementById("pocet").textContent = "Počítadlo " + pocet;
}

window.onload = function (){
    vyberSlovo();
}

function vedel (){
    pocet++;
    slovo.znalost++;
    vyberSlovo();
}

function nevedel (){
    pocet++;
    slovo.znalost--;
    vyberSlovo();
}

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