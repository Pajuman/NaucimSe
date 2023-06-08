window.onload = function (){
try {
    //proměnná "slovicka" je získána z jiného scriptu umístěného v HTML
        const slovickaPole = JSON.parse(slovicka.replace(/&quot;/g,'"'));

        const tabulka = document.getElementById("tabulka");

          slovickaPole.forEach((element) => {
            const radek = document.createElement("tr");
            Object.values(element).forEach((value) =>{
                    const bunka = document.createElement("td");
                    const bunkaText = document.createTextNode(value);
                    bunka.appendChild(bunkaText);
                    radek.appendChild(bunka);
            })
            tabulka.appendChild(radek);
            });


            }
     catch (ex) {
      console.error(ex);
    }







}