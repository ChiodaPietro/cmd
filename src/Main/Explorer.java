package Main;

import java.io.DataInput;
import java.io.File;

public class Explorer {
    private String starting_path;
    private String path;
    private File directory;

    public Explorer(String starting_path){
        this.starting_path=starting_path;
        path=starting_path;
    }
    public void cd(String path){
        try {
            if ((path.contains("/") || path.contains("\\"))) {
                if(new File(path).exists()){
                    directory = new File(path);
                    this.path = path;
                }else {
                    throw new NullPointerException();
                }


            } else {
                if(new File(this.path+"\\"+path).exists()){
                    directory = new File(this.path + "\\" + path);
                    this.path += "\\" + path;
                }else {
                    throw new NullPointerException();
                }

            }
        }catch (NullPointerException e){
            System.out.println("no path found");
        }

    }
    public void dir(){
        for (File files:directory.listFiles()){
            if(files.isFile()){
                System.out.println("F "+files.getName());
            }else {
                System.out.println("D "+files.getName());
            }
        }
    }
    public boolean mkdir(String name){
        directory=new File(path+"\\"+name);
        boolean result= directory.mkdirs();
        directory=new File(path);
        return result;
        //create directory
    }
    public boolean md(String name){
        directory=new File(path+"\\"+name);
        boolean result= directory.mkdirs();
        directory=new File(path);
        return result;
        //create directory
    }
    public boolean rename(String name, String new_name){
        directory=new File(path+"\\"+name);
        boolean result=directory.renameTo(new File(path+"\\"+new_name));
        directory=new File(path);
        return result;
        //rename a file
    }
    public boolean rmdir(String name){
        directory=new File(path+"\\"+name);
        boolean result=directory.delete();
        //remove a directory
        directory=new File(path);
        return result;
    }
    // dir | sort /R
    public void sort(){

    }
    public void tree(){

    }
    public String getPath() {
        return path;
    }

}
