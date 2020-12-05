package io.woolford.neo4j.entity;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@NodeEntity
public class PageUrl {

    @Id @GeneratedValue
    private Long id;

    @Property("page_url")
    private String pageUrl;

    public PageUrl(String pageUrl) {
        this(null, pageUrl);
    }

    public PageUrl(Long id, String pageUrl){
        this.id = id;
        this.pageUrl = pageUrl;
    }

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

    @Relationship(type="VIEWED", direction=Relationship.INCOMING)
    private Collection<NetworkUserId> networkUserIds = new ArrayList<>();

}
