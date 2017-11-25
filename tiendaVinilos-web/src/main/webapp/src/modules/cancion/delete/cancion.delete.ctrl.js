(function (ng) {
    var mod = ng.module("cancionModule");
    mod.constant("cancionContext", "api/cancion");
    mod.controller('cancionDeleteCtrl', ['$scope', '$http', 'cancionContext', '$state',
        function ($scope, $http, cancionContext, $state) {
            var idCancion = $state.params.cancionId;
            $scope.deleteCancion = function () {
                $http.delete(cancionContext + '/' + idCancion, {}).then(function (response) {
                    $state.go('cancionList', {cancionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);