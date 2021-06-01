import parcs.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Main {

    public static void main(String[] args) throws IOException {

        task curtask = new task();
        curtask.addJarFile("Vernam.jar");
        AMInfo info = new AMInfo(curtask, null);

        String text = new String ( Files.readAllBytes( Paths.get("input1.txt")));
		
        int N = 12;
        int n = text.length() / N;

        List<String> texts = new ArrayList<>();
        List<point> points = new ArrayList<>();
        List<channel> channels = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int l = i * n;
            int r = (i + 1) * n;
            String textPart = text.substring(l, r);
            texts.add(textPart);

            point p = info.createPoint();
            channel c = p.createChannel();

            points.add(p);
            channels.add(c);
        }

        for (int i = 0; i < texts.size(); i++) {
            String t = texts.get(i);
			
            points.get(i).execute("Vernam");
            channels.get(i).write(t);
			
			String result = (String) (channels.get(i).readObject());
			System.out.println("Cipher text:");
			System.out.println(result);
        }
		
        curtask.end();
    }
}
