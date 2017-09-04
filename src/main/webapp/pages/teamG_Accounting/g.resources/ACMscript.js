var acmApp = angular.module('acmApp', ["ngRoute"]);
acmApp.config(function ($routeProvider){
	console.log("page1");
	$routeProvider
	.when('/', {redirectTo : 'ACMindex.html'})
	.when('/coaList', {templateUrl : 'coaList.html'})
	.when('/viewGL', {templateUrl : 'viewGL.html'})
	.when('/addCOA', {templateUrl : 'addCOA.html'})
	.when('/addCoaResult', {templateUrl : 'addCoaResult.html'})
	.when('/coaSingle', {templateUrl : 'viewCOA.html'})
	.when('/coaDelError', {templateUrl : 'coaDeleteError.html'})
	.when('/coaDelPriorMsg', {templateUrl : 'coaDeletePriorMsg.html'})
	.when('/coaDelMsg', {templateUrl : 'coaDeletedMsg.html'})
	.when('/checkCompliance',{templateUrl:'complianceCheck.html'})
});
	
acmApp.controller('coaListCtrl', function ($scope, $http){    
	$scope.callCoaList = function () {
		console.log("coaListCtrl");
        $http.post(BASE_PATH +'/ACM/viewCOAlist').success(function (data) {
            $scope.coaList = data;
            console.log(data);
            window.location = "#/coaList";
					
        });
	}
$scope.checkCompliance1=function(){
		var individualname=this.individualname;
		var Countryname=this.Countryname;
		if(individualname==undefined || individualname=='null')
			individualname='';
		if(Countryname==undefined || Countryname=='null')
			Countryname='';
		if(individualname==''||Countryname==''){
			alert("Enter all the fields");
			window.location = "#/checkCompliance";
		}
		else{
			var url=BASE_PATH +'/ACM/CheckCompliance';
			$http({url:url,method:"POST",params:{'individualname':individualname,'Countryname':Countryname}}).success(function(data){	
				 alert(data);
				window.location = "#/";	
	            
			});
		}
		
	}
	
	$scope.callCoa = function (swiftID) {
		console.log("coaCtrl");
        $http({url:BASE_PATH +'/ACM/viewCOASINGLE',method:"POST",params:{'swiftID':swiftID}}).success(function (data) {
            $scope.coaSingle = data;
            console.log(data);
            window.location = "#/coaSingle";
					
        });
	}
	$scope.coaDelSingle= function(swiftID){
		$http({url:BASE_PATH +'/ACM/viewCOASINGLE',method:"POST",params:{'swiftID':swiftID}}).success(function (data) {
			$scope.coaSingle = data;
            console.log(data);
            window.location = "#/coaDelPriorMsg";
					
	    });
	}
	$scope.coaDelSingleConfirm= function(swiftID){
		console.log(swiftID);
		$http({url:BASE_PATH +'/ACM/coaDelSingle',method:"POST",params:{'swiftID':swiftID}}).success(function () {
           /* $scope.swift = data;*/
           console.log("coa Deleted");
           alert("Deleted");
           $scope.callCoaList();
            //window.location = "#/coaDelMsg";
					
	    });
	}
	
	$scope.callGl = function () {
        $http.post(BASE_PATH +'/ACM/viewGL').success(function (data) {
            $scope.glList = data;
            console.log(data);
            window.location = "#/viewGL";		
	    });
	}
	
	$scope.callGlSearch = function () {
		var acEntryNo=this.acEntryNo;
		var transNo=this.transNo;
		var custAcNo=this.custAcNo;
		var swiftID=this.swiftID;
		var paymentDate=this.paymentDate;
		var invoiceNo=this.invoiceNo;
		var drCr=this.drCr;
		var dueDate=this.dueDate;
		/*var dueDate=this.dueDate;*/
		if(acEntryNo==undefined||acEntryNo=='null')
			acEntryNo='';
		if(transNo==undefined||transNo=='null')
			transNo='';
		if(custAcNo==undefined||custAcNo=='null')
			custAcNo='';
		if(swiftID==undefined||swiftID=='null')
			swiftID='';
		if(paymentDate==undefined||paymentDate=='null')
			paymentDate='';
		else
			paymentDate=getFormattedDate(paymentDate);
		if(invoiceNo==undefined||invoiceNo=='null')
			invoiceNo='';
		if(drCr==undefined||drCr=='null')
			drCr='';
		if(dueDate==undefined||dueDate=='null')
			dueDate='';
		else
			dueDate=getFormattedDate(dueDate);
		console.log(paymentDate);
		console.log(dueDate);
		if(acEntryNo==''&&transNo==''&&custAcNo==''&&swiftID==''&&paymentDate==''&&invoiceNo==''&&drCr==''&&dueDate=='')
		{	
			alert("Enter atleast one field");
			$scope.callGl();
		}
		else
		{
			if(drCr=='allEvents')
				drCr='';
			var url=BASE_PATH +'/ACM/viewGLBySearch';
			$http({url:url,method:"POST",params:{'acEntryNo':acEntryNo,'transNo':transNo,'custAcNo':custAcNo,'swiftID':swiftID,'paymentDate':paymentDate,'invoiceNo':invoiceNo,'drCr':drCr,'dueDate':dueDate}}).success(function (data) {
	            $scope.glList = data;
	            console.log(data);
	            window.location = "#/viewGL";					
			});
		}
	}
	
	$scope.addCoa = function () {
		$scope.head=this.head;
		$scope.legalEntity=this.legalEntity;
		$scope.country=this.country;
		$scope.branch=this.branch;
		$scope.product=this.product;
		$scope.currency=this.currency;
		$scope.book=this.book;
		$scope.productSwiftID=this.productSwiftID;
		if($scope.head==undefined||$scope.head=='null')
			$scope.head='';
		if($scope.legalEntity==undefined||$scope.legalEntity=='null')
			$scope.legalEntity='';
		if($scope.country==undefined||$scope.country=='null')
			$scope.country='';
		if($scope.branch==undefined||$scope.branch=='null')
			$scope.branch='';
		if($scope.product==undefined||$scope.product=='null')
			$scope.product='';
		if($scope.currency==undefined||$scope.currency=='null')
			$scope.currency='';
		if($scope.book==undefined||$scope.book=='null')
			$scope.book='';
		if($scope.productSwiftID==undefined||$scope.productSwiftID=='null')
			$scope.productSwiftID='';
		if($scope.head==''||$scope.legalEntity==''||$scope.country==''||$scope.branch==''||$scope.product==''||$scope.currency==''||$scope.book==''||$scope.productSwiftID=='')
		{	
			alert("Enter all the fields");
			//$scope.callGl();
		}
		else
		{
			var url=BASE_PATH +'/ACM/addCOAContoller';
			$http({url:url,method:"POST",params:{'head':$scope.head,'legalEntity':$scope.legalEntity,'country':$scope.country,'branch':$scope.branch,'product':$scope.product,'currency':$scope.currency,'book':$scope.book,'productSwiftID':$scope.productSwiftID}}).success(function (data) {
	           // $scope.glList = data;
	            console.log(data);  
	            //alert("Chart Of Accounts Added");
	            window.location = "#/addCoaResult";					
			});
			//$scope.callCoaList();
		}
	}
});

function getFormattedDate(input) {
    var pattern = /(.*?)-(.*?)-(.*?)$/;
    var result = input.replace(pattern,function(match,p3,p1,p2){
        var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
        return p2 + "-" + months[(p1-1)] + "-" + p3%100;
    });
    return result;
}