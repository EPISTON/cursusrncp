// creer un module (app) "produitBio"
angular.module("produitBio", []);

// si vous ne mettez pas les dependances
// cela veu dire que vous ne creer pas un module
// mais recupérer un module déjà creer
angular.module("produitBio")
       .controller("produitCtrl", function($scope) {
       $scope.data = {"message" : "vive la pizza"};
    });