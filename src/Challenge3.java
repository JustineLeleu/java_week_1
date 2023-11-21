import java.util.Arrays;

public class Challenge3
{
    public static void main(String[] args)
    {
        int[] arr = {1, 2, 8, 4};
        for (int i = 0; i < arr.length; i++)
        {
            if (i == 0)
            {
                arr[0] = arr[0] / arr[0];
                continue;
            }

            arr[i] = arr[i] / arr[i - 1];
        }

        System.out.println(Arrays.toString(arr));
    }
}