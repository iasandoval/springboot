package com.nacho.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping
    public List<Comment> listComments() {
        return this.commentsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment commentById(@PathVariable("id") Long id) {
        return this.commentsRepository.findById(id);
    }

    @PostMapping
    public Comment upsertComment(@RequestBody Comment comment) {
        Comment c;
        if(comment.getId() > 0) {
            c = this.commentsRepository.update(comment);
        } else {
            c = this.commentsRepository.add(comment);
        }
       return c;
    }

    @DeleteMapping("/{id}")
    public Comment addComment(@PathVariable("id") Long id) {
        return this.commentsRepository.delete(id);
    }

}
