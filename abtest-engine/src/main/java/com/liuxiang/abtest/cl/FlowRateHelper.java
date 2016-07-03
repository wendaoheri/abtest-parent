package com.liuxiang.abtest.cl;

import java.util.List;

import com.google.common.collect.Lists;

public class FlowRateHelper {
	
	public static List<Integer> percentsToDividers(List<Integer> percents) {
		List<Integer> dividers = Lists.newArrayList();
		int divider = 0;
		for(int i = 0;i < percents.size() - 1;i ++) {
			divider += percents.get(i);
			dividers.add(divider);
		}
		return dividers;
	}
	
}
