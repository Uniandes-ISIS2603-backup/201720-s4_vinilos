(function (ng) {
var mod = ng.module("carritoModules", []);
    mod.constant("carritoContext", "api/usuarios/");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Carrito/';
            $stateProvider.state('carritoUsuario', {
                url: '/usuarios/{id}/carroCompras',
                params:{
                    id:null
                },
                views: {
                    'mainView': {
                        controller: 'carroCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'carro.html'
                    }
                }
            })
        }]);

})(window.angular);


