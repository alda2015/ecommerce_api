'use strict';


angular.module('Profile')
  .controller('ProfileCtrl', ['$cookies', '$scope', '$http', '$window' 
    ,function ($cookies, $scope, $http, $window) {
      if($cookies.globals === undefined){
        $window.location.href="#/login";
        return ;
      }
      $scope.id = $cookies.globals.currentUser.id;
      $scope.email = $cookies.globals.currentUser.email;
      $scope.firstname = $cookies.globals.currentUser.firstName;
      $scope.lastname = $cookies.globals.currentUser.lastName;
      $scope.mdp = $cookies.globals.currentUser.mdp;
      $scope.mdpcheck = $cookies.globals.currentUser.mdp;
      $scope.tel = $cookies.globals.currentUser.tel;
      $scope.address = $cookies.globals.currentUser.address;
      $scope.admin = $cookies.globals.currentUser.admin;
      $scope.updateProfile = function(){
        if($scope.mdp === $scope.mdpcheck){

          $http.put('/ecommerce_api/users/update',{
            "id": $scope.id,
            "email": $scope.email,
            "firstName": $scope.firstname,
            "lastName": $scope.lastname,
            "mdp": $scope.mdp,
            "tel": $scope.tel,
            "address": $scope.address,
            "admin": $scope.admin
          }).success(function(response){
            console.log('success ',response);
            $window.location.href="#/profile";
          })
            .error(function(response){
              console.log('failed ',response);
            })
        }
          
      }
      console.log('init Mine service');
  }]);