(function (ng) {

    var mod = ng.module("viniloModules");
    mod.controller("viniloCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'viniloContext', function ($scope, $state, $stateParams, $http, context) {

             this.editVinilo = function(){
                return $http.post(context , {
                      nombre: $scope.viniloNombre,
                        precio: $scope.viniloPrecio,
                        anio: $scope.viniloAnio,
                       cantUnidades: $scope.viniloCantUnidades,
                    }).then(function () {
                                alert($scope.viniloNombre)
                               
                                $state.go('viniloList');
                            });
                };   

        }]);
})(window.angular);