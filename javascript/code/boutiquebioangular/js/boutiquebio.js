// creer un module (app) "produitBio"
// ce module a une dépendance au module customFilters
angular.module("produitBio", ["customFilters"]);

// si vous ne mettez pas les dependances
// cela veu dire que vous ne creer pas un module
// mais recupérer un module déjà creer
angular.module("produitBio")
       .controller("produitCtrl", function($scope) {
       // la variable $scope
       // c'est un contexte, zone de stockage de mon controlleur
       //
       // <div ng-controller="produitCtrl">         -->  $scope
       // ...   {{mydata.message}}         <--           $scope.mydata.message
       //  <input type="text" ng-model="mydata.nom" <->  $scope.mydata.nom
       // </div>
       //
       // on peu utiliser des filtres
       // qui permette de traiter, directement dans le html, les données
       // avant affichage : par exemple currency
       // donne | filter
       //
       $scope.data = {"message" : "vive la pizza",
                      "produits" : [
                          {"id":1,"nom":"acai amazonien sans gluten","poids":0.3,"prix":19.99,"stock":15},
                          {"id":2,"nom":"biere oceania","poids":0.5,"prix":19.99,"stock":0},
                          {"id":5,"nom":"pain au graine de courge","poids":0.25,"prix":4.99,"stock":450},
                          {"id":6,"nom":"quinoa des andes","poids":0.5,"prix":25.99,"stock":26},
                          {"id":8,"nom":"poulet equitable","poids":0.35,"prix":21.99,"stock":0},
                          {"id":9,"nom":"steack de lama","poids":0.75,"prix":115.0,"stock":7},
                          ]
                        };
        $scope.data.columns = [];
        for (col in $scope.data.produits[0]){
            if (col != "id")
                $scope.data.columns.push(col);
        }
        $scope.data.isStockFilterActive = false;
    });