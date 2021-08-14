import java.util.Arrays;

public class MyHeap {
    public static final int TRIALS = 5;

    public static void main(String[] args) {
        System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
        int[] MAX_LIST = { 1000000000,500,10};
        for (int MAX : MAX_LIST) {
            for (int size = 31250; size < 2000001; size *= 2) {
                long qtime = 0;
                long btime = 0;
                // average of 5 sorts.
                for (int trial = 0; trial <= TRIALS; trial++) {
                    int[] data1 = new int[size];
                    int[] data2 = new int[size];
                    for (int i = 0; i < data1.length; i++) {
                        data1[i] = (int) (Math.random() * MAX);
                        data2[i] = data1[i];
                    }
                    long t1, t2;
                    t1 = System.currentTimeMillis();
                    MyHeap.heapsort(data2);
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

    private static void pushDown(int[] data, int size, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (left >= size) {
            return;
        }
        if (right < size) {
            if (data[left] > data[right]) {
                if (data[left] > data[index]) {
                    int temp = data[index];
                    data[index] = data[left];
                    data[left] = temp;
                    pushDown(data, size, left);
                }
            } else if (data[right] > data[index]) {
                int temp = data[index];
                data[index] = data[right];
                data[right] = temp;
                pushDown(data, size, right);
            }
        } else if (data[left] > data[index]) {
            int temp = data[index];
            data[index] = data[left];
            data[left] = temp;
            pushDown(data, size, left);
        }

    }
    
    public static void heapify(int[] data) {
        int l = data.length;
        int index = (l - 2) / 2;
        while (index >= 0) {
            pushDown(data, l, index);
            index--;
        }
    }

    public static void heapsort(int[] data) {
        heapify(data);
        int end = data.length - 1;
        while (end > 0) {
            int temp = data[0];
            data[0] = data[end];
            data[end] = temp;
            pushDown(data, end, 0);
            end--;
        }
    }

}