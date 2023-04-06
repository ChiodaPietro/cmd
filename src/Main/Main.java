package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Explorer explorer;

    public static void main(String[] args) throws IOException {
        System.out.println("starting path");
        explorer = new Explorer(reader.readLine());
        create_explorer();
        while (true) {
            try {
                System.out.println();
                String[] array = reader.readLine().split(" ");
                switch (array[0]) {
                    case "cd..":
                            explorer.cdpunto();
                        break;
                    case "cd", "chdir":
                        explorer.cd(array[1].replace(" ", ""));
                        break;
                    case "dir":
                        if(array[1].contains("/")){
                            explorer.sort(array[1]);
                            break;
                        }
                        explorer.dir();
                        break;
                    case "md", "mkdir":
                        explorer.mkdir(array[1].replace(" ", ""));
                        break;
                    case "rename":
                        if (!explorer.rename(array[1].replace(" ", ""), array[2].replace(" ", ""))) {
                            System.out.println("no file found");
                        }
                        break;
                    case "rmdir":
                        explorer.rmdir(array[1].replace(" ", ""));
                        break;
                    case "tree":
                        explorer.tree(new File(explorer.getPath()),0);
                        break;

                }

            } catch (Exception e) {
                System.out.println("wrong expression"+e);
            }
            System.out.println();
            System.out.println(explorer.getPath());

        }
    }

    public static Explorer create_explorer() throws IOException {
        while (true) {
            try {
                if (explorer.exists()) {
                    System.out.println();
                    System.out.println(explorer.getPath());
                    return explorer;
                }else {
                    System.out.println("path doesn't exist, insert a new one");
                    explorer = new Explorer(reader.readLine());
                }
            } catch (NullPointerException e) {
                System.out.println("path doesn't exist, insert a new one");
                explorer = new Explorer(reader.readLine());
            }
        }
    }
}
