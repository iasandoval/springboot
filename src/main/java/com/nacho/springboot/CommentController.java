package com.nacho.springboot;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final AtomicLong idComment = new AtomicLong();
    private final Map<Long, Comment> commentList = new HashMap<>();


    @GetMapping
    public List<Comment> listComments() {
        return new ArrayList(this.commentList.values());
    }

    @GetMapping("/{id}")
    public Comment commentById(@PathVariable("id") Long id) {
        return this.commentList.get(id);
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        Comment c = null;
        if(!StringUtils.isEmpty(comment.getComment())){
            Long id = this.idComment.incrementAndGet();
            c = new Comment(id, comment.getComment(), new Date());
            this.commentList.put(id, c);
        }
        return c;
    }

}
