import java.util.ArrayList;
public class recursion{
    /*You may write additional private methods */

    /*Recursively find the sqrt using Newton's approximation
     *tolerance is the allowed percent error the squared answer is away from n.
     *precondition: n is non-negative
    */
	public static double sqrt(double n, double tolerance){
		if (n == 0)
			return 0;
		return sqrtHelper (n, 1, tolerance);
	}
	private static double sqrtHelper(double n, double current, double tolerance){
		current = ( (n / current) + current) / 2;
		if (Math.abs(((current * current) - n)) < tolerance)
			return current;
		else
			return sqrtHelper(n, current, tolerance);
	}

    /*Recursively find the n'th fibbonaci number in linear time
     *fib(0) = 1; fib(1) = 1; fib(5) = 5
     *precondition: n is non-negative
     */
    public static int fib(int n) {
		if (n == 0) {
			return 0;
		}
		if (n < 2) {
			return 1;
		}
		return fibHelper(n - 2, 1, 0);
	}

	private static int fibHelper(int n, int last, int last2) {
		if (n <= 0){
			return last + last2;
		}
		return fibHelper(n - 1, last + last2, last);
	}

    /*As Per classwork*/
    public static ArrayList<Integer> makeAllSums(int n){
		ArrayList<Integer> sums = new ArrayList<Integer>();
		return makeAllHelper(n,0,sums);
	}
	private static ArrayList<Integer> makeAllHelper(int n, int curSum, ArrayList<Integer> sums) {
		if (n < 1){
			sums.add(curSum);
		}else{
			makeAllHelper(n-1,curSum + n, sums);
			makeAllHelper(n-1,curSum, sums);
		}
		return sums;
	}

}