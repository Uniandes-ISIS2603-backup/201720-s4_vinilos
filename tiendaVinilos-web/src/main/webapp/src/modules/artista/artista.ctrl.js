(function (ng) {

    var mod = ng.module("artistaModule");

    mod.controller("artistaCtrl", ['$scope', '$state', '$stateParams', '$http', 'artistaContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de ciudades está vacio
            $scope.recordsArtista = {};
            // carga las ciudades
            $http.get(context).then(function (response) {
                $scope.recordsArtista = response.data;
            });

            // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.artistaId !== null && $stateParams.artistaId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.artistaId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentArtista = response.data;
                        });

                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.currentArtista = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }


        }]);
})(window.angular);

