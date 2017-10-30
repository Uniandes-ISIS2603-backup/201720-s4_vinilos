(function (ng) {
var mod = ng.module("pagoClienteModule", []);
    mod.constant("pagoClienteContext", "api/pagoCliente");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagoCliente/';
            $urlRouterProvider.otherwise("/pagoClienteList");
            $stateProvider.state('pagoClienteList', {
                url: '/pagoCliente',
                views: {
                    'mainView': {
                        controller: 'pagoClienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pagoCliente.list.html'
                    }
                }
            }).state('pagoClienteSee', {
                url: '/pagoCliente/:pagoClienteId',
                param:{
                    proveedorId: null
                },
                views: {
                    'mainView': {
                        controller: 'pagoClienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pagoCliente.see.html'
                    }
                }
            })
        }]);

})(window.angular);