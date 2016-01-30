'use strict';

angular.module('Test')	
	.controller('desire', ['$scope','$location','Upload','$timeout','$cookies', function($scope,$location,Upload,$timeout,$cookies) {

		/*alert($cookies.globals.currentUser.id);
		alert("test test ");
		*/
		$scope.model ={};
		
		for (var key in $scope.model) { 
			console.log("adding "+key+" with calue:"+$scope.model[key]);
		}

		$scope.uploadPic =function(file){
			$scope.announce = {
					title: $scope.model.titre,
					descr:$scope.model.desc,
					surface :$scope.model.surface,
					localisation :$scope.model.localisation,
					prix :$scope.model.prix
			};  

			for (var key in $scope.model) { 
				console.log("adding "+key+" with calue:"+$scope.model[key]);
			}
			console.log("file---------------->",JSON.stringify(file));

			file.upload = Upload.http({
				url: '/ws/ecommerce_api/announcements/announcement/'+JSON.stringify($scope.announce)+'/'+$cookies.globals.currentUser.id,
				headers: {
					'Content-Type': file.type
				},
				method: 'POST',
				data : file
			});

			file.upload.then(function(response){
				$timeout(function() {
					
					console.log(" success " + response.status + "  DATA  : " + response.data);
					$scope.show = $scope.show();
					$location.url('/news');
				});
			}, function(response){
				if (response.status > 0)
					console.log("response.status " , response.status );
			}, function(evt) {
				file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
			});
		}

		$scope.show=function(){
			$scope.isVisible = true;
		}

	}]);
