package com.liuxiang.abtest.cl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liuxiang.abtest.model.Solution;
import com.liuxiang.abtest.sdk.context.ABContext;
import com.liuxiang.abtest.sdk.solution.SolutionBase;
import com.liuxiang.abtest.util.UUIDGenerator;

@Component
public class SolutionHandler {
	
	
	/**
	 * ���еķ���ID�б�
	 */
	private static List<String> solutionIds = Lists.newArrayList();
	
	/**
	 * soulution����������ٷֱ�
	 * ��:[30,40,30]
	 */
	private static List<Integer> percents = Lists.newArrayList();
	
	/**
	 * ��������ķָ���
	 * ��[30,70]
	 */
	private static List<Integer> dividers = Lists.newArrayList();
	
	/**
	 * ����jar����classloader Map
	 * key:solutionId
	 * value:SolutionClassLoader
	 */
	private static Map<String,SolutionClassLoader> classLoaderMap = Maps.newHashMap();
	
	/**
	 * ������Map
	 * key:solutionId
	 * value:SolutionBase��ʵ����
	 */
	private static Map<String,Class<SolutionBase>> classMap = Maps.newHashMap();
	
	/**
	 * �������ʵ��Map
	 * key:solutionId
	 * value:SolutionBase��ʵ��
	 */
	private static Map<String,SolutionBase> solutionInstanceMap = Maps.newHashMap();
	
	public static void init() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(SolutionHandler.class.getClassLoader().getResourceAsStream("solutions.json")));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String json = sb.toString();
		
		List<Solution> solutions = JSON.parseArray(json, Solution.class);
		for(Solution solution : solutions) {
			addSolution(solution);
		}
		dividers = FlowRateHelper.percentsToDividers(percents);
	}
	
	public static void main(String[] args) throws Exception {
		String uuid = UUIDGenerator.getUUID();
		for(String solutionId : solutionInstanceMap.keySet()) {
			SolutionBase solutionBase = solutionInstanceMap.get(solutionId);
			solutionBase.execute(newABContext(solutionId));
		}
	}
	public static void addSolution(Solution solution) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		String solutionId = solution.getSolutionId();
		solutionIds.add(solutionId);
		percents.add(solution.getFlowRate());
		SolutionClassLoader scl = new SolutionClassLoader(solution.getJarPath());
		classLoaderMap.put(solutionId, scl);
		Class<SolutionBase> clazz = scl.loadClass(solution.getSolutionClass());
		classMap.put(solutionId, clazz);
		SolutionBase instance = clazz.newInstance();
		solutionInstanceMap.put(solutionId, instance);
	}
	
	public static ABContext newABContext(String solutionId) {
		ABContext context = new ABContext();
		return context;
	}
}
