package com.example.backend.services.impl;

import com.example.backend.entities.Interaction;
import com.example.backend.enums.Action;
import com.example.backend.repositories.jpa.InteractionRepository;
import com.example.backend.services.InteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InteractionServiceImpl implements InteractionService {
    
    private final InteractionRepository interactionRepository;
    
    @Override
    public void makeInteraction(Long profileId, Long targetProfileId, Action action) {
        Interaction interaction = Interaction.builder()
                .profileId(profileId)
                .targetProfileId(targetProfileId)
                .profileAction(action)
                .build();
        this.interactionRepository.saveAndFlush(interaction);
    }

    @Override
    public List<Interaction> findInteractionsWhereProfileWasTargetAndAction(Long profileId, Action action) {
        return this.interactionRepository.findInteractionWhereProfileWasTarget(profileId, action);
    }

    @Override
    public void addInteractions(List<Interaction> interactions) {
        this.interactionRepository.saveAll(interactions);
    }
}
