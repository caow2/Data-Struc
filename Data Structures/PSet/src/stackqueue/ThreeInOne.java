package stackqueue;
//Use single array to implement 3 stacks
public class ThreeInOne {
	/*
	 *To simulate 2 stacks in one array, you could divide up a single array in half - one part for each respective stack
	 *Likewise, to implement 3 stacks in one array, you could divide up the array into 3 parts
	 *
	 *Assuming the size of each stack will be the same -> can just divide array size by 3 to get the start and end points
	 *of each individual stack
	 *
	 *If one stack gets too large - will have to create a larger array and copy everything over accordingly
	 */
	
	public static void main(String[] args) {
		Stack s = new Stack();
		s.pushS1(1);
		s.pushS1(2);
		s.pushS1(3);
		s.pushS2(4);
		s.pushS2(5);
		s.pushS2(6);
		s.pushS2(7);
		s.pushS3(8);
		s.pushS3(9);
		s.pushS3(10);
		System.out.println(s);
		System.out.println(s.popS1());
		System.out.println(s.popS2());
		System.out.println(s.popS3());
		System.out.println(s.popS3());
		System.out.println(s.popS3());
		System.out.println(s.popS3()); //prints out 0 -> should be empty stack exception
		System.out.println(s);
	}
	
	static class Stack {
		int[] arr;
		int s1_size = 0, s2_size = 0, s3_size = 0;
		int s1_start, s2_start, s3_start;
		
		public Stack() {
			arr = new int[10];
			s1_start = 0;
			s2_start = (arr.length/3);
			s3_start = s2_start * 2;
		}
		
		public boolean isEmpty() {
			return totalSize() == 0;
		}
		
		private int totalSize() {
			return s1_size + s2_size + s3_size;
		}
		
		public void pushS1(int i) {
			if(s1_start + s1_size >= s2_start || totalSize() >= arr.length) {
				resize();
			}
			arr[s1_start + s1_size] = i;
			s1_size++;
		}
		
		public void pushS2(int i) {
			if(s2_start + s2_size >= s3_start || totalSize() >= arr.length) {
				resize();
			}
			arr[s2_start + s2_size] = i;
			s2_size++;
		}
		
		public void pushS3(int i) {
			if(s3_start + s3_size >= arr.length || totalSize() >= arr.length) {
				resize();
			}
			arr[s3_start + s3_size] = i;
			s3_size++;
		}
		
		public int popS1(){
			return arr[s1_start + --s1_size];
		}
		
		public int popS2(){
			return arr[s2_start + --s2_size];
		}
		
		public int popS3(){
			return arr[s3_start + --s3_size];
		}
		
		public void resize() {
			int[] temp = new int[arr.length * 3];
			int temp_s2 = temp.length / 3;
			int temp_s3 = temp_s2 * 2;
			for(int i = 0; i < s1_size; i++) {
				temp[i] = arr[i];
			}
			
			for(int i = 0; i < s2_size; i++) {
				temp[temp_s2 + i] = arr[s2_start + i]; 
			}
			
			for(int i = 0; i < s3_size; i++) {
				temp[temp_s3 + i] = arr[s3_start + i];
			}
			
			s2_start = temp_s2;
			s3_start = temp_s3;
			arr = temp;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder("Stack 1: [");
			for(int i = 0; i < s1_size; i++) {
				sb.append(arr[s1_start + i]);
				if(i != s1_size - 1)
					sb.append(",");
			}
			sb.append("]");
			
			sb.append("\nStack 2: [");
			for(int i = 0; i < s2_size; i++) {
				sb.append(arr[s2_start + i]);
				if(i != s2_size - 1)
					sb.append(",");
			}
			sb.append("]");
			
			sb.append("\nStack 3: [");
			for(int i = 0; i < s3_size; i++) {
				sb.append(arr[s3_start + i]);
				if(i != s3_size - 1)
					sb.append(",");
			}
			sb.append("]");
			
			return sb.toString();
		}
	}
}
