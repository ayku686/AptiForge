package com.springprojects.AptiForge.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipantDto implements Serializable{
    private UUID userId;
    private String name;
    private int score = 0;
    private int teamId;
}