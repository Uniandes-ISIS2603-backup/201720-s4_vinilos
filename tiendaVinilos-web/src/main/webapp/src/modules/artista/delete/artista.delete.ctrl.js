(function (ng) {
    var mod = ng.module("artistaModule");
    mod.constant("artistaContext", "api/artista");
    mod.controller('artistaDeleteCtrl', ['$scope', '$http', 'artistaContext', '$state',
        function ($scope, $http, artistaContext, $state) {
            var idArtista = $state.params.artistaId;
            $scope.deleteArtista = function () {
                $http.delete(artistaContext + '/' + idArtista, {}).then(function (response) {
                    $state.go('artistaList', {artistaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);