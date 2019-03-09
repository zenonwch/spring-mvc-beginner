let cartApp = angular.module('cartApp', []);

cartApp.controller('cartController', function ($scope, $http) {
    let cartBaseUrl = '/webstore/rest/cart/';

    /**
     * Cart
     * @typedef {Object} Cart
     * @property {CartItem} cartItems
     * @property grandTotal
     *
     * CartItem
     * @typedef {Object} CartItem
     * @property productId
     * @property name
     * @property unitPrice
     * @property quantity
     * @property totalPrice
     */
    $scope.refreshCart = function (cartId) {
        $http.get(cartBaseUrl + cartId)
            .then(resp => {
                $scope.cart = resp.data;
            });
    };

    $scope.clearCart = function () {
        $http.delete(cartBaseUrl + $scope.cartId)
            .then(() => {
                $scope.refreshCart($scope.cartId);
            });
    };

    $scope.initCartId = function (cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart(cartId);
    };

    $scope.addToCart = function (productId, successMessage) {
        $http.put(cartBaseUrl + 'add/' + productId, null)
            .then(() => {
                let modalElt = document.getElementById('modal');
                let modalBody = document.getElementsByClassName('modal-body')[0].getElementsByTagName('p')[0];
                modalBody.innerHTML = successMessage;

                try {
                    // noinspection JSUnresolvedFunction
                    let modal = new Modal(modalElt);
                    modal.show();
                } catch (err) {
                    if (err.message === 'Modal is not defined') {
                        alert(successMessage);
                    }
                }
            });
    };

    $scope.removeFromCart = function (productId) {
        $http.put(cartBaseUrl + 'remove/' + productId, null)
            .then(() => {
                return $scope.refreshCart($scope.cartId);
            });
    };
});