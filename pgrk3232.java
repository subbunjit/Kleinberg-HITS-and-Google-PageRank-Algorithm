/* SUBRAMANIAN SURYANARAYANAN   cs610 3232 prp */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

class CreateNode3232
{
	int val;
	CreateNode3232 next;
	
	CreateNode3232(int val)
	{
		this.val=val;
		this.next=null;
	}
}
class AdjacencyList3232
{
	CreateNode3232 head;
}
class Graphs3232
{
	int V;
	AdjacencyList3232 hub[];
	AdjacencyList3232 authority[];		
	public Graphs3232(int V)
	{
		this.V=V;
		this.authority=new AdjacencyList3232[V];
		this.hub=new AdjacencyList3232[V];
		for(int i=0;i<V;++i)
		{
			this.authority[i]=new AdjacencyList3232();
			this.authority[i].head=null;
			this.hub[i]=new AdjacencyList3232();
			this.hub[i].head=null;
		}
	}
}
public class pgrk3232 
{
	int iterations;
	int initialvalue;
	String filename;
	//Creating Edges for the given Graph
	static Graphs3232 creatingEdge3232(Graphs3232 g,int a,int b)
	{
		//Creating hub or outdegree Adjacency List
		CreateNode3232 n=new CreateNode3232(b);
		n.next=g.hub[a].head;
	    g.hub[a].head=n;
	    
		//Creating authority or indegree Adjacency List
		CreateNode3232 nd=new CreateNode3232(a);
		nd.next=g.authority[b].head;
		g.authority[b].head=nd;
		
		return g;
	}
	//Calculating the Outdegree values for the Graph
	static int Outdegree3232(Graphs3232 g,int vertices)
	{
	    CreateNode3232 n=new CreateNode3232(vertices);
	    n=g.hub[vertices].head;
	    int cont=0;
	    while(n!=null)
	    {
	    	cont++;
	    	n=n.next;
	    }
		return cont;
	}
	//Adding the values in Adjacency List for Authority
	static Vector<Integer> authorityList3232(Graphs3232 g, int vertices, Vector<Integer> authL)
	{
		CreateNode3232 n=new CreateNode3232(vertices);
		n=g.authority[vertices].head;
		while(n!=null)
		{
			authL.add(n.val);
			n=n.next;
		}
		return authL;
	}
	//Calculating the Iteration values for Hub
	static void iteration3232(Graphs3232 g,int vertices,int t, Vector<Double> previous, Vector<Double> current)
	{
		for(int i=0;i<vertices;i++)
		{
			double res = 0.15/vertices;
			Vector<Integer> authL = new Vector<>();
			authL=authorityList3232(g,i,authL);
			for(int j=0;j<authL.size();j++)
			{
				res+=previous.get(authL.get(j))*0.85/(double)Outdegree3232(g,authL.get(j));
			}
			current.add(i,res);
		}	
		if(vertices<=10)
		{
			System.out.print("Iter : " + t + " : ");
			for(int i=0;i<vertices;i++)
			{
				System.out.printf("P[" + i + "] = %.7f\t",current.get(i));
			}
			System.out.print("\n");
		}
	}
	//Constructor for the class pgrk3232
	public pgrk3232(int iterations,int initialvalue,String filename) throws FileNotFoundException
	{
		this.iterations=iterations;
		this.initialvalue=initialvalue;
		this.filename=filename;
        double errorrate=0;	
		int vertices;
		int edges;
		double invalue;
		Scanner scan=new Scanner(new File(filename));
		vertices=scan.nextInt();
		edges=scan.nextInt();
		Graphs3232 g=new Graphs3232(vertices);
		int a,b;	
		for(int i=0;i<edges;i++)
		{
			a=scan.nextInt();
			b=scan.nextInt();
			g=creatingEdge3232(g,a,b);
		}
		Vector<Double> previous=new Vector<>(vertices,0);
		Vector<Double> current=new Vector<>(vertices,0);
		//When No Of Vertices is greater than zero
		if(vertices>10)
		{
			iterations=0;
			initialvalue=-1;
		}
		if(initialvalue==1)
		invalue=1;
		else if(initialvalue==-1)
		invalue=1/(double)vertices;
		else if(initialvalue==-2)
		invalue=1/(double)Math.sqrt(vertices);
		else invalue=0;
		for(int i=0;i<vertices;i++)
		{
			previous.add(i,invalue);
		}
		//Printing Base Case
		if(vertices<=10)
		{
			System.out.print("Base : 0 :");
			for(int i=0;i<vertices;i++)
			{
				System.out.printf("P[" + i + "]=%.7f\t",previous.get(i));
			}
			
			System.out.println("");
		}
		int t=1;
		if(iterations>0)
		{
			for(t=1;t<=iterations;t++)
			{
				iteration3232(g,vertices,t,previous,current);
				for(int i=0;i<vertices;i++)
				{
					previous.add(i, current.get(i));
				}
			}
		}
		else
		{
			if(iterations==0)
			errorrate=Math.pow(10, -5);
			else
			errorrate=Math.pow(10, iterations);
			while(true)
			{
				iteration3232(g,vertices,t,previous,current);
				boolean brk=true;
				for(int i=0;i<vertices;i++)
				{
					double difference=Math.abs(previous.get(i)-current.get(i));
					
					if(difference<errorrate)
					continue;
					else
					{
						brk=false;
						break;
					}			
				}
				if(brk) break;
				for(int i=0;i<vertices;i++)
				{
					previous.set(i,current.get(i));
				}
				t++;
			}
		}
		//Printing results for Vertices which are greater than 10
		if(vertices>10)
		{
			System.out.print("Iter : " +t + "\n");
			for(int i=0;i<vertices;i++)
			{
				System.out.printf("P[" + i + "] =%.7f",current.get(i));
			}
		}
		scan.close();
	}
public static void main(String[] args) throws IOException
{
	if(args.length!=3)
	{
		System.out.println("Enter 3 arguments: iterations initialvalue filename");
	}
	Scanner sc=new Scanner(System.in);
	int it=Integer.parseInt(args[0]);
	int ival = Integer.parseInt(args[1]);
	String file=args[2];
	pgrk3232 pgrk=new pgrk3232(it,ival,file);
}
}




