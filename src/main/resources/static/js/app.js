var petManagerModule = angular.module('petManagerApp', ['ngAnimate']).config(['$routeProvider',
                                                                              function($routeProvider) {
    $routeProvider.
      when('/home', {
        templateUrl: 'index.html',
        controller: 'petManagerController'
      }).
      when('/pet/details', {
        templateUrl: 'pet-details.html',
        controller: 'petManagerController'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);;

petManagerModule.controller('petManagerController', function ($scope,$http) {
	
	var urlBase="";
	$scope.toggle=true;
	$scope.selection = [];
	$scope.statuses=['available','reserved'];
	$scope.categories=['Dog','Cat','Other'];
	$http.defaults.headers.post["Content-Type"] = "application/json";

    function findAllPets() {
        //get all tasks and display initially
        $http.get(urlBase + '/pets/search/findByStatus?status=available').
            success(function (data) {
                if (data._embedded != undefined) {
                    $scope.pets = data._embedded.pets;
                } else {
                    $scope.pets = [];
                }
                $scope.Name="";
                $scope.Category="";
                $scope.Status="";
                $scope.toggle='!toggle';
            });
    }

    findAllPets();

	//add a new task
	$scope.addPet = function addPet() {
		if($scope.Name=="" || $scope.Category == "" || $scope.Status == ""){
			alert("Insufficient Data! Please provide values for pet name, category and status");
		}
		else{
		 $http.post(urlBase + '/pets', {
             name: $scope.Name,
             category: $scope.Category,
             status: $scope.Status
         }).
		  success(function(data, status, headers) {
			 alert("Pet added");
             var newPetUri = headers()["location"];
             console.log("Might be good to GET " + newPetUri + " and append the Pet.");
             // Refetching EVERYTHING every time can get expensive over time
             // Better solution would be to $http.get(headers()["location"]) and add it to the list
             findAllPets();
		    });
		}
	};
	
	$scope.deletePet = function deletePet(id) {
		$http.delete(urlBase + '/pets/' + id ).
		success(function(data, status, headers) {
			alert("Pet deleted");
            var newPetUri = headers()["location"];
            console.log("Might be good to GET " + newPetUri + " and append the Pet.");
            // Refetching EVERYTHING every time can get expensive over time
            // Better solution would be to $http.get(headers()["location"]) and add it to the list
            findAllPets();
		});
	}
			
});

//Angularjs Directive for confirm dialog box
petManagerModule.directive('ngConfirmClick', [
	function(){
         return {
             link: function (scope, element, attr) {
                 var msg = attr.ngConfirmClick || "Are you sure?";
                 var clickAction = attr.confirmedClick;
                 element.bind('click',function (event) {
                     if ( window.confirm(msg) ) {
                         scope.$eval(clickAction);
                     }
                 });
             }
         };
 }]);