(function (ng) {

    var mod = ng.module("carritoModules");

    mod.controller("carroCtrl", ['$scope', '$state', '$stateParams', '$http', 'carritoContext', function ($scope, $state, $stateParams, $http, context) {


            $scope.carrito = {};
            $scope.precioTotal=0;
            $scope.currentUsuarioId=$stateParams.id; 
            $http.get(context + $stateParams.id + "/carroCompras").then(function (response) {
                $scope.carrito = response.data;
                var total=0;
                for (i = 0; i<$scope.carrito.vinilos.length;i++) {
                    var vinilo=$scope.carrito.vinilos[i];
                    var precio=vinilo.precio;
                    total+=precio;
                }
                $scope.precioTotal=total;
            });
            this.deleteVinilo = function (vinilo) {
                var decodedCookie = decodeURIComponent(document.cookie);
                var ca = decodedCookie.split('=');
                 $http.delete(context + ca[1] + "/carroCompras/" + vinilo.id)
                        .then(function () {
                            void(0);
                            $state.reload();
                        });
            }
        }]);
})(window.angular);