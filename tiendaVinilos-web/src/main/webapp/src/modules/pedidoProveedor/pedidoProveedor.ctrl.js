(function (ng) {

    var mod = ng.module("pedidoProveedorModules");

    mod.controller("pedidoProveedoresCtrl", ['$scope', '$state', '$stateParams', '$http', 'pedidoProveedorContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de ciudades está vacio
            $scope.pedidoProveedores = {};
            // carga las ciudades
            $http.get(context).then(function (response) {
                $scope.pedidoProveedores = response.data;
            });

            // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.pedidoProveedoresId !== null && $stateParams.pedidoProveedoresId !== undefined) {

                // toma el id del parámetro
                var id = $stateParams.pedidoProveedoresId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.pedidoProveedorActual = response.data;
                            $scope.pedidoProveedorFecha = response.data.fecha;
                            $scope.pedidoProveedorPrecio = response.data.precio;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.pedidoProveedorActual = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    fecha: '' /*Tipo String*/,
                    precio: ''
                };
                $scope.alerts = [];
            }

           
//
//// Código continua con las funciones de despliegue de errores
//
//

 this.deletePedidoProveedor = function(record) {
                confirmarDelete =  confirm("Esta seguro que lo quiere eliminar?");
                 if (confirmarDelete) return $http.delete(context + "/" + $stateParams.pedidoProveedoresId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.pedidoProveedores.indexOf(record);
                                if (index > -1) {
                                    $scope.pedidoProveedores.splice(index, 1);
                                }
                                 $state.go('proveedorList');
                            });
            }
        }]);
})(window.angular);

