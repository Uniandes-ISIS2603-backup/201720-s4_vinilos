(function (ng) {
    var mod = ng.module("viniloModules");
    mod.constant("viniloContext", "api/vinilos");
    mod.controller('viniloCreateArtistaCtrl', ['$scope', '$http', 'viniloContext', '$state',  '$rootScope',
        function ($scope, $http, viniloContext, $state,  $rootScope) {
            $rootScope.edit = false;
            var idVinilo=$state.params.idVinilo;
            $scope.createArtista = function () {
                $http.post(viniloContext+"/"+idVinilo+"/artistas", {
                    id:$scope.artistaId,
                    name: $scope.artistaName                    
                }).then(function () {
                    //Cancion created successfully
                    $state.go('viniloSee', {viniloId: idVinilo}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);