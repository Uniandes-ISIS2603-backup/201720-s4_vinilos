(function (ng) {
    var mod = ng.module("pedidoClienteModules");
    mod.constant("pedidoClienteContext", "api/pedidoCliente");
    mod.controller("pedidoClienteDeleteCtrl", ['$scope', '$state', '$stateParams', '$http', 'pedidoClienteContext', 
    function ($scope, $state, $stateParams, $http, pedidoClienteContext) {
    var idPedido = $state.params.pedidoClienteId;
            $scope.deletePedido = function () {
                $http.delete(pedidoClienteContext + '/' + idPedido, {}).then(function (response) {
                    $state.go('pedidoClienteList', {pedidoClienteId: response.data.id}, {reload: true});
                });
            };

    }]);
})(window.angular);

