package com.liuxiang.abtest.sdk.context;

import java.util.Map;

import lombok.Data;

@Data
public class ABContext {
	
	private Map<String,String> btsParameters;
	
	private Map<String,String> requestParamters;
	
}
