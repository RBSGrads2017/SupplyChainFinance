var app=  angular.module("list_of_PO", []);
app.controller("POList_Ctrl",function($scope,$location,$window,$http){
    
    
    
    $scope.purorder
    
    =[{pid:'12897', status:'pending'},
        {pid:'12888', status:'approved'}];
    
    
    
    $scope.redirect= function(){
        $window.location.href = 'http://localhost:8181/jersey-heroku-webapp/pages/purchase_request.html';
    }
});