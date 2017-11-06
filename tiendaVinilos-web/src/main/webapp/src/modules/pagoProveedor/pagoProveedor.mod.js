(function (ng) {
    var mod = ng.module("pagoProveedorModules", []);
    mod.constant("pagoProveedorContext", "api/pagoProveedor");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pagoProveedor/';
            $stateProvider
                    .state('pagoProveedorList',
                            {
                                url: '/pagoProveedores',
                                views: {
                                    'mainView': {
                                        controller: 'pagoProveedoresCtrl',
                                        controllerAs: 'ctrl',
                                        templateUrl: basePath + 'pagoProveedor.list.html'
                                    }
                                }}
                    )
            
             .state('pagoProveedorSee', {
                url: '/pagoProveedores/:pagoProveedoresId',
                param:{
                    tarjetaId: null
                },
                views: {
                    'mainView': {
                        controller: 'pagoProveedoresCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pagoProveedor.see.html'
                    }
                }
            })
        }]);

})(window.angular);

