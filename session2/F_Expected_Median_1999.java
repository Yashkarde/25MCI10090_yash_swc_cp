import java.io.*;
import java.util.*;

public class F_Expected_Median {

    static final long MOD = 1_000_000_007L;
    static final int MAX = 200000;

    static long[] fact = new long[MAX + 1];
    static long[] invFact = new long[MAX + 1];

    static long modPow(long a, long b) {
        long res = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * a) % MOD;
            }
            a = (a * a) % MOD;
            b >>= 1;
        }

        return res;
    }

    static void precompute() {
        fact[0] = 1;

        for (int i = 1; i <= MAX; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        invFact[MAX] = modPow(fact[MAX], MOD - 2);

        for (int i = MAX - 1; i >= 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    static long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;

        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        precompute();

        int t = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();

        while (t-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int ones = 0;

            for (int i = 0; i < n; i++) {
                ones += Integer.parseInt(st.nextToken());
            }

            int zeros = n - ones;
            int need = (k + 1) / 2;

            long result = 0;

            for (int i = need; i <= Math.min(ones, k); i++) {
                int zeroNeeded = k - i;

                if (zeroNeeded > zeros) continue;

                long ways =
                        (nCr(ones, i) * nCr(zeros, zeroNeeded)) % MOD;

                result = (result + ways) % MOD;
            }

            ans.append(result).append('\n');
        }

        System.out.print(ans);
    }
}