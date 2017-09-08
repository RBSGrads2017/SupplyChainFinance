var app=  angular.module("reason_for_override", []);
app.controller("input_ctrl",function($scope,$location,$window){	
	$scope.overRide= function(){
		 $window.location.href = 'http://localhost:8090/scm/pages/teame_funding/congrats.html';
	}
});
