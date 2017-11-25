(function (ng) {
    var mod = ng.module("viniloModules");
    mod.constant("viniloContext", "api/vinilos");
    mod.controller('viniloCreateCancionCtrl', ['$scope', '$http', 'viniloContext', '$state',  '$rootScope',
        function ($scope, $http, viniloContext, $state,  $rootScope) {
            $rootScope.edit = false;
            var idVinilo=$state.params.idVinilo;
            $scope.createCancion = function () {
                $http.post(viniloContext+"/"+idVinilo+"/canciones", {
                    id:$scope.cancionId,
                    name: $scope.cancionName,
                    duracion: $scope.cancionDuracion,
                }).then(function (response) {
                    //Cancion created successfully
                    $state.go('viniloSee', {viniloId: idVinilo}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);