package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import pages.ArticlePage;
import pages.BaseFunc;
import pages.CommentPage;
import pages.HomePage;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CompareDelfiArticles {
    private static final Logger LOGGER = LogManager.getLogger(CompareDelfiArticles.class);

    private static final String DESKTOP_URL = "www.delfi.lv/";
    private static final String MOBILE_URL = "m.delfi.lv/";

//    private static final By ARTICLE_WEB = By.xpath(".//h3[@class='top2012-title']");
    private static final By HEADER = By.xpath(".//div[@class='article-title']");
    private static final By ARTICLE_WEB = By.xpath(".//h3[contains(@class, 'top2012-title')]");
    private static final By ARTICLE_MOB = By.xpath(".//div[@class='md-mosaic-title']");
    private static final By TITLE_WEB = By.xpath(".//a[@class='top2012-title']");
    private static final By TITLE_MOB = By.xpath(".//a[@class = 'md-scrollpos']");

    private static final By COMMENTS_WEB = By.xpath(".//a[contains(@class, 'comment-count')]");
    private static final By COMMENTS_MOB = By.xpath(".//a[contains(@class, 'commentCount')]");
    private static final By ANONYMOUS_COMMENTS_LOCATOR = By
            .xpath(".//a[@class='comment-thread-switcher-list-a comment-thread-switcher-list-a-reg']/span");
    private static final By REGISTERED_COMMENTS_LOCATOR = By
            .xpath(".//a[@class='comment-thread-switcher-list-a comment-thread-switcher-list-a-anon']/span");


    private BaseFunc baseFunc = new BaseFunc();
    private HomePage homePage = new HomePage(this.baseFunc);
    private ArticlePage articlePage = new ArticlePage(this.baseFunc);
    private CommentPage commentPage = new CommentPage(this.baseFunc);

    List<String> articleTitlesWeb = new ArrayList<>();
    List<String> articleTitlesMob = new ArrayList<>();
    List<String> articleUrlsWeb = new ArrayList<>();
    List<String> articleUrlsMob = new ArrayList<>();
    List<Integer> articleCommentsWeb = new ArrayList<>();
    List<Integer> articleCommentsMob = new ArrayList<>();


    @Test
    public void collectAllArticleTitlesWeb() {
        baseFunc.goToUrl(DESKTOP_URL);
        articleTitlesWeb = homePage.getAllArticleTitles(ARTICLE_WEB, TITLE_WEB);
        homePage.printArticleUrls(articleTitlesWeb);
    }

    @Test
    public void collectAllArticleUrlsWeb() {
        baseFunc.goToUrl(DESKTOP_URL);
        articleUrlsWeb = homePage.getAllArticleUrls(ARTICLE_WEB, TITLE_WEB);
        homePage.printArticleUrls(articleUrlsWeb);
    }

    @Test
    public void collectAllArticleTitlesMob() {
        baseFunc.goToUrl(MOBILE_URL);
        articleTitlesMob = homePage.getAllArticleTitles(ARTICLE_MOB, TITLE_MOB);
        homePage.printArticleUrls(articleTitlesMob);
    }

    @Test
    public void collectAllArticleUrlsMob() {
        baseFunc.goToUrl(MOBILE_URL);
        articleUrlsMob = homePage.getAllArticleUrls(ARTICLE_MOB, TITLE_MOB);
        homePage.printArticleUrls(articleUrlsMob);
    }

    @Test
    public void collectFiveArticleTitlesWeb() {
        baseFunc.goToUrl(DESKTOP_URL);
        articleTitlesWeb = homePage.getFirstFiveArticleTitles(ARTICLE_WEB, TITLE_WEB);
        homePage.printArticleUrls(articleTitlesWeb);
    }

    @Test
    public void collectFiveArticleUrlsWeb() {
        baseFunc.goToUrl(DESKTOP_URL);
        articleUrlsWeb = homePage.getFirstFiveArticleUrls(ARTICLE_WEB, TITLE_WEB);
        homePage.printArticleUrls(articleUrlsWeb);
    }

    @Test
    public void collectFiveArticleTitlesMob() {
        baseFunc.goToUrl(MOBILE_URL);
        articleTitlesMob = homePage.getFirstFiveArticleTitles(ARTICLE_MOB, TITLE_MOB);
        homePage.printArticleUrls(articleTitlesMob);
    }

    @Test
    public void collectFiveArticleUrlsMob() {
        baseFunc.goToUrl(MOBILE_URL);
        articleUrlsMob = homePage.getFirstFiveArticleUrls(ARTICLE_MOB, TITLE_MOB);
        homePage.printArticleUrls(articleUrlsMob);
    }

    @Test
    public void collectFiveArticleCommentsWeb() {
        baseFunc.goToUrl(DESKTOP_URL);
        articleCommentsWeb = homePage.getFirstFiveArticleComments(ARTICLE_WEB, COMMENTS_WEB);
        homePage.printArticleComments(articleCommentsWeb);
    }

    @Test
    public void collectFiveArticleCommentsMob() {
        baseFunc.goToUrl(MOBILE_URL);
        articleCommentsMob = homePage.getFirstFiveArticleComments(ARTICLE_MOB, COMMENTS_MOB);
        homePage.printArticleComments(articleCommentsMob);
    }

    @Test
    public void collectAndCompareArticleCommentsFromHomePageAndArticlePageWeb() {
        baseFunc.goToUrl(DESKTOP_URL);

        articleCommentsWeb = homePage.getFirstFiveArticleComments(ARTICLE_WEB, COMMENTS_WEB);
        articleUrlsWeb = homePage.getFirstFiveArticleUrls(ARTICLE_WEB, TITLE_WEB);

        List<Integer> commentCountArticlePage = new ArrayList<>();

        for (String url : articleUrlsWeb) {
            baseFunc.goToUrl(url);
            commentCountArticlePage.add(articlePage.getArticleComments(HEADER, COMMENTS_WEB));
            LOGGER.info(articlePage.getArticleComments(HEADER, COMMENTS_WEB));
        }
        LOGGER.info("Comparing home page comments count: " + articleCommentsWeb + " with article page comments count: " + commentCountArticlePage);
        Assert.assertEquals(articleCommentsWeb, commentCountArticlePage);
    }

    @Test
    public void collectAndCompareArticleCommentsFromHomePageAndArticlePageMob() {
        baseFunc.goToUrl(MOBILE_URL);

        articleCommentsMob = homePage.getFirstFiveArticleComments(ARTICLE_MOB, COMMENTS_MOB);
        articleUrlsMob = homePage.getFirstFiveArticleUrls(ARTICLE_MOB, TITLE_MOB);

        List<Integer> commentCountArticlePage = new ArrayList<>();

        for (String url : articleUrlsMob) {
            baseFunc.goToUrl(url);
            commentCountArticlePage.add(articlePage.getArticleComments(HEADER, COMMENTS_MOB));
            LOGGER.info(articlePage.getArticleComments(HEADER, COMMENTS_MOB));
        }
        LOGGER.info("Comparing home page comments count: " + articleCommentsMob + " with article page comments count: " + commentCountArticlePage);
        Assert.assertEquals(articleCommentsMob, commentCountArticlePage);
    }

    @Test
    public void collectAndCompareArticleCommentsFromArticlePageAndCommentPageWeb() {
        baseFunc.goToUrl(DESKTOP_URL);
        articleUrlsWeb = homePage.getFirstFiveArticleUrls(ARTICLE_WEB, TITLE_WEB);
        List<Integer> commentCountArticlePage = new ArrayList<>();
        List<Integer> commentCountCommentPage = new ArrayList<>();

        for (String url : articleUrlsWeb) {
            baseFunc.goToUrl(url);
            commentCountArticlePage.add(articlePage.getArticleComments(HEADER, COMMENTS_WEB));
            articlePage.goToArticleComments(COMMENTS_WEB);
            commentCountCommentPage.add(commentPage.getCommentCount(ANONYMOUS_COMMENTS_LOCATOR, REGISTERED_COMMENTS_LOCATOR));
        }
        LOGGER.info("Comparing article page comments count: " + commentCountArticlePage + " with article comment page comments count: " + commentCountCommentPage);
        Assert.assertEquals(commentCountArticlePage, commentCountCommentPage);
    }

    @Test
    public void collectAndCompareArticleCommentsFromArticlePageAndCommentPageMob() {
        baseFunc.goToUrl(MOBILE_URL);
        articleUrlsMob = homePage.getFirstFiveArticleUrls(ARTICLE_MOB, TITLE_MOB);
        List<Integer> commentCountArticlePage = new ArrayList<>();
        List<Integer> commentCountCommentPage = new ArrayList<>();

        for (String url : articleUrlsMob) {
            baseFunc.goToUrl(url);
            commentCountArticlePage.add(articlePage.getArticleComments(HEADER, COMMENTS_MOB));
            articlePage.goToArticleComments(COMMENTS_MOB);
            commentCountCommentPage.add(commentPage.getCommentCount(ANONYMOUS_COMMENTS_LOCATOR, REGISTERED_COMMENTS_LOCATOR));
        }
        LOGGER.info("Comparing article page comments count: " + commentCountArticlePage + " with article comment page comments count: " + commentCountCommentPage);
        Assert.assertEquals(commentCountArticlePage, commentCountCommentPage);
    }

    @Test
    public void ensureArticleTitlesWebSameAsMobile() {
        Assert.assertNotNull(articleTitlesWeb);
        Assert.assertNotNull(articleTitlesMob);
        LOGGER.info("[MOBILE] Comparing is Titles web array size equals Title mobile array size : " + articleTitlesWeb.size() + " : " + articleTitlesMob.size());
        Assert.assertEquals(articleTitlesWeb, articleTitlesMob);
    }

    @Test
    public void ensureSameSizeTitlesWithUrls() {
        Assert.assertNotNull(articleTitlesWeb);
        Assert.assertNotNull(articleUrlsWeb);
        LOGGER.info("[WEB] Comparing is Titles web array size equals Urls array size : " + articleTitlesWeb.size() + " : " + articleUrlsWeb.size());
        Assert.assertEquals(articleTitlesWeb.size(), articleUrlsWeb.size());
    }

    @After
    public void closeBrowser() {
        LOGGER.info("Browser closed gracefully");
        baseFunc.quitDriver();
    }
}
