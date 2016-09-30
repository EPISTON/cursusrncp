angular.module("customFilters", [])
       .filter("noStock", function() {
           return function(data, isActive) {
               if (!isActive) {
                   return data;
               }
               if (angular.isArray(data)) {
                   var results = [];
                   for(var i = 0; i < data.length; i++) {
                       if (data[i].stock > 0){
                            results.push(data[i]);
                       }
                   }
                   return results;
               }
               else {
                   return data;
               }
           }
       });