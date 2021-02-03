/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gurar
 */
public class Files {

    /**
     * @param s
     * @return
     *
     */
    public static String sortList(String s) {
        String[] parts = s.split(" ");
        String text = "";
        List<String> list = new ArrayList();
        for (String part : parts) {
            list.add(part);
        }

        list.sort((s1, s2) -> s2.length() - s1.length());

        int index = 0;
        for (String part : list) {
            if (index == list.size()) {
                text += part;
            } else {
                text += part + "\r\n";
            }
            index++;
        }
        
        return text;
    }

    public static String readFile(String[] fileName) {
        String text = "";
        for (String fileName1 : fileName) {
            try ( FileInputStream fis = new FileInputStream(fileName1)) {
                Reader fr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(fr);
                String line;
                do {
                    line = br.readLine();
                    if (line != null) {
                        text += line.replaceAll("[,.]", "") + " ";
                    }
                } while (line != null && !"".equals(line));
            } catch (IOException ex) {
                System.out.println("Error reading file: " + ex.getMessage());
            }
        }

        return sortList(text);
    }

    public static void writeDataToFile(String s, String fileName) {
        byte[] strToBytes = s.getBytes();
        try ( FileOutputStream fis = new FileOutputStream(fileName);) {
            fis.write(strToBytes);
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        String[] files = {"a.txt", "b.txt", "c.txt"};
        writeDataToFile(readFile(files), "d.txt");
    }
}
