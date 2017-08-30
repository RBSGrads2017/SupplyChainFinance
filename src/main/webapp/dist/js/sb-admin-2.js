/*!
 * Start Bootstrap - SB Admin 2 v3.3.7+1 (http://startbootstrap.com/template-overviews/sb-admin-2)
 * Copyright 2013-2016 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap/blob/gh-pages/LICENSE)
 */
$(function() {
    $('#side-menu').metisMenu();
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        var topOffset = 50;
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    // var element = $('ul.nav a').filter(function() {
    //     return this.href == url;
    // }).addClass('active').parent().parent().addClass('in').parent();
    var element = $('ul.nav a').filter(function() {
        return this.href == url;
    }).addClass('active').parent();

    while (true) {
        if (element.is('li')) {
            element = element.parent().addClass('in').parent();
        } else {
            break;
        }
    }
});




var testSession = angular.module("sessionApp", []);
testSession.controller("TestController", ["$scope", "$http", "$window", function($scope, $http, $window) {
	
    $scope.UserNameMod = "";
    $scope.PasswordMod = "";
    
    
    $scope.SessionUsername  =  "";
    $scope.SessionUsertype  =  ""; 
    
    var promise = $http({
        url: BASE_PATH + '/service/login/getUserInfo',
        method: 'POST',
        headers: { 'Content-Type': 'text/plain' }
    });
    promise.then(function (response) {
    	console.log(response);
    	
    	if (response.data == "no session") {
    		$window.location.href = './HomePage.html'
    	} else {
    		$scope.SessionUsername  =  response.data.username;
    	    $scope.SessionUsertype  =  response.data.userType; 
    		console.log(response.data.username);
        	console.log(response.data.userType);
    	}
    }, function(response) {
    	console.log("couldnot load data");
    });
    
    $scope.TestClick = function () {

        var promise = $http({
            url: 'http://localhost:8089/scm/service/login/checkCredentials',
            method: 'POST',
            headers: { 'Content-Type': 'text/plain' }
        });
        promise.then(function (response) {
        	console.log(response);
        	//var data = JSON.parse(response.data)
        	console.log(response.data.username);
        	console.log(response.data.userType);
        }, function(response) {
        	console.log("couldnot load data");
        });
        
    }

}]);





















