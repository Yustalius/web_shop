package qa.guru.web.helpers;

public class Random {

    public static int getRandomNumber(int from, int to) {
        return ((int) (Math.random() * to)+from);
    }
}
