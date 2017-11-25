(function (ng) {
    var mod = ng.module("pagoClienteModules");
    mod.constant("pagoClienteContext", "api/pagoCliente");
    mod.controller("pagoClienteCtrl", ['$scope', '$state', '$stateParams', '$http', 'pagoClienteContext', 
        function ($scope, $state, $stateParams, $http, pagoClienteContext) {

            // inicialmente el listado de ciudades está vacio
            $scope.pagos = {};
            // carga las ciudades
            $http.get(pagoClienteContext).then(function (response) {
                $scope.pagos = response.data;
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
                            $scope.currentPago = response.data;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.currentPago = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }


           

// Código continua con las funciones de despliegue de errores


        }]);
})(window.angular);

