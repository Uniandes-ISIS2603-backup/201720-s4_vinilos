(function (ng) {
    var mod = ng.module("pagoClienteModules");
    mod.constant("pagoClienteContext", "api/pagoCliente");
    mod.controller("pagoClienteDeleteCtrl", ['$scope', '$state', '$stateParams', '$http', 'pagoClienteContext', 
    function ($scope, $state, $stateParams, $http, pagoClienteContext) {
    var idPago = $state.params.pagoClienteId;
            $scope.deletePago = function () {
                $http.delete(pagoClienteContext + '/' + idPago, {}).then(function (response) {
                    $state.go('pagoClienteList', {pagoClienteId: response.data.id}, {reload: true});
                });
            };

    }]);
})(window.angular);

