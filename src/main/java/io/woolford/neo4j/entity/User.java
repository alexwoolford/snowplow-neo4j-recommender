package io.woolford.neo4j.entity;

import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Node("User")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Property("domain_userid")
    private String domainUserid;

    @Relationship(type="VIEWED", direction = Relationship.Direction.OUTGOING)
    private List<Page> pageViews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainUserid() {
        return domainUserid;
    }

    public void setDomainUserid(String domainUserid) {
        this.domainUserid = domainUserid;
    }

}
