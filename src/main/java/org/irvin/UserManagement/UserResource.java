package org.irvin.UserManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users")
public class UserResource 
{

	UserRepository repo = new UserRepository();
	Utility utility = new Utility();
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<User> getUsers() 
	{
		return repo.getUsers();
	}	
	
	@GET
	@Path("user/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User getUser(@PathParam("id") String id) 
	{
		return repo.getUser(id);
	}
	
	
	@POST
	@Path("user")
	public User createUser(User user)
	{
		repo.create(user);
		return repo.getUser(user.getEmployeeID());
	}
	
	@PUT
	@Path("user")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User updateUser(User user)
	{
		repo.update(user);
		return repo.getUser(user.getEmployeeID());
	}
	
	
	@DELETE
	@Path("user")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User deleteUser(User user)
	{
		repo.delete(user);
		return repo.getUser(user.getEmployeeID());
	}

	
	@GET
	@Path("quickSort")
	@Produces({MediaType.TEXT_PLAIN})
	public String quickSort() 
	{
		int[] toSortArray = new int[]{75, 99, 21, 8, 54, 45, 69, 15, 25, 32};
		int wall = 0;
		int pivot = toSortArray.length - 1;
		return Arrays.toString(utility.quickSort(toSortArray, wall, pivot));
	}	
		

	@GET
	@Path("getSum/{index}")
	@Produces({MediaType.TEXT_PLAIN})
	public String getSum(@PathParam("index") int index) 
	{
		int[] array = new int[]{3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3};
		int sum = 0;
		
		int[] parentIndices = utility.getParentIndices(array);
		int[] binaryIndexedTree = utility.getBinaryIndexedTree(array, parentIndices);
		
		index--;
		
		int x = 0;
		do {
			System.out.println("--------------------------------");
			System.out.println("                index : "+index);
			System.out.println(" parentIndices[index] : "+parentIndices[index]);
			sum += binaryIndexedTree[index];
			System.out.println("                  sum : "+sum);
			index = parentIndices[index] - 1;
			System.out.println("            new index : "+index);
			
			if (x++ > array.length)
				break;
			
		} while(index > 0);

		return Integer.toString(sum);
	}
	
	
	@GET
	@Path("getSumFibonacciEven/{limit}")
	@Produces({MediaType.TEXT_PLAIN})
	public String getSumFibonacciEven(@PathParam("limit") int limit) 
	{
		long sum = 2;
		int index = 1;
		List<Integer> fibonacci = new ArrayList<Integer>();
		fibonacci.add(1);
		fibonacci.add(2);
		do
		{
			index++;
			fibonacci.add(fibonacci.get(index-1) + fibonacci.get(index-2));
			if (fibonacci.get(index) % 2 == 0 && fibonacci.get(index) <= limit)
				sum += fibonacci.get(index);
		} 
		while(fibonacci.get(index) <= limit );
		
		System.out.println(Arrays.toString(fibonacci.toArray()));
		
		return Long.toString(sum);
	}
	
	
	@GET
	@Path("largestPrimeFactor/{number}")
	@Produces({MediaType.TEXT_PLAIN})
	public String largestPrimFactor(@PathParam("number") long number) 
	{
		System.out.println("----------------------------------------");
		long pivot = 2;
		TreeSet<Long> primeFactors = new TreeSet<Long>();
		primeFactors = utility.getPrimeFactors(primeFactors, pivot, number);
		
 		return Long.toString(primeFactors.last());
	}	
	
 
	@GET
	@Path("palindromic/{digit}")
	@Produces({MediaType.TEXT_PLAIN})
	public String largestPrimFactor(@PathParam("digit") int digit) 
	{
		System.out.println("----------------------------------------");
		int palindrome = utility.getLargestPalindrome(digit);
		
 		return Integer.toString(palindrome);
	}		
	
	
}
