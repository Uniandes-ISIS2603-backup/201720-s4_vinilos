(function (ng) {
var mod = ng.module("usuarioModules", []);
    mod.constant("usuarioContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Usuario/';
            $stateProvider.state('usuarioList', {
                url: '/usuarios',
                views: {
                    'mainView': {
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuario.list.html'
                    }
                }
            })
                   .state('usuarioSee', {
                url: '/usuarios/:usuarioId',
                param:{
                    usuarioId: null
                },
                views: {
                    'mainView': {
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuario.see.html'
                    }
                }
            })
            .state('usuarioEdit', {
                url: '/usuarios/:usuarioId',
                param:{
                    proveedorId: null
                },
                views: {
                    'mainView': {
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuario.edit.html'
                    }
                }
            })
                    .state('usuarioPost',{
                        url:'/usuarios',
                views: {
                    'mainView': {
                        controller: 'usuarioCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'usuario.edit.html'
                    }
                }
            }
                    )
        }]);

})(window.angular);


