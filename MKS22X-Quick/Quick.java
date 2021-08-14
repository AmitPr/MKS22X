import java.util.Arrays;
import java.util.Random;

public class Quick {

    private static final int INSERTION = 7;

    public static void main(String[] args) {
        System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
        int[] MAX_LIST = { 1000000000, 500, 10 };
        for (int MAX : MAX_LIST) {
            for (int size = 31250; size < 2000001; size *= 2) {
                long qtime = 0;
                long btime = 0;
                // average of 5 sorts.
                for (int trial = 0; trial <= 5; trial++) {
                    int[] data1 = new int[size];
                    int[] data2 = new int[size];
                    for (int i = 0; i < data1.length; i++) {
                        data1[i] = (int) (Math.random() * MAX);
                        data2[i] = data1[i];
                    }
                    long t1, t2;
                    t1 = System.currentTimeMillis();
                    Quick.quicksort(data2);
                    t2 = System.currentTimeMillis();
                    qtime += t2 - t1;
                    t1 = System.currentTimeMillis();
                    Arrays.sort(data1);
                    t2 = System.currentTimeMillis();
                    btime += t2 - t1;
                    if (!Arrays.equals(data1, data2)) {
                        System.out.println("FAIL TO SORT!");
                        System.exit(0);
                    }
                }
                System.out.println(size + "\t\t" + MAX + "\t" + 1.0 * qtime / btime);
            }
            System.out.println();
        }
    }

    /*
     * return the value that is the kth smallest value of the array.
     */
    public static int quickselect(int[] data, int k) {
        int end = data.length - 1;
        int start = 0;
        int x = partition(data, start, end);
        while (x != k) {
            x = partition(data, start, end);
            if (x < k) {
                start = x;
            } else if (x > k) {
                end = x - 1;
            }
        }
        return data[x];
    }

    /*
     * Modify the array to be in increasing order.
     */
    public static void quicksort(int[] data) {
        quickHelper(data, 0, data.length - 1);
    }

    public static void quickHelper(int[] data, int lo, int hi) {
        if (hi - lo > 0) {
            if (hi - lo <= INSERTION) {
                insertionSort(data, lo, hi);
            } else {
                int[] ltgt = partitionDutch(data, lo, hi);
                quickHelper(data, lo, ltgt[0] - 1);
                quickHelper(data, ltgt[1] + 1, hi);
        }

        }
    }

    private static void insertionSort(int[] data, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
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

    /*
     * Choose a random pivot element between the start and end index inclusive, Then
     * modify the array such that:
     * 
     * 1. Only the indices from start to end inclusive are considered in range
     * 
     * 2. A random index from start to end inclusive is chosen, the corresponding
     * element is designated the pivot element.
     * 
     * 3. all elements in range that are smaller than the pivot element are placed
     * before the pivot element.
     * 
     * 4. all elements in range that are larger than the pivot element are placed
     * after the pivot element.
     * 
     * @return the index of the final position of the pivot element.
     */
    public static int partition(int[] data, int start, int end) {
        int pIndex = start;
        int middle = (int) (new Random().nextBoolean() ? Math.ceil((start + end) / 2f)
                : Math.floor((start + end) / 2f));
        int median = median(data[start], data[middle], data[end], start, middle, end);
        if (!(end - start == 0)) {
            pIndex = median;
        }
        int pivot = data[pIndex];
        data[pIndex] = data[start];
        data[start] = pivot;
        for (int i = start + 1; i <= end; i++) {
            int x = data[i];
            if (x > pivot) {
                while (data[end] > pivot) {
                    if (i != end) {
                        end--;
                    } else {
                        data[start] = data[i - 1];
                        data[i - 1] = pivot;
                        return i - 1;
                    }
                }
                data[i] = data[end];
                data[end] = x;
            }

            if (x == pivot) {
                if (new Random().nextBoolean()) {
                    while (data[end] > pivot) {
                        if (i != end) {
                            end--;
                        } else {
                            data[start] = data[i - 1];
                            data[i - 1] = pivot;
                            return i - 1;
                        }
                    }
                    data[i] = data[end];
                    data[end] = x;
                }
            }

            if (i == end) {
                if (x > pivot) {
                    data[start] = data[i - 1];
                    data[i - 1] = pivot;
                    return i - 1;
                }
                data[start] = data[i];
                data[i] = pivot;
                return i;
            }
        }
        return end;
    }

    /*
     * DUTCH FLAG OPTIMIZATION
     * 
     * Choose a random pivot element between the start and end index inclusive, Then
     * modify the array such that:
     * 
     * 1. Only the indices from start to end inclusive are considered in range
     * 
     * 2. A random index from start to end inclusive is chosen, the corresponding
     * element is designated the pivot element.
     * 
     * 3. all elements in range that are smaller than the pivot element are placed
     * before the pivot element.
     * 
     * 4. all elements in range that are larger than the pivot element are placed
     * after the pivot element.
     * 
     * @return the index of the final position of the pivot element.
     */
    public static int[] partitionDutch(int[] data, int lo, int hi) {
        int pIndex = lo;
        int middle = (int) (new Random().nextBoolean() ? Math.ceil((lo + hi) / 2f) : Math.floor((lo + hi) / 2f));
        int median = median(data[lo], data[middle], data[hi], lo, middle, hi);
        if (!(hi - lo == 0)) {
            pIndex = median;
        }
        int pivot = data[pIndex];
        data[pIndex] = data[lo];
        data[lo] = pivot;
        int start = lo;
        lo++;
        int[] ltgt = new int[] { lo, hi };
        while (lo <= hi) {
            if (data[lo] > pivot) {
                while (data[hi] > pivot) {
                    hi--;
                    ltgt[1]--;
                    if (lo >= hi) {
                        data[start] = data[ltgt[0] - 1];
                        data[ltgt[0] - 1] = pivot;
                        ltgt[0]--;
                        if (lo == hi)
                            ltgt[1]--;
                        return ltgt;
                    }
                }
                if (data[hi] <= pivot) {
                    int temp = data[hi];
                    data[hi] = data[lo];
                    data[lo] = temp;
                    hi--;
                    ltgt[1]--;
                    if (data[lo] == pivot) {
                        lo++;
                    }
                }
            } else if (data[lo] < pivot) {
                int temp = data[lo];
                data[lo] = data[ltgt[0]];
                data[ltgt[0]] = temp;
                ltgt[0]++;
                lo++;
            } else {
                lo++;
            }
        }
        data[start] = data[ltgt[0] - 1];
        data[ltgt[0] - 1] = pivot;
        ltgt[0]--;
        return ltgt;
    }

    public static int median(int a, int b, int c, int aInd, int bInd, int cInd) {
        if (a > b) {
            if (a > c) {
                if (b > c) {
                    return cInd;
                } else {
                    return bInd;
                }
            } else {
                return aInd;
            }
        } else {
            if (a > c) {
                return aInd;
            } else {
                if (b > c) {
                    return cInd;
                } else {
                    return bInd;
                }
            }
        }
    }
}