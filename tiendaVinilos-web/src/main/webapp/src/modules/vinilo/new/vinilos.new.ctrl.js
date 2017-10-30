(function (ng) {
    var mod = ng.module("viniloModule");
    mod.constant("vinilosContext", "api/vinilos");
    mod.controller('viniloNewCtrl', ['$scope', '$http', 'vinilosContext', '$state', '$rootScope',
        function ($scope, $http, vinilosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createVinilo = function () {
                $http.post(vinilosContext, {
                   anio:$scope.viniloAnio,
                   cantUnidades:$scope.viniloCant,
                   id:$scope.viniloId,
                   nombre:$scope.viniloNombre,
                   precio:$scope.viniloPrecio
                }).then(function (response) {
                    //Vinilo created successfully
                    $state.go('vinilosList', {viniloId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);