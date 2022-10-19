angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.loadProducts = function () {
        $http.get('http://localhost:8189/market/api/v1/products').then(function (response) {
            $scope.products = response.data;
        });
        }

        $scope.deleteProduct = function (id) {
            $http.delete('http://localhost:8189/market/api/v1/products/' + id)
                .then(function (response) {
                    $scope.loadProducts();
                });
        }

        $scope.createNewProduct = function () {
            $http.post('http://localhost:8189/market/api/v1/products/', $scope.newProduct)
                .then(function (response) {
                    console.log($scope.newProduct);
                    $scope.newProduct=null;
                    $scope.loadProducts();

                });
        }
        $scope.addToCart = function (id)  {
            $http.get('http://localhost:8189/market/api/v1/cart/add/'+ id).then(function (response) {
                 console.log($scope.loadCart());
                $scope.cart=response.data;
            });

        }
    $scope.removeFromCart = function (productId) {
            console.log($scope.loadCart());
            $http.get('http://localhost:8189/market/api/v1/cart/remove/' + productId).then(function () {
                $scope.loadCart();
            });

        }
        $scope.clearCart=function() {
            console.log($scope.clearCart());
            $http.get('http://localhost:8189/market/api/v1/cart/clear/').then(function () {
                $scope.loadCart();
                $scope.loadCart(null);
            });

        }
        $scope.loadCart = function () {
            $http.get('http://localhost:8189/market/api/v1/cart/').then(function (response) {
                $scope.cart = response.data;
                console.log(response);
            });
        }
        $scope.loadCart();
        $scope.loadProducts();

    });
