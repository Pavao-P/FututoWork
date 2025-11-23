package com.futurowork.controller;

import com.futurowork.entity.AiRecommendation;
import com.futurowork.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {
    private final AiService ai;

    public AiController(AiService ai) { this.ai = ai; }

    @PostMapping("/suggest/{userId}")
    public ResponseEntity<AiRecommendation> suggest(@PathVariable Long userId, @RequestBody String prompt) {
        AiRecommendation r = ai.suggestForUser(userId, prompt);
        return ResponseEntity.ok(r);
    }
}
