(function (ng) {
var mod = ng.module("tarjetaModules", []);
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Tarjeta/';
            $stateProvider.state('tarjetaList', {
                url: '/tarjetas',
                views: {
                    'mainView': {
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'tarjeta.list.html'
                    }
                }
            })
                   .state('tarjetaSee', {
                url: '/tarjetas/:tarjetaId',
                param:{
                    usuarioId: null
                },
                views: {
                    'mainView': {
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'tarjeta.see.html'
                    }
                }
            })
        }]);

})(window.angular);
