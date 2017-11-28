(function (ng) {
    var mod = ng.module("cancionModule", ['ui.router']);
    mod.constant("cancionContext", "api/cancion");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/cancion/';
            $stateProvider.state('cancion', {
                url: '/cancion',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'cancion.html',
                        controller: 'cancionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('cancionList', {
                url: '/list',
                parent: 'cancion',
                views: {
                    'listView': {
                        templateUrl: basePath + 'cancion.list.html'
                    }
                }
            }).state('cancionDetail', {
                url: '/{cancionId:int}/detail',
                parent: 'cancion',
                param: {
                    cancionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'cancion.detail.html',
                        controller: 'cancionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('cancionCreate', {
                url: '/create',
                parent: 'cancion',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/cancion.new.html',
                        controller: 'cancionNewCtrl'
                    }
                }
            }).state('cancionUpdate', {
                url: '/update/{cancionId:int}',
                parent: 'cancion',
                param: {
                    cancionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/cancion.new.html',
                        controller: 'cancionUpdateCtrl'
                    }
                }
            }).state('cancionDelete', {
                url: '/delete/{cancionId:int}',
                parent: 'cancion',
                param: {
                    cancionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/cancion.delete.html',
                        controller: 'cancionDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);