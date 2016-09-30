// creer un module (app) "produitBio"
// ce module a une dépendance au module customFilters
// dependance au module angular-route
angular.module("gigaApp", ["customFilters", "ngRoute", "tagServices", "ngFileUpload", "ui.bootstrap"])
        .config(function($routeProvider) {
            // le module ngRoute fournit un routeProvider
            // qui permet de configurer/definir la navigation dans notre
            // application
            $routeProvider.when("/images", {
                "templateUrl": 'views/imagesView.html'
                        }).when("/tags", {
                "templateUrl": 'views/tagsView.html'
                        }).when("/galerie", {
                "templateUrl": 'views/galerieView.html'
                        }).when("/upload", {
                "templateUrl": 'views/uploadView.html'
                        }).otherwise({
                "templateUrl": 'views/imagesView.html'            
                        });
            

        });

