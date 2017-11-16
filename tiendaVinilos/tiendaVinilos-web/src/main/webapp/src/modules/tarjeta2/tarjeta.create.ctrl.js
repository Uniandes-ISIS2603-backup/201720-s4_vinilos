(function (ng) {

    var mod = ng.module("tarjetaModules");
    mod.controller("tarjetaCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'tarjetaContext', function ($scope, $state, $stateParams, $http, context) {

             this.editTarjeta = function(){
                return $http.post(context , {
                    numero:$scope.tarjetaNumber,
                    nombrePropietario:$scope.tarjetaOwner
                    }).then(function () {
                                alert($scope.tarjetaNumber)
                                alert($scope.tarjetaOwner)
                                $state.go('tarjetaList');
                            });
                };   

        }]);
})(window.angular);