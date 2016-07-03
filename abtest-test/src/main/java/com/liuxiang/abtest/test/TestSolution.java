package com.liuxiang.abtest.test;

import java.util.Map;

import com.liuxiang.abtest.sdk.context.ABContext;
import com.liuxiang.abtest.sdk.solution.SolutionBase;
import com.liuxiang.abtest.sdk.solution.SolutionResult;

public class TestSolution extends SolutionBase{

	@Override
	public SolutionResult execute(ABContext context) {
		Map<String, String> btsParameters = context.getBtsParameters();
		Map<String, String> requestParamters = context.getRequestParamters();
		System.out.println(btsParameters);
		System.out.println(requestParamters);
		System.out.println(this.getClass().getClassLoader());
		return null;
	}

}
