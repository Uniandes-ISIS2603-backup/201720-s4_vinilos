(function (ng) {

    var mod = ng.module("carritoModules");

    mod.controller("carroCtrl", ['$scope', '$state', '$stateParams', '$http', 'carritoContext', function ($scope, $state, $stateParams, $http, context) {


            $scope.carrito = {};

            $http.get(context+$stateParams.id+"/carroCompras").then(function (response) {
                $scope.carrito = response.data;
            });
            this.deleteVinilo = function(vinilo) {
                 return $http.delete(context + "/" + $stateParams.idUsuario+"/carroCompras"+$stateParams.idVinilo)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.usuarios.indexOf(vinilo);
                                if (index > -1) {
                                    $scope.usuarios.splice(index, 1);
                                }
                                 $state.go('carritoUsuario');
                            });
            }
        }]);
})(window.angular);