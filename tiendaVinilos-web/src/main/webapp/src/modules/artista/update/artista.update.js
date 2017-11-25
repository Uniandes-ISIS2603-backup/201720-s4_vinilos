(
        function (ng) {
            var mod = ng.module("artistaModule");
            mod.constant("artistaContext", "api/artista");
            mod.controller('artistaUpdateCtrl', ['$scope', '$http', 'artistaContext', '$state',  '$rootScope', '$filter',
                function ($scope, $http, artistaContext, $state,  $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idArtista = $state.params.artistaId;

                  

                   
                    //Consulto el autor a editar.
                    $http.get(artistaContext + '/' + idArtista).then(function (response) {
                        var artista = response.data;
                        $scope.artistaName = artista.name;
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

                    $scope.createArtista = function () {

                        $http.put(artistaContext + "/" + idArtista, {
                            name: $scope.artistaName,
                        }).then(function (response) {
                            $state.go('artistaList', {artistaId: response.data.id}, {reload: true});
                            if (idsBook.length >= 0) {
                                $http.put(artistaContext + "/" + response.data.id + "/books", $scope.allBooksArtista).then(function (response) {
                                });
                            }
                            //Artista created successfully
                            
                        });
                    };

                }
            ]);
        }
)(window.angular);
