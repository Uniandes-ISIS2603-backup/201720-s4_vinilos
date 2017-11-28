(function (ng) {
    var mod = ng.module("tarjetaModules", []);
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/tarjeta2/';
            $stateProvider.state('tarjetaUsuario', {
                url: '/usuarios/{usuarioId}/tarjetas',
                param: {
                    usuarioId: null
                },
                views: {
                    'mainView': {
                        controller: 'tarjetaUsuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'tarjeta.list.html'
                    }
                }
            }).state('tarjetaSee', {
                        url: '/tarjetas/:tarjetaId',
                        param: {
                            usuarioId: null,
                            tarjetaId: null
                        },
                        views: {
                            'mainView': {
                                controller: 'tarjetaUsuarioCtrl',
                                controllerAs: 'ctrl',
                                templateUrl: basePath + 'tarjeta.see.html'
                            }
                        }
                    })
                    .state('tarjetaEdit', {
                        url: '/tarjetas/:tarjetaId',
                        param: {
                            usuarioId: null,
                            tarjetaId: null
                        },
                        views: {
                            'mainView': {
                                controller: 'tarjetaUsuarioCtrl',
                                controllerAs: 'ctrl',
                                templateUrl: basePath + 'tarjeta.edit.html'
                            }
                        }
                    })
                    .state('tarjetaPost', {
                        url: '/tarjetas',
                        param: {
                            usuarioId: null,
                        },
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


