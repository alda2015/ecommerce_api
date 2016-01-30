'use strict';

angular.module('Inscription')
  .controller('InscriptionCtrl', ['$scope','$http', '$location' ,function ($scope,$http,$location) {
    $scope.email = "papsow@u-bordeaux.xxx";
    $scope.pwd = "repasser33";
    $scope.confpwd= "repasser33"
    $scope.inscription = function(){
        console.log('inscription');
        var cb = function(r){
            alert(r);
            $location.path('/login');
        }
    	if($scope.pwd != $scope.confpwd){
            cb("error pwd");
            return;
        }
    $http.post('/ecommerce_api/users/addUser',{
            "email": $scope.email,
            "mdp": $scope.pwd
        }).success(function(response){
            console.log('success ',response);
            cb("Inscription Effectué");
        }).error(function(response){
            console.log('failed ',response);
            cb("Inscription Echoué");
        });
    }
  }]);