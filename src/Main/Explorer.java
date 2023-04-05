package Main;

import java.io.File;

public class Explorer {
    private String path;
    private File file_or_directory;

    public Explorer(String starting_path) {
        path = starting_path;
    }

    public void cd(String path) {
        try {
            if ((path.contains("/") || path.contains("\\"))) {
                if (new File(path).exists()) {
                    file_or_directory = new File(path);
                    this.path = path;
                } else {
                    throw new NullPointerException();
                }


            } else {
                if (new File(this.path + "\\" + path).exists()) {
                    file_or_directory = new File(this.path + "\\" + path);
                    this.path += "\\" + path;
                } else {
                    throw new NullPointerException();
                }

            }
        } catch (NullPointerException e) {
            System.out.println("no path found");
        }

    }

    public void dir() {
        for (File files : file_or_directory.listFiles()) {
            if (files.isFile()) {
                System.out.println("F " + files.getName() + " size " + files.length());
            } else {
                System.out.println("D " + files.getName() + " size " + files.length());
            }
        }
    }

    public boolean mkdir(String name) {
        file_or_directory = new File(path + "\\" + name);
        boolean result = file_or_directory.mkdirs();
        file_or_directory = new File(path);
        return result;
        //create directory
    }

    public boolean rename(String name, String new_name) {
        file_or_directory = new File(path + "\\" + name);
        boolean result = file_or_directory.renameTo(new File(path + "\\" + new_name));
        file_or_directory = new File(path);
        return result;
        //rename a file
    }

    public boolean rmdir(String name) {
        file_or_directory = new File(path + "\\" + name);
        if (file_or_directory.isDirectory()) {
            boolean result = file_or_directory.delete();
            //remove a directory
            file_or_directory = new File(path);
            return result;
        } else {
            return false;
        }
    }

    // dir | sort /R
    public void sort(String type) {
        File[] list = file_or_directory.listFiles();
        switch (type.toLowerCase()) {
            case "/os"://sort by size
                for (int i = 0; i < list.length; i++) {//bubble sort, if is a directory place it on top and move everything by one
                    for (int j = 0; j < list.length - 1; j++) {
                        if (list[j].length() > list[j + 1].length()) {
                            if (list[j].isDirectory()) {
                                File mem = list[j];
                                for (int k = 0; k < j; k++) {
                                    list[j - k] = list[j - k - 1];

                                }
                                list[0] = mem;
                            }
                            File mem = list[j + 1];
                            list[j + 1] = list[j];
                            list[j] = mem;
                        }
                    }
                }
                for (int j = 0; j < list.length - 1; j++) {//repeat the directory check, because it didn't work well the first tiem
                    if (list[j].isDirectory()) {
                        File mem = list[j];
                        for (int k = 0; k < j; k++) {
                            list[j - k] = list[j - k - 1];

                        }
                        list[0] = mem;
                    }
                }
                for (File files : list) {// output everything
                    if (files.isFile()) {
                        System.out.println("F " + files.getName() + " size " + files.length());
                    } else {
                        System.out.println("D " + files.getName() + " size " + files.length());
                    }
                }
                break;
            case "/n":
                for (int i = 0; i < list.length; i++) {//bubble sort but with names this time
                    for (int j = 0; j < list.length - 1; j++) {
                        if (list[j].getName().compareTo(list[j + 1].getName()) > 0) {// i don't really know how the compare to works
                            File mem = list[j + 1];
                            list[j + 1] = list[j];
                            list[j] = mem;
                        }
                    }
                }
                for (File files : list) {// output everything
                    if (files.isFile()) {
                        System.out.println("F " + files.getName());
                    } else {
                        System.out.println("D " + files.getName());
                    }
                }
                //by name
                break;
            case "/od":
                for (int i = 0; i < list.length; i++) {//bubble sort but with names this time
                    for (int j = 0; j < list.length - 1; j++) {
                        if (list[j].lastModified() > (list[j + 1].lastModified())) {// i don't really know how the compare to works
                            File mem = list[j + 1];
                            list[j + 1] = list[j];
                            list[j] = mem;
                        }
                    }
                }
                for (File files : list) {// output everything
                    if (files.isFile()) {
                        System.out.println("F " + files.getName());
                    } else {
                        System.out.println("D " + files.getName());
                    }
                }
                //by date
                break;
        }
    }

    public void tree(File directory, int directory_level) {
        if (!directory.isDirectory()) {
            return;
        }

        String spacing = "";
        for (int i = 0; i < directory_level; i++) {
            spacing += "|   ";
        }

        spacing += "|-- ";
        System.out.println(spacing + directory.getName());
        File[] files = directory.listFiles();
        for (File file : files) {
            tree(file, directory_level + 1);
        }
    }

//    public String tree(String path) {
//        StringBuilder str = new StringBuilder();
//        File file_or_directory = new File(path);
//        if (!file_or_directory.isDirectory()) {
//            return "";
//        }
//        for (File file : file_or_directory.listFiles()) {
//            if (file.isDirectory()) {
//                str.append("D "+file.getName() + tree(file.getAbsolutePath())+"\n");
//            }else {
//                str.append(file.getName()+"\n");
//            }
//        }
//        return str.toString();
//    }


    public boolean exists() {
        file_or_directory = new File(path);
        if (file_or_directory.exists()) {
            return true;
        }
        return false;
    }

    public int getDirnum() {
        int tot = 0;
        for (File file : file_or_directory.listFiles()) {
            if (file.isDirectory()) {
                tot++;
            }
        }
        return tot;
    }

    public String getPath() {
        return path;
    }

}
