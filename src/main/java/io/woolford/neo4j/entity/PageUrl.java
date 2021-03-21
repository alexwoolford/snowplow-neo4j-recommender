package io.woolford.neo4j.entity;

import org.neo4j.ogm.annotation.*;


@NodeEntity
public class PageUrl {

    @Property("page_url")
    private String pageUrl;

    public PageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

}
