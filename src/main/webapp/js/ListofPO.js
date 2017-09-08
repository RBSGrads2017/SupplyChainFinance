var app = angular.module('list_of_PO', []);


app.controller('listofPOSeller', function($scope, $http, $window, $location) {
    
	$http.get("/service/purchase_order_list_bank_user").then(function(response){
    	console.log(response.data);
    	
    });
	$scope.purorder = [{pid:'47', status:'pending'},{pid:'36', status:'approved'}];
    $scope.SellerPOList= function(){
//    	var promise = $http({
//	        url: BASE_PATH + '/service/login/getUserInfo',
//	        method: 'POST',
//	        headers: { 'Content-Type': 'text/plain' }
//	    });
//	    promise.then(function (response) {
//	    	console.log(response);
//	    	
//	    	if (response.data == "no session") {
//	    		$window.location.href = './HomePage.html'
//	    	} else {
//	    		console.log(response.data.username);
//	        	console.log(response.data.userType);
//	    	}
//	    }, function(response) {
//	    	console.log("couldnot load data");
//	    });
	    
//	    $http.get("/service/purchase_order_list_bank_user?username="+response.data.username).then(function(response){
//	    	console.log(response.data);
//	    });
    }
    
    $scope.openOrder= function(){
    	window.location.href="/pages/teame_funding/purchase_request_review.html";
    }
    
});
