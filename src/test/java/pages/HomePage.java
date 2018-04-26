package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private BaseFunc baseFunc;

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
    private static final Integer ARTICLES_TO_DOWNLOAD = 5;


    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void printArticleUrls(List<String> articlesUrls) {
        for (String url : articlesUrls) {
            LOGGER.info("Printing article URL : " + url);
        }
    }

    public void printArticleComments(List<Integer> articlesComments) {
        for (Integer commentCount : articlesComments) {
            LOGGER.info("Printing article comment counter : " + commentCount);
        }
    }

    public List<String> getFirstFiveArticleTitles(By articleLocator, By titleLocator) {
        List<WebElement> articles = baseFunc.getElements(articleLocator);

        List<String> articleTitles = new ArrayList<>();
        for (int i = 0; i < ARTICLES_TO_DOWNLOAD; i++) {
            articleTitles.add(articles.get(i).findElement(titleLocator).getText());
        }
        return articleTitles;
    }

    public List<String> getFirstFiveArticleUrls(By articleLocator, By titleLocator) {
        List<WebElement> articles = baseFunc.getElements(articleLocator);

        List<String> articleUrls = new ArrayList<>();
        for (int i = 0; i < ARTICLES_TO_DOWNLOAD; i++) {
            articleUrls.add(articles.get(i).findElement(titleLocator).getAttribute("href"));
        }
        return articleUrls;
    }

    public List<String> getAllArticleTitles(By articleLocator, By titleLocator) {
        List<WebElement> articles = baseFunc.getElements(articleLocator);

        List<String> articleTitles = new ArrayList<>();
        for (WebElement article : articles) {
            articleTitles.add(article.findElement(titleLocator).getText());
        }
        return articleTitles;
    }

    public List<String> getAllArticleUrls(By articleLocator, By titleLocator) {
        List<WebElement> articles = baseFunc.getElements(articleLocator);

        List<String> articleUrls = new ArrayList<>();
        for (WebElement article : articles) {
            articleUrls.add(article.findElement(titleLocator).getAttribute("href"));
        }
        return articleUrls;
    }

    public List<Integer> getFirstFiveArticleComments(By articleLocator, By commentLocator) {
        List<WebElement> articles = baseFunc.getElements(articleLocator);

        List<Integer> articleComments = new ArrayList<>();
        for (int i = 0; i < ARTICLES_TO_DOWNLOAD; i++) {
            if (articles.get(i).findElements(commentLocator).isEmpty()) {
                articleComments.add(0);
            } else {
                articleComments.add(baseFunc.removeParentheses(articles.get(i).findElement(commentLocator).getText()));
            }
        }
        return articleComments;
    }
}
