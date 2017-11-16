(function (ng) {

    var mod = ng.module("proveedorModules");
    mod.controller("proveedorCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'proveedorContext', function ($scope, $state, $stateParams, $http, context) {

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

        }]);
})(window.angular);

