class Delivery {
    int over;
    int ball;

    Delivery(int over, int ball) {
        this.over = over;
        this.ball = ball;
    }
}

public class CricketCountingSort {

    // Stable Counting Sort by Ball Number
    static void countSortBall(Delivery arr[]) {
        int n = arr.length;
        Delivery output[] = new Delivery[n];

        int count[] = new int[7]; // Ball numbers 1-6

        for (int i = 0; i < n; i++)
            count[arr[i].ball]++;

        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i].ball] - 1] = arr[i];
            count[arr[i].ball]--;
        }

        for (int i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // Stable Counting Sort by Over Number
    static void countSortOver(Delivery arr[]) {
        int n = arr.length;
        Delivery output[] = new Delivery[n];

        int maxOver = 3;
        int count[] = new int[maxOver + 1];

        for (int i = 0; i < n; i++)
            count[arr[i].over]++;

        for (int i = 1; i < count.length; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i].over] - 1] = arr[i];
            count[arr[i].over]--;
        }

        for (int i = 0; i < n; i++)
            arr[i] = output[i];
    }

    static void printDeliveries(Delivery arr[]) {
        for (Delivery d : arr) {
            System.out.print("(" + d.over + "," + d.ball + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Delivery deliveries[] = {
            new Delivery(2,4),
            new Delivery(1,1),
            new Delivery(3,6),
            new Delivery(1,5),
            new Delivery(2,2),
            new Delivery(3,1),
            new Delivery(1,3),
            new Delivery(2,6),
            new Delivery(3,4),
            new Delivery(1,2)
        };

        System.out.println("Unsorted Deliveries:");
        printDeliveries(deliveries);

        // LSD Style Sorting
        countSortBall(deliveries);
        countSortOver(deliveries);

        System.out.println("\nSorted Deliveries:");
        printDeliveries(deliveries);
    }
}
