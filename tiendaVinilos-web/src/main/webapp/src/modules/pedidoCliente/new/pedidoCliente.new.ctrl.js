(function (ng) {
    var mod = ng.module("pedidoClienteModules");
    mod.constant("pedidoClienteContext", "api/usuarios/944/pedidos");
    mod.controller('pedidoClienteNewCtrl', ['$scope', '$http', 'pedidoClienteContext', '$state', '$rootScope',
        function ($scope, $http, pedidoClienteContext, $state, $rootScope) {


          this.createPedido = function(){
            var decodedCookie = decodeURIComponent(document.cookie);
            var ca = decodedCookie.split('=');
             console.log($scope.pedidoDireccion);
            $http.post("api/usuarios/" + ca[1]+ "/pedidos", {
                    telefono: $scope.pedidoTelefono,
                    precio: $state.params.totalPedido,
                    direccion: $scope.pedidoDireccion
                 }).then(function () {
                   console.log($scope.pedidoTelefono);
                             // $http.post es una promesa
                             // cuando termine bien, cambie de estado
                             $state.go('pedidoClienteList', {reload: true});
                         });
             };


        }
    ]);
}
)(window.angular);
