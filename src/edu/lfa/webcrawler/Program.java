package edu.lfa.webcrawler;

import java.io.*;
import java.net.*;
import java.util.regex.*;

///
///FOR EDUCATIONAL PURPOSES ONLY
///CREATE A FOLDER "images" IN YOUR C DRIVE
//
public class Program {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://hamrobazaar.com/c104-mobile-and-accessories-handsets-samsung");
            URLConnection con = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = " ";
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            br.close();
            String regex = "img src=\"(.*?)\"";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(sb);

            while (matcher.find()) {
                String pic_path = "";
                if (matcher.group(1).endsWith(".gif") == false) {
                    pic_path = matcher.group(1);
                } else {
                    break;
                }
                URL pic = new URL(pic_path);
                URLConnection pic_con = pic.openConnection();
                InputStream is = pic_con.getInputStream();
                FileOutputStream fos = new FileOutputStream("C:\\images\\" + pic_path);
                byte[] abc = new byte[1024 * 1];
                int i = 0;
                while ((i = is.read(abc)) != -1) {
                    fos.write(abc, 0, i);
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
