var app=  angular.module("myApp", []);
app.controller("bodyController",function($scope,$location,$window,$http){
	$scope.uploadlibor= false;
	$scope.liborSelected= true;
	$scope.uploadLIBOR= function(){
		$scope.uploadlibor= true;
		$http.get("http://localhost:8090/scm/service/fetchlibor").then(function(response){
			$scope.libors=response.data;
			console.log($scope.libors);
		});
	};
	
	
	/*$scope.libors=[{currency:"abc libor", duration:"2 months", rate:"0.47%"},
	{currency:"pqr libor", duration:"4 months", rate:"0.99%"},
	{currency:"xyz libor", duration:"2 months", rate:"0.73%"}]*/
	
	$scope.redirect= function(){
		 $window.location.href = 'http://localhost:8090/scm/pages/teame_funding/porderlist_bankuser.html';
	}
});