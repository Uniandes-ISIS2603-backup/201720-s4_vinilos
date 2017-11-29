(function (ng) {
var mod = ng.module("viniloModules", []);
    mod.constant("viniloContext", "api/vinilos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/vinilo/';
            $stateProvider.state('viniloList', {
                url: '/vinilos',
                param:
                        {
                            viniloNombreP:null
                        },
                views: {
                    'mainView': {
                        controller: 'viniloCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vinilo.list.html'
                    }
                }
            })
                   .state('viniloSee', {
                url: '/vinilos/:viniloId/:viniloNombreP',
                param:{
                    viniloId: null,
                    usuarioId:943,
                    viniloNombreP: null
                    
                },
                views: {
                    'mainView': {
                        controller: 'viniloCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vinilo.see.html'
                    }
                }
            })
            .state('viniloEdit', {
                url: '/vinilos/:viniloId',
                param:{
                   viniloId: null
                },
                views: {
                    'mainView': {
                        controller: 'viniloCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vinilo.edit.html'
                    }
                }
            })
                    .state('viniloPost',{
                        url:'/vinilos/create',
                views: {
                    'mainView': {
                        controller: 'viniloCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vinilo.edit.html'
                    }
                }
            }
                    )
            .state('createCancion', {
                        url: '/vinilos/:viniloId/cancion',
                        params:{
                            viniloId:null
                        },
                        views: {
                            'mainView': {
                                controller: 'viniloCreateCancionCtrl',
                                controllerAs: 'ctrl',
                                templateUrl: basePath + 'viniloCancion.new.html'
                            }
                        }
                    }
                    )
            
        }]);

})(window.angular);


