package com.epam.ilya.form;

import com.epam.ilya.model.Comment;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;

public class CommentForm extends ValidatorForm {
    private Comment newComment = new Comment();

    public Comment getNewComment() {
        return newComment;
    }

    public void setNewComment(Comment newComment) {
        this.newComment = newComment;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        validateNewComment(errors);
        return errors;
    }

    private void validateNewComment(ActionErrors errors) {
        if (!newComment.getAuthor().matches(".{1,50}")) {
            errors.add("author", new ActionMessage("err.comment.author.required"));
        }
        if (!newComment.getContent().matches(".{1,400}")) {
            errors.add("content", new ActionMessage("err.comment.content.required"));
        }
    }
}
