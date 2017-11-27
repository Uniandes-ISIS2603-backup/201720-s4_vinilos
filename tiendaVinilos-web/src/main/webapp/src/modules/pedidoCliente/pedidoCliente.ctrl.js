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
