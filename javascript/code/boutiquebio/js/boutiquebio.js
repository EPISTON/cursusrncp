$(document).ready(function() {
    // intialisation de la page
});

// le service responsable de la recuperation des produits
produitService = function(idDivListe, urlServiceJson) {
    // closure (prive)
    var produits = [];
    var idDiv =  idDivListe;
    var url = urlServiceJson;
    var currentSearch = ""; 

    // publique
    var produitServiceModule = {
        "initModule" : function() {

        },

        "setSearchTerm" : function(searchTerm) {
            
        }
    };
    return produitServiceModule;
}();
