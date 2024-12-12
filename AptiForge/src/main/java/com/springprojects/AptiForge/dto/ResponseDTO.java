package com.springprojects.AptiForge.dto;

import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO{
	private UUID participant_id;
	private UUID question_id;
	private UUID option_id;
}