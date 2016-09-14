
function create() {
    var c1 = {
        "nom" : "bob",
        "prenom" : "joe",
        "affiche" : function () {
            console.log(this.nom + " , " + this.prenom);
        }
    };
    c1.affiche();

    // new creer un objet vide, appel la fonction sur cet objet (dans le this)
    // puis renvoie l'objet initialisé
    var c2 = new Client("courtalon", "vincent");
    c2.affiche();

    // prototype en parametre
    // equivalent à c3 = {};
    var c3 = Object.create(null);
    c3.nom = "toto";
    c3.prenom = "titi";
    c3.affiche = function() {
        console.log(this.nom + " , " + this.prenom);
    };
    //....
    c3.affiche();

    // heritage en javascript
    // très particulier
     // un objet c1 herite prototypal d'un objet c2 
     /*
     * c1       ->        c2
     *  "a1": 2           "a1" : 8
     *  "a2": 5           "a3" : 15
     *                    "m5" : function { this.a5 = 12;}
     *                       
     * que se passe t'il si:
     *  je fait une lecture avec c1.a1  -> 2
     *  je fait une lecture avec c1.a3  -> 15
     *  je fait une lecture avec c1.a2  -> 5
     *  
     *  je fait une lecture avec c2.a1  -> 8
     *  je fait une lecture avec c2.a2  -> undefined
     * 
     *  en ecriture
     *  je fait une ecriture sur c1.a1 = 125 -> c1["a1"] = 2 -> 125
     *  je fait une ecriture sur c1.a4 = 111 -> c1["a4"] = 111
     *  je fait une ecriture sur c1.a3 = 225 -> c1["a3"] = 225
     *  effectivement, même si la propriete existe chez les ancetres
     *  lorsque l'on ecrit, il creer localement la propriété
     *
     *  c1.m5() ---> c1["a5"] = 12
     * le this reste sur l'objet d'origine de l'appel
     * 
     * comment sont reliés un enfant et son parent en javascript
     *  on parle d'heritage prototypal, on creer un objet a partir d'un autre objet
     *  en javascript, un objet a une propriété automatique "prototype"  
     *  c'est une propriété spéciale, qui référence l'objet parent 
     * 
     *  cette propriété est accessible en lecture, mais pas en ecriture
     *  je peut connaitre le prototype d'un objet
     *  mais je ne peut pas changer le prototype (choisir un nouvel objet)
     *  une fois celui-ci creer
     *  la liaison a la création
     * 
     *  le fait qu'on puisse acceder au prototype d'un objet et que l'on
     *  puisse modifier celui-ci veut dire que l'on peu, a posteriori
     *  alterer ce dont on herite
     *  
     */
    console.log("-----------------");
    // grace a la fonction create, je creer un objet vide avec c1 comme prototype
    var c4 = Object.create(c1);
    c4.affiche();
    c4.nom = "marcelus";
    c4.affiche();

    var c5 = new ClientGold("willis", "bruce", "bruce@diehard.com");
    c5.affiche();
}
// constructeur de client
function Client(nom, prenom) {
    this.nom = nom;
    this.prenom = prenom;
    this.affiche = function() {
        console.log(this.nom + " , " + this.prenom);
    };
}

var cbase = new Client("anonyme", "anonyme");

function ClientGold(nom, prenom, email) {
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
};
// je renseigne le prototype sur la fonction Constructrice de ClientGold
ClientGold.prototype = cbase;


