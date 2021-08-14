import java.util.Arrays;

public class Merge {
    static final int TRIALS = 50;
    // How many elements in array till it goes to insertion
    static final int INSERTION = 7;

    public static void main(String[] args) {
        System.out.println("Size\t\tPerformance");

        long stime, qtime, btime;
        float ratio_sum = 0;

        for (int size = 31250; size < 1000001; size <<= 1) {
            qtime = btime = 0;

            for (int t = 0; t < TRIALS; t++) {

                int[] data1 = new int[size];
                int[] data2 = new int[size];

                for (int i = 0; i < data1.length; i++) {
                    data2[i] = data1[i] = (int) (Math.random() * Integer.MAX_VALUE);
                }

                stime = System.currentTimeMillis();
                Merge.mergesort(data2);
                qtime += System.currentTimeMillis() - stime;

                stime = System.currentTimeMillis();
                Arrays.sort(data1);
                btime += System.currentTimeMillis() - stime;

                if (!Arrays.equals(data1, data2)) {
                    System.out.println("FAIL TO SORT!");
                    System.exit(0);
                }
            }
            ratio_sum += 1.0 * qtime / btime;
            System.out.printf("%d\t\t%.3f\n", size, 1.0 * qtime / btime);
        }
        System.out.printf("\nAverage\t\t%.3f\n", ratio_sum / 6);
    }

    public static void mergesort(int[] data) {
        mergeHelper(data, Arrays.copyOf(data, data.length), 0, data.length);
    }

    private static void mergeHelper(int[] data, int[] temp, int lo, int hi) {
        if (hi - lo <= INSERTION) {
            insertionSort(data, lo, hi);
        } else {
            if (hi - lo > 1) {
                // System.out.println(Arrays.toString(Arrays.copyOfRange(data, lo, hi)));
                int mid = ((hi - lo) / 2) + lo;
                mergeHelper(temp, data, lo, mid);
                mergeHelper(temp, data, mid, hi);
                merge(data, temp, lo, mid, hi);
            }
        }
        // if sub-array is 2 long swap them
        /*
         * else if(hi - lo == 2){ if(temp[lo]>temp[lo+1]){ data[lo]=temp[lo+1];
         * data[lo+1]=temp[lo]; }else{ data[lo]=temp[lo]; data[lo+1]=temp[lo+1]; } }
         */
        return;
    }

    private static void insertionSort(int[] data, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            for (int x = i; x > lo; x--) {
                if (data[x] < data[x - 1]) {
                    int temp = data[x];
                    data[x] = data[x - 1];
                    data[x - 1] = temp;
                } else {
                    break;
                }
            }
        }
    }

    private static void mergeHelper(int[] data) {
        if (data.length > 1) {
            int[] left = Arrays.copyOfRange(data, 0, data.length / 2);
            mergeHelper(left);
            int[] right = Arrays.copyOfRange(data, data.length / 2, data.length);
            mergeHelper(right);
            merge(left, right, data);
        }
        return;
    }

    private static void merge(int[] a1, int[] a2, int[] data) {
        int j, k;
        j = k = 0;
        for (int i = 0; i < data.length; i++) {
            if (j < a1.length && k < a2.length) {
                if (a1[j] < a2[k]) {
                    data[i] = a1[j];
                    j++;
                } else {
                    data[i] = a2[k];
                    k++;
                }
            } else {
                if (j >= a1.length) {
                    data[i] = a2[k];
                    k++;
                } else if (k >= a2.length) {
                    data[i] = a1[j];
                    j++;
                }
            }
        }
    }

    private static void merge(int[] data, int[] temp, int lo, int mid, int hi) {
        int j, k;
        j = lo;
        k = mid;
        for (int i = lo; i < hi; i++) {
            if (j < mid) {
                if (k < hi) {
                    if (temp[j] < temp[k]) {
                        data[i] = temp[j];
                        j++;
                    } else {
                        data[i] = temp[k];
                        k++;
                    }
                } else {
                    data[i] = temp[j];
                    j++;
                }
            } else {
                data[i] = temp[k];
                k++;
            }
        }
    }
}