package com.ikhokha.techcheck;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

import com.ikhokha.techcheck.service.ContainsService;
import com.ikhokha.techcheck.service.LengthService;
import com.ikhokha.techcheck.service.OccurrenceService;
import com.ikhokha.techcheck.service.PatternService;

public class Main {

	private static final int NUMBER_OF_THREAD_IN_POOL = 4;

	public static void main(String[] args)  {

		Set<Future<Map<String, Integer>>> fileResults = new HashSet<>();
		Map<String, Integer> totalResults = new HashMap<>();
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));

		generateReport(commentFiles,fileResults);
		getFileReport(fileResults, totalResults);

		System.out.println("RESULTS\n********");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}

	/**
	 * Runs the files report by submitting to a execution service
	 * @param files the list of comment files
	 * @param results the future set of the report results
	 */
	private static void generateReport(File[] files, Set<Future<Map<String, Integer>>> results){
		ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREAD_IN_POOL);
		try {
			List<PatternService> matchers = createReportMatcher();
			for (File commentFile : files) {
				Callable<Map<String, Integer>> callable = new CommentAnalyzer(commentFile, matchers);
				Future<Map<String, Integer>> future = service.submit(callable);
				results.add(future);
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Cannot generate report: " + e.getMessage());
		}
	}

	/**
	 * Get report for each comment file
	 * @param fileResults the set of result future map
	 * @param totalResults the target results  map
	 */
	private static void getFileReport(Set<Future<Map<String, Integer>>> fileResults, Map<String, Integer> totalResults){
		for (Future<Map<String, Integer>> future : fileResults) {
			try {
				addReportResults(future.get(), totalResults);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("InterruptedException: " + e.getMessage());
			} catch (ExecutionException e) {
				e.printStackTrace();
				System.out.println("ExecutionException: " + e.getMessage());
			} catch (Exception e){
				e.printStackTrace();
				System.out.println(" Error generating report: " + e.getMessage());
			}
		}
	}

	/**
	 * Creates the report matchers instances
	 * @return List the list of matchers
	 */
	private static List<PatternService> createReportMatcher() throws Exception{
		final String URL_REGEX = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";
		List<PatternService> matchers = Arrays.asList(
				new OccurrenceService("MOVER_MENTIONS", "Mover"),
				new OccurrenceService("SHAKER_MENTIONS", "Shaker"),
				new LengthService("SHORTER_THAN_15", LengthService.Condition.LessThan, 15),
				new ContainsService("SPAM", URL_REGEX),
				new ContainsService("QUESTIONS", "[?]")
		);
		return matchers;
	}

	/**
	 * Adds the result counts from a source map to the target map
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {
		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			target.putIfAbsent(entry.getKey(), 0);
			target.put(entry.getKey(), target.get(entry.getKey()) + entry.getValue());
		}
	}
}
