<<<<<<< HEAD
(function (ng) {
    var mod = ng.module("pedidoClienteModules");
    mod.constant("pedidoClienteContext", "api/pedidocliente");
    mod.controller('pedidoClienteCtrl', ['$scope', '$http', 'pedidoClienteContext', '$state',
        function ($scope, $http, pedidoClienteContext, $state) {
            $http.get(pedidoClienteContext).then(function (response) {
                $scope.pedidosRecords = response.data;
            });

            if (($state.params.pedidoId !== undefined) && ($state.params.pedidoId !== null)) {
                $http.get(pedidoClienteContext + '/' + $state.params.pedidoId).then(function (response) {
                    $scope.pagoRecord = response.data.pago;
                    $scope.currentPedido = response.data;
                });
            }
        }
    ]);
}
)(window.angular);

=======
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

>>>>>>> origin/master
