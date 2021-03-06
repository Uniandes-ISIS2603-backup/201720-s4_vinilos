(function (ng) {
    var mod = ng.module("pedidoClienteModules", ['ui.router', 'ui.bootstrap']);
    mod.constant("pedidoClienteContext", "api/pedidocliente");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pedidoCliente/';
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
                url: '/usuarios/{id}/pedidos',
                params:{
                    id:null
                },
                views: {
                    'mainView': {
                        controller: 'pedidoClienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pedidoCliente.list.html'
                    }
                }
            }).state('pedidoClienteCreate', {
                url: '/create',
                parent: 'pedidoCliente',
                params:{
                    totalPedido:null
                },
                views: {
                    'detailView': {
                        controller: 'pedidoClienteNewCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + '/new/pedidoCliente.new.html'
                    }
                }
            }).state('pedidoClienteUpdate', {
                url: '/update/{pedidoClienteId}',
                parent: 'pedidoCliente',
                param: {
                    pedidoClienteId: null
                },
                views: {
                    'detailView': {
                        controller: 'pedidoClienteUpdateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + '/update/pedidoCliente.update.html'
                    }
                }
            }).state('pedidoClienteDelete', {
                url: '/delete/{pedidoClienteId}',
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
            }).state('pagoClienteDelete', {
              url: '/deletePago/{pedidoClienteId}',
              parent: 'pedidoCliente',
              param: {
                  pedidoClienteId: null
              },
              views: {
                  'detailView': {
                      templateUrl: basePath + '/delete/pagoCliente.delete.html',
                      controller: 'pedidoClienteDeleteCtrl'
                  }
              }
          });
        }]);
})(window.angular);
