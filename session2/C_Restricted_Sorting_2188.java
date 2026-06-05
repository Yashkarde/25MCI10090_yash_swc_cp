import java.io.*;
import java.util.*;

public class C_Restricted_Sorting_2188{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());

            int[] a = new int[n];
            int[] b = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            int mn = Integer.MAX_VALUE;
            int mx = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                b[i] = a[i];

                mn = Math.min(mn, a[i]);
                mx = Math.max(mx, a[i]);
            }

            Arrays.sort(b);

            long ans = Long.MAX_VALUE;
            boolean sorted = true;

            for (int i = 0; i < n; i++) {
                if (a[i] != b[i]) {
                    sorted = false;

                    long cur = Math.max(
                            (long) a[i] - mn,
                            (long) mx - a[i]
                    );

                    ans = Math.min(ans, cur);
                }
            }

            if (sorted) {
                out.append("-1\n");
            } else {
                out.append(ans).append('\n');
            }
        }

        System.out.print(out);
    }
}