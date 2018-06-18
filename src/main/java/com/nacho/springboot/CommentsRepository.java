package com.nacho.springboot;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CommentsRepository {

    private final AtomicLong idComment = new AtomicLong();
    private final Map<Long, Comment> commentDB = new HashMap<>();

    public List<Comment> findAll() {
        return new ArrayList(this.commentDB.values());
    }

    public Comment findById(Long id) {
        return this.commentDB.get(id);
    }

    public Comment add(Comment comment) {
        Comment c = null;
        if (!StringUtils.isEmpty(comment.getComment())) {
            Long id = this.idComment.incrementAndGet();
            c = new Comment(id, comment.getComment(), new Date());
            this.commentDB.put(id, c);
        }
        return c;
    }

    public Comment update(Comment comment) {
        if (comment.getId() > 0) {
            Comment c = this.findById(comment.getId());

            if (null != c) {
                this.commentDB.replace(comment.getId(), c, comment);
            }
        }
        return comment;
    }

    public Comment delete(Long id) {
        Comment c = null;
        if (null != id) {
            c = this.findById(id);

            if (null != c) {
                this.commentDB.remove(c.getId());
            }
        }
        return c;
    }

}
