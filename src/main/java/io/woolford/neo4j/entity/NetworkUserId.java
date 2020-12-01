package io.woolford.neo4j.entity;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;

public class NetworkUserId {

    //TODO: possibly an unnecessary class

    @Id
    private String id;
    @Property("network_userid")
    private String networkUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNetworkUserId() {
        return networkUserId;
    }

    public void setNetworkUserId(String networkUserId) {
        this.networkUserId = networkUserId;
    }

}
