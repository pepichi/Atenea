/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


angular.module('categoria', ['ngSanitize', 'ui.grid', 'ngTouch', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav', 'ui.grid.selection']);

angular.module('categoria').controller('categoriaController', function ($scope, $http, $q) {
    $scope.gridOptions = {
        enableFiltering: true,
        enableRowSelection: true,
        multiSelect: true
    };
    $http({
        method: 'POST',
        url: '/Atenea/Servlet/ListadoCategoriaServlet',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        $scope.gridOptions.data = response.data;
    });
    $scope.saveRow = function (rowEntity) {
        let indice = $scope.gridOptions.data.indexOf(rowEntity);
        alert(indice);
        var deferred = $q.defer();
        var promise = $http({
            method: 'POST',
            url: '/Atenea/Servlet/ActualizarCategoriaServlet',
            data: rowEntity,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            $scope.gridOptions.data[indice] = response.data;
            deferred.resolve(response.data);
        }).catch(function (error) {
            console.error("Error al guardar la fila en el servidor:", error);
            deferred.reject(error);
        });

        $scope.gridApi.rowEdit.setSavePromise(rowEntity, promise);
    };
    $scope.gridOptions.onRegisterApi = function (gridApi) {
        //set gridApi on scope
        $scope.gridApi = gridApi;
        gridApi.rowEdit.on.saveRow($scope, $scope.saveRow);
    };
    $scope.anadirFila = function () {
        $scope.gridOptions.data.push({});
    };

    $scope.borrarFilasSeleccionadas = function () {
        var selectedRows = $scope.gridApi.selection.getSelectedRows();
        selectedRows.forEach(function (row) {
            var index = $scope.gridOptions.data.indexOf(row);
            $scope.gridOptions.data.splice(index, 1);
        });
         var promise = $http({
            method: 'POST',
            url: '/Atenea/Servlet/BorrarCategoriaServlet',
            data: selectedRows,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            deferred.resolve(response.data);
        }).catch(function (error) {
            console.error("Error al borrar las filas en el servidor:", error);
            deferred.reject(error);
        });
    };
});