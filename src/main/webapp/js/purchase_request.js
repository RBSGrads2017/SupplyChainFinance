var app = angular.module('purchase_request', []);
app.controller('purchase_request_Ctrl', function($scope,$location,$window,$http) {
    $scope.isSellerReviewed= true;
    $scope.Buyer="Air Bus";
    $scope.Seller="Tata Steel";
    $scope.Status;
    $scope.DueDate;
    $scope.PaymentDate;
    $scope.FinancingInstitution="RBS";
    $scope.FinancingId= Math.floor(Math.random() * 100);
    $scope.InvestedAmount=0;
    $scope.DiscountRate;
    $scope.Fees=2000;
    $scope.NetPayable=$scope.Fees + $scope.InvestedAmount;
    var IDs;
    
    $scope.table_data;
//    =[{InvoiceDate:'12-10-2017', InvoiceDueDate:'17-11-2017', PaymentDueDate:'21-11-2017', InvestedAmount:'20000', Status:'Pending', PaymentStatus:'Due'},
//        {InvoiceDate:'12-10-2017', InvoiceDueDate:'17-11-2017', PaymentDueDate:'21-11-2017', InvestedAmount:'78000.00', Status:'Pending', PaymentStatus:'Due'}];
    
    /* $http.get("customers.php").then(function (response) {
        $scope.table_data = response.data.records;
        }); */
    

    $scope.checked= function(){
        var count= 0;
        var datedue= 0;
        var datepayment= 0;
        $scope.PaymentDate=datepayment;
        $scope.InvestedAmount=0;
        count= $("input:checkbox").length;
        $scope.DueDate=datedue;
        for(var i=1; i<count;i++){
            
            if($("input:checkbox")[i].checked){
                $scope.InvestedAmount+= parseInt($scope.table_data[i-1].invoiceAmount);
                console.log("date "+ $scope.table_data[i-1].invoiceDueDate);
                var d1= new Date($scope.table_data[i-1].invoiceDueDate).getTime();
                var d2= new Date($scope.table_data[i-1].paymentDueDate).getTime();
                console.log(d1);
                if( d1> datedue){
                    datedue= d1;
                    $scope.DueDate= $scope.table_data[i-1].invoiceDueDate;
                }
                console.log(d2);
                if( d2> datepayment){
                    datepayment= d2;
                    $scope.PaymentDate= $scope.table_data[i-1].paymentDueDate;
                }
                IDs.push($scope.table_data[i-1].invoiceID);
            }
            $scope.NetPayable=parseInt($scope.Fees) + (((100-parseInt($scope.DiscountRate))/100)* parseInt($scope.InvestedAmount));
            //console.log("date"+ datedue);
        }
    }
    
    $scope.search= function(){
        var buyer= $scope.Buyer;
        var seller= $scope.Seller;
        $http.get("http://localhost:8181/jersey-heroku-webapp/webapi/invoice").then(function(response){
            console.log(response.data);
            $scope.table_data= response.data;
            $scope.DiscountRate=3;
        });
    }
    
    $scope.submitPR= function(){
        $window.location.href = 'http://localhost:8181/jersey-heroku-webapp/pages/ListofPO.html';
        var Indata= {'Buyer': $scope.Buyer, 'Seller': $scope.Seller, 'DueDate': $scope.DueDate, 'PaymentDate': $scope.PaymentDate,'FinancingInstitution': $scope.FinancingInstitution,'ContractID': $scope.FinancingId, 'InvestedAmount': $scope.InvestedAmount, 'DiscountRate': $scope.DiscountRate, 'Fees': $scope.Fees=2000, 'NetPayable': $scope.NetPayable, 'InvoiceID': IDs};
        $http.post("http://localhost:8181/jersey-heroku-webapp/webapi/createPO",Indata).then(function(){
            
        });    
    }
    $scope.processPR=function(){
        $http.get("http://localhost:8181/jersey-heroku-webapp/webapi/processPO").then(function(response){
            console.log(response.data);
            $scope.DiscountRate= response.data;
        });
    }
    
    $scope.accepted= function(){
        $window.location.href = 'http://localhost:8181/jersey-heroku-webapp/pages/SorryPage.html';
    }
    
    $scope.rejected= function(){
        $window.location.href = 'http://localhost:8181/jersey-heroku-webapp/pages/SorryPage.html';
    }
    
    $scope.loadPRSeller= function(){
        $http.get("").then(function(response){
            console.log(response.data);
        });
    }
});