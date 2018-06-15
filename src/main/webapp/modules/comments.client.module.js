var commentsModule = angular.module('CommentsModule', ['ngResource']);

commentsModule.factory('Comments', ['$resource', function ($resource) {
    return $resource('/comments');
}]);

commentsModule.factory('CommentById', ['$resource', function ($resource) {
    return $resource('/comments/:commentId', {
        commentId: '@commentId'
    });
}]);

commentsModule.controller('CommentsController', ['$scope', 'Comments', 'CommentById',function ($scope, Comments, CommentById) {

    $scope.message = '';

    $scope.addComment = function() {
        $scope.comment = new Comments();
        $scope.saving = true;

        $scope.comment.$save(function (response) {
            if (response) {
                $scope.message = 'Everything cool!';
            } else {
                $scope.message = 'Error!';
            }
            $scope.saving = false;
        });
    };

    $scope.loadComments = function() {
        $scope.loadingComments = true;

        var listComments = Comments.get(function () {
            if (listComments) {
                $scope.listComments = listComments;
                $scope.message = 'Comments loaded!';
            } else {
                $scope.message = 'There are no comments...';
            }
            $scope.loadingComments = false;
        });
    };

    $scope.getCommentById = function() {

        $scope.loadingComments = true;

        var comment = CommentById.get({commentId: $scope.id}, function () {
            if (comment) {
                $scope.comment = comment;
                $scope.message = 'Comment found!';
            } else {
                $scope.message = 'Comment not found';
            }
            $scope.loadingComments = false;
        });
    };

}]);

