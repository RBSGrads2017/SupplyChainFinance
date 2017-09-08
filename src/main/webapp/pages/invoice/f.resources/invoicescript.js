function myFunction() {
   			    var x = document.getElementById('demo');
   			    if (x.style.display === 'none') {
   			        x.style.display = 'block';
   			    } else {
   			        x.style.display = 'none';
   			    }
  }
   function myFunction1() {
   			    var x = document.getElementById('demo1');
   			    if (x.style.display === 'none') {
   			        x.style.display = 'block';
   			    } else {
   			        x.style.display = 'none';
   			    }
  	}
   function myFunction2() {
		    var x = document.getElementById('demo2');
		    if (x.style.display === 'none') {
		        x.style.display = 'block';
		    } else {
		        x.style.display = 'none';
		    }
}
 
  function toggleview(){
	  var x = document.getElementById('invoicelist');
	  var y = document.getElementById('view');
	  var z = document.getElementById('view1');
		    if (y.style.display === 'none') {
		        x.style.display = 'none';
		        z.style.display = 'none';
		        y.style.display = 'block';
		    } else {
		    	x.style.display = 'block';
		        y.style.display = 'none';
		        z.style.display = 'none';
		    }
}
  function toggleview1(){
	  var x = document.getElementById('invoicelist');
	  var y = document.getElementById('view');
	  var z = document.getElementById('view1');
		    if (z.style.display === 'none') {
		        x.style.display = 'none';
		        y.style.display = 'none';
		        z.style.display = 'block';
		    } else {
		    	x.style.display = 'block';
		        y.style.display = 'none';
		        z.style.display = 'none';
		    }
}
  function toggleview3(){
	  var x = document.getElementById('invoicelist');
	  var y = document.getElementById('view');
	  var z = document.getElementById('view3');
		    if (z.style.display === 'none') {
		        x.style.display = 'none';
		        y.style.display = 'none';
		        z.style.display = 'block';
		    } else {
		    	x.style.display = 'block';
		        y.style.display = 'none';
		        z.style.display = 'none';
		    }
}
  
  function toggleview2(draft,approval,compliance,funding){
	  var x = document.getElementById('invoice');
	  var y = document.getElementById('view');
	  var z = document.getElementById('buttongroup');
	  var draftbutton = document.getElementById('draftbutton');
	  var compliancebutton = document.getElementById('compliancebutton');
	  var viewbutton = document.getElementById('viewbutton');
	  //console.log(draft);
	  if (draft==1)
		  draftbutton.style.display='block';
	  if (draft==0 && approval==1 && compliance==0)
	  	  compliancebutton.style.display='block';
	  if (draft==0 && approval==1 && compliance==1)
	  		viewbutton.style.display='block';
	  if (y.style.display === 'none') {
		        x.style.display = 'none';
		        y.style.display = 'block';
		       z.style.display = 'block';
		    } else {
		    	x.style.display = 'block';
		        y.style.display = 'none';
		        z.style.display = 'none';
		    }
}
  function getStatus(x){
	  if(x==0)
		  {
		  	document.getElementById("status").innerHTML="Draft";
		  }
	  else if(x==1)
	  {
	  	document.getElementById("status").innerHTML="Approved";
	  }
	  else
	  {
	  	document.getElementById("status").innerHTML="Rejected";
	  }
  }
  var invoiceApp = angular.module('invoiceApp', ["ngRoute"]);
	invoiceApp.config(function ($routeProvider){
		$routeProvider
		.when('/InvoiceLanding', {
		templateUrl : 'InvoiceLanding.html'
		})
		.when('/InvoiceCreate', {
		templateUrl : 'InvoiceCreate.html'
		})
		.when('/InvoiceView', {
		templateUrl : 'InvoiceView.html'
		})
		.when('/InvoiceUpdate', {
		templateUrl : 'InvoiceUpdate.html'
		})
		.when('/InvoiceDelete', {
		templateUrl : 'InvoiceDelete.html'
		})
		.when('/InvoiceSearch', {
		templateUrl : 'InvoiceSearch.html',
	
		})
		.when('/InvoiceDeleteConfirm', {
		templateUrl : 'InvoiceDeleteConfirm.html',
	
		})
	});

	invoiceApp.controller('mainController', function($scope, $http){
		/*
		 * 
		 * $scope.SessionUsername  =  "";
	    $scope.SessionUsertype  =  ""; 
	   
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
	    	    
	    	}
	    	
	    }, function(response) {
	    	console.log("couldnot load data");
	    });
		*
		*/
		
		
        $scope.deletefunc = function () {
        console.log("on delete function ");
        	var billBookNo = $scope.billBookNo;
        	console.log(billBookNo);
			/*  view Invoice  */
			 $http.post('http://localhost:8181/scm/invoice/searchInvoice?billBookNo='+$scope.billBookNo+'&sellerid='+$scope.sessionid).success(function(data) {
				console.log( "in search for delete http");
				$scope.invoice = data;
                console.log($scope.invoice);
                $http.post('http://localhost:8181/scm/invoice/viewProduct/?id=' + $scope.invoice.invoiceID)
    			.success(function (data) {
                    $scope.productslist = data;
                    console.log($scope.productslist);
                    });
                window.location = "#/InvoiceDeleteConfirm";
            });
			/*  view product  */
            
                
		}
        $scope.createInvoiceFunc = function() {

            console.log("in Add Invoice function ");
            console.log($scope.invoice.invoiceID);
            console.log($scope.billBookNo);
            console.log($scope.selectedContract);
            console.log($scope.contract.buyerID);
            console.log($scope.contract.sellerID);
            console.log($scope.paymentDate.split(' ')[0].split('-').join('/'));
            console.log($scope.invoiceDueDate.split(' ')[0].split('-').join('/'));
            console.log($scope.selectedProduct);
            console.log($scope.quantity);

            /*  view Invoice  */
            $http.post('http://localhost:8181/scm/invoice/addInvoice?invoiceID=' + $scope.invoice.invoiceID + "&productID=" + $scope.selectedProduct + "&quantity=" + $scope.quantity + "&contractID=" + $scope.selectedContract + "&sellerID=" + $scope.contract.sellerID + "&buyerID=" + $scope.contract.buyerID + "&senderID=" + $scope.contract.sellerID + "&receiverID=" + $scope.contract.buyerID + "&billbookNo=" + $scope.billBookNo + "&paymentDate=" +$scope.paymentDate.split(' ')[0].split('-').join('/') + "&invoiceDueDate=" +$scope.invoiceDueDate.split(' ')[0].split('-').join('/')).success(function(data) {
                console.log("in Add page After Add service");
                $scope.status = data;
                console.log($scope.message);
                alert("Invoice Addition Status: " + $scope.status.message);
                window.location = "#/InvoiceUpdate";
            });
        }
        $scope.getInvoiceNofunc=function() {
    		
    		console.log("inside get invoice no. function");
    		$http.get('http://localhost:8181/scm/invoice/getNextInvoiceNo').success(function(data) {
                console.log("in get invoice no.http");
                $scope.invoice = data;
                console.log($scope.invoice);
            });
    		$http.post('http://localhost:8181/scm/invoice/getContractNos?sellerID='+$scope.sessionid).success(function(data) {
                console.log("in get contract no.");
                $scope.contractslist = data;
                console.log($scope.contractslist);
    		});
            //window.location = "#/InvoiceCreate";
           
    	}
        
        $scope.getContractDetailsfunc=function(selectedContract) {
    		console.log("in get contract details function");
    		$http.post('http://localhost:8181/scm/invoice/getContractNo?contractID='+selectedContract).success(function(data) {
                console.log("in get contracthttp");
                $scope.contract = data;
                console.log($scope.contract);
            });
    		$http.post('http://localhost:8181/scm/invoice/getContractItems?contractID='+selectedContract).success(function(data) {
                console.log("in get contract items http");
                $scope.productlist = data;
                console.log($scope.productlist);
            });
    		window.location = "#/InvoiceCreate";
    	}
        $scope.getproductsinInvfunc=function() {
    		console.log("in get contract details function");
    		$http.post('http://localhost:8181/scm/invoice/getContractNo?contractID='+$scope.invoice.contractID).success(function(data) {
                console.log("in get contracthttp");
                $scope.contract = data;
                console.log($scope.contract);
            });
    		window.location = "#/InvoiceCreate";
    	}
        
        $scope.getProductDetailsfunc=function(selectedProduct) {
    		console.log("in get product details function");
    		$http.post('http://localhost:8181/scm/invoice/getProductDetails?productID='+selectedProduct).success(function(data) {
                console.log("in get product http");
                $scope.product = data;
                console.log($scope.product);
            });
    		/*$http.post('http://localhost:8181/scm/invoice/getitemDetails?productID='+selectedProduct+'&quantity='+$scope.quantity).success(function(data) {
                console.log("in get contract items http");
                $scope.item = data;
                console.log($scope.item);
            });*/
    		window.location = "#/InvoiceCreate";
    	}
        
        $scope.searchfunc = function () {
            
        	console.log("in search function ");
    			var invoiceNo = $scope.invoiceNo;
    			console.log(invoiceNo);
    			/*  view Invoice  */
    			$http.post('http://localhost:8181/scm/invoice/searchInvoice?invoiceID='+invoiceNo).success(function (data) {
    				console.log( "in search page after service");
    				$scope.invoice = data;
                    console.log($scope.invoice);
                    window.location = "#/InvoiceSearch";
                });
    			/*  view product  */
                $http.post('http://localhost:8181/scm/invoice/viewProduct/?id=' + invoiceNo)
    			.success(function (data) {
                    $scope.productslist = data;
                    console.log($scope.productslist);
                });
    	}
      
        $scope.updateSearchfunc = function () {
            
        	 console.log("in search function ");
             var billBookNo = $scope.billBookNo;
             console.log(billBookNo);
             /*  view Invoice  */
             $http.post('http://localhost:8181/scm/invoice/searchInvoice?billBookNo='+$scope.billBookNo+'&sellerid='+$scope.sessionid).success(function(data) {
                 console.log("in updat after Search http");
                 $scope.invoice = data;
                 console.log($scope.invoice);
                 $http.post('http://localhost:8181/scm/invoice/viewProduct/?id=' +$scope.invoice.invoiceID)
                 .success(function(data) {
                     $scope.productslist = data;
                     console.log($scope.productslist);
                     console.log("in get produt dropdown function");
                     $http.post('http://localhost:8181/scm/invoice/getContractItems?contractID='+$scope.invoice.contractID).success(function(data) {
                         console.log("in get contract items http");
                         $scope.productlist = data;
                         console.log($scope.productlist);
                     });
                 });
                 window.location = "#/InvoiceUpdate";
             });
             /*  view product  */
             
    	}
        $scope.addItemFunc = function () {
            
        	console.log("in addItem function ");
    			var invoiceNo = $scope.invoice.invoiceID;
    			console.log('invoice: ' + $scope.invoice.invoiceID);
    			console.log('Product id: ' + $scope.invoice.product_id);
    			console.log('quantity: ' + $scope.quantity);
    		
    			/*  Add New product  */
                $http.post('http://localhost:8181/scm/invoice/addItems?invoiceID='+$scope.invoice.invoiceID+'&productID='+ $scope.selectedProduct+'&quantity='+ $scope.quantity)
    			.success(function (data) {
    				alert("Added new Item");
    				$http.post('http://localhost:8181/scm/invoice/viewProduct/?id=' + $scope.invoice.invoiceID)
        			.success(function (data) {
                        $scope.productslist = data;
                        console.log($scope.productslist);
                    });
    				$http.post('http://localhost:8181/scm/invoice/searchInvoice?billBookNo='+$scope.invoice.billBookNo+'&sellerid='+$scope.sessionid).success(function(data) {
        				console.log( "in updat after Search http");
        				$scope.invoice = data;
                        console.log($scope.invoice);
                        window.location = "#/InvoiceUpdate";
                    });
        			/*  view product  */
                    $http.post('http://localhost:8181/scm/invoice/viewProduct/?id=' + $scope.invoice.invoiceID)
        			.success(function (data) {
                        $scope.productslist = data;
                        console.log($scope.productslist);
                    });
                    $scope.productID="";
                    $scope.quantity="";
                });
                
    	}
        
        $scope.deleteItemFunc = function () {
            
        	console.log("in update function ");
    			var invoiceNo = $scope.invoice.invoiceID;
    			console.log('invoice: ' + $scope.invoice.invoiceID);
    			console.log('Product id: ' + $scope.delProductId);
    			
    			/*  view Invoice  
    			$http.post('http://localhost:8181/invoice/searchInvoice?invoiceID='+invoiceNo).success(function (data) {
    				console.log( "in updat after Search http");
    				$scope.invoice = data;
                    console.log($scope.invoice);
                });*/
    			/*  delete product  */
              $http.post('http://localhost:8181/scm/invoice/deleteItem?invoiceID='+$scope.invoice.invoiceID +'&productID=' + $scope.delProductId)
    			.success(function (data) {
    				alert(" deleted item with product id= "+$scope.delProductId+ "Item");
    				$http.post('http://localhost:8181/scm/invoice/viewProduct/?id=' + $scope.invoice.invoiceID)
        			.success(function (data) {
                        $scope.productslist = data;
                        console.log($scope.productslist);
                    });
    				$http.post('http://localhost:8181/scm/invoice/searchInvoice?billBookNo='+$scope.invoice.billBookNo+'&sellerid='+$scope.sessionid).success(function(data) {
        				console.log( "in updat after Search http");
        				$scope.invoice = data;
                        console.log($scope.invoice);
                        window.location = "#/InvoiceUpdate";
                    });
        			/*  view product  */
                    $http.post('http://localhost:8181/scm/invoice/viewProduct?id=' + $scope.invoice.invoiceID)
        			.success(function (data) {
                        $scope.productslist = data;
                        console.log($scope.productslist);
                    });});
    	}
        $scope.deleteresultfunc = function () {
    		console.log('go to delete result page');
    		 $http.post('http://localhost:8181/scm/invoice/deleteInvoice?invoiceID=' + $scope.invoice.invoiceID)
    			.success(function (data) {
                    $scope.message = data;
                    console.log(data);
                    });
            }
        
		$scope.landingfunc = function () {
		console.log('go to landing page');
		window.location = "#/InvoiceLanding";
        }
	
	 
		
		

		$scope.receivedInvoicefunc = function () {
			console.log('inside received invoice');
      		$http.post('http://localhost:8181/scm/invoice/ReceivedInvoices?buyerID=' + $scope.sessionid)
			.success(function (data) {
				console.log("inside http");
                $scope.invoicelist = data;
                console.log( $scope.invoicelist);
                });
      		$scope.message="Click to view received invoices";
      		window.location = "#/InvoiceLanding";
        }
		$scope.viewapproved = function () {
			console.log('inside view approved invoice');
      		$http.post('http://localhost:8181/scm/invoice/approvedInvoices?sellerID=' + $scope.sessionid)
			.success(function (data) {
				console.log("after the req is hit");
                $scope.invoicelist2 = data;
                console.log( $scope.invoicelist2);
                });
      		$scope.message="Click to view Approved invoices";
      		window.location = "#/InvoiceLanding";
        }
		
		$scope.sentInvoicefunc = function () {
			console.log('inside sent invoice');
      		$http.post('http://localhost:8181/scm/invoice/SentInvoices?sellerID=' + $scope.sessionid)
			.success(function (data) {
				console.log("inside http");
                $scope.invoicelist1 = data;
                console.log($scope.invoicelist1);
                });
      		$scope.message1="Click to view sent invoices";
      		window.location = "#/InvoiceLanding";
        }
		$scope.viewfunc = function (billbookNo,invoiceNo) {
			console.log('inside view invoice');
			$http.post('http://localhost:8181/scm/invoice/searchInvoicebill?billBookNo='+billbookNo).success(function(data) {
				console.log( "in search page after service");
				$scope.invoice = data;
                console.log($scope.invoice);
            });
			/*  view product  */
            $http.post('http://localhost:8181/scm/invoice/viewProduct/?id=' + invoiceNo)
			.success(function (data) {
                $scope.productslist = data;
                console.log($scope.productslist);
            });
            window.location = "#/InvoiceLanding";
	}
		
		$scope.viewfunc1 = function (billbookNo,invoiceNo) {
			console.log('inside view invoice');
			$http.post('http://localhost:8181/scm/invoice/searchInvoicebill?billBookNo='+billbookNo).success(function(data) {
				console.log( "in search page after service");
				$scope.invoice = data;
                console.log($scope.invoice);
                
            });
			/*  view product  */
            $http.post('http://localhost:8181/scm/invoice/viewProduct/?id=' + invoiceNo)
			.success(function (data) {
                $scope.productslist = data;
                console.log($scope.productslist);
            });
            window.location = "#/InvoiceLanding";
	}
		$scope.availfinance = function (invoiceNo) {
			console.log('inside avail finance invoice');
			$http.post('http://localhost:8181/scm/invoice/availFinance?invoiceID='+invoiceNo).success(function (data) {
				console.log( "in avail finance page after service");
				$scope.status = "You have sent invoice for approval for invoice no:"+invoiceNo;
                console.log($scope.status);  
                alert($scope.status);
                
            });
            window.location = "#/InvoiceLanding";
	}
		$scope.approvefunc = function (invoiceNo) {
			console.log('inside approve invoice');
			$http.post('http://localhost:8181/scm/invoice/approveInvoice?invoiceID='+invoiceNo).success(function (data) {
				console.log( "in approve page after service");
				$scope.status = "You have approved invoice no:"+invoiceNo;
                console.log($scope.status);  
                
            });
			
            window.location = "#/InvoiceLanding";
	}
		$scope.rejectfunc = function (invoiceNo) {
			console.log('inside approve invoice');
			$http.post('http://localhost:8181/scm/invoice/rejectInvoice?invoiceID='+invoiceNo).success(function (data) {
				console.log( "in reject page after service");
				$scope.status = "You have rejected invoice no:"+invoiceNo;
                console.log($scope.status);  
                
            });
			
            window.location = "#/InvoiceLanding";
	}
		$scope.savedraftfunc = function () {
			console.log('inside save as draft invoice');
			
			/*$http.post('http://localhost:8181/invoice/rejectInvoice?invoiceID='+invoiceNo).success(function (data) {
				console.log( "in reject page after service");
				$scope.status = "You have rejected invoice no:"+invoiceNo;
                console.log($scope.status);  
                
            });
			*/
			$scope.draftstatus = "You have saved the invoice as draft";
			//windows.alert("You have saved the invoice as draft");
            window.location = "#/InvoiceLanding";
	}
		$scope.sendtoapproval = function () {
			console.log('inside send to approval invoice');
			
			/*$http.post('http://localhost:8181/invoice/rejectInvoice?invoiceID='+invoiceNo).success(function (data) {
				console.log( "in reject page after service");
				$scope.status = "You have rejected invoice no:"+invoiceNo;
                console.log($scope.status);  
                
            });
			*/$scope.draftstatus = "You have sent the invoice for approval";
			//windows.alert("You have saved the invoice as draft");
            window.location = "#/InvoiceLanding";
	}
		$scope.viewInvoicefunc = function () {
            
        	console.log("in view invoice function ");
    			var invoiceID = $scope.invoiceID;
    			console.log(invoiceID);
    			/*  view Invoice  */
    			$http.post('http://localhost:8181/scm/invoice/searchInvoice?invoiceID='+invoiceID).success(function (data) {
    				console.log( "in view page after service");
    				$scope.invoice = data;
                    console.log($scope.invoice);
                    
                });
    			/*  view product  */
                $http.post('http://localhost:8181/scm/invoice/viewProduct/?id=' + invoiceID)
    			.success(function (data) {
                    $scope.productslist = data;
                    console.log($scope.productslist);
                });
                $scope.message="Click to view invoice";
                window.location = "#/InvoiceView";
    	}
		$scope.statusfunc = function (draft,approval,compliance,funding) {
            
        	console.log("in status function ");
    		toggleview2(draft,approval,compliance,funding);
    	}
		$scope.compliancefunc=function(buyerID){
			console.log("in complaince");
			if (buyerID=123){
				$scope.message="Your invoice from BUYER="+buyerID+"has passed the complaince Check!!!";
			}
			else {
				$scope.message="Your invoice from BUYER="+buyerID+"has failed the complaince Check!!!";
			}
			alert($scope.message);
		}
      
	});
	