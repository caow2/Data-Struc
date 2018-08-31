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

        int[] arr2 = {-1, 1, 1, 4, 5};
        System.out.println(findMagicIndexBF(arr2));
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

    /** Implement Follow Up
     *  Consider our Binary Search approach. We assumed that values were distinct.
     *  Since we have could have non distinct values now, if we modify the Binary Search:
     *  if A[i] > i, a magic index could still exist on both sides of index i -> consider [-1, 1, 3, 3, 5]
     *  if A[i] < i, we get the same situation -> consider [-1, 1, 1, 3, 5]
     *
     *  Therefore, if want to keep the same approach, we have to look on both halves of the midpoint each time.
     *  By this, we would get O(N) since we never get trim down the area we are looking for a magic index -> just splitting it into pieces, until we finally get N pieces of size 1
     *  [-1], [1], [3], [3], [5].
     *
     *  Consider this approach, it's easier to just do a linear search through the array and return the first magic index found or -1.
     */
    public static int findMagicIndexBF(int[] arr) {
        if(arr.length == 0) return -1;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == i)
                return i;
        }

        return -1;
    }


    /** For the CTCI explanation - if we implement Binary Search recursively, we can easily extend that to the follow up question case
     *  where we recursively search both sides of the midpoint.
     *  In cases where the array is distinct, this will still give us O(N) since we always search both the left and right side of the midpoint -> same as BF.
     */

}
