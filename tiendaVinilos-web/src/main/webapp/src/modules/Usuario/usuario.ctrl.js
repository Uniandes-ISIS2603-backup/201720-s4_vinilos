(function (ng) {

    var mod = ng.module("usuarioModules");

    mod.controller("usuarioCtrl", ['$scope', '$state', '$stateParams', '$http', 'usuarioContext','$rootScope', function ($scope, $state, $stateParams, $http, context,$rootScope) {

            // inicialmente el listado de ciudades está vacio
            $scope.usuarios = {};
            // carga las ciudades
            $http.get(context).then(function (response) {
                $scope.usuarios = response.data;
            });
            // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.usuarioId !== null && $stateParams.usuarioId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.usuarioId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentUsuario=response.data;
                            $rootScope.currentUser = response.data;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.currentUsuario = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }


            this.editUsuario = function(){
                // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + $scope.currentUsuario.id, {
                        name: $scope.usuarioName,
                        email: $scope.usuarioEmail
                    })
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('usuarioList');
                            });
                };
            
            this.deleteUsuario = function(usuario) {
                 return $http.delete(context + "/" + $stateParams.usuarioId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.usuarios.indexOf(usuario);
                                if (index > -1) {
                                    $scope.usuarios.splice(index, 1);
                                }
                                 $state.go('usuarioList');
                            });
            }
            
//
//// Código continua con las funciones de despliegue de errores
//
//
        }]);
})(window.angular);