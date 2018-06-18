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
    $scope.comment = new Comments();

    $scope.addUpdateComment = function() {
        $scope.message = 'Saving comment...';

        $scope.comment.$save(function (response) {
            if (response) {
                $scope.message = 'Everything cool!';
                $scope.comment = new Comments();
                $scope.loadComments();
            }
        });
    };

    $scope.loadComments = function() {
        $scope.message = 'Loading comments...';

        var listComments = Comments.query({}, function () {
            if (listComments) {
                $scope.listComments = listComments;

                if($scope.listComments.length == 0) {
                    $scope.message = 'No Comments!';
                } else {
                    $scope.message = 'Comments loaded: ' + $scope.listComments.length ;
                }

            }
        });
    };

    $scope.getCommentById = function() {

        $scope.message = 'Get comment...';

        var comment = CommentById.get({commentId: $scope.comment.id}, function () {
            if (comment.id) {
                $scope.comment = comment;
                $scope.message = 'Comment found!';
            } else {
                $scope.comment = new Comment();
                $scope.message = 'Comment not found';
            }
        });
    };

    $scope.deleteComment = function() {

        $scope.message = 'Deleting comment...';

        var comment = CommentById.remove({commentId: $scope.comment.id}, function () {
            if (comment) {
                $scope.message = 'Comment Deleted!';
                $scope.comment = new Comments();
                $scope.loadComments();
            } else {
                $scope.message = 'Comment not deleted';
            }
        });
    };

}]);

