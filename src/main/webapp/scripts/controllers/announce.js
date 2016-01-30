'use strict';


angular.module('Announce')
  .controller('AnnounceCtrl', ['$scope', '$http' , '$routeParams', '$cookies', '$window'
    ,function ($scope, $http, $routeParams, $cookies, $window) {
      console.log('cookies',$cookies);
      $scope.mine = false;
      $scope.title = "Failed to load Data";
      $scope.datePost ="16/11/1993";
      $scope.desc = "Description ....";
      $scope.prix = 100;
      $scope.surface = 100;
      $scope.localisation = "Gironde";
      $scope.user = "psow"
      $scope.getA = function(){
        $http.get('/ecommerce_api/announcements/a/'+$routeParams.id)
        .success(function(response){
          console.log('success '+response.id);
          $scope.title = response.title;
          $scope.datePost = response.datePost;
                var d = new Date($scope.datePost);
                $scope.datePost = d.getDate().toString()+"/"+d.getMonth().toString()+"/"+d.getFullYear().toString();
          $scope.desc = response.descr;
          $scope.prix = response.prix;
          $scope.surface = response.surface;
          $scope.localisation = response.localisation;
          $scope.user = response.user;
          $scope.user_id = response.user;
          $scope.photo = response.photo;
          if(typeof $cookies.globals !=  "undefined"){
            console.log($cookies.globals.currentUser.id,$scope.user_id);
            $scope.mine = $cookies.globals.currentUser.id == $scope.user_id;
          }
        }).error(function(response){
          console.log('failed '+response);
        })  
      }
      
      $scope.putA = function (){
        $http.put('/ecommerce_api/announcements/update',{
            "id" : $routeParams.id,
            "datePost" : $scope.datePost,
            "title": $scope.title,
            "prix": parseFloat($scope.prix),
            "surface": parseFloat($scope.surface),
            "localisation": $scope.localisation,
            "descr": $scope.desc,
            "user" : $scope.user_id
        })
        .success(function(response){
          console.log('success '+response.id);
        }).error(function(response){
          console.log('failed '+response);
        });
      }
      $scope.delA = function (){
        $http.delete('/ecommerce_api/announcements/'+$routeParams.id+'/'+$cookies.globals.currentUser.id)
        .success(function(response){
          console.log('success '+response.id);
          $window.location.href="#/mine"
        }).error(function(response){
          console.log('failed '+response);
        });
      }
      $scope.getA();
      console.log('init Announce service',$routeParams);
  }]);