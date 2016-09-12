
function create() {
    var vnom = document.getElementById("nom").value;
    var vprenom = document.getElementById("prenom").value;

    var client = {};
    client.nom = vnom;
    client.prenom = vprenom;
    client.salutation = function () {
        console.log(typeof(this));
        console.log("bonjour, " + this.nom + " " + this.prenom);
    };

    // je récupere le bouton
    var btn = document.getElementById("salutation");
    // j'attache la fonction salutation au click du bouton
    // avec closure pour 'reattacher' a l'objet original'

    //btn.addEventListener('click', client.salutation);
    btn.addEventListener('click', function() {
        client.salutation();
    });
}

function ajouter() {
    var vnom = document.getElementById("nom").value;
    var vprenom = document.getElementById("prenom").value;

    var client = {};
    client.nom = vnom;
    client.prenom = vprenom;
    client.salutation = function () {
        console.log("bonjour, " + this.nom + " " + this.prenom);
    };
    // je cree un nouveau bouton pour ce nouveau client
    var newbutton = document.createElement("button");
    newbutton.innerText = "saluer " + vnom;


    //btn.addEventListener('click', client.salutation);
    newbutton.addEventListener('click', function() {
        client.salutation();
    });
    // j'ajoute ce bouton dans le div 'clients'
    var wrapper = document.getElementById("clients");
    wrapper.appendChild(newbutton);
}