
var app = angular.module('gigaApp', ['ngFileUpload', 'ui.bootstrap']);

app.controller('uploadCtrl', ['$scope', 'Upload', '$timeout', function ($scope, Upload, $timeout) {
    $scope.$watch('files', function () {
        $scope.upload($scope.files);
    });
    $scope.log = '';
    $scope.fileProgressBars = {};
    $scope.uploadAlerts = [];
    $scope.upload = function (files) {
        if (files && files.length) {
            for (var i = 0; i < files.length; i++) {
              var file = files[i];
              if (!file.$error) {
                var p = {};
                $scope.fileProgressBars[file.name] = 0;
                Upload.upload({
                    url: 'http://localhost:8080/gigaGallerie/photo/upload',
                    data: {
                      file: file  
                    }
                }).progress(function (evt) {
                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    $scope.fileProgressBars[evt.config.data.file.name] = progressPercentage;
                    /*$scope.log = 'progress: ' + progressPercentage + '% ' +
                                evt.config.data.file.name + '\n' + $scope.log;*/
                }).success(function (data, status, headers, config) {
                    $timeout(function() {
                        delete $scope.fileProgressBars[config.data.file.name];
                        var alertUpload = { type: "success"};
                        alertUpload.message="successfully uploaded " + config.data.file.name;
                        $scope.uploadAlerts.push(alertUpload);
                    });
                });
              }
            }
        }
    };
    $scope.closeAlertUpload = function(index) {
      $scope.uploadAlerts.splice(index, 1);  
    };
}]);