package io.woolford.neo4j.web;

import io.woolford.neo4j.entity.Page;
import io.woolford.neo4j.repository.RecommendationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecommendationsController {

    private final Logger LOG = LoggerFactory.getLogger(RecommendationsController.class);

    private final RecommendationsRepository recommendationsRepository;

    public RecommendationsController(RecommendationsRepository recommendationsRepository) {
        this.recommendationsRepository = recommendationsRepository;
    }

    @GetMapping("/recommendations/{network_userid}")
    public ResponseEntity<List<Page>> getRecommendations(@PathVariable String network_userid) {

        LOG.info("network_userid: " + network_userid);

        return ResponseEntity.ok(recommendationsRepository.getRecommendations(network_userid));
    }

}
