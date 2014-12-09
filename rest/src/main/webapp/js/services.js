(function() {

    var services = angular.module('wickedBoardServices', ['ngResource']);

    services.factory('$topic', function ($resource) {
        return $resource('rest/topics/:id', {
            'update': {method: 'PUT'}
        });
    });

})();
