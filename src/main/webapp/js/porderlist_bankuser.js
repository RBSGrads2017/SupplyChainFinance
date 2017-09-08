var app=  angular.module("list_of_PO", []);
app.controller("POList_Ctrl",function($scope,$location,$window,$http){
    
    
    
    $scope.porderFetch= function(){
    	//alert(1);
    	$http.get("http://localhost:8090/scm/service/purchase_order_list_bank_user").then(function(response){
    		console.log(response.data);
    		$scope.purorder= response.data;
    	});
    }
   
    $scope.redirect= function(){
        $window.location.href = 'http://localhost:8090/scm/pages/teame_funding/purchase_request.html';
    }

    $scope.openPO= function(id){
    	$window.location.href = 'http://localhost:8090/scm/pages/teame_funding/SorryPage.html';
    }
});