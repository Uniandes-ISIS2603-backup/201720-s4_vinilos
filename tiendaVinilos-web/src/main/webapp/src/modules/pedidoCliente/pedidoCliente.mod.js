(function (ng) {
var mod = ng.module("pedidoClienteModules", []);
    mod.constant("pedidoClienteContext", "api/pedidoCliente");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pedidoCliente/';
            $urlRouterProvider.otherwise("/pedidoClienteList");
            $stateProvider.state('pedidoCliente', {
                url: '/pedidoCliente',
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'pedidoClienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pedidoCliente.html'
                    }
                }
            }).state('pedidoClienteList', {
                url: '/pedidoClienteList',
                parent: 'pedidoCliente',
                views: {
                    'listView': {
                        templateUrl: basePath + 'pedidoCliente.list.html'
                    }
                }
            }).state('pedidoClienteDetail', {
                url: '/{pedidoClienteId:int}/pedidoClienteDetail',
                parent: 'pedidoCliente',
                param: {
                    pedidoClienteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'pedidoCliente.see.html',
                     controller: 'pedidoClienteCtrl',
                    controllerAs: 'ctrl'
                    }
                }

            }).state('pedidoClienteCreate', {
                url: '/pedidoClienteCreate',
                parent: 'pedidoCliente',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/pedidoCliente.new.html',
                        controller: 'pedidoClienteNewCtrl'
                    }
                }
            }).state('pedidoClienteDelete', {
                url: '/delete/{pedidoClienteId:int}',
                parent: 'pedidoCliente',
                param: {
                    pedidoClienteId: null
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