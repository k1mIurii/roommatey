package com.example.backend.services;

import com.example.backend.entities.Interaction;
import com.example.backend.enums.Action;

import java.util.List;

public interface InteractionService {
    void makeInteraction(Long profileId, Long targetProfileId, Action action);

    List<Interaction> findInteractionsWhereProfileWasTargetAndAction(Long profileId, Action action);

    void addInteractions(List<Interaction> interactions);
}
