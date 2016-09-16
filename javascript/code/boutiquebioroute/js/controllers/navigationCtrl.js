angular.module("produitBio")
        .controller("navigationCtrl", function($scope) {

            var views = ['productsView.html', 'commandeView.html'];

            $scope.currentView = 0;

            $scope.getViewAdress = function() {
                return 'views/' + views[$scope.currentView];
            };

            $scope.setCurrentView = function(novue) {
                $scope.currentView = novue;
            };
        });