package com.liuxiang.abtest.sdk.solution;

import com.liuxiang.abtest.sdk.context.ABContext;

import lombok.Data;
@Data
public abstract class SolutionBase {
	
	public abstract SolutionResult execute(ABContext context);
	
}
