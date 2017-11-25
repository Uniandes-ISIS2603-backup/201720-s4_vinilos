(function (ng) {
var mod = ng.module("tarjetaModules", []);
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.config(['$stateProvider', function ($stateProvider) {
            var basePath = 'src/modules/tarjeta2/';
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
                    tarjetaId: null
                },
                views: {
                    'mainView': {
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'tarjeta.see.html'
                    }
                }
            })
            .state('tarjetaEdit', {
                url: '/tarjetas/:tarjetaId',
                param:{
                    tarjetaId: null
                },
                views: {
                    'mainView': {
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'tarjeta.edit.html'
                    }
                }
            })
                    .state('tarjetaPost',{
                        url:'/tarjetas',
                views: {
                    'mainView': {
                        controller: 'tarjetaCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'tarjeta.edit.html'
                    }
                }
            }
                    )
        }]);

})(window.angular);


