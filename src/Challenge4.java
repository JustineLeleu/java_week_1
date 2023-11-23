import java.util.Arrays;

public class Challenge4
{
    public static void main(String[] args)
    {
        int[] arr = {2, 2, 16, 4, 8};
        for (int i = 0; i < arr.length; i++)
        {
            if (i == 0)
            {
                arr[0] /= arr[0];
                continue;
            }

            try
            {
                arr[i] = arr[i] / arr[i - 1];
            }
            catch (ArithmeticException e)
            {
                System.out.println("error: try dividing by zero");
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}