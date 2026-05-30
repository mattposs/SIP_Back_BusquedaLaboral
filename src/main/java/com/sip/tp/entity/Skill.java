package com.sip.tp.entity;

import com.sip.tp.types.converters.SkillTypeConverter;
import com.sip.tp.types.definition.SkillType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Convert(converter = SkillTypeConverter.class)
    @Column(nullable = false)
    private SkillType type;
}
