package com.test.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	FileReader fr = null;
		//FileWriter fw = null;
		try {
			fr = new FileReader("C:\\Users\\ksathyas\\git\\TreeDemo\\TreeDemo\\Testcases\\tc15.txt");
			//fw = new FileWriter("C:\\Users\\ksathyas\\OneDrive - Capgemini\\Documents\\Answer.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader buffread = new BufferedReader(fr);
		//BufferedWriter buffwrite = new BufferedWriter(fw);
		try {
			int q = Integer.parseInt(buffread.readLine().trim());
			//System.out.println(q);
			IntStream.range(0, q).forEach(qItr ->
			{
				int n = 0;
				List<Integer> a = new ArrayList<>();
				try {
					n = Integer.parseInt(buffread.readLine().trim());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					a = Stream.of(buffread.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt).collect(Collectors.toList());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				List<List<Integer>>edges = new ArrayList<>();
				IntStream.range(0, n-1).forEach(i -> {
					try {
						edges.add(Stream.of(buffread.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
					} catch(IOException ex) {
						throw new RuntimeException(ex);
					}
				});
				long result = Result.balancedForest(a, edges);
				System.out.println("Answer: "+result);
			}
			);
		} catch (NumberFormatException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		try {
			fr.close();
			//fw.close();
			buffread.close();
			//buffwrite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
