var app = angular.module('myApp2', []);
app.controller('sorryBank', function($scope,$location,$window,$http) {
    $scope.manualOverride= function(){
    	$window.location.href = "/pages/teame_funding/reason_for_override.html";
    }
    $scope.declineFunding= function(){
    	$window.location.href = "/pages/teame_funding/sorrySeller.html";
    }
});
