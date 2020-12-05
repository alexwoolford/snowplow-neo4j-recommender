package io.woolford.neo4j.service;

import io.woolford.neo4j.entity.PageUrl;
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
    public List<PageUrl> getRecommendations(String networkUserId) {
        List<PageUrl> result = recommendationsRepository.getRecommendations(networkUserId);
        return result;
    }

}
