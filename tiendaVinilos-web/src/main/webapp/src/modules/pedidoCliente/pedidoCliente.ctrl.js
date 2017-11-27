(function (ng) {
    var mod = ng.module("pedidoClienteModules");
    mod.constant("pedidoClienteContext", "api/pedidocliente");
    mod.controller('pedidoClienteCtrl', ['$scope', '$http', 'pedidoClienteContext', '$state',
        function ($scope, $http, pedidoClienteContext, $state) {


            this.pedidosUsuario = function() {

                var decodedCookie = decodeURIComponent(document.cookie);
                var ca = decodedCookie.split('=');


                $http.get("api/usuarios/"+ca[1]+"/pedidos").then(function(response) {
                    $scope.pedidosRecords = response.data;
                });

            };

            this.getPagoPedido = function(pedidoClienteId) {

               $http.get("api/pedido/"+pedidoClienteId+"/pago").then(function(response) {
                   $scope.pagoPedido = response.data;
              });
            };

            this.getPedidosProveedor = function(pedidoClienteId){
              var decodedCookie = decodeURIComponent(document.cookie);
              var ca = decodedCookie.split('=');
              $http.get("usuarios/" + ca[1]+'/pedidos/'+pedidoClienteId+ '/pedidoProveedor' ).then(function (response) {
                    $scope.pedidosProveedorRecords = response.data;
                });
            };

            this.cancelarPedido = function(pedidoClienteId){
              $http.put(pedidoClienteContext + '/cancelarPedido/' + pedidoClienteId ).then(function(response){
                $state.go('pedidoClienteList', {pedidoClienteId: response.data.id}, {reload: true});
              });
            };






        }
    ]);
}
)(window.angular);
