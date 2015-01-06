(function() {
    var app = angular.module('wickedBoardApp', ['ui.bootstrap', 'ngRoute', 'LocalStorageModule', 'wickedBoardServices']);
    app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'pages/topic.html',
            controller: 'TopicController'
        }).when('/topic/:topicId', {
            templateUrl: 'pages/topic.html',
            controller: 'TopicController'
        });
    }]);

    app.controller('ModalController', ['$scope', '$modalInstance', 'data', function ($scope, $modalInstance, data) {
        this.modalData = (data && data.modalData) ? data.modalData : {};
        $scope.inputData = data;
        this.ok = function () {
            $modalInstance.close(this.modalData);
        };

        this.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }]);
    app.controller('UserController', ['$rootScope', '$scope', 'localStorageService', '$modal', '$log', 'User', function($rootScope, $scope, localStorageService, $modal, $log, User) {
        //check for stored user
        $rootScope.user = localStorageService.get('user');
        if (!$rootScope.user) {
            $rootScope.user = {
                userData: {},
                isAuth: false
            };
        }
        this.login = function(email, password) {
            if (email !== '' && password !== '') {
                $rootScope.user.userData = User.login({email: email}, password,
                    function () {//success
                        $rootScope.user.isAuth = true;
                        localStorageService.set('user', $rootScope.user);
                    },
                    function () {//error
                        $rootScope.user = {
                            userData: {},
                            isAuth: false
                        };
                        localStorageService.remove('user');
                        $log.info("invalid credentials");
                    });
            }
        };
        this.logout = function() {
            $rootScope.user = {
                userData: {},
                isAuth: false
            };
            localStorageService.remove('user');
        };
        this.addUser = function(email, name, password) {
            var userData = {
                email: email,
                name: name,
                password: password
            };
            $rootScope.user.userData = User.save(userData,
                function() {//success
                    $rootScope.user.isAuth = true;
                    localStorageService.set('user', $rootScope.user);
                },
                function () {//error
                    $rootScope.user = {
                        userData: {},
                        isAuth: false
                    };
                    localStorageService.remove('user');
                    $log.info("couldn't sign up");
                });
        };

        this.showLogin = function () {
            if (!$rootScope.user.isAuth) {
                var modalInstance = $modal.open({
                    templateUrl: 'pages/login.html',
                    controller: 'ModalController as modalCtrl',
                    resolve: {data: function () { return null; }}
                });
                modalInstance.result.then(function (modalData) {
                    $scope.userCtrl.login(modalData.email, modalData.password);
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            }
        };
        this.showSignUp = function () {
            if (!$rootScope.user.isAuth) {
                var modalInstance = $modal.open({
                    templateUrl: 'pages/signup.html',
                    controller: 'ModalController as modalCtrl',
                    resolve: {data: function () { return null; }}
                });
                modalInstance.result.then(function (modalData) {
                    $scope.userCtrl.addUser(modalData.email, modalData.name, modalData.password);
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            }
        };
    }]);
    app.controller('TopicController', ['$rootScope', '$scope', '$routeParams', '$modal', '$log', 'Topic', 'SubTopic', function ($rootScope, $scope, $routeParams, $modal, $log, Topic, SubTopic) {
        this.name = 'Topics';
        if ($routeParams.topicId) {
            this.topics = SubTopic.query({id: $routeParams.topicId});
        }
        else {
            this.topics = Topic.query();
        }
        this.userIsAuth = function () {
            return $rootScope.user.isAuth;
        }
        this.delTopic = function (topic) {
            if (this.userIsAuth() && topic) {
                Topic.delete({id: topic.id},
                    function() {//success
                        //remove from topics
                        var index = $scope.topicCtrl.topics.indexOf(topic);
                        if (index >= 0){
                            $scope.topicCtrl.topics.splice(index, 1);
                        }
                    },
                    function () {//error
                        $log.info("couldn't delete topic");
                    });
            }
        }
        this.saveTopic = function (topicId, parentId, title) {
            if (this.userIsAuth()) {
                var topic = {
                    id: topicId,
                    parentId : parentId,
                    title: title
                };
                if (topicId) {
                    Topic.update({id: topicId}, {parentId: parentId, title: title},
                        function(data) {//success
                            if (data) {
                                //update topics
                                var topics = $scope.topicCtrl.topics;
                                for (i = topics.length - 1; i >= 0; i--) {
                                    if (topics[i].id === data.id){
                                        if (!$routeParams.topicId || $routeParams.topicId != data.parent.id) {
                                            //remove from current topics
                                            $scope.topicCtrl.topics.splice(i, 1);
                                        }
                                        else {
                                            //update data in current topics
                                            topics[i] = data;
                                        }
                                    }
                                }
                            }
                        },
                        function () {//error
                            $log.info("couldn't update topic");
                        });
                }
                else {
                    Topic.save(topic,
                        function(data) {//success
                            if (data) {
                                if (($routeParams.topicId && $routeParams.topicId === data.parent.id)
                                    || (!$routeParams.topicId && !data.parent.id)){
                                    //add to topics
                                    $scope.topicCtrl.topics.push(data);
                                }
                            }
                        },
                        function () {//error
                            $log.info("couldn't update topic");
                        });
                }
            }
        }
        this.showTopic = function (topicId) {
            if (this.userIsAuth()) {
                var topic = {};
                if (topicId){
                    topic = Topic.get({id: topicId},
                        function() {//success
                        },
                        function () {//error
                            $log.info("couldn't get topic");
                        });
                }

                var modalInstance = $modal.open({
                    templateUrl: 'pages/topicForm.html',
                    controller: 'ModalController as modalCtrl',
                    resolve: {
                        data: function () {
                            return {
                                title: (topicId) ? 'Edit Topic' : 'New Topic',
                                topics: $scope.topicCtrl.topics,
                                modalData: {
                                    topic: topic
                                }
                            };
                        }
                    }
                });
                modalInstance.result.then(function (modalData) {
                    $scope.topicCtrl.saveTopic(modalData.topic.id,
                        (modalData.topic.parent) ? modalData.topic.parent.id : null,
                        modalData.topic.title);
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            }
        };
    }]);
})();