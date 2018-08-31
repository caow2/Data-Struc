package recursivedp;

/** A magic index in an array A[0 ... n - 1] is an index such that A[i] = i
 *  Given a sorted array with distinct integers, write a method to find a magic index, if one exists in the array, or -1.
 *
 *  Follow up - what if the values are not distinct?
 */
public class MagicIndex {
    public static void main(String[] args) {
        int[] arr = {-1, -2, 0, 3, 5};
        System.out.println(findMagicIndex(arr)); // 3
    }

    /** Brute Force solution - we can just scan the whole array and see if there's a magic index. If we've searched whole array and still
     *  haven't found a magic index, return -1.
     *  O(n) where n is size of array since we look thru the whole array.
     *  O(1) space.
     *
     *  Do we really need to look thru the whole array?
     *  Given that the array is sorted, we might be able to run a modified Binary Search to find a magic index.
     *  If we check an index i, there are 3 possibilities:
     *  1. i is a magic index
     *  2. A[i] > i -> If this is the case, The magic index cannot exist to the right of i because A[i+1] has to be > A[i] and A[i + 1] cannot = i + 1. Magic index must be to the left.
     *  3. A[i] < i -> Magic index cannot exist to the left of i for similar reasons like above. Magic index must be to the right.
     *
     *  Binary Search will terminate when Magic index is found or the start index and end index are invalid. (Only search when start <= end)
     *  O(log n) Time for Binary Search. O(1) Space.
     */
    public static int findMagicIndex(int[] arr) {
        if(arr.length == 0)
            return -1;

        int start = 0, end = arr.length - 1;
        while(start <= end) {
            int mid = (start + end) / 2;

            if(arr[mid] == mid)
                return mid;
            else if(arr[mid] > mid)
                end = mid - 1;
            else if(arr[mid] < mid)
                start = mid + 1;
        }

        return -1;
    }

    /**Implement Follow Up */

}
