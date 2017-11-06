(function (ng) {

    var mod = ng.module("pagoProveedorModules");

    mod.controller("pagoProveedoresCtrl", ['$scope', '$state', '$stateParams', '$http', 'pagoProveedorContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de ciudades está vacio
            $scope.pedidoProveedores = {};
            // carga las ciudades
            $http.get(context).then(function (response) {
                $scope.pagoProveedores = response.data;
            });

            // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.pagoProveedorId !== null && $stateParams.pagoProveedorId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.pagoProveedorId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.pagoProveedorActual = response.data;
                            $scope.pagoProveedorFecha = response.data.fecha;
                            $scope.pagoProveedorPrecio = response.data.precio;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.proveedorActual = {
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
        }]);
})(window.angular);

