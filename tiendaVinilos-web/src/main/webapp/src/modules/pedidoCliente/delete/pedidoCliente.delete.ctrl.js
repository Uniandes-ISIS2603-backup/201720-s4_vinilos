(function (ng) {
    var mod = ng.module("pedidoClienteModules");
    mod.constant("pedidoClienteContext", "api/pedidocliente");
    mod.controller('pedidoClienteDeleteCtrl', ['$scope', '$http', 'pedidoClienteContext', '$state',
        function ($scope, $http, pedidoClienteContext, $state) {
            var idPedido = $state.params.pedidoId;
            $scope.deletePedido = function () {
                $http.delete(pedidoClienteContext + '/' + idPedido, {}).then(function (response) {
                    $state.go('pedidoClienteList', {pedidoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);