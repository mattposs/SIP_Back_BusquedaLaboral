package com.sip.tp.controller;

import com.sip.tp.dto.request.AddSkillRequest;
import com.sip.tp.dto.skill.CandidateSkillResponse;
import com.sip.tp.dto.skill.SkillResponse;
import com.sip.tp.service.domain.candidate.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Skills", description = "Skill catalog and candidate skill management")
public class SkillController {

    private final SkillService skillService;

    @GetMapping("/skills")
    @Operation(summary = "Search skill catalog")
    public ResponseEntity<List<SkillResponse>> searchSkills(@RequestParam(required = false, defaultValue = "") String search) {
        return ResponseEntity.ok(skillService.searchCatalog(search));
    }

    @GetMapping("/skills/suggestions")
    @Operation(summary = "Get suggested skills based on role")
    public ResponseEntity<List<SkillResponse>> getSuggestedSkills(@Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId) {
        return ResponseEntity.ok(skillService.getSuggestedSkillsForCandidate(candidateId));
    }

    @GetMapping("/candidates/me/skills")
    @Operation(summary = "List own skills with levels and scores")
    public ResponseEntity<List<CandidateSkillResponse>> getMySkills(@Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId) {
        return ResponseEntity.ok(skillService.getCandidateSkills(candidateId));
    }

    @PostMapping("/candidates/me/skills")
    @Operation(summary = "Add a skill to profile")
    public ResponseEntity<Void> addSkill(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @RequestBody AddSkillRequest request) {
        skillService.addSkillToCandidate(candidateId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/candidates/me/skills/{id}")
    @Operation(summary = "Remove a skill from profile")
    public ResponseEntity<Void> removeSkill(
            @Parameter(hidden = true) @AuthenticationPrincipal UUID candidateId,
            @PathVariable UUID id) {
        skillService.removeSkillFromCandidate(candidateId, id);
        return ResponseEntity.noContent().build();
    }
}
