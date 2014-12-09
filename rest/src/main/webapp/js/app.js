(function() {

    var app = angular.module('wickedBoardApp', ['wickedBoardServices']);

    app.controller('TopicController', ['$topic', function($topic) {
        this.topics = $topic.query();
    }]);

})();
