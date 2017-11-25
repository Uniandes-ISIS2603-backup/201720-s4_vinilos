(function (ng) {

    var mod = ng.module("cancionModule");

    mod.controller("cancionCtrl", ['$scope', '$state', '$stateParams', '$http', 'cancionContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de ciudades está vacio
            $scope.recordsCancion = {};
            // carga las ciudades
            $http.get(context).then(function (response) {
                $scope.recordsCancion = response.data;
            });

            // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.cancionId !== null && $stateParams.cancionId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.cancionId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentCancion = response.data;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.currentCancion = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }

//
//            this.saveRecord = function (id) {
//                currentRecord = $scope.currentRecord;
//
//                // si el id es null, es un registro nuevo, entonces lo crea
//                if (id == null) {
//
//                    // ejecuta POST en el recurso REST
//                    return $http.post(context, currentRecord)
//                            .then(function () {
//                                // $http.post es una promesa
//                                // cuando termine bien, cambie de estado
//                                $state.go('citiesList');
//                            });
//
//                    // si el id no es null, es un registro existente entonces lo actualiza
//                } else {
//
//// 
//            this.editRecord = function(id){
//                // ejecuta PUT en el recurso REST
//                    return $http.put(context + "/" + currentRecord.id, currentRecord)
//                            .then(function () {
//                                // $http.put es una promesa
//                                // cuando termine bien, cambie de estado
//                                $state.go('cancionList');
//                            });
//                };
            
//            this.deleteRecord = function(record) {
//                 return $http.delete(context + "/" + $stateParams.cancionId)
//                            .then(function () {
//                                // $http.delete es una promesa
//                                // cuando termine bien, cambie de estado
//                                var index = $scope.recordsCancion.indexOf(record);
//                                if (index > -1) {
//                                    $scope.recordsCancion.splice(index, 1);
//                                }
//                                 $state.go('cancionList');
//                            });
//            }
//
//// Código continua con las funciones de despliegue de errores
//
//
        }]);
})(window.angular);