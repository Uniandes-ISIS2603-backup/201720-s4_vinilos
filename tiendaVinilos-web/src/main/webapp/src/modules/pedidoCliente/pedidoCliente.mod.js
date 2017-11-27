(function (ng) {
    var mod = ng.module("pedidoClienteModules", ['ui.router']);
    mod.constant("pedidoClienteContext", "api/pedidocliente");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pedidoCliente/';
            var basePathPago = 'src/modules/pagoCliente/';
            $urlRouterProvider.otherwise("/pedidoClienteList");
            $stateProvider.state('pedidoCliente', {
                url: '/pedidoCliente',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'pedidoCliente.html',
                        controller: 'pedidoClienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pedidoClienteList', {
                url: '/list',
                parent: 'pedidoCliente',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pedidoCliente.list.html'
                    }
                }
            }).state('pedidoClienteDetail', {
                url: '/{pedidoId:int}/detail',
                parent: 'pedidoCliente',
                param: {
                    pedidoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePathPago + 'pagoCliente.list.html',
                        controller: 'pedidoClienteCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'pedidoCliente.detail.html',
                        controller: 'pedidoClienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pedidoClienteCreate', {
                url: '/create',
                parent: 'pedidoCliente',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/pedidoCliente.new.html',
                        controller: 'pedidoClienteNewCtrl'
                    }
                }
            }).state('pedidoClienteUpdate', {
                url: '/update/{pedidoId:int}',
                parent: 'pedidoCliente',
                param: {
                    pedidoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/pedidoCliente.new.html',
                        controller: 'pedidoClienteUpdateCtrl'
                    }
                }
            }).state('pedidoClienteDelete', {
                url: '/delete/{pedidoId:int}',
                parent: 'pedidoCliente',
                param: {
                    pedidoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/pedidoCliente.delete.html',
                        controller: 'pedidoClienteDeleteCtrl'
                    }
                }
            });
        }]);
})(window.angular);
