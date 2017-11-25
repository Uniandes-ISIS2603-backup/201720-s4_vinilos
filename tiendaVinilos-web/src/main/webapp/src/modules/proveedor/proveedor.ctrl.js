(function (ng) {

    var mod = ng.module("proveedorModules");

    mod.controller("proveedorCtrl", ['$scope', '$state', '$stateParams', '$http', 'proveedorContext', function ($scope, $state, $stateParams, $http, context) {

            $scope.proveedores = {};
            $http.get(context).then(function (response) {
                $scope.proveedores = response.data;
            });
            if ($stateParams.proveedorId !== null && $stateParams.proveedorId !== undefined) {
                var id = $stateParams.proveedorId;
                $http.get(context + "/" + id)
                        .then(function (response) {
                            $scope.proveedorActual = response.data;
                            $scope.proveedorName = response.data.name;
                            $scope.proveedorEmail = response.data.email;
                        });
            } else {
                $scope.proveedorActual = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    email: ''
                };
                $scope.alerts = [];
            }
            this.editProveedor = function(){
                // ejecuta PUT en el recurso REST
                var confirmarDelete =  confirm("Esta seguro que lo quiere modificar?");
                 if (confirmarDelete){
                     return $http.put(context + "/" + $stateParams.proveedorId, {
                        name: $scope.proveedorName,
                        email: $scope.proveedorEmail
                    }).then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('proveedorList');
                            });
                }};
             
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
                 if (confirmarDelete) {
                     return $http.delete("api/vinilos/"+ viniloId)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                 $state.go('proveedorList');
                            });
            }}
            
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

        }]);
})(window.angular);

