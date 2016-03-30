package adgaonkar.shrirang;
import java.util.*;
import java.math.*;
class scheduler{
	public static void main(String[] args){
		
		Scanner s=new Scanner(System.in);
		System.out.println("enter no of players: ");
		int n=s.nextInt();
		int days;
		int [] list;
		//check whether # of players is even or odd to SET # OF DAYS REQD
		if(n%2==0){
			days=n-1;
			list=new int[n];
			for(int i=0;i<n;i++)
				list[i]=i+1;
		}
		else{
			days=n;
			list=new int[n+1];
			for(int i=0;i<n;i++)
				list[i]=i+1;
			list[n]=0;
		}
		
		int size=list.length;
		//System.out.println("size is :"+size);
		int [] listA=new int[size/2];
		int [] listB=new int[size/2];
		
		// now split array into 2 halves. and assign values
			for(int i=0;i<size/2;i++)
				listA[i]=list[i];
			for(int i=0;i<size/2;i++)
				listB[i]=list[size-i-1];
		
			
			int [] listP=new int[size];	
		//print	
			int k=0;
		while(days>0){
			days--;k++;
			for(int i=0;i<size/2;i++){
				listP[Math.abs(listA[i]-1)]=listB[i];
				listP[Math.abs(listB[i]-1)]=listA[i];
			}
			System.out.print("\n"+k);
			if(n%2==0){
			for(int i=0;i<size;i++)	
				System.out.print(":"+listP[i]);
			}
			else{
				for(int i=0;i<size-1;i++){
					if(listP[i]==0)
						System.out.print(":-");
					else
						System.out.print(":"+listP[i]);
				}
			}
			
			int tempA=listA[size/2-1];
			int tempB=listB[0];
			for(int i=size/2;i>1;i--){
				listA[i-1]=listA[i-2];
				listB[size/2-i]=listB[size/2-i+1];
			}
			listA[1]=tempB;
			listB[size/2-1]=tempA;
		}
	}
}