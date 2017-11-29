(function (ng) {

    var mod = ng.module("proveedorModules");

    mod.controller("proveedorCtrl", ['$scope', '$state', '$stateParams', '$http', 'proveedorContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de ciudades está vacio
            $scope.proveedores = {};
            // carga las ciudades
            $http.get(context).then(function (response) {
                $scope.proveedores = response.data;
            });

            // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.proveedorId !== null && $stateParams.proveedorId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.proveedorId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.proveedorActual = response.data;
                            $scope.proveedorName = response.data.name;
                            $scope.proveedorEmail = response.data.email;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.proveedorActual = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    email: ''
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
// 
            this.editProveedor = function(){
                // ejecuta PUT en el recurso REST
                confirmarDelete =  confirm("Esta seguro que lo quiere modificar?");
                 if (confirmarDelete)return $http.put(context + "/" + $stateParams.proveedorId, {
                        name: $scope.proveedorName,
                        email: $scope.proveedorEmail
                    }).then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('proveedorList');
                            });
                };
                
                 this.editPedido = function(PedidoId, precio){
                // ejecuta PUT en el recurso REST
                confirmarDelete =  confirm("¿Está seguro que lo quiere modificar y hacer el 25% de descuento?");
                 if (confirmarDelete)$http.put("api/proveedores/"+$stateParams.proveedorId+"/pedidos/"+PedidoId,{
                     precio:precio,
                     id:PedidoId
                 }).then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                
                            });
                };
             
            this.deleteProveedor = function(record) {
                confirmarDelete =  confirm("Esta seguro que lo quiere eliminar?");
                 if (confirmarDelete) return $http.delete(context + "/" + $stateParams.proveedorId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.proveedores.indexOf(record);
                                if (index > -1) {
                                    $scope.proveedores.splice(index, 1);
                                }
                                 $state.go('proveedorList');
                            });
            }

            
            this.deleteFeedback = function(feedBackId) {
                confirmarDelete =  confirm("Esta seguro que lo quiere eliminar?");
                 if (confirmarDelete) return $http.delete("api/feedbacks/"+ feedBackId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                 $state.go('proveedorList');
                            });
            }
            
            this.deleteVinilo = function(viniloId) {
                confirmarDelete =  confirm("Esta seguro que lo quiere eliminar?");
                 if (confirmarDelete) return $http.delete("api/vinilos/"+ viniloId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                 $state.go('proveedorList');
                            });
            }
            
            this.deletePedido = function(PedidoId, proveedorID) {
                confirmarDelete =  confirm("Esta seguro que lo quiere eliminar?");
                 if (confirmarDelete) return $http.delete("api/proveedores/"+proveedorID+"/pedidos/"+PedidoId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                               
                            });
            }
            
            this.addVinilo =  function(){
                // ejecuta POST en el recurso REST
                return $http.post(context + "/" + $stateParams.proveedorId + "/vinilos" , {
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
//
//// Código continua con las funciones de despliegue de errores
//
//
        }]);
})(window.angular);
