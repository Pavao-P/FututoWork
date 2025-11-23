package com.futurowork.service;

import com.futurowork.entity.AiRecommendation;
import com.futurowork.repository.AiRecommendationRepository;
import org.springframework.stereotype.Service;

/*
  Este serviço é um stub de integração com IA generativa.
  Pode ser implementado posteriormente com integração a OpenAI/Azure.
*/
@Service
public class AiService {
    private final AiRecommendationRepository repo;

    public AiService(AiRecommendationRepository repo) {
        this.repo = repo;
    }

    public AiRecommendation suggestForUser(Long userId, String prompt) {
        // Exemplo simples: retorno estático. Substituir por chamada real à OpenAI.
        AiRecommendation r = AiRecommendation.builder()
                .userId(userId)
                .recommendation("Sugestão gerada: trilha básica em Dados e IA para 3 meses.")
                .build();
        return repo.save(r);
    }
}
