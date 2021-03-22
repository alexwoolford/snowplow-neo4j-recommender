package io.woolford.neo4j.entity;

import org.springframework.data.neo4j.core.schema.*;

@Node("Page")
public class Page {

    @Id
    @GeneratedValue
    private Long id;

    @Property("page_url")
    private String pageUrl;

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

}
