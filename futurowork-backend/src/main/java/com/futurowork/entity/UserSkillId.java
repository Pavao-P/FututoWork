package com.futurowork.entity;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSkillId implements Serializable {
    private Long userId;
    private Long skillId;
}
