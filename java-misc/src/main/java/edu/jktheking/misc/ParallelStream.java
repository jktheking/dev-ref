package edu.jktheking.misc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelStream {
  
	public static void main(String[] args) {
		//IntStream.range(2, 7).flatMap().average();
		
		List<List<String>> list = Arrays.asList(
				  Arrays.asList("a"),
				  Arrays.asList("b"));
				System.out.println(list);
				
				list.stream().flatMap(lst->lst.stream()).collect(Collectors.toList());
	}
}
