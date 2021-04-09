package com.epam;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int counter = 0;
        File file = new File("text.txt");
        FileWriter out = new FileWriter(file);
        BufferedWriter w = new BufferedWriter(out);
        Main.walk("Study", w, counter);
        w.close();
    }

    public static String removeRootPrefixFromPath(String path) {
        String home = System.getenv("HOME");
        return path.replace(home + "/IdeaProjects/IOTask", "");
    }

    public static void writeTreeStructure(String pathToWrite, BufferedWriter bw) throws IOException {
        bw.write("|");
        bw.write("__");
        bw.write(pathToWrite);
        bw.write("\n");
    }

    public static void walk(String path, BufferedWriter bw, int counter) throws IOException {
        File root = new File(path);
        File[] list = root.listFiles();

        if (counter == 0) {
            bw.write(removeRootPrefixFromPath(root.getAbsolutePath()));
            bw.write("\n");
        } else {
            writeTreeStructure(removeRootPrefixFromPath(root.getAbsolutePath()), bw);
        }

        if (list == null) {
            return;
        }
        for (File f : list) {
            if (f.isDirectory()) {
                counter++;
                walk(f.getAbsolutePath(), bw, counter);
            } else {
                for (int i = 0; i < counter; i++) {
                    bw.write(" ");
                }
                writeTreeStructure(f.getName(), bw);
            }
        }
    }
}
