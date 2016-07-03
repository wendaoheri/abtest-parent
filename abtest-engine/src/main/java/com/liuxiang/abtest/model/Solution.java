package com.liuxiang.abtest.model;

import java.util.List;

import lombok.Data;

@Data
public class Solution {
	private String solutionId;
	private int flowRate;
	private String jarPath;
	private String solutionClass;
	private List<Bucket> buckets;
	
	
}
