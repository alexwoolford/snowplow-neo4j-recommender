package io.woolford.neo4j.entity;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@NodeEntity
public class NetworkUserId {

    //TODO: possibly an unnecessary class

    @Property("network_userid")
    private String networkUserId;

    public NetworkUserId(String networkUserId) {
        this.networkUserId = networkUserId;
    }

    public String getNetworkUserId() {
        return networkUserId;
    }

    public void setNetworkUserId(String networkUserId) {
        this.networkUserId = networkUserId;
    }

    @Relationship(type="VIEWED", direction=Relationship.OUTGOING)
    private Collection<PageUrl> pageUrls = new ArrayList<>();

}
