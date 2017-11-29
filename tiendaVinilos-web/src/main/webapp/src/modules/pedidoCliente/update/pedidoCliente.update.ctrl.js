(
        function (ng) {
            var mod = ng.module("pedidoClienteModules");
            mod.constant("pedidoClienteContext", "api/pedidocliente");
            mod.controller('pedidoClienteUpdateCtrl', ['$scope', '$http', 'pedidoClienteContext', '$state', '$rootScope',
                function ($scope, $http, pedidoClienteContext, $state) {


                      // toma el id del parámetro
                      var pedidoId = $state.params.pedidoClienteId;
                      // obtiene el dato del recurso REST
                      $http.get("api/pedidocliente/" + pedidoId)
                              .then(function (response) {

                                  // $http.get es una promesa
                                  // cuando llegue el dato, actualice currentRecord
                                  $scope.pedidoActual = response.data;
                                  console.log($scope.pedidoActual);
                                  $scope.pedidoTelefono = response.data.telefono;
                                  $scope.pedidoDireccion = response.data.direccion;
                              });

                      // el controlador no recibió un cityId


                  this.editPedidoCliente = function(){
                      // ejecuta PUT en el recurso REST
                      console.log($scope.pedidoActual);

                       $http.put("api/pedidocliente/" + pedidoId, {
                              telefono: $scope.pedidoTelefono,
                              direccion: $scope.pedidoDireccion
                          }).then(function () {
                                      // $http.put es una promesa
                                      // cuando termine bien, cambie de estado
                                      $state.go('pedidoClienteList', {pedidoId: response.data.id}, {reload: true});
                                  });
                      };

              //No poner nada
                }

            ]);
        }
)(window.angular);
