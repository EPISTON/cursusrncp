$(document).ready(function() {
    // intialisation de la page

    // initialisation du module de recuperation des produits
    produitService.initModule();
});

// le service responsable de la recuperation des produits
produitService = function(idDivListe, urlServiceJson) {
    // closure (prive)
    var produits = []; // la liste des produits
    var idDiv =  idDivListe; // identifiant du div ou sera affiché la liste
    var url = urlServiceJson; // url on envoyer les requettes ajax pour les produits
    var currentSearch = ""; // terme de recherche pour filtrer les produits 

    // la fonction refreshList se charge d'nevouyer une requete ajax au serveur
    // pour recuperer la liste des produits a jour (stockée dans produits)
    // et appel ensuite displayList pour rafraichir le html
    var refreshList = function() {
        var requesturl = url;
        // si c'est une recherche, ajouter ce qu'il faut a l'url
        if (currentSearch != "") {
            requesturl += "/search/" + currentSearch;
        }
        // envoie de la requette ajax
        $.getJSON(requesturl, function(data) {
            // quand nous recevons les données, remplacer
            // l'ancienne liste de produit par celle en provenance du serveur
            produits = data;
            // et rafraichir l'affichage
            displayList();
        });
    };
    // cette fonction rafraichi l'affichage, c.a.d regenere le tableau html
    // des produits a partir de notre liste de produits
    var displayList = function() {
        // on recupere le div contenant le tableau html et on le vide
        var div = $("#" + idDiv);
        div.empty();
        // creation de l'en tete et de la balise table
        // la fonction $() (une fonction de jQuery) permet de récupérer un element
        // du document
        // mais, si on lui passe, au lieu d'un selecteur css, directement
        // du code html, cette fonction creer les balises correspondante
        // et nous renvoie l'element cree
        var table = $("<table class='table'></table>");
        table.append("<tr><th>nom</th><th>prix</th><th>stock</th></tr>");
        // pour chaque produit, ajout d'une ligne dans la table html
        $.each(produits, function(index, item) {
            table.append("<tr><td>" + item.nom + "</td>" +
                         "<td>" + item.prix + "</td>" +
                         "<td>" + item.stock + "</td></tr>");
        });
        div.append(table); // on insere la table html nouvelle dans le div
    };

    // publique
    var produitServiceModule = {
        "initModule" : function() {
            // a l'initalisation du module, rafraichir la liste des produits
            // une première fois
            refreshList();
        },

        "setSearchTerm" : function(searchTerm) {

        }
    };
    return produitServiceModule;
}("listeProduit", "http://localhost:8080/produitBioForm/produit");

