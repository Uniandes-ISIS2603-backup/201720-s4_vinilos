(function (ng) {

    var mod = ng.module("tarjetaModules");
    mod.controller("tarjetaCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'tarjetaContext', function ($scope, $state, $stateParams, $http, context) {
            var decodedCookie = decodeURIComponent(document.cookie);
            var ca = decodedCookie.split('=');
            this.editTarjeta = function () {
                return $http.post("api/usuarios/" + ca[1] + "/tarjetas", {
                    numero: $scope.tarjetaNumber,
                    nombrePropietario: $scope.tarjetaOwner
                }).then(function () {
                    $state.go('tarjetaUsuario', '{usuarioId:'+ca[1]+'}');
                });
            };

        }]);
})(window.angular);