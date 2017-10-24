(function (ng) {
var mod = ng.module("proveedorModules", []);
    mod.constant("proveedorContext", "api/proveedores");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/proveedor/';
            $urlRouterProvider.otherwise("/proveedorList");
            $stateProvider.state('proveedorList', {
                url: '/proveedores',
                views: {
                    'mainView': {
                        controller: 'proveedorCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'proveedor.list.html'
                    }
                }
            }).state('proveedorSee', {
                url: '/proveedores/:proveedorId',
                param:{
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
                param:{
                    proveedorId: null
                },
                views: {
                    'mainView': {
                        controller: 'proveedorCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'proveedor.edit.html'
                    }
                }
            })
        }]);

})(window.angular);

