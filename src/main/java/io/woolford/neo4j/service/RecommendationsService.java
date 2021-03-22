package io.woolford.neo4j.service;

import io.woolford.neo4j.entity.Page;
import io.woolford.neo4j.repository.RecommendationsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecommendationsService {

    private final RecommendationsRepository recommendationsRepository;

    public RecommendationsService(RecommendationsRepository recommendationsRepository) {
        this.recommendationsRepository = recommendationsRepository;
    }

    @Transactional(readOnly = true)
    public List<Page> getRecommendations(String networkUserId) {
        List<Page> result = recommendationsRepository.getRecommendations(networkUserId);
        return result;
    }

}
