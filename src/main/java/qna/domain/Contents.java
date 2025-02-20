package qna.domain;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import qna.util.ValidationUtils;

@Embeddable
public class Contents {
    @Lob
    private String contents;

    protected Contents() {
    }

    private Contents(String contents) {
        this.contents = contents;
    }

    public static Contents of(String contents) {
        checkValidation(contents);
        return new Contents(contents);
    }
    
    private static void checkValidation(String contents) {
        ValidationUtils.checkEmpty(contents);
    }
    
}
