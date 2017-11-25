(function (ng) {
    var mod = ng.module("pagoClienteModules");
    mod.constant("pagoClienteContext", "api/pagoCliente");
    mod.controller("pagoClienteNewCtrl", ['$scope', '$state', '$stateParams', '$http', 'pedidoClienteContext', 
    function ($scope, $state, $stateParams, $http, pagoClienteContext) {
    $scope.createPago = function () {
                $http.post(pagoClienteContext, {
                    valor: $scope.valorPago,
                    fecha: $scope.fechaPago
                }).then(function (response) {
                    //Author created successfully
                    $state.go('pagoClienteList', {pagoClienteId: response.data.id}, {reload: true});
                });
};

        }]);
})(window.angular);

