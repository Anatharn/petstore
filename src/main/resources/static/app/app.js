var app = angular.module('myApp', ['ngRoute']);
app.factory("services", ['$http', function($http) {
  var serviceBase = ''
    var obj = {};
    obj.getPets = function(){
        return $http.get(serviceBase + '/pets/search/findByStatus?status=available');
    }
    obj.getPet = function(id){
        return $http.get(serviceBase + '/pets/' + id);
    }

    obj.insertPet = function (pet) {
    return $http.post(serviceBase + '/pets', pet).then(function (results) {
        return results;
    });
	}
    obj.deletePet = function (pet) {
	    return $http.delete(serviceBase + '/pets/' + pet.id).then(function (status) {
	        return status.data;
	    });
	}
    obj.getCategories = function(){
        return $http.get(serviceBase + '/categories');
    };
    return obj;   
}]);

app.controller('listCtrl', function ($scope, $rootScope,  services, $location) {
	services.getPets().then(function(data){
		if( typeof data.data._embedded != 'undefined')
			$scope.pets = data.data._embedded.pets;
		else
			$scope.pets = {};
    });
    $scope.deletePet = function(pet) {
    	if(confirm("Are you sure to delete the pet named: "+pet.name)==true)
        services.deletePet(pet).then(function(data){
        	$rootScope.alert_text = "Pet " + pet.name + " has been deleted";
        	$rootScope.alert_raised = true;
        	$location.path('#/');        	
        });
        
      };
});

app.controller('editCtrl', function ($scope, $rootScope, $location, $routeParams, services) {
	$scope.selection = [];
	$scope.statuses=['available','reserved'];
	$scope.categories = [];
	services.getCategories().then( function( data ) {
		$scope.categories= data.data._embedded.categories		
	})
	$scope.savePet = function(pet) {
        services.insertPet(pet).then( function() {
            $location.path('#/');        	
        });
	}
        
});

app.controller('detailsCtrl', function ($scope, $rootScope, $location, $routeParams, services) {
	var id = $routeParams.param;
	services.getPet(id).then( function ( data ) {
		$scope.pet = data.data;
	});
	$scope.deletePet = function(pet) {
		if(confirm("Are you sure to delete the pet named: "+pet.name)==true)
        services.deletePet(pet).then(function(data){
        	$rootScope.alert_text = "Pet " + pet.name + " has been deleted";
        	$rootScope.alert_raised = true;
        });
        
      };
});


app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/', {
        title: 'Pet Store',
        templateUrl: 'partials/pet-list.html',
        controller: 'listCtrl'
      })
      .when('/pet-add', {
        title: 'Edit Pet',
        templateUrl: 'partials/pet-add.html',
        controller: 'editCtrl'

      })
      .when('/pet-details/:param', {
        title: 'Pet Details',
        templateUrl: 'partials/pet-details.html',
        controller: 'detailsCtrl'

      })
      .otherwise({
        redirectTo: '/'
      });
}]);
