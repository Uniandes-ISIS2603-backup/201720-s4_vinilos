(function (ng) {
    var mod = ng.module("pedidoClienteModule");
    mod.constant("pedidoClienteContext", "api/pedidoCliente");
    mod.controller("pedidoClienteCtrl", ['$scope', '$state', '$stateParams', '$http', 'pedidoClienteContext', 
        function ($scope, $state, $stateParams, $http, pedidoClienteContext) {

            // inicialmente el listado de ciudades está vacio
            $scope.records = {};
            // carga las ciudades
            $http.get(pedidoClienteContext).then(function (response) {
                $scope.records = response.data;
            });

            // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.pedidoId !== null && $stateParams.pedidoId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.pedidoId;
                // obtiene el dato del recurso REST
                $http.get(pedidoClienteContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id === null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(pedidoClienteContext, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('pedidoClienteList');
                            });

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(pedidoClienteContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('pedidoClienteList');
                            });
                }
                ;
            };

            this.deleteRecord = function (id) {
                $http.delete(pedidoClienteContext + "/" + id);
                $state.reload('pedidoClienteList');

            }

// Código continua con las funciones de despliegue de errores


        }]);
})(window.angular);

