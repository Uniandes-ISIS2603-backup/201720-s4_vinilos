(function (ng) {

    var mod = ng.module("viniloModules");

    mod.controller("viniloCtrl", ['$scope', '$state', '$stateParams', '$http', 'viniloContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de vinilos está vacio
            $scope.vinilos = {};
            // carga los vinilos
            $http.get(context).then(function (response) {
                $scope.vinilos = response.data;
            });
            // el controlador recibió un viniloId ??
            // revisa los parámetros (ver el :viniloId en la definición de la ruta)
            if ($stateParams.viniloId !== null && $stateParams.viniloId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.viniloId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentVinilo = response.data;
                            $scope.viniloNombre = response.data.nombre;
                            $scope.viniloAnio = response.data.anio;
                            $scope.viniloPrecio = response.data.precio;
                            $scope.viniloCantUnidades = response.data.cantUnidades;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.currentVinilo = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    nombre: '' /*Tipo String*/,
                    anio: 0 /*Tipo Integer.*/,
                    precio: 0 /*Tipo Double.*/,
                    cantUnidades: 0 /*Tipo Integer.*/
                };

                $scope.alerts = [];
            }


            this.editVinilo = function(){
                // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + $scope.currentVinilo.id, {
                        nombre: $scope.viniloNombre,
                        anio: $scope.viniloAnio,
                        precio: $scope.viniloPrecio,
                        cantUnidades: $scope.viniloCantUnidades
                    })
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('viniloList');
                            });
                };
            
            this.deleteVinilo = function(vinilo) {
                 return $http.delete(context + "/" + $stateParams.viniloId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.vinilos.indexOf(vinilo);
                                if (index > -1) {
                                    $scope.vinilos.splice(index, 1);
                                }
                                 $state.go('viniloList');
                            });
            };
            this.addToCart = function() {
                 return $http.post("api/usuarios/"+$stateParams.usuarioId +"/carroCompras/"+$stateParams.viniloId)
                            .then(function () {
                               
                                 $state.go('viniloList');
                            });
            }
            
//
//// Código continua con las funciones de despliegue de errores
//
//
        }]);
})(window.angular);