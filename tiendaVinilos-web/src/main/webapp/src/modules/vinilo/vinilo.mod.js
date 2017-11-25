(function (ng) {
    var mod = ng.module("viniloModules", []);
    mod.constant("viniloContext", "api/vinilos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/vinilo/';
            var basePathCancion = 'src/modules/cancion/';
            $stateProvider.state('viniloList', {
                url: '/vinilos',
                views: {
                    'mainView': {
                        controller: 'viniloCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vinilo.list.html'
                    }
                }
            }).state('viniloSee', {
                url: '/vinilos/:viniloId',
                param: {
                    viniloId: null,
                    usuarioId: 943
                },
                views: {
                    'mainView': {
                        controller: 'viniloCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vinilo.see.html'
                    }
                }
            }).state('viniloEdit', {
                url: '/vinilos/:viniloId',
                param: {
                    viniloId: null
                },
                views: {
                    'mainView': {
                        controller: 'viniloCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vinilo.edit.html'
                    }
                }
            }).state('viniloPost', {
                url: '/vinilos',
                views: {
                    'mainView': {
                        controller: 'viniloCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vinilo.edit.html'
                    }
                }
            }).state('viniloCancionCreate', {
                url: '/vinilos/{idVinilo:int}/createCancion',
                params:
                        {
                            idVinilo: null
                        },
                views: {
                    'mainView': {
                        controller: 'viniloCreateCancionCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'viniloCancion.new.html'
                    }
                }
            }
            ).state('viniloArtistaCreate', {
                url: '/vinilos/{idVinilo:int}/createArtista',
                params:
                        {
                            idVinilo: null
                        },
                views: {
                    'mainView': {
                        controller: 'viniloCreateArtistaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'viniloArtista.new.html'
                    }
                }
            }
            );
        }]);

})(window.angular);


