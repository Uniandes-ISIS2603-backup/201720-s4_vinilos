(function (ng) {
var mod = ng.module("infoModules", []);
    mod.constant("infoContext", "api/infos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/info/';
            $stateProvider.state('infoList', {
                url: '/infos',
                views: {
                    'mainView': {
                        controller: 'infoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'info.list.html'
                    }
                }
            })
                   .state('infoSee', {
                url: '/infos/:infoId',
                param:{
                    infoId: null
                },
                views: {
                    'mainView': {
                        controller: 'infoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'info.see.html'
                    }
                }
            })
            .state('infoEdit', {
                url: '/infos/:infoId',
                param:{
                   infoId: null
                },
                views: {
                    'mainView': {
                        controller: 'infoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'info.edit.html'
                    }
                }
            })
                    .state('infoPost',{
                        url:'/infos',
                views: {
                    'mainView': {
                        controller: 'infoCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'info.edit.html'
                    }
                }
            }
                    )
        }]);

})(window.angular);


