(function (ng) {
    var mod = ng.module("proveedorModules", []);
    mod.constant("proveedorContext", "api/proveedores");
    mod.config(['$stateProvider', function ($stateProvider) {
            var basePath = 'src/modules/proveedor/';
            $stateProvider
                    .state('proveedorList',
                            {
                                url: '/proveedores',
                                views: {
                                    'mainView': {
                                        controller: 'proveedorCtrl',
                                        controllerAs: 'ctrl',
                                        templateUrl: basePath + 'proveedor.list.html'
                                    }
                                }}
                    ).state('proveedorSee', {
                url: '/proveedores/:proveedorId',
                param: {
                    proveedorId: null
                },
                views: {
                    'mainView': {
                        controller: 'proveedorCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'proveedor.see.html'
                    }
                }
            }).state('proveedorEdit', {
                url: '/proveedor/:proveedorId',
                param: {
                    proveedorId: null
                },
                views: {
                    'mainView': {
                        controller: 'proveedorCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'proveedor.edit.html'
                    }
                }
            }).state('proveedorCreate', {
                url: '/proveedores',
                views: {
                    'mainView': {
                        controller: 'proveedorCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'proveedor.edit.html'
                    }
                }
            }).state('viniloCreate', {
                url: '/proveedores/:proveedorId/vinilos',
                views: {
                    'mainView': {
                        controller: 'proveedorCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'proveedor.agregarVinilo.html'
                    }
                }
            }).state('modificarVinilo', {
                url: '/proveedores/:proveedorId/vinilos/:viniloId',
                 param: {
                    viniloId: null
                },
                views: {
                    'mainView': {
                        controller: 'proveedorCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'proveedor.agregarVinilo.html'
                    }
                }
            })
        }]);

})(window.angular);

