(function (ng) {
    var mod = ng.module("viniloModule");
    mod.constant("vinilosContext", "api/vinilos");
    mod.controller('viniloCtrl', ['$scope', '$http', 'vinilosContext', '$state',
        function ($scope, $http, vinilosContext, $state) {
            $http.get(vinilosContext).then(function (response) {
                $scope.vinilosRecords = response.data;
            });

            if (($state.params.viniloId !== undefined) && ($state.params.viniloId !== null)) {
                $http.get(vinilosContext + '/' + $state.params.viniloId).then(function (response) {
                    $scope.currentVinilo = response.data;
                });
            }
        }
    ]);
}
)(window.angular);