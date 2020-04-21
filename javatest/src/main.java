public class main {
    public static void main(String[] arg)
    {
        char[][] str1 = {
                {'1', '3', '4'},
                {'8', '6', '2'},
                {' ', '7', '5'}};

        char[][] str2 = {
                {'1', '2', '3'},
                {'8', ' ', '4'},
                {'7', '6', '5'}};

        String s ="";
        for (int i = 0; i != 3; i++)
        {
            s += String.valueOf(str1[i]);
        }
        System.out.println(s);
    }
}
