(function() {
    var app = angular.module('wickedBoardApp', ['ui.bootstrap', 'ngRoute', 'LocalStorageModule', 'wickedBoardServices']);
    app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'pages/index.html'
        }).when('/topic', {
            templateUrl: 'pages/topic.html',
            controller: 'TopicController'
        });
    }]);

    app.controller('UserController', ['$rootScope', '$scope', 'localStorageService', '$modal', '$log', 'User', function($rootScope, $scope, localStorageService, $modal, $log, User) {
        //check for stored user
        $scope.user = localStorageService.get('user');
        if (!$scope.user) {
            $scope.user = {
                userData: {},
                isAuth: false
            };
        }
        this.login = function(email, password) {
            if (email !== '' && password !== '') {
                $scope.user.userData = User.login({email: email}, password,
                    function () {//success
                        $scope.user.isAuth = true;
                        localStorageService.set('user', $scope.user);
                    },
                    function () {//error
                        $scope.user = {
                            userData: {},
                            isAuth: false
                        };
                        localStorageService.remove('user');
                        $log.info("invalid credentials");
                    });
            }
        };
        this.logout = function() {
            $scope.user = {
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
            $scope.user.userData = User.save(userData,
                function() {//success
                    $scope.user.isAuth = true;
                    localStorageService.set('user', $scope.user);
                },
                function () {//error
                    $scope.user = {
                        userData: {},
                        isAuth: false
                    };
                    localStorageService.remove('user');
                    $log.info("couldn't sign up");
                });
        };

        this.showLogin = function () {
            if (!$scope.user.isAuth) {
                var modalInstance = $modal.open({
                    templateUrl: 'pages/login.html',
                    controller: 'ModalController as modalCtrl'
                });
                modalInstance.result.then(function (modalData) {
                   $scope.userCtrl.login(modalData.email, modalData.password);
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            }
        };
        this.showSignUp = function () {
            if (!$scope.user.isAuth) {
                var modalInstance = $modal.open({
                    templateUrl: 'pages/signup.html',
                    controller: 'ModalController as modalCtrl'
                });
                modalInstance.result.then(function (modalData) {
                    $scope.userCtrl.addUser(modalData.email, modalData.name, modalData.password);
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            }
        };
    }]);
    app.controller('ModalController', ['$modalInstance', '$scope', function ($modalInstance, $scope) {
        this.modalData = {};
        /**this.error = false;
        this.incomplete = false;
        this.validate = function() {
            if (this.modalData.password !== this.modalData.passwordRep) {
                this.error = true;
            } else {
                this.error = false;
            }
            this.incomplete = false;
            if (!this.email.length || !this.name.length ||
                !this.password.length || !this.passwordRep.length) {
                this.incomplete = true;
            }
        };*/
        this.ok = function () {
            $modalInstance.close(this.modalData);
        };

        this.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }]);
    app.controller('TopicController', ['$scope', 'Topic', function ($scope, Topic) {
        Topic.query(function(data) {
            $scope.topics = data;
        });
    }]);
})();