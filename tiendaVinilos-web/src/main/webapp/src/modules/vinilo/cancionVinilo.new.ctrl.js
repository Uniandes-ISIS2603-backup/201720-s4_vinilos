(function (ng) {
    var mod = ng.module("viniloModules");
    mod.constant("viniloContext", "api/vinilos");
    mod.controller('viniloCreateCancionCtrl', ['$scope', '$http', 'viniloContext', '$state',  '$rootScope', '$stateParams',
        function ($scope, $http, viniloContext, $state,  $rootScope, $stateParams) {
            $rootScope.edit = false;
            this.createCancion = function () {
                            
                $http.post(viniloContext+"/"+$state.params.viniloId+"/canciones", {
                    id:$scope.cancionId,
                    name: $scope.cancionName,
                    duracion: $scope.cancionDuracion,
                    link: $scope.cancionLink,
                }).then(function () {
                    //Cancion created successfully
                    $state.go('viniloList');
                });
            };
        }
    ]);
}
)(window.angular);