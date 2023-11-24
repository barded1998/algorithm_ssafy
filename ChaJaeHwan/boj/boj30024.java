import java.io.*;
import java.util.*;

public class boj30024 {
    static class File implements Comparable<File>{
        String HEAD;
        String NUMBER;
        String TAIL;
        public File(String s) {
            int headIdx = 0;
            while(!('0' <=  s.charAt(headIdx) && s.charAt(headIdx)<= '9')) {
                headIdx++;
            }
            int numberIdx = headIdx;
            while(numberIdx < s.length() && '0' <=  s.charAt(numberIdx) && s.charAt(numberIdx)<= '9') {
                numberIdx++;
            }
            HEAD = s.substring(0, headIdx);
            NUMBER = s.substring(headIdx, numberIdx);
            TAIL = s.substring(numberIdx);
        }

        public String toString() {
            return HEAD + NUMBER + TAIL;
        }

        @Override
        public int compareTo(File f) {
            if(!this.HEAD.toUpperCase().equals(f.HEAD.toUpperCase())) {
                return this.HEAD.toUpperCase().compareTo(f.HEAD.toUpperCase());
            } else {
                return Integer.compare(Integer.parseInt(this.NUMBER), Integer.parseInt(f.NUMBER));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File f = new File("F-15");
        System.out.println(f);
    }

}
