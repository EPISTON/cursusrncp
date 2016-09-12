
function test1() {
    // l'objet window est a la "racine" de l'espace de variable
    for (var prop in window) {
        console.log(prop);
    }
};

/*function test2() {
    mavar1 = 42; // equivalent a window.mavar1 = 42;
}*/

window.test2 = function() {
    mavar1 = 42; // equivalent a window.mavar1 = 42;
};

function test3() {
    var fct = function() {
        console.log("une petite fonction anonyme");
    };
    console.log(typeof(fct));
    fct();
    console.log(fct);

    var o = {};
    o.attr1 = 42;
    o.methode1 = fct;
    // je peu librement copier et affecter les fonctions ou je veux
    o.methode1();
};

// test des methodes
function test4() {
    attr1 = 16;

    // notation json, objet litteral (cle : valeur, cle2 : valeur2, etc...)
    var o1 = {
        attr1 : 1,
        methode1 : function () {
            this.attr1++;
            console.log("attr1 = " + this.attr1);
        }
    };
    o1.methode1();

    var o2 = {
        attr1: 42
    };
    o2.methode2 = o1.methode1; // ceci marche
    o2.methode2();

    var f1 = o1.methode1;

    // dans cet appel, this sera positionné sur window
    f1();
};


//window.test3 = window.test1;

function test5() {
    var compteur = 1;
    // au moment ou une fonction est déclarée
    // javascript stock dans une closure associée à celle-ci
    // toutes les variable LOCALE visible a ce moment la
    var fct = function() {
        var i = 5;
        console.log(compteur++);
    };

    fct();
    window.test6 = fct;
};

function launchCounter() {
    // recuperation de l'element champ
    var champ = document.getElementById('nom');
    // recuperation du contenu du champ
    var nom = champ.value;
    var compteur = 1;

    // chaque fois que je creer fctcompteur
    // javascript memorise dans sa closure champ, nom et compteur
    // visible lors de cet appel
    var fctcompteur = function() {
        console.log("compteur " + nom + " = " + compteur++);
    }
    // rapeller la fonction toutes les 2 secondes
    window.setInterval(fctcompteur, 2000);
};
