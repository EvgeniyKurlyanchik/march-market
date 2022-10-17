angular.module('market', []).controller('indexController', function ($scope, $http) {
        $scope.loadProducts = function () {
            $http.get('http://localhost:8189/market/api/v1/products').then(function (response) {
                $scope.productList = response.data;
                console.log(response);
            });
        }

        $scope.deleteProduct = function (id) {
            $http.delete('http://localhost:8189/market/api/v1/products/' + id)
                .then(function (response) {
                    $scope.loadProducts();
                });
        }

        $scope.createNewProduct = function () {
            console.log($scope.newProduct);
            $http.get('http://localhost:8189/market/api/v1/products', $scope.newProduct)
                .then(function (response) {
                    $scope.newProduct = null;
                    $scope.loadProducts();

                });
        }
        $scope.addToCart = function (productId) {
            console.log($scope.loadCart());
            $http.get('http://localhost:8189/market/api/v1/cart/add/'+productId).then(function (response) {
                $scope.loadCart();
            });

        }
        $scope.removeFromCart = function (productId) {
            console.log($scope.loadCart());
            $http.get('http://localhost:8189/market/api/v1/cart/remove/' + productId).then(function (response) {
                $scope.loadCart();
            });

        }
        $scope.clearCart = function () {
            console.log($scope.loadCart());
            $http.get('http://localhost:8189/market/api/v1/cart/clear/').then(function (response) {
                $scope.loadCart();
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