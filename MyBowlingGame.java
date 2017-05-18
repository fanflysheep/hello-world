import java.io.*;
import java.util.*;

class MyBowlingGame  
{
    public static int getBowlingScore(String str) {
        int len = str.length();
        int ind = len-1;
        
        //split string
        str.toUpperCase();
        while (str.charAt(ind)!='|') {
            ind--;
        }
        String extra = str.substring(ind+1);
        String contents = str.substring(0, ind-1);
        //System.out.println("extra:"+extra);
        //System.out.println("ten:"+contents);
        
        //each ball score
        String tenTokens[] = contents.split("\\|");
        String tmp;
        //int[] tenThrowBallTimes = new int[10];
        int[] scores = new int[30];
        int totalBalls, tmp_score;
        tmp_score = 0;
        totalBalls = 0;
        ind = 0;
        for (int i=0; i<tenTokens.length; i++) {
            tmp = tenTokens[i];
            totalBalls += tmp.length();
            //tenThrowBallTimes[i] = tmp.length();
            for (int j=0; j<tmp.length(); j++) {
                switch (tmp.charAt(j)) {
                    case 'X':{
                        tmp_score = 10;
                        break;
                    }
                    case '-':{
                        tmp_score = 0;
                        break;
                    }
                    case '/':{
                        tmp_score = 10 - scores[ind-1];
                        break;
                    }
                    default:{
                        tmp_score = tmp.charAt(j)-'0';
                        break;
                    }
                }
                scores[ind++] = tmp_score;
            }
            //System.out.println(i+":"+tenTokens[i]);
        }
        for (int i=0; i<extra.length(); i++) {
            switch (extra.charAt(i)) {
                case 'X':{
                    tmp_score = 10;
                    break;
                }
                case '-':{
                    tmp_score = 0;
                    break;
                }
                case '/':{
                    tmp_score = 10 - scores[ind-1];
                    break;
                }
                default:{
                    tmp_score = extra.charAt(i)-'0';
                    break;
                }
            }
            scores[ind++] = tmp_score;
        }
        
        //sum
        int res = 0;
        ind = 0;
        for (int i=0; i<tenTokens.length; i++) {
            tmp_score = 0;
            tmp = tenTokens[i];
            for (int j=0; j<tmp.length(); j++) {
                tmp_score += scores[ind++];
            }
            switch (tmp.charAt(tmp.length()-1)) {
                case 'X':{
                    tmp_score += scores[ind];
                    tmp_score += scores[ind+1];
                    break;
                }
                case '/':{
                    tmp_score += scores[ind];
                    break;
                }
            }
            //System.out.println(i+":"+tmp_score);
            res += tmp_score;
        }
        return res;
    }
	public static void main (String[] args) throws java.lang.Exception
	{
	    Scanner scan = new Scanner(System.in);
		String str = scan.next();
		int result = getBowlingScore(str);
		System.out.println(result);
	}
}
