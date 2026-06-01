package com.sip.tp.service.algorithm;

import org.springframework.stereotype.Component;

@Component
public class ProfileCompletionCalculator {

    public int calculate(ProfileSignals signals) {
        int completion = 0;

        if (signals.hasProfilePhoto()) completion += 10;
        if (signals.hasHeadline()) completion += 10;
        if (signals.identityVerified()) completion += 10;
        if (signals.skillCount() > 0) completion += 20;
        if (signals.validatedSkillCount() > 0) completion += 20;
        if (signals.hasProjects()) completion += 10;
        if (signals.hasWorkExperience()) completion += 20;

        return Math.min(completion, 100);
    }

    public record ProfileSignals(
            boolean hasProfilePhoto,
            boolean hasHeadline,
            boolean identityVerified,
            long skillCount,
            long validatedSkillCount,
            boolean hasProjects,
            boolean hasWorkExperience
    ) {
    }
}

