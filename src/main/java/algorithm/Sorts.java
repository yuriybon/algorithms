package algorithm;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class Sorts {
	private int[] a;
	
	public int[] getA() {
		return a;
	}

	public void setA(int[] a) {
		this.a = a;
	}

	public int[] generate(int len) {
		a = new int[len];
		for (int i = 0; i < len; i++) {
			a[i] = randInt(10,2000);
		}
		return a;
	}
	
	public void print(int[] a) {
		System.out.println(Arrays.toString(a));
	}
	
	public static void insertSort(int[] arr) {
		int j;
		for (int i = 0; i < arr.length; i++) {
			j=i;
			while (j>0 && arr[j-1] > arr[j]) {
				int temp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] =temp;
				j=j-1;
			}
			
		}
		
	}


	public static int randInt(int min, int max) {

	    // NOTE: This will (intentionally) not run as written so that folks
	    // copy-pasting have to think about how to initialize their
	    // Random instance.  Initialization of the Random instance is outside
	    // the main scope of the question, but some decent options are to have
	    // a field that is initialized once and then re-used as needed or to
	    // use ThreadLocalRandom (if using at least Java 1.7).
	    // 
	    // In particular, do NOT do 'Random rand = new Random()' here or you
	    // will get not very good / not very random results.
	    Random rand = new Random();
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static void merge(int[] arr,int l, int m, int r) {
		if (l<r) 
			arr[m] = l;
		else
			arr[m] = r;
	}
	
	public static void copyFrom(int[] src, int[] s1, int[] s2) {
		for (int i = 0; i < src.length; i++) {
			if (i<s1.length) 
				s1[i] = src[i];
			else {
				//if (s2.length-1>(i - s1.length-1))
				s2[i - s1.length ]= src[i];
			}	
		}
	}
	
	public static void recSort(int[] a, int pos) {
		if (pos == a.length-1)
			return;
		if (a[pos] > a[pos+1]) {
			a[pos+1] = a[pos+1]+a[pos];
			a[pos] = a[pos+1]-a[pos];
			a[pos+1] = a[pos+1]-a[pos];
			if (pos != 0) 
			    recSort(a,--pos);
//			else
//				recSort(a,0);	
		}
		recSort(a,++pos);
	}
	
	public static void mergeSort(int[] arr) {
		//1. split array into two arrays
		int[] a1 = new int[arr.length/2];
		int[] a2 = new int[arr.length-arr.length/2];
		copyFrom(arr,a1,a2);
		//2. sort those arrys by recSort
		recSort(a1,0);
		//insertSort(a1);
		recSort(a2,0);
		//insertSort(a2);
		//System.out.println(Arrays.toString(a1));
		//System.out.println(Arrays.toString(a2));
		//3. execute merge sort
		int pos1 = 0;
		int pos2 = 0;
		for (int i = 0; i < arr.length; i++) {
			if (a1.length == pos1 || a2.length == pos2) {
				if ( pos1 < pos2 )
					arr[i] = a1[pos1];
				else {
					arr[i] = a2[pos2];
				}
			} 
			else {	
			if (a1[pos1] <= a2[pos2]) { 
				arr[i]=a1[pos1];
				pos1++;
			} else {
				arr[i]=a2[pos2];
				pos2++;
			}
			}
				
		}
		
	}
	
	public static void sort(String action, int[] arr) {
		
		long millisStart = Calendar.getInstance().getTimeInMillis();

		if (action.equals("Insert")) {
			insertSort(arr);
		}
			 
		if (action.equals("Merge")) {
			mergeSort(arr);
		}
		System.out.print(action+" ");
		System.out.println( Calendar.getInstance().getTimeInMillis()-millisStart);
	}

	public static void main(String[] args) {
//		Sorts s = new Sorts();
//		int[] a1 = s.generate(100);
		//int[] a2 = Arrays.copyOf(a1, a1.length);
		int len = 1_000_000;
		int[] a1 = new int[len];
		for (int i = 0; i < len; i++) {
			a1[i] = randInt(10,10_000_000);
		}
		
		int[] a2 = new int[a1.length];
		for (int i = 0; i < a1.length; i++) {
			a2[i] = a1[i];
		}
		
//		System.out.println(Arrays.toString(a1));
//		System.out.println(Arrays.toString(a2));
		if (Arrays.equals(a1, a2)) 
			System.out.println("ravno");
		else 
			System.out.println("not");

		// TODO Auto-generated method stub
		sort("Insert",a1);
//		System.out.println(Arrays.toString(a1));
//		System.out.println(Arrays.toString(a2));
		sort("Merge",a2);
//		System.out.println(Arrays.toString(a2));

		for (int i = 0; i < len; i++) {
			a1[i] = randInt(10,2000);
		}
		
		for (int i = 0; i < a1.length; i++) {
			a2[i] = a1[i];
		}
		
//		System.out.println(Arrays.toString(a1));
//		System.out.println(Arrays.toString(a2));
		if (Arrays.equals(a1, a2)) 
			System.out.println("ravno");
		else 
			System.out.println("not");

		sort("Merge",a2);
//		System.out.println(Arrays.toString(a2));

		
		// TODO Auto-generated method stub
		sort("Insert",a1);
//		System.out.println(Arrays.toString(a1));
//		System.out.println(Arrays.toString(a2));
	
	}

}
