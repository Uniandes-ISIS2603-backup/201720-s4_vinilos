(function (ng) {
var mod = ng.module("pagoClienteModules", []);
    mod.constant("pagoClienteContext", "api/pagoCliente");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagoCliente/';
            $urlRouterProvider.otherwise("/pagoClienteList");
            $stateProvider.state('pagoCliente', {
                url: '/pagoCliente',
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'pagoClienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pagoCliente.html'
                    }
                }
            }).state('pagoClienteList', {
                url: '/pagoCliente',
                parent: 'pagoCliente',
                views: {
                    'mainView': {
                        controller: 'pagoClienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pagoCliente.list.html'
                    }
                }
            }).state('pagoClienteDetail', {
                url: '/{pagoClienteId:int}/pagoClienteDetail',
                parent: 'pagoCliente',
                param: {
                    pagoClienteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'pagoCliente.see.html',
                     controller: 'pagoClienteCtrl',
                    controllerAs: 'ctrl'
                    }
                }

            }).state('pagoClienteCreate', {
                url: '/pagoClienteCreate',
                parent: 'pagoCliente',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/pagoCliente.new.html',
                        controller: 'pagoClienteNewCtrl'
                    }
                }
            }).state('pagoClienteDelete', {
                url: '/delete/{pagoClienteId:int}',
                parent: 'pagoCliente',
                param: {
                    pagoClienteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/pagoCliente.delete.html',
                        controller: 'pagoClienteDeleteCtrl'
                    }
                }
            });
        }]);

})(window.angular);