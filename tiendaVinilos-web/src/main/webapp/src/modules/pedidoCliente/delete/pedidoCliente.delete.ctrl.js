(function (ng) {
    var mod = ng.module("pedidoClienteModules");
    mod.constant("pedidoClienteContext", "api/pedidocliente");
    mod.controller('pedidoClienteDeleteCtrl', ['$scope', '$http', 'pedidoClienteContext', '$state',
        function ($scope, $http, pedidoClienteContext, $state) {
            var idPedido = $state.params.pedidoClienteId;
            $scope.deletePedido = function () {
                $http.delete("api/pedidocliente/" + idPedido).then(function (response) {
                    $state.go('pedidoClienteList', {pedidoId: response.data.id}, {reload: true});
                }),
                function(response) {
                  $state.go('pedidoClienteList', {pedidoId: response.data.id}, {reload: true});
                  $scope.data = response.data || 'Request failed';
                  $scope.status = response.status;
                };
            };

            $scope.deletePago = function () {
                $http.delete("api/pedido/" + idPedido + "/pago").then(function (response) {
                    $state.go('pedidoClienteList', {pedidoId: response.data.id}, {reload: true});
                }),
                function(response) {
                  $state.go('pedidoClienteList', {pedidoId: response.data.id}, {reload: true});
                  $scope.data = response.data || 'Request failed';
                  $scope.status = response.status;
                };
            };
        }
    ]);
}
)(window.angular);
