angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/market/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
                 console.log(response);
            });
    };

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:8189/market/api/v1/products/' + id)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.get('http://localhost:8189/market/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.loadProducts();

            });
    }
    $scope.addToCart=function(id){

        $http.get('http://localhost:8189/market/api/v1/cart/add/' + id).then(function(response){
            $scope.loadCart();
        });

    }
    $scope.loadCart =function (){
        $http.get('http://localhost:8189/market/api/v1/cart').then(function (response){
            $scope.cart = response.data;
            console.log(response);
        });
    }



     $scope.loadProducts();
    $scope.loadCart();
});