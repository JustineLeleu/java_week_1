import java.util.Arrays;

public class Challenge2
{
    public static void main(String[] args)
    {
        int b = 55;
        String bb = "45";

        Integer x = new Integer(b);
        Integer y = new Integer(bb);
        System.out.println("x = " + x);
        System.out.println("y = " + y);

        int value = 45;
        System.out.println("Integer.bitcount(value)=" + Integer.bitCount(value));

        int hash = x.hashCode();
        System.out.println("hashcode(x) = " + hash);

        int e = Integer.compare(x, y);
        System.out.println("compare(x,y) = " + e);

        int[] c = { 10, 20, 30, 40 };
        int[] d = c;
        d[1] = 50;
        System.out.println("Array c: " + Arrays.toString(c));
        System.out.println("Array d: " + Arrays.toString(d));
    }
}