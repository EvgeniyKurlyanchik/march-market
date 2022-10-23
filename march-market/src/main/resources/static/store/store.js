angular.module('market').controller('storeController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };

    $scope.addProductToCart = function (productId) {
        $http.post('http://localhost:8189/market/api/v1/cart/add/' + productId, $localStorage.cartName)
            .then(function (response) {
            });
    };

    $scope.loadOrders = function () {
        $http.get(contextPath + '/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.loadProducts();
    $scope.loadOrders();
});