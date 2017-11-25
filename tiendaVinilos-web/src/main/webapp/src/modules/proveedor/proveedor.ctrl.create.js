(function (ng) {

    var mod = ng.module("proveedorModules");
    mod.controller("proveedorCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'proveedorContext', function ($scope, $state, $stateParams, $http, context) {

             if ($stateParams.viniloId !== null && $stateParams.viniloId !== undefined) {

                // toma el id del parámetro
                var id = $stateParams.viniloId;
                // obtiene el dato del recurso REST
                $http.get("api/vinilos/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                           $scope.viniloActual = response.data;
                        })};


             this.editProveedor = function(){
                // ejecuta POST en el recurso REST
                return $http.post(context , {
                       name: $scope.proveedorName,
                        email: $scope.proveedorEmail,
                        id: null
                    }).then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('proveedorList');
                            });
                };
                
            this.addVinilo =  function(){
                // ejecuta POST en el recurso REST.
                return $http.put(context + "/" + $stateParams.proveedorId + "/vinilos/" + $stateParams.viniloId , {
                         nombre: $scope.viniloActual.nombre,
                        precio: $scope.viniloActual.precio,
                        anio: $scope.viniloActual.anio,
                       cantUnidades: $scope.viniloActual.cantUnidades,
                    }).then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('proveedorList');
                            });
                };   

        }]);
})(window.angular);

