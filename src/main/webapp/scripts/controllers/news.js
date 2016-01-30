'use strict';

angular.module('News')
  .controller('NewsCtrl', ['$scope','$http' ,function ($scope,$http) {
    $scope.announcements = []
    $scope.search = "";
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
    $scope.find = function () {
        $http.get('/ecommerce_api/announcements/find/'+$scope.search)
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
    };
    console.log('init News service');
  }]);