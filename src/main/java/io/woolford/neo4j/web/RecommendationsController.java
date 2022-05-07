package io.woolford.neo4j.web;

import io.woolford.neo4j.NextCountResults;
import io.woolford.neo4j.NextCountResultsImpl;
import io.woolford.neo4j.entity.Page;
import io.woolford.neo4j.repository.PageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

@CrossOrigin(origins = "https://woolford.io", maxAge = 3600)
@RestController
public class RecommendationsController {

    private final Logger LOG = LoggerFactory.getLogger(RecommendationsController.class);

    private final PageRepository pageRepository;

    private final NextCountResultsImpl nextCountResults;

    public RecommendationsController(PageRepository pageRepository, NextCountResultsImpl nextCountResults) {
        this.pageRepository = pageRepository;
        this.nextCountResults = nextCountResults;
    }

    @GetMapping("/recommendations/{domain_userid}")
    public ResponseEntity<List<Page>> getRecommendations(@PathVariable String domain_userid) {

        LOG.info("retrieving recommendations for domain_userid: " + domain_userid);

        return ResponseEntity.ok(pageRepository.getRecommendations(domain_userid));
    }

    @GetMapping("/next")
    public ResponseEntity<Collection<NextCountResults.Result>> getNext(@RequestParam String url) {

        LOG.info("getting next pages for url: " + url);

        return ResponseEntity.ok(nextCountResults.getNextCount(url));
    }

}
