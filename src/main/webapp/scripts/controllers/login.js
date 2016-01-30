'use strict';

angular.module('Login')
  .controller('LoginCtrl', ['$scope', '$window', 'LoginService', function ($scope, $window, LoginService) {
    $scope.email = "papsow@u-bordeaux.xxx";
    $scope.pwd = "repasser33";
    $scope.signin = function(){
    	LoginService.login($scope.email,$scope.pwd,function(response){
    		$window.location.href="#/mine"
    	});
    }
  }])
  .factory('LoginService',['$http','$rootScope' ,'$cookies'
  	,function($http, $rootScope,$cookies){
  		var service = {};
  		console.log($cookies);
  		service.login = function(email, pwd, cb){
  			$http.post('/ecommerce_api/users/login',{
  				"email": email,
  				"mdp":pwd
  			}).success(function(response){
  				console.log('success ',response);
          if(response.error==="not found")
              return alert('User Not in DB');
  				service.setSession(response)
  				cb(response);
  			}).error(function(response){
  				console.log('failed ',response);
          alert('User Not in DB');
  			});
  		}
  		service.setSession = function(user){
  			$rootScope.globals = {
                currentUser: user
            };
            $cookies.globals = $rootScope.globals;
            console.log($cookies.globals.currentUser);
  		}
  		service.destroySession = function(){
  			$rootScope.globals= {}
  			$cookieStore.remove('globals');
  		}
  		console.log('Init Service Login');
  		return service;

  }]);


