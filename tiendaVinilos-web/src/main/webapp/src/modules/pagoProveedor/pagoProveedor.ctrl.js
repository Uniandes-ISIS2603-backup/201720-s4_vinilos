(function (ng) {

    var mod = ng.module("pagoProveedorModules");

    mod.controller("pagoProveedoresCtrl", ['$scope', '$state', '$stateParams', '$http', 'pagoProveedorContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de ciudades está vacio
            $scope.pagoProveedores = {};
            // carga las ciudades
            $http.get(context).then(function (response) {
                $scope.pagoProveedores = response.data;
            });

            // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.pagoProveedoresId !== null && $stateParams.pagoProveedoresId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.pagoProveedoresId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.pagoProveedorActual = response.data;
                           
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.pagoProveedorActual = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    fecha: '' /*Tipo String*/,
                    valor: ''
                };
                $scope.alerts = [];
            }
            
            this.deletePagoProveedor = function(record) {
                confirmarDelete =  confirm("Esta seguro que lo quiere eliminar?");
                 if (confirmarDelete) return $http.delete(context + "/" + $stateParams.pagoProveedoresId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.pagoProveedores.indexOf(record);
                                if (index > -1) {
                                    $scope.pagoProveedores.splice(index, 1);
                                }
                                 $state.go('pagoProveedorList');
                            });
            }

           
//
//// Código continua con las funciones de despliegue de errores
//
//
        }]);
})(window.angular);

