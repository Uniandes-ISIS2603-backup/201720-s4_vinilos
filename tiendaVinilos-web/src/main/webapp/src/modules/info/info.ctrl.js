(function (ng) {

    var mod = ng.module("infoModules");

    mod.controller("infoCtrl", ['$scope', '$state', '$stateParams', '$http', 'infoContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de infos está vacio
            $scope.infos = {};
            // carga los infos
            $http.get(context).then(function (response) {
                $scope.infos = response.data;
            });
            // el controlador recibió un infoId ??
            // revisa los parámetros (ver el :infoId en la definición de la ruta)
            if ($stateParams.infoId !== null && $stateParams.infoId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.infoId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentInfo
                                    = response.data;
                            $scope.infoId = response.data.id;
                            $scope.infoDescripcion = response.data.descripcion;
                            $scope.infoRutas = response.data.rutas;
                            $scope.infoUrl = response.data.url;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.currentInfo
                        = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    descripcion: '' /*Tipo String*/,
                    rutas: '' /*Tipo String*/,
                    url: '' /*Tipo String*/
                   
                };

                $scope.alerts = [];
            }


            this.editInfo
                    = function(){
                // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + $scope.currentInfo
                            .id, {
                        id: $scope.infoId,
                        descripcion: $scope.infoDescripcion,
                        rutas: $scope.infoRutas,
                        url: $scope.Url
                    })
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('infoList');
                            });
                };
            
            this.deleteInfo
                    = function(info) {
                 return $http.delete(context + "/" + $stateParams.infoId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.infos.indexOf(info);
                                if (index > -1) {
                                    $scope.infos.splice(index, 1);
                                }
                                 $state.go('infoList');
                            });
            }
            
//
//// Código continua con las funciones de despliegue de errores
//
//
        }]);
})(window.angular);