(function (ng) {
    var mod = ng.module("pedidoClienteModules");
    mod.constant("pedidoClienteContext", "api/pedidoCliente");
    mod.controller("pedidoClienteCtrl", ['$scope', '$state', '$stateParams', '$http', 'pedidoClienteContext', 
        function ($scope, $state, $stateParams, $http, pedidoClienteContext) {

            // inicialmente el listado de ciudades está vacio
            $scope.pedidos = {};
            // carga las ciudades
            $http.get(pedidoClienteContext).then(function (response) {
                $scope.pedidos = response.data;
            });
            
            if (($state.params.pedidoClienteId !== undefined) && ($state.params.pedidoClienteId !== null)) {
                $http.get(pedidoClienteContext + '/' + $state.params.pedidoClienteId).then(function (response) {
                    $scope.currentPedido = response.data;
                });
            }else {
                // el registro actual debe estar vacio
                $scope.currentPedido = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }


// Código continua con las funciones de despliegue de errores


        }]);
})(window.angular);

