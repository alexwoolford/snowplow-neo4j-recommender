package io.woolford.neo4j;

import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface NextCountResults {

    class Result {

        @Property("page_url")
        public final String pageUrl;

        public final Long count;

        Result(String pageUrl, Long count) {
            this.pageUrl = pageUrl;
            this.count = count;
        }

    }

    @Transactional(readOnly = true)
    Collection<Result> getNextCount(String pageUrl);

}
