(function (ng) {
    var mod = ng.module("pedidoClienteModules");
    mod.constant("pedidoClienteContext", "api/usuarios/944/pedidos");
    mod.controller('pedidoClienteNewCtrl', ['$scope', '$http', 'pedidoClienteContext', '$state', '$rootScope',
        function ($scope, $http, pedidoClienteContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};

            $scope.createPedido = function () {
                $http.post(pedidoClienteContext, {
                       estado: $scope.pedidoEstado,
                        total: $scope.pedidoTotal,
                        telefono: $.scope.pedidoTelefono,
                        direccion: $scope.pedidoDireccion,
                        fechaEstimada: $scope.pedidoFechaEstimada
                    }).then(function (response) {
                    $state.go('pedidoClienteList', {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);