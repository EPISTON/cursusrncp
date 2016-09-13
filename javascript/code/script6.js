

function Personne(nom, prenom) {
    this.nom = nom;
    this.prenom = prenom;
};

Personne.prototype = Object.create(null);
Personne.prototype.affiche = function() {
    console.log(this.nom + ", " + this.prenom);
};

/*
* Personne ----> Personne.prototype
                    affiche()
*
*
*/

function Client(nom, prenom, email) {
    // chainage constructeur
    Personne.call(this, nom, prenom);
    this.email = email;
};
Client.prototype = Object.create(Personne.prototype);
// cette ligne la retablit le bon nom de constructeur (on peu faire sans)
Client.prototype.constructor = Client; 

Client.prototype.contacter = function() {
    console.log("envoie email a adresse " + this.email + " pour " + this.nom);
};


function Employe(nom, prenom, poste) {
    Personne.call(this, nom, prenom);
    this.poste = poste;
};

// etablir le chainage , le prototype de Employe est un objet vide dont le
// prototype "parent" est le prototype de Personne
Employe.prototype = Object.create(Personne.prototype);
Employe.prototype.constructor = Employe;

Employe.prototype.travailler = function () {
    console.log(this.nom + " tavaille au poste " + this.poste);
};

function test1() {
    var c1 = new Client("willis", "bruce", "bruce@diesoft.com");
    /*
    *    c1 {nom, prenom, email}
    *     +----> Client { contacter()}
    *               +---->Personne {affiche()}
    *
    *
    */
    c1.contacter();
    c1.affiche();

    var e1 = new Employe("etoile" ,"patrick", "chasser les meduses");
    e1.travailler();

    /*
    *    c1 {nom, prenom, email}
    *     +----> Client { contacter()}
    *               +---->Personne {affiche()}
    *     
    *    e1 {nom, prenom, poste}
    *     +----> Employe { travailler()}
    *               +---->Personne {affiche()}
    *    
    */
 

};


