var app=  angular.module("reason_for_override", []);
app.controller("input_ctrl",function($scope,$location,$window){	
	$scope.overRide= function(){
		 $window.location.href = '/pages/teame_funding/congrats.html';
	}
});
