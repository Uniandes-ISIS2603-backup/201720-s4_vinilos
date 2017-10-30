(function (ng) {
    var mod = ng.module("viniloModule");
    mod.constant("vinilosContext", "api/vinilos");
    mod.controller('viniloDeleteCtrl', ['$scope', '$http', 'vinilosContext', '$state',
        function ($scope, $http, vinilosContext, $state) {
            var idVinilo = $state.params.viniloId;
            $scope.deleteVinilo = function () {
                $http.delete(vinilosContext + '/' + idVinilo, {}).then(function (response) {
                    $state.go('vinilosList', {viniloId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);