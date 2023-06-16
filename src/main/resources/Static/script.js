window.onload = function (){
    try {
    //proměnná "okruhyPole" je získána z jiného scriptu umístěného v HTML
        const okruhyPole = JSON.parse(okruhy.replace(/&quot;/g,'"'));
        const okruhySelect = document.getElementById("okruh_selection");

            for (let i = 0; i < okruhyPole.length; i++) {
            const newOption = document.createElement("option");
            newOption.value = okruhyPole[i];
            newOption.text = okruhyPole[i];
            okruhySelect.appendChild(newOption);
            }
    } catch (ex) {
      console.error(ex);
    }
}