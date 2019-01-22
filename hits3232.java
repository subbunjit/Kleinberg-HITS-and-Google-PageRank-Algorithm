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
public class hits3232 
{
	int iterations;
	int initialvalue;
	String filename;
	//Creating Edges for the given Graph
	static Graphs3232 creatingEdge(Graphs3232 g, int a, int b)
	{
		//Creating hub or outdegree adjacency List
		CreateNode3232 n=new CreateNode3232(b);
		n.next=g.hub[a].head;
		g.hub[a].head=n;
		
		//Creating authority or indegree adjacency List
		CreateNode3232 nd=new CreateNode3232(a);
		nd.next=g.authority[b].head;
		g.authority[b].head=nd;
		
		return g;
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
	//Adding the values in Adjacency List for Hub
	static Vector<Integer> hubList3232(Graphs3232 g, int vertices, Vector<Integer> hubL) 
	{
		CreateNode3232 n=new CreateNode3232(vertices);
		n=g.hub[vertices].head;
		
		while(n!=null)
		{
			hubL.add(n.val);
			n=n.next;
		}
		return hubL;
	}
	//Calculating the Iteration values for Hub and Authority
	static void iteration3232(Graphs3232 g, int vertices, int t, Vector<Double> authority, Vector<Double> hub) 
	{
		double sumsqrtA=0;
		for(int i=0;i<vertices;i++)
		{
			double res=0;
			//Authority Vector Calculation
			Vector<Integer> authL = new Vector<>();
			authL=authorityList3232(g,i,authL);
			for(int j=0;j<authL.size();j++)
			{
				res+=hub.get(authL.get(j));
			}
			authority.add(i, res);
			sumsqrtA+=authority.get(i)*authority.get(i);
		}
		double sumsqrtH=0;
		for(int i=0;i<vertices;i++)
		{
			double res=0;
			Vector<Integer> hubL=new Vector<>();
			hubL=hubList3232(g,i,hubL);
			for(int j=0;j<hubL.size();j++)
			{
			res+=authority.get(hubL.get(j));
			}
			hub.add(i,res);
			sumsqrtH+=hub.get(i)*hub.get(i);
		}
		
		//Scaling Factor
		for(int i=0;i<vertices;i++)
		{
			authority.set(i,authority.get(i)/(Math.sqrt(sumsqrtA)));
			hub.set(i,hub.get(i)/(Math.sqrt(sumsqrtH)));
		}
		
		//Printing results for Vertices which are greater than 10
		if(vertices<=10)
		{
			System.out.print("Iter : " + t + ":");
			for(int i=0;i<vertices;i++)
			{
				System.out.printf("A/H [" + i + "]=%.7f /%.7f\t",authority.get(i),hub.get(i));
			}
			System.out.println("");
		}
	}

public hits3232(int iterations,int initialvalue,String filename) throws FileNotFoundException
{		
		this.iterations=iterations;
		this.initialvalue=initialvalue;
		this.filename=filename;
		int a,b;
		double errorrate=0;
		int vertices;
		int edges;
		double invalue;
		Scanner scan=new Scanner(new File(filename));
		vertices=scan.nextInt();
		edges=scan.nextInt();
		Graphs3232 g=new Graphs3232(vertices);
		for(int i=0;i<edges;i++)
		{
			a=scan.nextInt();
			b=scan.nextInt();
			g=creatingEdge(g,a,b);
		}
		Vector<Double> authority=new Vector<>(vertices,0);
		Vector<Double> hub=new Vector<>(vertices,0);
		Vector<Double> prevauthority=new Vector<>(vertices,0);
		Vector<Double> prevhub=new Vector<>(vertices,0);
		//When No Of Vertices is greater than zero
		if(vertices>10)
		{
			iterations=0;
			invalue=-1;
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
			authority.add(i,invalue);
			hub.add(i, invalue);
			prevauthority.add(i,invalue);
			prevhub.add(i,invalue);
		}
		
		//Printing Base Case
		if(vertices<=10)
		{
			System.out.print("Base : " );
			for(int i=0;i<vertices;i++)
			{
				System.out.printf("A/H [" + i + "]=%.7f/%.7f\t",authority.get(i),hub.get(i));
			}
			System.out.println("");
		}
		if(initialvalue==0)
		return;
		int t=1;
		if(iterations>10)
		{
			for(t=1;t<=iterations;t++)
			{
				iteration3232(g,vertices,t,authority,hub);
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
				iteration3232(g,vertices,t,authority,hub);
				boolean brkA=true, brkH=true;
				for(int i=0;i<vertices;i++)
				{
					double diffA=Math.abs(authority.get(i)-prevauthority.get(i));
					double diffH=Math.abs(hub.get(i)-prevhub.get(i));
					if(diffA<errorrate && diffH<errorrate) continue;
					else
					{
						brkA=false;
						brkH=false;
						break;
					}
					
				}
				if(brkA && brkH) break;
				for(int i=0;i<vertices;i++)
				{
					prevauthority.set(i, authority.get(i));
					prevhub.set(i, hub.get(i));
				}
				t++;
			}
		}
		//Printing results for Vertices which are greater than 10
		if(vertices>10)
		{
			System.out.print("\nIter : " + t);
			for(int i=0;i<vertices;i++)
			{
				System.out.printf("A/H [" + i + "]=%.7f/%.7f\t",authority.get(i),hub.get(i));
			}
		}
		scan.close();
	}

	public static void main(String args[]) throws IOException 
	{
	if(args.length!=3)
	{
	System.out.println("Enter 3 arguments: iterations initialvalue filename");
	}
	Scanner sc=new Scanner(System.in);
	int it=Integer.parseInt(args[0]);
	int ival=Integer.parseInt(args[1]);
	String file=args[2];
	hits3232 hits=new hits3232(it,ival,file);
}
}