'use strict';
define(['angular','angular-route'], function(angular) {
    var app = angular.module('myApp',['ngRoute','ngAnimate','routeResolverServices','pascalprecht.translate','angularShiro', 'ui.bootstrap']);

    app.constant("CONTEXT_PATH", window.SERVLET_CONTEXT_PATH);

    app.run(['$rootScope','$location', '$routeParams', 'subject','$httpBackend','subject', function($rootScope, $location, $routeParams, subject) {
        /*$rootScope.$on('$routeChangeSuccess', function(e, current, pre) {
            console.log('Current route name: ' + $location.path());
            
            if(current.scope) {
            	if(current.scope.secondMenuNode) {
                    var index = current.scope.secondMenuNode.index;            
                    current.scope.$parent.secondMenus[index] = current.scope.secondMenuNode.node;
                }
            }
            
            var path = $location.path();//获得当前路由地址
        })   */   
    }]);
    
    app.config(['$routeProvider', 'routeResolverProvider','$controllerProvider','$compileProvider',
            '$provide','$filterProvider','$translateProvider','angularShiroConfigProvider','CONTEXT_PATH', function($routeProvider, routeResolverProvider, $controllerProvider, $compileProvider, $provide, $filterProvider,$translateProvider, shiroConfig, contextPath) {
                app.register = {
                    controller: $controllerProvider.register,
                    directive: $compileProvider.directive,
                    filter: $filterProvider.register,
                    factory: $provide.factory,
                    service: $provide.service
                };
                shiroConfig.setFilter('/**/*','authc');
                shiroConfig.setAuthenticateUrl(contextPath+'/api/authenticate');
                var route = routeResolverProvider.route;
                //app路由
                $routeProvider
                	.when('/error',
                			{
                			templateUrl:contextPath+'/views/error.html',
                			})
                    .when('/user', route.resolve('sysUserListController','list','sysuser','vm', false))
                    .when('/user/add', route.resolve('sysUserAddController','add','sysuser','vm', false))
                    .when('/user/upd/:userId', route.resolve('sysUserUpdateController','update','sysuser','vm', false))
                	.otherwise('/user', route.resolve('sysUserListController','list','sysuser','vm', false));
                //i18n angular-translate
                $translateProvider.useStaticFilesLoader({
                	prefix: contextPath+'/i18n/',
                    suffix: '.json'
                });
                $translateProvider.preferredLanguage('zh-CN');
		}
    ]);

    app.controller("indexController" , ['$scope', '$translate','$http', '$location', 'indexService','subject','usernamePasswordToken','$uibModal','CONTEXT_PATH',
                                        function($scope, $translate, $http, $location, indexService,subject, usernamePasswordToken, $uibModal, contextPath) {
    	$scope.taken = usernamePasswordToken;
    	$scope.taken.username = "admin";
    	$scope.taken.password = "admin";
    	
    	$scope.secondMenus = [
    	    {href:contextPath+"/index/#/user",icon:"entypo-home",translatekey:"'i18n.MENU.HOME'"}
    	];
    	
        $scope.queryMenuData = function() {
            indexService.queryMenus().then(function(data) {
                $scope.menus = data;
                //注册一个广播，directive端接收广播
                $scope.$broadcast("menuDataPrepared");
            }, function(error) {
                alert(error);
            });
        };
        $scope.queryMenuData();

        $scope.setLanguage = function(lang) {
    		$translate.use(lang);
        }
        //shiro
		subject.login($scope.taken).then(function(data) {
			console.log(data);
		}, function(data) {
			console.log(data);
		});
        
    }]);
    
    
    app.factory('httpErrorResponseInterceptor', ['$q', '$location', function($q, $location) {
    	return {
    		request: function(config) {
    			console.log("request")
    	      	console.log(config);
    	      	return config;
    	 	},
    	    // optional method
			requestError: function(rejection) {
				console.log("requestError")
				console.log(rejection);
				// do something on error
				return $q.reject(rejection);
			},
			response: function(responseData) { 
				console.log("response")
				console.log(responseData);
				return responseData; 
			},
			responseError: function error(response) {
				console.log("responseError")
				console.log(response);
				
				switch (response.status) {
					case 401:
						$location.path('/login');
						break;
					case 404:
						$location.path('/404');
						break;
					default:
						$location.path('/error');
				}
				return $q.reject(response);
			}
     	};
	}]);
                                               

    //Http Intercpetor to check auth failures for xhr requests
    app.config(['$httpProvider', 
        function($httpProvider) {
			$httpProvider.interceptors.push('httpErrorResponseInterceptor');
       }
	]);
    
    return app;
});