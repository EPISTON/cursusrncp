
// appelé quand la page est prete
$(document).ready(function() {
	myAutoComplete.init();
});

// cet objet contiendra la logique de ma boite de recherche
// il memorise la valeur du champ de recherche
// il envoie les requettes ajax si nécéssaire, et rafraichit la liste
// dans la page
myAutoComplete = {
	"oldSearchTerm" : "",
	"ajaxurl" : "produit/indexjson/",   // adresse ou on enverra les requettes ajax
	
	init : function() {
		// je récupere le champ de recherche
		this.searchBox = $("input#search");
		// je récupere le div ou on affichera la liste
		this.reponseBox = $("div#resultat");
		// je lance le premier controle de ma searchBox
		this.checkSearchBox();
	},
	
	checkSearchBox : function() {
		var self = this;
		// je récupere le contu de la textBox
		var contenu = this.searchBox.val();
		// si le contenu n'as pas changé
		if (contenu == this.oldSearchTerm) {
			// rappeler la fonction dans 2 secondes (2000 millisecondes)
			window.setTimeout(function () {
				self.checkSearchBox();
			}, 2000);
		}
		else {
			// le contenu a changé
			this.oldSearchTerm = contenu;
			$.getJSON(this.ajaxurl + contenu, function(data) {
				self.refreshList(data);
			});
		}
	},
	
	refreshList : function(data) {
		// je vide le div reponse
		this.reponseBox.empty();
		var ul = $("<ul></ul>");
		var size = data.length;
		// je parcours les produits renvoyés
		for (var i = 0; i < size; i++) {
			var produit = data[i];
			// pour chaque produit, ajouter une ligne li
			ul.append($("<li>" + produit.nom + " - "
						+ produit.prix + " - stock = "
						+ produit.stock + "</li>"));
		}
		this.reponseBox.append(ul);
		// on reverifiera dans 2 secondes
		var self = this;
		window.setTimeout(function () {
			self.checkSearchBox();
		}, 2000);
	}
};
