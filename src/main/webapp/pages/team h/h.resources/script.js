var myApp = angular.module('myApp', ["ngRoute"]);
		myApp.config(function ($routeProvider){
		console.log("page1");
		$routeProvider
		.when('/', {
		redirectTo : 'index.html'
		})
		.when('/view_table', {
		templateUrl : 'view_table.htm'
		})
		.when('/features', {
		templateUrl : 'features.htm'
		})
		.when('/view_stats', {
		templateUrl : 'view_stats.htm'
		})
		.when('/addsubmission', {
		templateUrl : 'addsubmission.htm'
		})
		.when('/viewaddresp', {
		templateUrl : 'viewaddresp.htm'
		})
		.when('/nostatus', {
		templateUrl : 'nostatus.htm'
		})
		.when('/nostatus2', {
		templateUrl : 'nostatus2.htm'
		})
		.when('/buyer', {
		templateUrl : 'buyerdetails.htm'
		})
		.when('/payanddelivery', {
		templateUrl : 'payanddelivery.htm'
		})
	});
	
	myApp.controller('myController', function($scope,$rootScope, $http){	
		
		/*function to fetch request for proposals*/
		/*function to fetch request for proposals*/
	    $scope.SessionUsername  =  "";
	    $scope.SessionUsertype  =  ""; 
	    $scope.seller_id = ""; 
	    var promise = $http({
	        url: BASE_PATH + '/service/login/getUserInfo',
	        method: 'POST',
	        headers: { 'Content-Type': 'text/plain' }
	    });
	    promise.then(function (response) {
	    	console.log(response.data);
	    	
	    	if (response.data == "no session") {
	    		$window.location.href = './HomePage.html'
	    	} else {
	    		
	    	    $scope.SessionUsertype  =  response.data.userType; 
	    	    $scope.SessionUsername  =  response.data.fullName;
	    	    $scope.seller_id = response.data.usernameInt;
	    	}
	    	
	    }, function(response) {
	    	console.log("couldnot load data");
	    });
	    
	    
		$scope.myfunc = function () {
			console.log("on click function 1");
			var x = $scope.seller_id;
			console.log(x);
			$http.post(BASE_PATH + '/contractmanagementseller/viewrfp/' + x)
			.success(function (data) {
                $scope.proposals = data;
                console.log(data);
                $rootScope.proposals = data;
                if(data==[]){
                	window.alert('you do not have any new/pending proposals!!');
                	window.location="#/nostatus"
                	/*var b='no new or pending proposals found';
                	console.log(b);
                	document.getElementById("proposals").innerHTML=b;*/
                }
                else
                	{
                console.log("RS: " + $rootScope.proposals);
                window.location = "#/view_table";
                	}
			});			
		}
		$scope.getBuyerDetails = function(i){
			
			var p=i.proposal_id;
			console.log("bd:pid" + p);
			$rootScope.proposal_id_for_buyerdet = p;
			console.log("bd:pid" + p + "l" +$rootScope.proposal_id_for_buyerdet);
			window.location = "#/buyer"
		}
		$scope.getPaymentDetails = function(i){
			var p=i.proposal_id;
			$rootScope.proposal_id_for_pd = p;
			console.log("bd:pid" + p + "l" +$rootScope.proposal_id_for_pd);
			window.location = "#/payanddelivery"
		}
		
		$scope.getFeatures = function(i) { 
			
			var prop_id=i.proposal_id;
			console.log("get features of proposal_id clicked: " + prop_id);
				$rootScope.proposal_id = prop_id;
				console.log("prop_id made as root scope :" + $rootScope.proposal_id);	
		        window.location = "#/features";
		}
		/*function to get status of accepted proposals*/
		
		$scope.myfuncstats = function () {
        	
			console.log("on click function 3");
			var x2 = $scope.seller_id;
			console.log(x2);
			$http.post(BASE_PATH + '/contractmanagementseller/fetchbuyerStatus/' + x2)
			.success(function (data) {
                $scope.stats = data;                    
                console.log(data);
                if(data==[]){
                	window.alert('you do not have any accepted proposals !!!!');
                	window.location="#/nostatus2"
                }
                else
                {
				    window.location = "#/view_stats";
                }
			});
		}   
		/*function to send additional response
		*/
		$scope.addNew = function(){
			console.log("on click: Add another additional feature");
			$scope.prod_id = '';
			$scope.specification = '';
			$scope.addresponse();
		}
		$scope.addresponse = function () {   
			console.log("on click function 4");
			var s = $scope.seller_id;
			console.log("S:" +s);
			var p= $scope.prod_id;
			console.log("P:" +p);
			var spec = $scope.specification;
			console.log("Spec:" + spec);
			if(p==undefined || p=='null')
				p='';
			if(spec==undefined || spec=='null')
				spec='';
			if(p=='' || spec=='')
				alert("Please enter valid Product ID and Specification");
			//var spec= new String('i can provide higher');
			$http.post(BASE_PATH + '/contractmanagementseller/addresponse/' +s+ '/' +p+ '/' +spec)
			.success(function (data) {
                $scope.stats = data;                    
                console.log('hi');
                alert("Product ID:" + p + "and Specification:" + spec + "has been added");
			});
		}   

	});
	
	myApp.controller("BDCtrl", function($scope,$rootScope, $http, $window) {
		
		 /*function to get Features for a given proposal id */
           
           console.log("Proposal Table Ctroller: on click function 2");	
			var y = $rootScope.proposal_id_for_buyerdet;
			
			console.log("proposal id=" +y);		
			console.log("seller id=" + $scope.seller_id);
	        $http.post(BASE_PATH + '/contractmanagementseller/buyerdetails/' +y)
	        .success(function (data) {
	            $scope.buyerdetails = data;
	            console.log(data);
	        });
	});
	myApp.controller("PandDCtrl", function($scope,$rootScope, $http, $window) {
	    		
	   		 /*function to get Features for a given proposal id */
	              
	              console.log("Proposal Table Ctroller: on click function 2");	
	   			var y = $rootScope.proposal_id_for_pd;
	   			
	   			console.log("proposal id=" +y);		
	   			console.log("seller id=" + $scope.seller_id);
	   	        $http.post(BASE_PATH + '/contractmanagementseller/payanddeliverydetails/' +y)
	   	        .success(function (data) {
	   	            $scope.p_and_d = data;
	   	            console.log(data);
	   	        });
	});
	        
	
	myApp.controller("AddRespCtrl", function($scope,$rootScope, $http, $window) {
		
		 /*function to get Features for a given proposal id */
           
           console.log("Add response Ctroller: on click function 6");	
			var y = $scope.seller_id;
			
			console.log("seller id=" +y);		
			//console.log("seller id=" + $scope.seller_id);
	        $http.post(BASE_PATH + '/contractmanagementseller/viewaddresp/' +y)
	        .success(function (data) {
	            $scope.addres = data;
	            console.log(data);
	            window.location="#/viewaddresp"
	        });
	        
	
	});
	
	myApp.controller("proposalTableCtrl", function($scope,$rootScope, $http, $window) {
		
		 /*function to get Features for a given proposal id */
            
            console.log("Proposal Table Ctroller: on click function 2");	
			var y = $rootScope.proposal_id;
			
			console.log("proposal id=" +y);		
			console.log("seller id=" + $scope.seller_id);
	        $http.post(BASE_PATH + '/contractmanagementseller/listfeatures/' +y)
	        .success(function (data) {
	            $scope.features = data;
	            console.log(data);
	            $rootScope.features = $scope.features;
	        });
	        
	        $scope.update = function (prodid, fid, resp, index) {        	
				console.log("radio button service");
				var s = $scope.seller_id;
				console.log("seller id:" +s);
				var p = prodid;
				console.log("product id:" +p);
				var f = fid;
				console.log("feature id:" + f);
				var r = resp;
				console.log("response:" + r);
				  if(resp == 'N'){
					  var text = "cost"+index;
					  var x = document.getElementById("cost"+index);
					  console.log(x);
					    if (x.style.display === 'block') {
					    	console.log("inside if");
					        x.style.display = 'none';
					    }
				  }
				  
				  var cos=document.getElementById("cost"+index).value;
				  console.log(cos);
				
				console.log(BASE_PATH + '/contractmanagementseller/updatesellerresponse/'
						+ $rootScope.proposal_id +'/' + p + '/' +f + '/' + s + '/' +r + '/' +cos);
				$http.post(BASE_PATH + '/contractmanagementseller/updatesellerresponse/'
						+ $rootScope.proposal_id +'/' + p + '/' +f + '/' + s + '/' +r +'/' +cos)
				.success(function (data) {                  
	                console.log('updated Radio Button and cost');
				});
			}   			
				$scope.showconfirmbox = function (status) {
				console.log("sampleCtrl");
				console.log("length:" + $rootScope.features.length);
				var s = $scope.seller_id;
				if(status=='A'){
					var x=$rootScope.features.length;
					var i;
					for(i=1;i<=x;i++) {
					var group = document.getElementsByName(i);
					console.log("group:" +group);
					console.log("GLen:" + group.length);
					for (var j=0; j<group.length; j++) {
					if (group[j].checked){
						console.log(group[j].value);
						break;
					}
					}
					if (j==group.length)
					return alert("No radio button is checked");
					}
					if($window.confirm("Do you want to accept the proposal?")){
						//$scope.result = "You accepted this proposal";
						var x = document.getElementById("addresp");
					    if (x.style.display === 'none') {
					    	console.log("inside if");
					        x.style.display = 'block';
					    }
					  /*  else {
					        x.style.display = 'none';
					    }*/
						var i=0;
						for(i=0;i<$rootScope.features.length; i++){
							console.log("rd val:" + [i].value);
						}
						console.log("seller id:" +s);
						$http.post(BASE_PATH + '/contractmanagementseller/updatebidsellerstatus/' +s+ '/' +$rootScope.proposal_id + '/'+ status)
						.success(function (data) {                 
			                console.log('updated Seller Status A');
						});
						
					}
				}
				if(status=='R'){
					if ($window.confirm("Do you want to reject the proposal?")){
						//$scope.result = "You accepted this proposal";
						$http.post(BASE_PATH + '/contractmanagementseller/updatebidsellerstatus/' +s+ '/' +$rootScope.proposal_id + '/'+ status)
						.success(function (data) {                 
			                console.log('updated Seller Status R');
						});
					}
					}
				if(status=='P'){
					if ($window.confirm("Do you want to see the proposal later?")){
						$http.post(BASE_PATH + '/contractmanagementseller/updatebidsellerstatus/' +s+ '/' +$rootScope.proposal_id + '/'+ status)
						.success(function (data) {                 
			                console.log('updated Seller Status P');
						});
					}
					
					}
				}
	});