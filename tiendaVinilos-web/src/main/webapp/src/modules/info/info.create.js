(function (ng) {

    var mod = ng.module("infoModules");
    mod.controller("infoCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'infoContext', function ($scope, $state, $stateParams, $http, context) {

             this.editInfo = function(){
                return $http.post(context , {
                      id: $scope.infoId,
                      descripcion: $scope.infoDescripcion,
                        url: $scope.infoUrl,
                       rutas: $scope.infoRutas,
                    }).then(function () {
                                $state.go('infoList');
                            });
                };   

        }]);
})(window.angular);