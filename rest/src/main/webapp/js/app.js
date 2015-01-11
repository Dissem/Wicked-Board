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
    app.controller('TopicController', ['$rootScope', '$scope', '$routeParams', '$modal', '$log', 'Topic', 'SubTopic', 'Post', function ($rootScope, $scope, $routeParams, $modal, $log, Topic, SubTopic, Post) {
        this.name = 'Topics';
        this.isSubTopic = false;

        /**
         * associative array with topicId as key and topicData as value
         * @type {{object}}
         */
        var me = this;
        this.postsForTopicId = {};
        if ($routeParams.topicId) {
            this.isSubTopic = true;
            this.topics = SubTopic.query({id: $routeParams.topicId}, function (data) {
                me.resolveParent(data);
            });
        }
        else {
            this.topics = Topic.query({}, function (data) {
                me.resolveParent(data);
            });
        }

        /**
         * Resolve parent topic name.
         * @param data the topic list retrieved by REST.
         */
        this.resolveParent = function(data) {
            if (data && data.length > 0) {
                if (data[0].parent && data[0].parent.title) {
                    this.name = "Topics of " + data[0].parent.title;
                }
            }
        };

        /**
         * checks if the current user is authenticated
         * @param userEmail optional to check for a specific user
         * @returns {boolean}
         */
        this.userIsAuth = function (userEmail) {
            return $rootScope.user.isAuth
                && (!userEmail || $rootScope.user.userData.email === userEmail);
        };
        /**
         * deletes the specified topic
         * @param topic
         */
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
        };
        /**
         * saves a topic
         * @param topicId optional for existing topic
         * @param parentId
         * @param title
         */
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
                                        if ((!$routeParams.topicId && data.parentId)
                                            || $routeParams.topicId != data.parentId) {
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
                                if (($routeParams.topicId && $routeParams.topicId == data.parentId)
                                    || (!$routeParams.topicId && !data.parentId)){
                                    //add to topics
                                    $scope.topicCtrl.topics.push(data);
                                }
                            }
                        },
                        function () {//error
                            $log.info("couldn't insert topic");
                        });
                }
            }
        };
        /**
         * show topic editor for new and existing
         * @param topicId optional for existing topic
         */
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
                                topics: (topicId) ? topic.parent : Topic.query(),
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
        /**
         * toggle post visibility for the specified topic
         * reads all posts for the topic if necessary
         * @param topicId
         */
        this.togglePosts = function(topicId) {
            if (topicId) {
                //check for existing data
                var topicData = this.postsForTopicId[topicId];
                if (!topicData){
                    topicData = {};
                    //read and display posts for topic
                    topicData.visible = true;
                    topicData.posts = Post.query({topicId: topicId});
                    if (!topicData.posts){
                        //no posts yet...
                        //init array
                        topicData.posts = [];
                    }
                    this.postsForTopicId[topicId] = topicData;
                }
                else {
                    //toggle visibility
                    topicData.visible = !topicData.visible;
                }
            }
        };
        /**
         * deletes the specified post
         * @param post
         */
        this.delPost = function (post) {
            if (post && this.userIsAuth()) {
                Post.delete({topicId: post.topic.id, id: post.id},
                    function() {//success
                        //remove from posts
                        var index = $scope.topicCtrl.postsForTopicId[post.topic.id].posts.indexOf(post);
                        if (index >= 0){
                            $scope.topicCtrl.postsForTopicId[post.topic.id].posts.splice(index, 1);
                        }
                    },
                    function () {//error
                        $log.info("couldn't delete post");
                    });
            }
        };
        /**
         * saves a new or existing post
         * @param postId optional for existing post
         * @param topicId
         * @param title
         * @param text
         */
        this.savePost = function (postId, topicId, title, text) {
            if (this.userIsAuth() && topicId) {
                var post = {
                    id: postId,
                    topic: {id: topicId},
                    title: title,
                    text : text,
                    email: $rootScope.user.userData.email
                };
                if (postId) {
                    Post.update({topicId: post.topic.id, id: postId}, post,
                        function(data) {//success
                            if (data) {
                                //update posts
                                var posts = $scope.topicCtrl.postsForTopicId[data.topic.id].posts;
                                for (i = posts.length - 1; i >= 0; i--) {
                                    if (posts[i].id === data.id){
                                        //update data in posts
                                        posts[i] = data;
                                    }
                                }
                            }
                        },
                        function () {//error
                            $log.info("couldn't update post");
                        });
                }
                else {
                    Post.save({topicId: post.topic.id}, post,
                        function(data) {//success
                            if (data) {
                                //add to posts
                                $scope.topicCtrl.postsForTopicId[data.topic.id].posts.push(data);
                            }
                        },
                        function () {//error
                            $log.info("couldn't insert post");
                        });
                }
            }
        };
        /**
         * show post editor for new and existing
         * @param topicId
         * @param postId optional for existing post
         */
        this.showPost = function (topicId, postId) {
            if (this.userIsAuth() && topicId) {
                var post = { topic: {id: topicId} };
                if (postId){
                    post = Post.get({topicId: topicId, id: postId},
                        function(data) {//success
                            //check for edit-permission on post
                            $scope.topicCtrl.userIsAuth(data.user.email);
                        },
                        function () {//error
                            $log.info("couldn't get post");
                        });
                }

                var modalInstance = $modal.open({
                    templateUrl: 'pages/postForm.html',
                    controller: 'ModalController as modalCtrl',
                    resolve: {
                        data: function () {
                            return {
                                title: (post.postId) ? 'Edit Post' : 'New Post',
                                modalData: {
                                    post: post
                                }
                            };
                        }
                    }
                });
                modalInstance.result.then(function (modalData) {
                    $scope.topicCtrl.savePost(modalData.post.id,
                        modalData.post.topic.id,
                        modalData.post.title,
                        modalData.post.text);
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            }
        };
    }]);
})();