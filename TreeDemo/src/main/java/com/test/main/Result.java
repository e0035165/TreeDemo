package com.test.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Result {
	 public static long[]nodal = new long[50000];
	  public static Map<Integer, List<Integer>>mapper = new HashMap<>();
	  public static List<Integer>value = new ArrayList<>();
	  //public static Map<Integer, List<Integer>>childmapper = new HashMap<>();
	  public static Map<Long, Integer>totalmapper = new HashMap<>();
	  public static Map<Integer, List<Long>>maplist = new HashMap<>();
	  public static Map<Integer, Map<Long, Integer>>testmap = new HashMap<>();
	  public static long balancedForest(List<Integer> c, List<List<Integer>> edges) {
	  
	     value = List.copyOf(c);
	     
	     boolean[] visited = new boolean[c.size()];
	       for(int i=0;i<c.size();i++)
	       {
	         visited[i] = false;
	         mapper.put(i, new ArrayList<>());
	         maplist.put(i, new ArrayList<>());
	         //childmapper.put(i, new ArrayList<>());
	         testmap.put(i, new HashMap<>());
	       }
	     
	       for(List<Integer>ddd:edges)
	       {
	         mapper.get(ddd.get(0)-1).add(ddd.get(1)-1);
	         mapper.get(ddd.get(1)-1).add(ddd.get(0)-1);
	       }
	     
	     
	     
	     long total = DFS(0,visited);
	     long max = Long.MAX_VALUE;
	     
	     
	     for(int i=1;i<c.size();++i)
	     {
	       long A = nodal[i];
	       long B = total - nodal[i];
	       if( B > A)
	       {
	         if(totalmapper.get(nodal[i]) > testmap.get(i).get(nodal[i]) && B-nodal[i]<=nodal[i])
	         {
	           max = max > (3*nodal[i] - total) ? (3*nodal[i] - total) : max;
	         }
	         
	         if(totalmapper.get(B - nodal[i]) != null && B-nodal[i] <= nodal[i]
	             && (testmap.get(i).get(B-nodal[i]) == null || totalmapper.get(B-nodal[i]) > testmap.get(i).get(B - nodal[i])))
	         {
	           max = max > (3*nodal[i] - total) ? (3*nodal[i] - total) : max;
	         }
	         
	       } else {
	         if((totalmapper.get(B) != null) && testmap.get(i).get(B) != null && nodal[i] - B <= B)
	         {
	           max = max > (3*B - total) ? (3*B - total) : max;
	         }
	         
	         if(totalmapper.get(nodal[i]-B)!=null && testmap.get(i).get(nodal[i]-B) != null && nodal[i] - B <= B)
	         {
	           max = max > (3*B - total) ? (3*B - total) : max;
	         }
	         
	         if(B == A)
	         {
	           max = max > 3*B ? 3*B : max;
	         }
	       }
	     }
	     
	     
	     
	     
	     if(max == Long.MAX_VALUE)
	     {
	       max = -1;
	     }
	     maplist.clear();
	     testmap.clear();
	     totalmapper.clear();
	     return max;
	    }
	  
	  
	  
	  public static long DFS(int index, boolean[] visited)
	  {
	    visited[index] = true;
	    long ans = value.get(index);
	    testmap.put(index, new HashMap<>());
	    
	    for(Integer subnode : mapper.get(index))
	    {
	      if(!visited[subnode])
	      {
	        ans += DFS(subnode,visited);
	        //childmapper.get(index).add(subnode);
	        maplist.get(index).add(nodal[subnode]);
	        testmap.get(index).putIfAbsent(nodal[subnode], 0);
	        testmap.get(index).put(nodal[subnode], testmap.get(index).get(nodal[subnode]) + 1);
	        if(maplist.get(subnode)!=null)
	        {
	          //childmapper.get(index).addAll(childmapper.get(subnode));
	          maplist.get(index).addAll(maplist.get(subnode));
	          //prt[subnode] = index;
	          
	        }
	        
	        for(Long key : maplist.get(subnode))
	        {
	          testmap.get(index).putIfAbsent(key, 0);
	          testmap.get(index).put(key, testmap.get(index).get(key) + 1);          
	        }
	        
	      }
	    }
	    nodal[index] = ans;
	    testmap.get(index).putIfAbsent(ans, 0);
	    testmap.get(index).put(nodal[index], testmap.get(index).get(ans) + 1);
	    
	    if(totalmapper.get(nodal[index]) != null)
	    {
	      totalmapper.put(nodal[index], totalmapper.get(nodal[index]) + 1);
	    } else {
	      totalmapper.put(nodal[index], 1);
	    }
	    
	    return ans;
	  }
	
	
}
