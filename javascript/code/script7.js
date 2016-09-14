
//ce callback ne sera rappelé qu'une fois que le html
// de la page est "pret" a etre manipulé par du javascript
$(document).ready(function () {
    console.log("la page est prete!");
    // le selecteur de jquery, fantastique
    //$("ul#moisul li.ski").addClass("surligne");    


    $("#boutonski1").click(function() {
        // ce sélécteur se lit:
        // selectionner tous les li de classe ski
        // qui sont des descendants du 'ul' d'identifiant 'moisul'
        $("ul#moisul li.ski").toggleClass("surligne");
    });
    // je cache initialement ces li
    $("ul#regionul li.ski").hide();
    $("#boutonski2").click(function() {
        //$("ul#regionul li.ski").fadeIn(2000);
        $("ul#regionul li.ski").slideDown(2000);
    });
    
    var ul = $("ul#regionul");
    $("#boutontest").click(function() {
        ul.after($("<p>hello jquery</p>"));
    });

    $('#boutonajax').click(function() {
        $.getJSON('http://localhost:8080/produitBioForm/produit', function(data) {
            console.log("recu " + data.length + " produits");
        });
    });

});