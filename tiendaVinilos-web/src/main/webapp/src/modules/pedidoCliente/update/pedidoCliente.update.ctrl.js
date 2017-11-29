(
        function (ng) {
            var mod = ng.module("pedidoClienteModules");
            mod.constant("pedidoClienteContext", "api/pedidocliente");
            mod.constant("pagoClienteContext", "api/pagoCliente");
            mod.controller('pedidoClienteUpdateCtrl', ['$scope', '$http', 'pedidoClienteContext', '$state', 'pagoClienteContext', '$rootScope',
                function ($scope, $http, pedidoClienteContext, $state, pagoClienteContext) {

                    $scope.data = {};

                    var idPedido = $state.params.pedidoClienteId;

                    // Este arreglo guardara los ids de los books asociados y por asociar al autor.
                    $http.get("api/pedidocliente/" + idPedido).then(function (response) {
                        var pedido = response.data;
                        $scope.data.direccion = pedido.direccion;
                        $scope.data.telefono = pedido.telefono;
                    });
                    //Consulto el autor a editar.
                    $scope.updatePedido = function () {
                        /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                         en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                         */

                         confirmarDelete =  confirm("Esta seguro que lo quiere modificar?");
                          if (confirmarDelete)return $http.put("api/pedidocliente" + $stateParams.pedidoClienteId, {
                                 direccion: $scope.pedidoDireccion,
                                 telefono: $scope.pedidoTelefono
                             }).then(function () {
                                         // $http.put es una promesa
                                         // cuando termine bien, cambie de estado
                                         $state.go('pedidoClienteList');
                                     });
                    };

                }

            ]);
        }
)(window.angular);
