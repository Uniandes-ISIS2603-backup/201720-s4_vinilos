(function (ng) {
    var mod = ng.module("pedidoClienteModules");
    mod.constant("pedidoClienteContext", "api/pedidocliente");
    mod.controller('pedidoClienteCtrl', ['$scope', '$http', 'pedidoClienteContext', '$state',
        function ($scope, $http, pedidoClienteContext, $state) {
            
            if (($state.params.pedidoId !== undefined) && ($state.params.pedidoId !== null)) {
                $http.get(pedidoClienteContext + '/' + $state.params.pedidoId).then(function (response) {
                    $scope.pagoRecord = response.data.pago;
                    $scope.currentPedido = response.data;
                });
            }
            
            $scope.pedidosRecords = {};
            
            this.pedidosUsuario = function() {
                
                var decodedCookie = decodeURIComponent(document.cookie);
                var ca = decodedCookie.split('=');
    

                $http.get("api/usuarios/"+ca[1]+"/pedidos").then(function(response) {
                    $scope.pedidosRecords = response.data; 
                });
                
            };
        }
    ]);
}
)(window.angular);
