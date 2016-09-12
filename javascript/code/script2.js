// decouverte des mysteres des variables javascript
function test1() {
    // t1 sera de type String
    var t1 = "bonjour";
    console.log(t1);
    // t1 change de type pour etre un nombre
    t1 = 42;
    console.log(t1);
    /*
     * les types disponnibles en javascript
     *  String
     *  number -> nombre (int, double, etc...)
     *  boolean -> true/false
     *  object -> objets...
     *  array -> tableau
     *  function -> function
     *  undefined... 
     */

     var nbr1 = 1245.24; // virgule flottante
     var nbr2 = 0xAAFF42; // literal en hexadecimal
     var nbr3 = nbr1 / 0.0; // infinity
     var nbr4 = NaN;

     console.log(typeof(nbr1));
     console.log(nbr1);
     console.log(typeof(nbr2));
     console.log(nbr2);
     console.log(typeof(nbr3));
     console.log(nbr3);
     console.log(typeof(nbr4));
     console.log(nbr4);
     
     console.log("nbr3 is not a number ? " + isNaN(nbr3));
     console.log("nbr4 is not a number ? " + isNaN(nbr4));

     var nbr5 = 15;
     var texte1 = "15";
     // piege -> javascript converti automatiquement, sans faire d'erreur
    if (nbr5 == texte1) {
        console.log("nbr5 == texte1 -> true");
    }
    else {
        console.log("nbr5 == texte1 -> false");
    }
    // le triple egal verifie l'egalite de valeur ET de type
    console.log("nbr5 === texte1 -> " + (nbr5 === texte1));
    //console.log("bonjour" > 15);

    var texte2 = undefined;
    console.log(texte2);
    console.log(typeof(texte2));
    console.log("type de texte1 -> " + typeof(texte1));

    var b1 = true;
    console.log("type b1 -> " + typeof(b1));
};


// type avancés
function test2() {
    // creation d'un objet vide
    var o1 = {};
    console.log(typeof(o1));

    o1.attribut1 = 26;
    console.log(o1.attribut1);

    delete o1.attribut1; // efface une variable ou attribut
    console.log(o1.attribut1);

    // creation d'un objet avec 2 attributs
    var o2 = {
        "attribut1" : 45,
        "attribut2" : "coucou"
    };
    console.log(o2.attribut2 + " - " + o2.attribut1);

    // for in itere sur les attributs (at pas les valeurs) d'un objet
    for (var attr in o2) {
        console.log(attr + " -> " + o2[attr]);
    }

    // marche mais NE FAITES PAS CA!!!!
    o2["if the   et else"] = 12;
    console.log(o2["if the   et else"]);

      // for in itere sur les attributs (at pas les valeurs) d'un objet
    for (var attr in o2) {
        console.log(attr + " -> " + o2[attr]);
    }

    var tab1 = ["lundi", "mardi", "mercredi", 16, "yolo"];
    console.log(typeof(tab1)); // renvoie object, alots que c'est un tableau
    console.log(tab1[0]);
    console.log(tab1.length); // propriété automatique sur les tableaux
    for (var idx in tab1) {
        console.log(idx  + " -> " + tab1[idx]);
    }
    tab1[8] = "pizza";
    console.log(tab1.length); // propriété automatique sur les tableaux
    
    for (var idx in tab1) {
        console.log(idx  + " -> " + tab1[idx]);
    }
    // parcours classique, mais peux echouer si tableau bizarre
    var tab2 = ["orange", "poire", "pomme", "cerise"];
    for(var i = 0; i < tab2.length; i++) {
        console.log(tab2[i]);
    }

};


function test3() {
    var c1 = 1;
    var c2 = sousfonction1(c1);
    console.log("c1 dans test3 = " + c1 )
    console.log("c2 dans test3 = " + c2 );
    var c3 = sousfonction1(c2, "youpi", "comment ca va"); // ca marche quand même!!!!
    console.log("c3 dans test3 = " + c3 );
    sousfonction2(c1, c2, "hohoho");

    sousfonction3();
    sousfonction4();
    sousfonction5();    
};
// compteur est local a la fonction
function sousfonction1(compteur) {
    compteur++;
    console.log("compteur dans sousfonction1 = " + compteur);
    var c1 = 45; // variable locale
    return compteur;
};
// arguments est le tableau des arguments recu en parametre
function sousfonction2() {
    for (var i = 0; i < arguments.length; i++) {
        console.log("arg no " + i + " = " + arguments[i]);
    }
};
// les variables globales
function sousfonction3() {
    var cpt1 = 1;
    cpt2 = 1; // ceci est equivalent a une variable globale

    cpt1++;
    cpt2++;
    console.log("cpt1 = " + cpt1  + " cpt2 = " +cpt2);
};
function sousfonction4() {
    console.log("cpt2 = " + cpt2);
    if (typeof(cpt1) !== 'undefined') // verification si la variable existe
        console.log("cpt1 = " + cpt1);
    else
        console.log("cpt1 n'existe pas");
};

// declaration des variables locales
function sousfonction5() {
    var v1 = 15;

    if (typeof(v1) !== 'undefined') // verification si la variable existe
        console.log("v1 = " + v1);
    else
        console.log("v1 n'existe pas");

    console.log("v2 = " + v2);
    /*if (typeof(v2) !== 'undefined') // verification si la variable existe
        console.log("v2 = " + v2);
    else
        console.log("v2 n'existe pas");
*/
    console.log("hohoho");
    var v2 = 10; // en fait, v2 est déclaré ici au début de la fonction
    console.log("v2 = " + v2);
};



