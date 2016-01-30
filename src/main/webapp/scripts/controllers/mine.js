'use strict';


angular.module('Mine')
  .controller('MineCtrl', ['$cookies', '$scope', '$http', '$window' 
    ,function ($cookies, $scope, $http, $window) {
      if($cookies.globals === undefined){
    	alert("Not logged in");
        $window.location.href="#/login";
        return ;
      }
      $scope.title = 'title';
      $scope.price = '0';
      $scope.surface = '0';
      $scope.desc = 'descrption';
      $scope.localisation = 'location';
      $scope.announcements = [];
      $scope.foto = null;
      $scope.postA = function(){
        console.log('POST_A');
        $http.post('/ecommerce_api/announcements/add/'+$cookies.globals.currentUser.id,{
            "title": $scope.title,
            "prix": parseFloat($scope.price),
            "surface": parseFloat($scope.surface),
            "localisation": $scope.localisation,
            "descr": $scope.desc
        }).success(function(response){
            console.log('success ',response);
            $scope.getA();
        }).error(function(response){
            console.log('failed ',response);
        });
      };
      $scope.getA = function(){
        $http.get('/ecommerce_api/announcements/'+$cookies.globals.currentUser.id)
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
      }
      $scope.getA();
      console.log('init Mine service');
  }]);