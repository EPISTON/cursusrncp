// creer un module (app) "produitBio"
// ce module a une dépendance au module customFilters
// dependance au module angular-route
angular.module("produitBio", ["customFilters", "ngRoute"])
        .config(function($routeProvider) {
            // le module ngRoute fournit un routeProvider
            // qui permet de configurer/definir la navigation dans notre
            // application
            $routeProvider.when("/produit", {
                "templateUrl": 'views/productsView.html'
                        }).when("/commande", {
                "templateUrl": 'views/commandeView.html'
                        }).otherwise({
                "templateUrl": 'views/productsView.html'            
                        });
            

        });

