package com.dev.core.pageModel;

import com.dev.core.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentBlock {
    private Comment comment;
    private List<Comment> answerComment;

    public CommentBlock(Comment comment, List<Comment> answerComment) {
        this.comment = comment;
        this.answerComment = answerComment;
    }
}
