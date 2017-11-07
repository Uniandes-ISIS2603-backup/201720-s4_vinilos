(function (ng) {
    var mod = ng.module("artistaModule");
    mod.constant("artistaContext", "api/artista");
    mod.controller('artistaNewCtrl', ['$scope', '$http', 'artistaContext', '$state',  '$rootScope',
        function ($scope, $http, artistaContext, $state,  $rootScope) {
            $rootScope.edit = true;
            $scope.createArtista = function () {
                $http.post(artistaContext, {
                    name: $scope.artistaName,
                }).then(function (response) {
                    //Artista created successfully
                    $state.go('artistaList', {artistaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);