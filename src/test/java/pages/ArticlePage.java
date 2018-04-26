package pages;

import org.openqa.selenium.By;

public class ArticlePage {
    private BaseFunc baseFunc;

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public Integer getArticleComments(By headerLocator, By commentsLocator) {
        if (baseFunc.getElement(headerLocator).findElements(commentsLocator).isEmpty()) {
            return 0;
        } else {
            return baseFunc.removeParentheses(baseFunc
                    .getElement(headerLocator).findElement(commentsLocator).getText());
        }
    }

    public void goToArticleComments(By commentsLocator) {
        baseFunc.click(commentsLocator);
    }
}
