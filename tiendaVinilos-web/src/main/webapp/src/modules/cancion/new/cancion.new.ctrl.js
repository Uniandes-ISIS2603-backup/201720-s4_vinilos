(function (ng) {
    var mod = ng.module("cancionModule");
    mod.constant("cancionContext", "api/cancion");
    mod.controller('cancionNewCtrl', ['$scope', '$http', 'cancionContext', '$state',  '$rootScope',
        function ($scope, $http, cancionContext, $state,  $rootScope) {
            $rootScope.edit = false;
            $scope.createCancion = function () {
                $http.post(cancionContext, {
                    name: $scope.cancionName,
                    duracion: $scope.cancionDuracion,
                }).then(function (response) {
                    //Cancion created successfully
                    $state.go('cancionList', {cancionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);