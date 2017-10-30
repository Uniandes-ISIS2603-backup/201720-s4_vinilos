(function (ng) {
var mod = ng.module("feedbacksModules", []);
    mod.constant("feedbacksContext", "api/feedbacks");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/';
            $urlRouterProvider.otherwise("/feedbacksList");
            $stateProvider.state('feedbacksList', {
                url: '/feedbacks',
                views: {
                    'mainView': {
                        controller: 'feedbacksCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'feedbacks.list.html'
                    }
                }
            })
        }]);

})(window.angular);

