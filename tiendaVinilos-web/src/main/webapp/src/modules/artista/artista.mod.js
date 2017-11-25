(function (ng) {
    // Definición del módulo
    var mod = ng.module("artistaModule", ['ui.router']);
        mod.constant("artistaContext", "api/artista");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/artista/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            // Definición del estado 'artistaList' donde se listan los autores
            $stateProvider.state('artista', {
                url: '/artista',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'artista.html',
                        controller: 'artistaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('artistaList', {
                url: '/list',
                parent: 'artista',
                views: {
                    'listView': {
                        templateUrl: basePath + 'artista.list.html'
                    }
                }
            }).state('artistaDetail', {
                url: '/{artistaId:int}/detail',
                parent: 'artista',
                param: {
                    artistaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'artista.detail.html',
                        controller: 'artistaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('artistaCreate', {
                url: '/create',
                parent: 'artista',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/artista.new.html',
                        controller: 'artistaNewCtrl'
                    }
                }
            }).state('artistaUpdate', {
                url: '/update/{artistaId:int}',
                parent: 'artista',
                param: {
                    artistaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/artista.new.html',
                        controller: 'artistaUpdateCtrl'
                    }
                }
            }).state('artistaDelete', {
                url: '/delete/{artistaId:int}',
                parent: 'artista',
                param: {
                    artistaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/artista.delete.html',
                        controller: 'artistaDeleteCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


