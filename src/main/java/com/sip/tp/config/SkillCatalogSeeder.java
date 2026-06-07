package com.sip.tp.config;

import com.sip.tp.entity.Skill;
import com.sip.tp.repository.SkillRepository;
import com.sip.tp.types.definition.SkillType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SkillCatalogSeeder implements ApplicationRunner {

    private final SkillRepository skillRepository;

    private static List<String> techSkills() {
        return List.of(
                "Java", "Spring Boot", "Kotlin", "Python", "Django", "FastAPI", "Node.js",
                "TypeScript", "JavaScript", "React", "Next.js", "Angular", "Vue.js", "HTML", "CSS",
                "Tailwind CSS", "Go", "Rust", "C Sharp", "DotNet", "PHP", "Ruby", "Ruby on Rails",
                "Swift", "iOS Development", "Android Development", "Flutter", "React Native", "SQL",
                "PostgreSQL", "MySQL", "MongoDB", "Redis", "Elasticsearch", "GraphQL", "REST APIs",
                "Microservices", "Docker", "Kubernetes", "AWS", "Azure", "Google Cloud", "Terraform",
                "CI/CD", "Git", "Linux", "Apache Kafka", "RabbitMQ", "System Design", "Data Engineering",
                "Apache Spark", "Machine Learning", "TensorFlow", "PyTorch", "Data Analysis", "Power BI",
                "Tableau", "Figma", "UI Design", "UX Research", "Selenium", "QA Automation",
                "Cybersecurity", "DevOps", "Scrum", "Jira"
        );
    }

    private static List<String> softSkills() {
        return List.of(
                "Communication", "Leadership", "Teamwork", "Problem Solving", "Critical Thinking",
                "Adaptability", "Time Management", "Conflict Resolution", "Mentoring",
                "Stakeholder Management", "Product Management", "Agile Coaching", "Public Speaking",
                "Negotiation", "Emotional Intelligence", "Creativity", "Decision Making",
                "Cross-functional Collaboration"
        );
    }

    @Override
    public void run(ApplicationArguments args) {
        if (skillRepository.count() > 0) {
            return;
        }

        List<Skill> toSave = new ArrayList<>();
        for (String name : techSkills()) {
            toSave.add(Skill.builder().name(name).type(new SkillType.Tech()).build());
        }
        for (String name : softSkills()) {
            toSave.add(Skill.builder().name(name).type(new SkillType.Soft()).build());
        }
        skillRepository.saveAll(toSave);

        log.info("Seeded {} skills into catalog", toSave.size());
    }
}
