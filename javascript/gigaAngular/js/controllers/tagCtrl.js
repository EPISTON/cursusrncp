
angular.module("gigaApp")
       .controller("tagCtrl", ['$scope', 'tagRepo', function($scope, tagRepo) {
            
            $scope.data = {"tags" : [], "libelle": ""};
            tagRepo.listTags().then(function(response) {
                    $scope.data.tags = response.data;
                }, function(response) {
                    $scope.data.tags = [];
                });
            
            $scope.saveTag = function() {
                tagRepo.createTag({ "id" : 0, "libelle" : $scope.data.libelle})
                        .then(function(response) {
                            console.log("tag saved");
                        },function(response) {
                            console.log("error saving tag");
                        }
                        );
            }
            
    }]);