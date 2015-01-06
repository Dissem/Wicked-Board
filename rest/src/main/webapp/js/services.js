(function() {

    var services = angular.module('wickedBoardServices', ['ngResource']);

    services.factory('Topic', function ($resource) {
        return $resource('rest/topics/:id',
            null,
            {'update': {method: 'PUT'}
        });
    });
    services.factory('SubTopic', function ($resource) {
        return $resource('rest/topics/:id/topics');
    });

    services.factory('User', function ($resource) {
        return $resource('rest/users/:email',
            null,
            {'login': {method: 'POST'}}
        );
    });
})();
