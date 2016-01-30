'use strict';

/**
 * @ngdoc overview
 * @name ecommerceApp
 * @description
 * # ecommerceApp
 *
 * Main module of the application.
 */

angular.module('Login',['ngCookies']);
angular.module('Inscription',[]);
angular.module('News',[]);
angular.module('Mine',['ngCookies']);
angular.module('Profile',['ngCookies']);
angular.module('Announce',['ngCookies']);
angular.module('Test',['ngCookies']);

angular
  .module('ecommerceApp', [
    'ngRoute',
    'ngCookies',
    'ngFileUpload',
    'Login',
    'Inscription',
    'News',
    'Mine',
    'Announce',
    'Profile',
    'Test'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/image', {
        templateUrl: 'views/test.html',
        controller: 'desire'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl',
      })
      .when('/inscription',{
        templateUrl:'views/inscription.html',
        controller:'InscriptionCtrl'
        //controllerAs:'signup'
      })
      .when('/news',{
        templateUrl: 'views/news.html',
        controller:  'NewsCtrl'
      })
      .when('/mine',{
        templateUrl: 'views/mine.html',
        controller:  'MineCtrl'
      })
      .when('/profile',{
        templateUrl: 'views/profile.html',
        controller:  'ProfileCtrl'
      })
      .when('/a/:id',{
        templateUrl: 'views/announce.html',
        controller:  'AnnounceCtrl'
      })
      .otherwise({templateUrl: '404.html'})
  })
  .run(['$rootScope', '$location', '$cookies', '$http',
    function ($rootScope, $location, $cookies) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookies.globals || {};
        console.log($rootScope.globals);
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            console.log('page refresh ',$cookies.globals.currentUser.id);
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                $location.path('/login');
            }
        });
    }]);