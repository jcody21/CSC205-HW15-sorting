/*
 * This Java application provides implementions for numerous
 * comparison sort algorithms.
 *
 * @creator John Cody
 * @created 02019.04.10
 */

public class Sorts {
   private static int[][] arrays = { { 3, 4, 1, 7, 2, 5, 9, 12, 6, 15 },
                                     { 12, 6, 15, 9, 5, 2, 7, 1, 4, 3 },
                                     { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                                     { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 }, };
   private static int[] copy;
   private static int[] a;
   private static int ncompares;
   private static int nswaps;

   public static void main(String[] argv) {
      for (int i = 0; i < arrays.length; i++)
        doit(i);
   }

   private static void doit(int idx) {
      a = arrays[idx];
      A("shellSort", true); shellSort(); A("shellSort", false);
      A("bubbleSort", true); bubbleSort(); A("bubbleSort", false);
      A("selectionSort", true); selectionSort(); A("selectionSort", false);
      A("exchangeSort", true); exchangeSort(); A("exchangeSort", false);
      A("quickSort", true); quickSort(0, a.length-1); A("quickSort", false);
   }

   private static void A(String sort, boolean begin) {
      if (begin) {
         copy = new int[a.length];
         System.arraycopy(a, 0, copy, 0, a.length);
         System.out.println("beginning " + sort + "...");
         ncompares = nswaps = 0;
         A();
      } else {
         System.out.println("ending " + sort + "...");
         System.out.println("*** #compares: "+ncompares+"; #swaps: "+nswaps);
         A();
         System.arraycopy(copy, 0, a, 0, copy.length);
      }
   }

   private static void A() {
      for (int i = 0; i < a.length; i++)
         System.out.print(a[i] + "  ");
      System.out.println();
   }

   private static boolean compare(int i1, int i2) {
      ncompares++;
      return a[i1] > a[i2];
   }

   private static void swap(int i1, int i2) {
      int tmp = a[i1];
      a[i1] = a[i2];
      a[i2] = tmp;
      nswaps++;
   }

   private static void P(int i1, int i2) {
      System.out.print("\ta[" + i1 + "]=" + a[i1] + "; a[" + 
                        i2 + "]=" + a[i2]);
   }
   
   /**
    * foo() was renamed to shellSort() because
    * it implements the shell sorting algorithm. 
    * This can be clearly seen in the way that it 
    * features a gap between the numbers that it compares. 
    * If the numbers are out of order relative to 
    * each other, they are swapped.
    * The gap decreases in size as the program operates 
    * until the program is essentially an implementation
    * of insertion sort. 
    */
   private static void shellSort() { 
      int gap, i, j, n = a.length;
      for (gap = n/2; gap > 0; gap /= 2) {
         for (i = gap; i < n; i++) {
            for (j = i-gap; j >= 0; j -= gap) {
               P(j, j+gap);
               if (!compare(j, j+gap)) {
                  System.out.println();
                  break;
               }
               swap(j, j+gap);
               System.out.println(" [swapped]");
            }
         }
      }
   }
   
   /**
    * goo() was renamed to bubbleSort because 
    * it is an implementation of bubble sort.
    * This can be seen in the way that the program
    * works through the array by comparing and swapping
    * the two objects directly adjacent to each other. 
    * It loops through the array multiple times to ensure
    * that the array is in proper order. 
    */
   private static void bubbleSort() { 
      int end_i = a.length-1;
      while (end_i > 0) {
         int last_i = 0;
         for (int next_i = 0; next_i < end_i; next_i++) {
            P(next_i, next_i+1);
            if (compare(next_i, next_i+1)) {
               swap(next_i, next_i+1);
               System.out.println(" [swapped]");
               last_i = next_i;
            } else
               System.out.println();
         }
         end_i = last_i;
      }
   }
   
   /**
    * moo() was renamed to selectionSort() because 
    * it is an implementation of selection sort. 
    * This can be seen as the program loops through 
    * array looking for the index with the lowest value.
    * Once it reaches the end of the array, it swaps 
    * the first object with the lowest value index. 
    * Then, it starts the loop again, but starts at index
    * two, instead of one, and finds the next lowest object
    * after the object already swapped into position one.
    */
   private static void selectionSort() { 
      int cur_i, limit, small_i, next_i, n = a.length;
      for (cur_i = 0, limit = n-1; cur_i < limit; cur_i++) {
         small_i = cur_i;
         for (next_i = cur_i+1; next_i < n; next_i++) {
            P(small_i, next_i);
            System.out.println();
            if (compare(small_i, next_i))
               small_i = next_i;
         }
         if (small_i != cur_i) {
            swap(small_i, cur_i);
            System.out.println(" [swapped]");
         }
      }
   }
   
   /**
    * boo() was renamed to exchangeSort() because
    * it implements the exchange sort algorithm.
    * This can be seen as the program starts with the
    * first object and compares/swaps with ever other object
    * after 1 in the array. Then, the program moves to the 
    * second object as the first is now the lowest. While
    * on 2, it compares/swaps 2 with every other object 
    * after 2. Now 2, is the second smallest object in 
    * the array. The program does this until it reaches
    * the second to last object where it compares/swaps 
    * with the last object, and then the program is done.
    */
   private static void exchangeSort() { 
      int cur_i, limit, next_i, n = a.length;
      for (cur_i = 0, limit = n-1; cur_i < limit; cur_i++) {
         for (next_i = cur_i+1; next_i < n; next_i++) {
            P(cur_i, next_i);
            if (compare(cur_i, next_i)) {
               swap(cur_i, next_i);
               System.out.println(" [swapped]");
            } else
               System.out.println();
         }
      }
   }

   /**
    * poo() was renamed to quickSort() because 
    * it is an implementation of the quick sort 
    * algorithm. We can tell this because the program 
    * pivots around the variable last and the program
    * operates recursively, splitting the array in 
    * half multiple times before finishing.
    */
   private static void quickSort(int left, int right) { 
      int i, last;
      if (left >= right) return;
      swap(left, (left + right) / 2);
      last = left;
      for (i = left+1; i <= right; i++) {
         P(i, left);
         if (compare(i, left)) {
            if ((last+1) == i) 
               ++last;
            else {
               swap(++last, i);
               System.out.print(" [swapped]");
            }
         }
         System.out.println();
      }
      if (left != last)
         swap(left, last);
      quickSort(left, last-1);
      quickSort(last+1, right);
   }

   /*
    * TBI (To Be Implemented...)
    *
    * Implement a Counting Sort algorithm that sorts a byte[] where 
    * elements are in the [Byte.MIN_VALUE, Byte.MAX_VALUE] interval.
    *
    * The output for this method should match the following:
    *
    *    counting_sort()...
    *    original:  7 -128 0 7 -7 127 0 7 -128 7 42 -42 
    *    sorted:  -128 -128 -42 -7 0 0 7 7 7 7 42 127 
    */
   static void counting_sort() {
      final byte[] A = { 7,-128,0,7,-7,127,0,7,-128,7,42,-42 };
      System.out.println("\ncounting_sort()...");
      
      byte[] count = new byte[Byte.MAX_VALUE - Byte.MIN_VALUE+1];
      
      for(int i = 0; i < A.length; i++) {
          ++count[A[i]-Byte.MIN_VALUE];
      }
      
      for(int i = 1; i < Byte.MAX_VALUE-Byte.MIN_VALUE+1; i++) {
          count[i] += count[i-1];
      }
      
      byte[] B = new byte[A.length];
      
      for(int i = 0; i < A.length; i++) {
          B[count[A[i]-Byte.MIN_VALUE]-1] = A[i];
          count[A[i]-Byte.MIN_VALUE]--;
      }
      
      System.out.print("Array A is printing: [ ");
      for(int i = 0; i < A.length; i++) {
          System.out.print(A[i] + ", ");
      }
      System.out.println("]");
      
      System.out.print("Array B is printing: [ ");
      for(int i = 0; i < B.length; i++) {
          System.out.print(B[i] + ", ");
      }
      System.out.println("]");
      
   }
}           
