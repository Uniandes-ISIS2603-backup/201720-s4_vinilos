(function (ng) {

    var mod = ng.module("tarjetaModules");

    mod.controller("tarjetaCtrl", ['$scope', '$state', '$stateParams', '$http', 'tarjetaContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de ciudades está vacio
            $scope.tarjetas = {};
            // carga las ciudades
            $http.get(context).then(function (response) {
                $scope.tarjetas = response.data;
            });

            // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.tarjetaId !== null && $stateParams.tarjetaId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.tarjetaId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentTarjeta = response.data;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.currentTarjeta = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }
            this.editTarjeta = function(id){
                // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentTarjeta.id, currentTarjeta)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('tarjetaList');
                            });
                };
            
            this.deleteTarjeta = function(tarjeta) {
                 return $http.delete(context + "/" + $stateParams.tarjetaId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.tarjetas.indexOf(tarjeta);
                                if (index > -1) {
                                    $scope.tarjetas.splice(index, 1);
                                }
                                 $state.go('tarjetaList');
                            });
            }
//
//// Código continua con las funciones de despliegue de errores
//
//
        }]);
})(window.angular);
