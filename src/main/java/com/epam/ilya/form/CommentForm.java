package com.epam.ilya.form;

import com.epam.ilya.model.Comment;
import org.apache.struts.validator.ValidatorForm;

public class CommentForm extends ValidatorForm {
    private Comment newComment = new Comment();

    public Comment getNewComment() {
        return newComment;
    }

    public void setNewComment(Comment newComment) {
        this.newComment = newComment;
    }

}
