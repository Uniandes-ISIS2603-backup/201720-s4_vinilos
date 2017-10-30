(function (ng) {
var mod = ng.module("pedidoClienteModule", []);
    mod.constant("pedidoClienteContext", "api/pedidoCliente");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pedidoCliente/';
            $urlRouterProvider.otherwise("/pedidoClienteList");
            $stateProvider.state('pedidoClienteList', {
                url: '/pedidoCliente',
                views: {
                    'mainView': {
                        controller: 'pedidoClienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pedidoCliente.list.html'
                    }
                }
            }).state('pedidoClienteSee', {
                url: '/pedidoCliente/:pedidoClienteId',
                param:{
                    proveedorId: null
                },
                views: {
                    'mainView': {
                        controller: 'pedidoClienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pedidoCliente.see.html'
                    }
                }
            })
        }]);

})(window.angular);