package io.woolford.neo4j.entity;

import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node("Page")
public class Page {

    @Id
    @GeneratedValue
    private Long id;

    @Property("page_url")
    private String pageUrl;

    @Property("page_title")
    private String pageTitle;

    @Relationship(type="VIEWED", direction = Relationship.Direction.INCOMING)
    private List<User> pageViews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

}
