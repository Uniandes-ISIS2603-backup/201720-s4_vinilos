(function (ng) {

    var mod = ng.module("viniloModules");

    mod.controller("viniloCtrl", ['$scope', '$state', '$stateParams', '$http', 'viniloContext', '$sce', function ($scope, $state, $stateParams, $http, context, $sce) {

            // inicialmente el listado de vinilos está vacio
            $scope.vinilos = {};
            var vinilos;
            $scope.trustSrcurl = function (data)
            {
                return $sce.trustAsResourceUrl(data);
            }

            $scope.getProveedor = function (num) {
                console.log("entro");
                if (num !== undefined) {


                    var currentProveedor;
                    console.log("num:" + num);


                    $http.get("api/proveedores" + "/" + num).then(function (response) {
                        currentProveedor = response.data;
                        $scope.proveedorFull = response.data;
                    });

                    console.log("curr:" + currentProveedor);

                }
            };
            // carga los vinilos
            $http.get(context).then(function (response) {
                $scope.vinilos = response.data;
                vinilos = response.data;
                var str = $stateParams.viniloNombreP;
                var pos = 0;
                var seguir = true;
                for (var i = 0; seguir && (i < vinilos.length); i++)
                {
                    if (vinilos[i].nombre === str)
                    {
                        pos = i;
                        seguir = false;
                    }
                }
                var idProv = 0;
                $http.get(context + "/" + str)
                        .then(function (response) {
                            idProv = response.data.proveedor.id;
                        });

                var x = true;
                var modalShow = function ()
                {

                    $(document).ready(function () {
                        var func = $scope.getProveedor;
                        console.log("odprov:" + idProv);
                        if (!x) {
                            func(idProv);
                        }
                        console.log("ready" + pos);
                        $("#myModal" + pos).modal("show");
                        console.log("read2");
                    });
                    if (x) {
                        setTimeout(modalShow, 5500);
                    }
                    x = false;



                };

                if ($stateParams.viniloNombreP !== null && $stateParams.viniloNombreP !== undefined)
                {
                    var nombre = $stateParams.viniloNombreP;
                    console.log(nombre);
                    modalShow();
                }

            });


            // el controlador recibió un viniloId ??
            // revisa los parámetros (ver el :viniloId en la definición de la ruta)
            if ($stateParams.viniloId !== null && $stateParams.viniloId !== undefined) {

                // toma el id del parámetro
                var id = $stateParams.viniloId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentVinilo = response.data;
                            $scope.viniloNombre = response.data.nombre;
                            $scope.viniloAnio = response.data.anio;
                            $scope.viniloPrecio = response.data.precio;
                            $scope.viniloCantUnidades = response.data.cantUnidades;
                            $scope.info = response.data.infos[0];
                            $scope.viniloCanciones = response.data.canciones;


                        });


                // el controlador no recibió un cityId
            } else {
                // el registro actual debe estar vacio
                $scope.currentVinilo = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    nombre: '' /*Tipo String*/,
                    anio: 0 /*Tipo Integer.*/,
                    precio: 0 /*Tipo Double.*/,
                    cantUnidades: 0 /*Tipo Integer.*/
                };

                $scope.alerts = [];
            }


            this.editVinilo = function () {
                // ejecuta PUT en el recurso REST
                return $http.put(context + "/" + $scope.currentVinilo.id, {
                    nombre: $scope.viniloNombre,
                    anio: $scope.viniloAnio,
                    precio: $scope.viniloPrecio,
                    cantUnidades: $scope.viniloCantUnidades
                })
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('viniloList');
                        });
            };

            this.deleteVinilo = function (vinilo) {
                return $http.delete(context + "/" + $stateParams.viniloId)
                        .then(function () {
                            // $http.delete es una promesa
                            // cuando termine bien, cambie de estado
                            var index = $scope.vinilos.indexOf(vinilo);
                            if (index > -1) {
                                $scope.vinilos.splice(index, 1);
                            }
                            $state.go('viniloList');
                        });
            };
            this.addToCart = function (id) {

                var decodedCookie = decodeURIComponent(document.cookie);
                var ca = decodedCookie.split('=');


                return $http.post("api/usuarios/" + ca[1] + "/carroCompras/" + id)
                        .then(function () {

                            $state.go('viniloList');
                        });
            }

//
//// Código continua con las funciones de despliegue de errores
//
//
        }]);
})(window.angular);
