let zasoba = [
    {id:1, cesky:"stálo prase na terase, houpalo se na ocase, tak už víš?", anglicky:"dog", znalost:0},
    {id:2, cesky:"očak", anglicky:"cat", znalost:0},
    {id:3, cesky:"dům", anglicky:"house", znalost:0},
    {id:4, cesky:"slon", anglicky:"elephant", znalost:0},
    {id:5, cesky:"velryba", anglicky:"whale", znalost:0},
    {id:6, cesky:"delfín", anglicky:"dolphin", znalost:0},
    {id:7, cesky:"myš", anglicky:"mouse", znalost:0},
    {id:8, cesky:"velbloud", anglicky:"camel", znalost:0},
    {id:9, cesky:"pavouk", anglicky:"spider", znalost:0},
    {id:10, cesky:"kudlanka", anglicky:"mantis", znalost:0}
]

let puvodni = [
    {id:1, cesky:"stálo prase na terase, houpalo se na ocase, tak už víš?", anglicky:"dog", znalost:0},
    {id:2, cesky:"očak", anglicky:"cat", znalost:0},
    {id:3, cesky:"dům", anglicky:"house", znalost:0},
    {id:4, cesky:"slon", anglicky:"elephant", znalost:0},
    {id:5, cesky:"velryba", anglicky:"whale", znalost:0},
    {id:6, cesky:"delfín", anglicky:"dolphin", znalost:0},
    {id:7, cesky:"myš", anglicky:"mouse", znalost:0},
    {id:8, cesky:"velbloud", anglicky:"camel", znalost:0},
    {id:9, cesky:"pavouk", anglicky:"spider", znalost:0},
    {id:10, cesky:"kudlanka", anglicky:"mantis", znalost:0}
]

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
    document.getElementById("odpoved").textContent = slovo.anglicky;
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

function konec (){
    seradId();


}