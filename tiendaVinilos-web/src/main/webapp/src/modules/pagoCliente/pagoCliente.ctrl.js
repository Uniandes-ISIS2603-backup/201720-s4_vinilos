(function (ng) {
    var mod = ng.module("pagoClienteModule");
    mod.constant("pagoClienteContext", "api/pagoCliente");
    mod.controller("pagoClienteCtrl", ['$scope', '$state', '$stateParams', '$http', 'pagoClienteContext', 
        function ($scope, $state, $stateParams, $http, pagoClienteContext) {

            // inicialmente el listado de ciudades está vacio
            $scope.records = {};
            // carga las ciudades
            $http.get(pagoClienteContext).then(function (response) {
                $scope.records = response.data;
            });

            // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.pagoId !== null && $stateParams.pagoId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.pagoId;
                // obtiene el dato del recurso REST
                $http.get(pagoClienteContext + "/" + id)
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
                    return $http.post(pagoClienteContext, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('pagoClienteList');
                            });

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(pagoClienteContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('pagoClienteList');
                            });
                }
                ;
            };

            this.deleteRecord = function (id) {
                $http.delete(pagoClienteContext + "/" + id);
                $state.reload('pagoClienteList');

            }

// Código continua con las funciones de despliegue de errores


        }]);
})(window.angular);

