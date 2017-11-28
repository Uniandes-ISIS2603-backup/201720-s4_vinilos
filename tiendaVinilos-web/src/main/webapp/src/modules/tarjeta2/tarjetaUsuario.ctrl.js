(function (ng) {

    var mod = ng.module("tarjetaModules");

    mod.controller("tarjetaUsuarioCtrl", ['$scope', '$state', '$stateParams', '$http', function ($scope, $state, $stateParams, $http) {

            // inicialmente el listado de ciudades está vacio
            $scope.tarjetas = {};
            var decodedCookie = decodeURIComponent(document.cookie);
            var ca = decodedCookie.split('=');
            $scope.userId = ca[1];
            // carga las ciudades
            $http.get("api/usuarios/" + ca[1] + "/tarjetas").then(function (response) {
                $scope.tarjetas = response.data;
            });
            if ($stateParams.tarjetaId !== null && $stateParams.tarjetaId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.tarjetaId;
                // obtiene el dato del recurso REST
                $http.get("api/usuarios/" + ca[1] + "/tarjetas/" + $stateParams.tarjetaId)
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
            this.editTarjeta = function () {
                // ejecuta PUT en el recurso REST
                $http.put("api/usuarios/" + ca[1] + "/tarjetas/" + $scope.currentTarjeta.id, {
                    numero: $scope.tarjetaNumber,
                    nombrePropietario: $scope.tarjetaOwner
                }).then(function () {
                    // $http.put es una promesa
                    // cuando termine bien, cambie de estado
                    $state.go('tarjetaUsuario', '{usuarioId:'+ca[1]+'}');
                });
            };

            this.deleteTarjeta = function (tarjeta) {
                return $http.delete("api/usuarios/" + ca[1] + "/tarjetas/" + $stateParams.tarjetaId)
                        .then(function () {
                            // $http.delete es una promesa
                            // cuando termine bien, cambie de estado
                            var index = $scope.tarjetas.indexOf(tarjeta);
                            if (index > -1) {
                                $scope.tarjetas.splice(index, 1);
                            }
                            $state.go('tarjetaUsuario', '{usuarioId:'+ca[1]+'}');
                        });
            }

//
//// Código continua con las funciones de despliegue de errores
//
//
        }]);
})(window.angular);