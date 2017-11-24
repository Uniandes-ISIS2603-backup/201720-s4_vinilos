(function (ng) {

    var mod = ng.module("usuarioModules");
    mod.controller("usuarioCreateCtrl", ['$scope', '$state', '$stateParams', '$http', 'usuarioContext', function ($scope, $state, $stateParams, $http, context) {

             this.editUsuario = function(){
                return $http.post(context , {
                       name: $scope.usuarioName,
                        email: $scope.usuarioEmail
                    }).then(function () {
                                $state.go('usuarioList');
                            });
                };   

        }]);
})(window.angular);