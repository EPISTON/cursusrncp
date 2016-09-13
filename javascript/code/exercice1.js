// je creer un module panier
/*
* declaration fonction
* var f1 = function(param...) { code....};
* execute la fonction défini précédement et mise dans la variable f1
* module1 = f1(param...);
*
* on peu combiner les deux, pas besoin de variable f1 si on se ressert
* jamais de la fonction une deuxieme fois
*   variable glob  -  declaration fct anonyme - appel de celle-ci
*   module1 = function(param...) {code....} (param....);
*
* pollution minimal de l'expace de nom window
* possibilite de 'methode' et 'attribut' privé via la closure
* l'objet renvoyé expose les attributs et méthodes 'publique'
*/
panier = function(idpanier,idform) {
    // nous somme dans la closure
    // toutes les variables locale déclarée ici seront visible du module
    // attributs 'privé' car dans la closure de modulePanier

    var iddivpanier = idpanier;
    var iddivform = idform;
    var produits = []; // la collection des produits dans le panier (vide au debut)

    // fonction 'privé'  car dans la closure de modulePanier
    var rafraichirDiv = function () {
        // creation de la balise table
        var table = document.createElement("table");
        // ensuite, creation d'une balise tr qui contiendra les titres des colonnes
        var entete = document.createElement("tr");
        entete.innerHTML = "<th>quantite</th><th>nom</th><th>prix</th><th>poids</th><th>actions</th>";
        // on ajoute cette ligne avec les titre des colonnes au la table
        table.appendChild(entete);
        var prixTotal = 0.0;
        var poidsTotal = 0.0;

        // nous allons parcourir les produits dans le tableau
        // et pour chacun, générer une ligne tr dans la balise table
        // de plus, nous calculons les totaux au fur et a mesure
        for (var i = 0; i< produits.length ; i++) {
            // je recupere le produit courant
            var p = produits[i];
            // ligne représente la ligne du tableau qui contiendra ce produit
            var ligne = document.createElement("tr");

            // je creer une cellule par colonne a afficher dans cette ligne

            // permiere cellule, la quantite
            var cellule = document.createElement("td");
            cellule.appendChild(document.createTextNode(p.quantite));
            ligne.appendChild(cellule); // et j'ajoute a la ligne

            // deuxieme cellule, le nom du produit
            cellule = document.createElement("td");
            cellule.appendChild(document.createTextNode(p.nom));
            ligne.appendChild(cellule); // et j'ajoute a la ligne

            // troisieme celule, le prix du produit
            cellule = document.createElement("td");
            cellule.appendChild(document.createTextNode(p.prix));
            ligne.appendChild(cellule); // et j'ajoute a la ligne
            prixTotal += p.prix * p.quantite; // et j'ajoute au total du prix

            // quatrieme celule, le poids du produit
            cellule = document.createElement("td");
            cellule.appendChild(document.createTextNode(p.poids));
            ligne.appendChild(cellule);
            poidsTotal += p.poids * p.quantite;

            // la derniere cellule contiendra les boutons
            // permettant d'augmenter ou diminuer la quantite du produit
            // concerné
            // dans ces boutons, 2 choses a faire
            //  1-> stocker dans un attributs la position dans le tableau permettant
            // de savoir sur quel produit l'action est a réaliser
            //  2-> relier le click du bouton a la fonction a rappeler dans le module
            // panier

            cellule = document.createElement("td");
            // le bouton pour supprimer une ligne
            var bt = document.createElement("button");
            // de quel produit s'agi t'il ?
            bt.setAttribute("data-idproduit", i);
            // teste du bouton
            bt.appendChild(document.createTextNode("retirer"));
            // quand on clique sur le bouton, rappeler 'retirerProduit'
            bt.addEventListener('click', function() {
                // this, ici, contient le bouton sur lequel on a cliqué
                modulePanier.retirerProduit(this);
            });
            cellule.appendChild(bt);
 
            // le bouton pour augmenter une quantite
            bt = document.createElement("button");
            // de quel produit s'agi t'il ?
            bt.setAttribute("data-idproduit", i);
            bt.appendChild(document.createTextNode("augmenter"));
            // quand on clique sur le bouton rappeler 'augmenterQuantite'
            bt.addEventListener('click', function() {
                modulePanier.augmenterQuantite(this);
            });
            cellule.appendChild(bt);
            // ajouter la cellule avec les boutons dans la ligne du produit
            ligne.appendChild(cellule);
            table.appendChild(ligne);
        }
        var divpanier = document.getElementById(iddivpanier);
        // vider le html du panier
        divpanier.innerHTML = "";
        // le remplacer par le table que l'on vient de genérer
        divpanier.appendChild(table);
        // ajoute un paragraphe avec les totaux
        var totaldescription = document.createElement("p");
        totaldescription.innerText= "total prix = " + prixTotal 
                                + ", total poids = " + poidsTotal;
        divpanier.appendChild(totaldescription);
    };
    // dans modulePanier (qui sera retourné), les methodes et attributs 'public'
    var modulePanier = {
        "initPanier" : function() {
            // initPanier ne sera appelé qu'une fois le document html chargé
            // ceci est indispensable pour que le javascript ai accès au balise
            // du document
             rafraichirDiv();
             var btAdd = document.getElementById("ajouter");
             btAdd.addEventListener('click', function() {
                 modulePanier.ajouterProduit();
             });
             var btClear = document.getElementById("vider");
             btClear.addEventListener('click', function() {
                 modulePanier.viderPanier();
             });
             
        },

        "ajouterProduit" : function() {
            var form = document.getElementById(iddivform);
            var vnom = document.getElementById('nom').value;
            var vprix = Number(document.getElementById('prix').value);
            var vpoids = Number(document.getElementById('poids').value);
            // creation produit
            var produit = {
                "prix" : vprix,
                "poids" : vpoids,
                "nom"   : vnom,
                "quantite" : 1
            };
            // j'accede aux attributs et methode privé, c.a.d dans la closure
            produits.push(produit);
            rafraichirDiv();
        },
        "retirerProduit" : function(bouton) {
            console.log("retirer produit");

            var position = Number(bouton.getAttribute("data-idproduit"));
            produits[position].quantite--;
            if (produits[position].quantite <= 0)
                produits.splice(position, 1);
            rafraichirDiv();
        },
        "viderPanier" : function() {
            produits = [];
            rafraichirDiv();
        },
        "augmenterQuantite" : function(bouton) {
            var position = Number(bouton.getAttribute("data-idproduit"));
            produits[position].quantite++;
            rafraichirDiv();
        }
    };

    console.log("initialisation du module panier");

    return modulePanier;
}("panier", "produitForm");