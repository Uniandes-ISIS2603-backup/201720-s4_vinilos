(
        function (ng) {
            var mod = ng.module("cancionModule");
            mod.constant("cancionContext", "api/cancion");
            mod.controller('cancionUpdateCtrl', ['$scope', '$http', 'cancionContext', '$state',  '$rootScope', '$filter',
                function ($scope, $http, cancionContext, $state,  $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idCancion = $state.params.cancionId;

                  

                   
                    //Consulto el autor a editar.
                    $http.get(cancionContext + '/' + idCancion).then(function (response) {
                        var cancion = response.data;
                        $scope.cancionName = cancion.name;
                        $scope.cancionDuracion = cancion.duracion;
                    });





                    //funciones para el drag and drop de HTML5 nativo
                    $scope.allowDrop = function (ev) {
                        ev.preventDefault();
                    };

                    $scope.drag = function (ev) {
                        ev.dataTransfer.setData("text", ev.target.id);
                    };

                    $scope.dropAdd = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
  
 
                    };

                    $scope.dropDelete = function (ev) {
                        ev.preventDefault();
                        var data = ev.dataTransfer.getData("text");
                        ev.target.appendChild(document.getElementById(data));
                    };

                    $scope.createCancion = function () {

                        $http.put(cancionContext + "/" + idCancion, {
                            name: $scope.cancionName,
                            duracion: $scope.cancionDuracion,
                        }).then(function (response) {
                            $state.go('cancionList', {cancionId: response.data.id}, {reload: true});
                            if (idsBook.length >= 0) {
                                $http.put(cancionContext + "/" + response.data.id + "/books", $scope.allBooksCancion).then(function (response) {
                                });
                            }
                            //Cancion created successfully
                            
                        });
                    };

                }
            ]);
        }
)(window.angular);
