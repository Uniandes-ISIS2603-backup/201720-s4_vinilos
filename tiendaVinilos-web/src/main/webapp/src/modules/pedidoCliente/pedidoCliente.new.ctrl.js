(function (ng) {
    var mod = ng.module("pedidoClienteModules");
    mod.constant("pedidoClienteContext", "api/pedidoCliente");
    mod.controller("pedidoClienteNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'pedidoClienteContext', 
    function ($scope, $state, $stateParams, $http, pedidoClienteContext) {
    $scope.createPedido = function () {
                $http.post(pedidoClienteContext, {
                    userName: $scope.userName,
                    total: $scope.totalPedido,
                    direccion: $scope.direccionPedido,
                    telefono: $scope.telefonoContacto
                }).then(function (response) {
                    //Author created successfully
                    $state.go('pedidoClienteList', {pedidoClienteId: response.data.id}, {reload: true});
                });
};

        }]);
})(window.angular);

