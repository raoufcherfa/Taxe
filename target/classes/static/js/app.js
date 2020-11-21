var app = angular.module("MyApp", []);
app.controller("MyController", function ($scope, $http) {
    $scope.pageEntreprises = [];
    $http.get("http://localhost:8080/listEntreprises?page=1&size=3").then(function (response) {
        $scope.pageEntreprises = response.data;
    }, function myError(response) {
        $scope.pageEntreprises = response.statusText;})
});
