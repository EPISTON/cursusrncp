angular.module("tagServices", [])
       .constant("tagUrl", "http://localhost:8080/gigaGallerie/tag")
       .factory("tagRepo", function($http, tagUrl) {
           var serverUrl = tagUrl;

           var tagRepo = {
               "listTags" : function() {
                  return $http.get(serverUrl + '/liste');
               },
               "createTag" : function(tag) {
                  return $http.post(serverUrl + '/save',
                    {"tagID" : tag.id, "tagLibelle": tag.libelle},
                    {"headers": {'content-Type' : 'application/json'}}
                  );
               },
               "updateTag" : function(tag) {
                  return $http.get(serverUrl + '/liste');
               },
               "findTag" : function(tid) {
                  return $http.get(serverUrl + '/liste');
               }

           };
           return tagRepo;
       });