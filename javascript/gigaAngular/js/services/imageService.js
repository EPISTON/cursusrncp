angular.module("imageServices", ['ngFileUpload', 'ui.bootstrap'])
       .constant("imageUrl", "http://localhost:8080/gigaGallerie/image")
       .factory("imageRepo", function($http, imageUrl) {
           var serverUrl = imageUrl;


           var imageRepo = {
               "listImages" : function() {
                  return $http.get(serverUrl + '/liste');
               }

           };
           return imageRepo;
       })
       .factory("imageUpload", function(Upload, timeout, imageUrl) {
           var imageUpload = {
               
           };

           return imageUpload;
       } ]);