package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CommentPage {
    private BaseFunc baseFunc;

    private static final Logger LOGGER = LogManager.getLogger(CommentPage.class);

    public CommentPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public Integer getCommentCount(By anonymousCommentsLocator, By registeredCommentsLocator) {
        return baseFunc.removeParentheses(baseFunc.getElement(anonymousCommentsLocator).getText()) +
                baseFunc.removeParentheses(baseFunc.getElement(registeredCommentsLocator).getText());
    }
}
