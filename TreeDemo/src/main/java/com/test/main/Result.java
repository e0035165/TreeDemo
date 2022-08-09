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
	public static Map<Integer, List<Integer>>childmapper = new HashMap<>();
	public static int[]prt = new int[50000];
	public static Map<Integer, List<Long>>maplist = new HashMap<>();
	public static long balancedForest(List<Integer> c, List<List<Integer>> edges) {
	
	   value = List.copyOf(c);
	   
	   boolean[] visited = new boolean[c.size()];
	     for(int i=0;i<c.size();i++)
	     {
	       visited[i] = false;
	       mapper.put(i, new ArrayList<>());
	       maplist.put(i, new ArrayList<>());
	       childmapper.put(i, new ArrayList<>());
	     }
	   
	     for(List<Integer>ddd:edges)
	     {
	       mapper.get(ddd.get(0)-1).add(ddd.get(1)-1);
	       mapper.get(ddd.get(1)-1).add(ddd.get(0)-1);
	     }
	   
	   
	   
	   long total = DFS(0,visited);
	   long max = Long.MAX_VALUE;
	   //System.out.println("Total: "+total);
	   
	   for(int i=1;i<c.size();i++)
	   {
		   long temp = total - nodal[i];
		   long tempt = nodal[prt[i]]-nodal[i];
		   List<Long>listA = new ArrayList<>();
		   listA.addAll(maplist.get(0));
		   listA.remove(nodal[i]);
		   listA.removeAll(maplist.get(i));
		   listA.remove(nodal[prt[i]]);
		   listA.add(tempt);
		   /*System.out.println("Adot) "+temp+ " Bdot) "+nodal[i]+ " just check parent: "+nodal[prt[i]]);
		   System.out.println("A: "+listA);
		   System.out.println("B: "+maplist.get(i));*/
		   if(temp > nodal[i])
		   {
			   if(listA.contains(nodal[i]) || listA.contains(temp - nodal[i]))
			   {
				   if(temp - nodal[i] <= nodal[i])
					   max = max > (3*nodal[i] - total) ? (3*nodal[i] - total) : max;
			   }
			   
			   if(temp%2 == 0 && temp/2 >= nodal[i] && listA.contains(temp/2))
			   {
				   max = max > (3*(temp/2) - total) ? (3*(temp/2) - total) : max;
			   }
		   } else {
			   if(maplist.get(i).contains(temp) || maplist.get(i).contains(nodal[i] - temp) || nodal[i] == temp)
			   {
				   if(nodal[i] - temp <= temp)
					   max = max > (3*temp - total) ? (3*temp - total) : max;
			   }
			   
			   if(nodal[i]%2 == 0 && nodal[i]/2 >= temp && maplist.get(i).contains(nodal[i]/2))
			   {
				   max = max > (3*(nodal[i]/2) - total) ? (3*(nodal[i]/2) - total) : max;
			   }
		   }
		   
	   }
	   	   /*
	   for(int i=1;i<c.size();i++)
	   {
		   long temp = total - nodal[i];
		   List<Long>listA = maplist.get(i);
		   listA.remove(nodal[i]);
	   if(!maplist.get(i).isEmpty())
		   {
			   listA.removeAll(maplist.get(i));
		   }
		   if(temp > nodal[i])
		   {
			   if(listA.contains(nodal[i]) || (nodal[prt[i]] - nodal[i] == nodal[i]))
			   {
				   
			   }
		   } else {
			   if(maplist.get(i).contains(temp))
			   {
				   
			   }
		   }
	   }*/
	   
	   /*for(int i=1;i<c.size();i++) {
		   List<Integer>listA = (childmapper.get(0)).stream().collect(Collectors.toList());
		   listA.remove((Integer) i);
		   listA.removeAll(childmapper.get(i));
		   List<Integer>listB = new ArrayList<>();
		   if(childmapper.get(i) != null)
		   {
			   listB = (childmapper.get(i)).stream().collect(Collectors.toList());
		   }
		   long A = nodal[0] - nodal[i];
		   long B = nodal[i];
		   long CA = 0;
		   if(A == B)
		   {
			   max = A < max ? A : max;
		   }
		   boolean checkchild = false;
		   int[] flag = new int[listA.size()];
		   
		   for(Integer C : listA)
		   {
		
			   if(childmapper.get(C).contains(i))
			   {
				   long One = A -nodal[C]+nodal[i];
				   long Two = nodal[C]-nodal[i];
				   long Three = B;
				   if(One == Two && One > Three)
				   {
					   max = One -Three < max ? One - Three : max;
				   } else if(One == Three && Two < Three)
				   {
					   max = Three - Two < max ? Three-Two : max;
				   } else if (Two == Three && One < Three)
				   {
					   max = Three - One < max ? Three-One : max;
				   }
			   } else {
				   long One = A - nodal[C];
				   long Two = nodal[C];
				   long Three = B;
				   if(One == Two && One > Three)
				   {
					   max = One -Three < max ? One - Three : max;
				   } else if(One == Three && Two < Three)
				   {
					   max = Three - Two < max ? Three-Two : max;
				   } else if(Two == Three && One < Three)
				   {
					   max = Three - One < max ? Three-One : max;
				   }
			   }
		   }
		   for(Integer C : listB)
		   {
			   long One = B - nodal[C];
			   long Two = nodal[C];
			   long Three = A;
			   if(One == Two && One > Three)
			   {
				   max = One -Three < max ? One - Three : max;
			   } else if(One == Three && Two < Three)
			   {
				   max = Three - Two < max ? Three-Two : max;
			   } else if(Two == Three && One < Three)
			   {
				   max = Three - One < max ? Three-One : max;
			   }
		   }
		   
	   }*/
	   
	   
	   if(max == Long.MAX_VALUE)
	   {
		   max = -1;
	   }
	   maplist.clear();
	   return max;
    }
	
	
	
	
	
	public static long DFS(int index, boolean[] visited)
	{
		visited[index] = true;
		long ans = value.get(index);
		for(Integer subnode : mapper.get(index))
		{
			if(!visited[subnode])
			{
				ans += DFS(subnode,visited);
				childmapper.get(index).add(subnode);
				maplist.get(index).add(nodal[subnode]);
				if(childmapper.get(subnode)!=null)
				{
					childmapper.get(index).addAll(childmapper.get(subnode));
					maplist.get(index).addAll(maplist.get(subnode));
					prt[subnode] = index;
				}
				
			}
		}
		nodal[index] = ans;
		
		
		return ans;
	}
	
	
}
