(function (ng) {
    var mod = ng.module("pedidoProveedorModules", []);
    mod.constant("pedidoProveedorContext", "api/pedidoProveedor");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pedidoProveedor/';
            $stateProvider
                    .state('pedidoProveedorList',
                            {
                                url: '/pedidoProveedores',
                                views: {
                                    'mainView': {
                                        controller: 'pedidoProveedoresCtrl',
                                        controllerAs: 'ctrl',
                                        templateUrl: basePath + 'pedidoProveedor.list.html'
                                    }
                                }}
                    )
            
             .state('pedidoProveedorSee', {
                url: '/pedidoProveedores/:pedidoProveedoresId',
                param:{
                    pedidoProveedoresId: null
                },
                views: {
                    'mainView': {
                        controller: 'pedidoProveedoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pedidoProveedor.see.html'
                    }
                }
            })
        }]);

})(window.angular);

