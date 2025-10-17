import java.util.ArrayList;

public class App
{
	public static void main(String[] args) {
	    
	    
		//Random Array Maker for later tests
	    int sizeOfArray = (int)(Math.random() * 10000001); 
	    int[] randomArray = new int[sizeOfArray];
	    int arrayNumber = (int)(Math.random() * 10000001);
	    for (int i = 0; i < sizeOfArray; i++){
	        randomArray[i] = arrayNumber;
	        arrayNumber = (int)(Math.random() * 10000001);
	    }
        int[] newArray = {1,5,3,2,10,24,55,44,22,11,55,44,33,63,23};
        
        long timerStart = System.nanoTime();
        QuickSort(newArray);
	    
	    long timerEnd = System.nanoTime();
        long duration = timerEnd - timerStart;
        System.out.println("Sorting an array of " + sizeOfArray + " took QuickSort: ");
        System.out.println(duration + " nanoseconds.");
        System.out.printf("%.6f seconds%n", duration /1_000_000_000.0);
        System.out.println();
        
        timerStart = System.nanoTime();
	    DynamicShellSort(randomArray);
	    
	    timerEnd = System.nanoTime();
        duration = timerEnd - timerStart;
        System.out.println("Sorting an array of " + sizeOfArray + " took shellsort: ");
        System.out.println(duration + " nanoseconds.");
        System.out.printf("%.6f seconds%n", duration /1_000_000_000.0);
        System.out.println();
        
	}
	
	public static int[] QuickSort(int[] arr) {
    if (arr.length < 2) {
        return arr;
    }

    int pivotIndex = arr.length / 2;
    int pivot = arr[pivotIndex];

    ArrayList<Integer> lowerArray = new ArrayList<>();
    ArrayList<Integer> higherArray = new ArrayList<>();

    for (int i = 0; i < arr.length; i++) {
        if (i == pivotIndex) continue;
        if (arr[i] <= pivot) {
            lowerArray.add(arr[i]);
        } else {
            higherArray.add(arr[i]);
        }
    }

    int[] lowerSorted = QuickSort(lowerArray.stream().mapToInt(x -> x).toArray());
    int[] higherSorted = QuickSort(higherArray.stream().mapToInt(x -> x).toArray());

    int[] sorted = new int[lowerSorted.length + 1 + higherSorted.length];
    sorted[lowerSorted.length] = pivot;

    return sorted;
}
	 
	
	public static int[] DynamicShellSort(int[] arr){
	    //Makes the dynamic intervals
        int n = 0;
        while ((1 << (n + 1)) < arr.length) {
            n++;
        }
        int[] intervals = new int[n];
        for (int i = 0; i < n; i++) {
            intervals[i] = 1 << (n - i - 1);  
        }
        
	    int[] result = arr.clone();  
        for (int gap : intervals) {
            for (int i = gap; i < result.length; i++) {
                int temp = result[i];
                int j = i;
    
                while (j >= gap && result[j - gap] > temp) {
                    result[j] = result[j - gap];
                    j -= gap;
                }
    
                result[j] = temp;
            }
        }
    
        return result;
	}
	
	
}
