(function (ng) {
var mod = ng.module("viniloModules", []);
    mod.constant("viniloContext", "api/vinilos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/vinilo/';
            $stateProvider.state('viniloList', {
                url: '/vinilos',
                views: {
                    'mainView': {
                        controller: 'viniloCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vinilo.list.html'
                    }
                }
            })
                   .state('viniloSee', {
                url: '/vinilos/:viniloId',
                param:{
                    viniloId: null
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
                        url:'/vinilos',
                views: {
                    'mainView': {
                        controller: 'viniloCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vinilo.edit.html'
                    }
                }
            }
                    )
        }]);

})(window.angular);


