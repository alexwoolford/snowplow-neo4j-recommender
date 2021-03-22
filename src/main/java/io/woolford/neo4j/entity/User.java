package io.woolford.neo4j.entity;

import org.springframework.data.neo4j.core.schema.*;

@Node("User")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Property("network_userid")
    private String networkUserid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetworkUserid() {
        return networkUserid;
    }

    public void setNetworkUserid(String networkUserid) {
        this.networkUserid = networkUserid;
    }

}
