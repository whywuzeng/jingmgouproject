package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyClass {
    public static List<Map<String, Integer>> intz;
    public static String[] strs;

    public static String first = "com.news.android.R";
    private static BufferedReader br;
    private static PrintWriter pw;
    private static FilenameFilter filter;

    public static void main(String []args){
        try {
            Class< ?> clazz = Class.forName("com.news.android.R");
            System.out.println(clazz.getSimpleName());
            Class<?>[] classs = clazz.getClasses();
            System.out.println(classs.length);
            intz = new ArrayList<Map<String, Integer>>();
            strs = new String[classs.length];

//                        for (Class<?> class1 : classs) {
            for (int i = 0; i < classs.length; i++) {
                Class<?> class1 = classs[i];
                System.out.println(class1.getSimpleName());
                Field[] fields = class1.getFields();
                strs[i] = class1.getSimpleName();

                Map<String, Integer> tmp = new HashMap<String, Integer>();
//                                for (Field field : fields) {
                for (int j = 0; j < fields.length; j++) {
                    Field field = fields[j];
                    try {

                        Object object = field.get(class1);
//                                                strs[i][j] = field.getName();
                        System.out.println(field.getName()+field.get(class1));
                        if (object instanceof Integer) {
                            Integer new_name = (Integer) object;
                            tmp.put(field.getName(), new_name);
                        }else {
                            tmp.put(field.getName(), -1);
                        }

                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                intz.add(tmp);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(has(2130903109) != null){
            System.out.println(has(2130903109));
        }
        System.out.println("//");
                /*File file = new File("/media/备用/oldeclipse_android/新闻/src/com/qianlong/android/ui/main/MainActivity.java");
                int num = getFileAndNum(file);

                switch (num) {
                case 0:
                        System.out.println("发现并替换了数字并生成文件");
                        break;
                case 1:
                        System.out.println("源文件拷贝，不包含匹配的数字");
                        break;
                case -1:
                        System.out.println("发现了数字但没有在r文件中匹配，源文件拷贝");
                        break;
                case -2:
                        System.out.println("出现异常");
                        break;

                default:
                        break;
                }*/

//        File dir = new File("/media/备用/oldeclipse_android/新闻/src/com/qianlong/android");
//        F:\PullToRefreshListFragment\jingmgouproject\lib\java\com\ismartgo\app
        File dir = new File("F:\\PullToRefreshListFragment\\jingmgouproject\\lib\\java\\com\\ismartgo\\app");
        changeMore(dir,0);
    }

    public static void changeMore(File dir,int sum){
        if (!dir.exists() || !dir.canRead()) {
            System.out.println("路径无效或者文件或者文件夹不可读");
            return;
        }
        filter = new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (dir.isFile()) {
                    return name.endsWith(".java");
                } else {
                    return true;
                }
            }
        };
        StringBuilder sb = new StringBuilder();
        sb.append("-");
        for (int i = 0; i < sum; i++) {
            sb.append("-");
        }
        System.out.println(sb.append(dir.getName()).toString());

        if (dir.isFile()) {
            getFileAndNum(dir);

        } else {

            File[] listFiles = dir.listFiles(filter);
            for (File file : listFiles) {
//                                System.out.println(file.getName());
                int tmp = sum;
                changeMore(file,++tmp);
            }
        }
    }


    public static int getFileAndNum(File file){
        int num = -2;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            pw = new PrintWriter(file.getAbsolutePath()+".new");
            System.out.println(file.getParent()+"/Main.java");
            String line = null;
            while((line=br.readLine()) != null){
                if(line.startsWith("//")||line.startsWith("/*")||line.endsWith("*/")){
//                                        continue;
                }else{
                    String regEx="[2][1][3][\\d]{7}";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(line);
//                                        System.out.println( m.replaceAll("").trim());
                    while(m.find()){
                        String group = m.group();
                        System.out.println(group);

                        String r = has(Integer.parseInt(group));
                        if(r != null){
                            System.out.println(r);
                            line = m.replaceFirst(r);
                            System.out.println(line);
                            num = -3;
                        }else{
                            num = -4;
                        }
                        m = p.matcher(line);
                    }
                }
                pw.println(line);
            }

            num += 3;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if (pw != null) {
                pw.close();
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return num;
    }

    public static String has(int num){
        String second = null;
        String thired = null;

        for (int i = 0; i < intz.size(); i++) {
            second = strs[i];
            Map<String, Integer> map = intz.get(i);
            Set<Entry<String, Integer>> entrySet = map.entrySet();

            for (Entry<String, Integer> entry : entrySet) {
                if (entry.getValue() == num){
                    thired = entry.getKey();
                    return first+"."+second+"."+thired;
                }
            }


        }
        return null;
    }


}
