(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
       
        // Internal modules dependencies       
        'proveedorModules',
        'usuarioModules',
        'tarjetaModules',
        'pedidoProveedorModules',
        'pagoProveedorModules',
        'viniloModules',
        'infoModules',
        
        'artistaModule',
        'cancionModule',
        'carritoModules'
    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);

