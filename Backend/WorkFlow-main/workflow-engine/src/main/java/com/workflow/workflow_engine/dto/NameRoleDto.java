package com.workflow.workflow_engine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameRoleDto {
	
	private String name;
	
	private String role;

	private String token;
}
