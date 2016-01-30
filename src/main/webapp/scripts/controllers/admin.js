'use strict';

  angular.module('Admin')
  .controller('AdminCtrl', ['$scope','$http' ,function ($scope,$http) {
    $scope.user = []
    $http.get('/ecommerce_api/users')
        .success(function(response){
            console.log('success ',response);
            $scope.user = response;
        })
        .error(function(response){
            console.log('failed ',response);
            $scope.user = response;
        });

    $scope.announcements = []
    $http.get('/ecommerce_api/announcements/all')
        .success(function(response){
            console.log('success ',response);
            $scope.announcements = response;
            for(var i = 0;  i< $scope.announcements.length;i++){
                var d = new Date($scope.announcements[i].datePost);
                $scope.announcements[i].datePost = d.getDate().toString()+"/"+d.getMonth().toString()+"/"+d.getFullYear().toString();
            }
        })
        .error(function(response){
            console.log('failed ',response);
            $scope.announcements = response;
        });

  }]);