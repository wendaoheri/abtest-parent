package com.liuxiang.abtest.model;

import java.util.Map;

import lombok.Data;

@Data
public class Bucket {
	private String bucketId;
	private int flowRate;
	private Map<String,String> btsParameters;
}
