(function (ng) {
    var mod = ng.module("viniloModule", ['ui.router']);
    mod.constant("vinilosContext", "api/vinilos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/vinilo/';
            
            $urlRouterProvider.otherwise("/vinilosList");

            $stateProvider.state('vinilos', {
                url: '/vinilos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'vinilos.html',
                        controller: 'viniloCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('vinilosList', {
                url: '/list',
                parent: 'vinilos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'vinilos.list.html'
                    }
                }
            }).state('viniloDetail', {
                url: '/{viniloId:int}/detail',
                parent: 'vinilos',
                param: {
                    viniloId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'vinilos.detail.html',
                        controller: 'viniloCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('vinilosCreate', {
                url: '/create',
                parent: 'vinilos',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/vinilos.new.html',
                        controller: 'viniloNewCtrl'
                    }
                }
            }).state('viniloUpdate', {
                url: '/update/{viniloId:int}',
                parent: 'vinilos',
                param: {
                    viniloId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/vinilos.new.html',
                        controller: 'viniloUpdateCtrl'
                    }
                }
            }).state('viniloDelete', {
                url: '/delete/{viniloId:int}',
                parent: 'vinilos',
                param: {
                    viniloId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/vinilos.delete.html',
                        controller: 'viniloDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);