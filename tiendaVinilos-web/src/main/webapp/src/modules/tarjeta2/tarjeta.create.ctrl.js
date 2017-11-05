(function (ng) {

    var mod = ng.module("tarjetaModules");
    mod.controller("tarjeraCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'tarjetaContext', function ($scope, $state, $stateParams, $http, context) {

             this.editTarjeta = function(){
                return $http.post(context , {

                    }).then(function () {
                                alert($scope.tarjetaNumber)
                                alert($scope.tarjetaOwner)
                                $state.go('tarjetaList');
                            });
                };   

        }]);
})(window.angular);