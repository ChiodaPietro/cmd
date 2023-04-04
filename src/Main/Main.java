package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("starting path");
        Explorer explorer=new Explorer(reader.readLine());
        while(true){

            String[] array=reader.readLine().split(" ");
            switch (array[0]){
                case "cd", "chdir":
                    explorer.cd(array[1].replace(" ", ""));
                    break;
                case"dir":
                    explorer.dir();
                    break;
                case"md","mkdir":
                    explorer.mkdir(array[1].replace(" ", ""));
                    break;
                case "rename":
                    explorer.rename()
                    break;
                case "rmdir":
                    break;
            }
            System.out.println(explorer.getPath());
        }
    }
}
