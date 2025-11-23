package com.futurowork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserSkillId.class)
public class UserSkill {
    @Id
    private Long userId;
    @Id
    private Long skillId;
    private String level;
}
