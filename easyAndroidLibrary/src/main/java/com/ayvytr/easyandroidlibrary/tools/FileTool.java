package com.ayvytr.easyandroidlibrary.tools;

import com.ayvytr.easyandroidlibrary.exception.UnsupportedInitializationException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * 文件操作类，判断是不是文件/目录，是不是存在，重命名，创建文件/目录，列出/搜索文件，
 * 获取文件名/文件标题（不包含扩展名), 有没有扩展名，读/写文件等功能.
 * <p>
 * 前作者: Blankj
 * blog  : http://blankj.com
 * </p>
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */

public class FileTool
{
    //默认byte数组大小，读取文件时使用
    private static final int DEFAULT_BYTE_SIZE = 1024;

    private FileTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * return File from pathname(根据文件路径返回文件).
     *
     * @param pathname 文件路径
     * @return File
     */
    public static File fromName(String pathname)
    {
        return new File(pathname);
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isExists(String filePath)
    {
        return isExists(fromName(filePath));
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isExists(File file)
    {
        return file != null && file.exists();
    }

    /**
     * 重命名文件
     *
     * @param filePath 文件路径
     * @param newName  新名称
     * @return {@code true}: 重命名成功<br>{@code false}: 重命名失败
     */
    public static boolean rename(String filePath, String newName)
    {
        return rename(fromName(filePath), newName);
    }

    /**
     * 重命名文件
     *
     * @param file    文件
     * @param newName 新名称
     * @return {@code true}: 重命名成功<br>{@code false}: 重命名失败
     */
    public static boolean rename(File file, String newName)
    {
        if(file == null || !file.exists())
        {
            return false;
        }

        // 新的文件名为空返回false
        if(TextTool.isBlank(newName))
        {
            return false;
        }

        if(newName.equals(file.getName()))
        {
            return false;
        }

        File newFile = new File(file.getParent() + File.separator + newName);
        // 如果重命名的文件已存/重命名失败, 返回false
        return !newFile.exists() && file.renameTo(newFile);
    }

    /**
     * 判断是否是目录
     *
     * @param dirPath 目录路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(String dirPath)
    {
        return isDir(fromName(dirPath));
    }

    /**
     * 判断是否是目录
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDir(File file)
    {
        return isExists(file) && file.isDirectory();
    }


    /**
     * 判断是否是文件
     *
     * @param filePath 文件路径
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(String filePath)
    {
        return isFile(fromName(filePath));
    }

    /**
     * 判断是否是文件
     *
     * @param file 文件
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isFile(File file)
    {
        return isExists(file) && file.isFile();
    }

    /**
     * 根据pathname创建目录.
     *
     * @param pathname 文件路径
     */
    public static void createDir(String pathname)
    {
        createDir(fromName(pathname));
    }

    /**
     * 根据file创建目录.
     *
     * @param file File实例
     */
    private static void createDir(File file)
    {
        if(file != null && !isExists(file))
        {
            file.mkdirs();
        }
    }

    public static void createFile(String pathname)
    {
        createFile(fromName(pathname));
    }

    private static void createFile(File file)
    {
        if(file == null || file.exists())
        {
            return;
        }

        File parentFile = file.getParentFile();
        if(!parentFile.exists())
        {
            parentFile.mkdirs();
        }

        try
        {
            file.createNewFile();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 写入content到filepath文件中，覆盖文件内容
     *
     * @param filepath File
     * @param content  内容
     * @see FileTool#writeFile(String, String, boolean)
     */
    public static void writeFile(String filepath, String content)
    {
        writeFile(filepath, content, false);
    }

    /**
     * 写入content到file，不处理filepath，content异常
     *
     * @param filepath 文件路径
     * @param content  文件内容
     * @param isAppend 是不是追加模式
     * @see FileTool#writeFile(File, String, boolean)
     */
    public static void writeFile(String filepath, String content, boolean isAppend)
    {
        writeFile(fromName(filepath), content, isAppend);
    }

    /**
     * 写入content到file,覆盖文件内容
     *
     * @param file    File
     * @param content 内容
     * @see FileTool#writeFile(File, String, boolean)
     */
    public static void writeFile(File file, String content)
    {
        writeFile(file, content, false);
    }

    /**
     * 写入content到file，不处理file，content异常
     *
     * @param file     File
     * @param content  文件内容
     * @param isAppend 是不是追加模式
     */
    public static void writeFile(File file, String content, boolean isAppend)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file, isAppend);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(content.getBytes());
            bufferedOutputStream.close();
            fileOutputStream.close();
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 写入content到全路径为filepath的文件中,简化WriteFile方法名称.
     *
     * @param filepath 文件路径名
     * @param content  内容
     * @param isAppend true 追加, false 覆盖
     * @see FileTool#writeFile(String, String, boolean)
     */
    public static void write(String filepath, String content, boolean isAppend)
    {
        writeFile(filepath, content, isAppend);
    }

    /**
     * 写入content到文件filepath
     *
     * @param filepath File
     * @param content  内容
     * @see FileTool#write(File, String, boolean)
     */
    public static void write(String filepath, String content)
    {
        write(filepath, content, false);
    }

    /**
     * 写入content到文件file中，简化WriteFile方法名.
     *
     * @param file     File
     * @param content  内容
     * @param isAppend true 追加; false 覆盖
     * @see FileTool#writeFile(File, String, boolean)
     */
    public static void write(File file, String content, boolean isAppend)
    {
        writeFile(file, content, isAppend);
    }

    /**
     * 写入content到file中，覆盖文件内容.
     *
     * @param file    File
     * @param content 内容
     * @see FileTool#write(File, String, boolean)
     */
    public static void write(File file, String content)
    {
        write(file, content, false);
    }

    /**
     * 读取文件pathname，返回文件内容。如果文件为空，返回空字符串
     *
     * @param pathname File
     * @return 文件内容
     * @see FileTool#readFile(File)
     */
    public static String readFile(String pathname)
    {
        return readFile(fromName(pathname));
    }

    /**
     * 读取文件file，返回文件内容。如果文件为空，返回空字符串
     *
     * @param file File
     * @return 文件内容
     */
    public static String readFile(File file)
    {
        try
        {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] byteArray = getByteArray();
            StringBuffer stringBuffer = new StringBuffer();
            int length;
            while((length = bis.read(byteArray)) != -1)
            {
                stringBuffer.append(new String(byteArray, 0, length));
            }
            bis.close();
            fis.close();
            return stringBuffer.toString();
        } catch(IOException e)
        {
            e.printStackTrace();
        }

        return TextTool.emptyString();
    }

    /**
     * 读取文件pathname，返回文件内容。如果文件为空，返回空字符串
     *
     * @param pathname File
     * @return 文件内容
     * @see FileTool#readFile(File)
     */
    public static String read(String pathname)
    {
        return readFile(pathname);
    }

    /**
     * 读取文件file，返回文件内容。如果文件为空，返回空字符串
     *
     * @param file File
     * @return 文件内容
     * @see FileTool#readFile(String)
     */
    public static String read(File file)
    {
        return readFile(file);
    }

    /**
     * 返回一个长度1024的byte数组
     *
     * @see FileTool#DEFAULT_BYTE_SIZE
     */
    public static byte[] getByteArray()
    {
        return new byte[DEFAULT_BYTE_SIZE];
    }

    /**
     * 获取文件最后修改的毫秒时间戳
     *
     * @param filePath 文件路径
     * @return 文件最后修改的毫秒时间戳
     */
    public static long getLastModified(String filePath)
    {
        return getLastModified(fromName(filePath));
    }

    /**
     * 获取文件最后修改的毫秒时间戳
     *
     * @param file 文件
     * @return 文件最后修改的毫秒时间戳
     */
    public static long getLastModified(File file)
    {
        return file.lastModified();
    }


    /**
     * 根据文件路径获取文件名.
     *
     * @param pathname 文件路径名
     * @return 文件名
     */
    public static String getName(String pathname)
    {
        return getName(fromName(pathname));
    }

    private static String getName(File file)
    {
        return file.getName();
    }

    /**
     * 获取文件标题，不包含扩展名
     *
     * @param pathname 文件路径名
     * @return 文件标题
     */
    public static String getTitle(String pathname)
    {
        return getTitle(fromName(pathname));
    }

    /**
     * 获取文件标题
     *
     * @param file File
     * @return 文件标题
     */
    private static String getTitle(File file)
    {
        String name = file.getName();
        if(!name.contains("."))
        {
            return name;
        }

        return name.substring(0, name.lastIndexOf("."));
    }

    /**
     * 根据pathname返回文件扩展名
     *
     * @param pathname 文件路径名
     * @return 扩展名
     */
    public static String getExtension(String pathname)
    {
        return getExtension(fromName(pathname));
    }

    /**
     * 根据File返回扩展名
     *
     * @param file
     * @return 扩展名
     */
    public static String getExtension(File file)
    {
        String name = file.getName();
        if(!hasExtension(file))
        {
            return "";
        }

        return name.substring(name.lastIndexOf(".") + 1);
    }

    /**
     * 判断文件是不是有扩展名
     *
     * @param file
     * @return true 有扩展名
     */
    private static boolean hasExtension(File file)
    {
        String name = file.getName();
        if(!name.contains("."))
        {
            return false;
        }

        //防止文件名以.结束
        if(name.lastIndexOf(".") == name.length() - 1)
        {
            return false;
        }

        return true;
    }

    /**
     * 判断文件是不是类型化的文件（相当于判断是不是有扩展名)
     *
     * @param file File
     * @return true 是类型化文件. false 不是类型化文件
     */
    public static boolean isTyped(File file)
    {
        return hasExtension(file);
    }

    public static File[] listFiles(String pathname)
    {
        return listFiles(fromName(pathname));
    }

    public static File[] listFiles(File file)
    {
        return file.listFiles();
    }

    public static String[] listFilesNames(String pathname)
    {
        return listFilesNames(fromName(pathname));
    }

    public static String[] listFilesNames(File file)
    {
        File[] files = listFiles(file);
        if(files == null)
        {
            return null;
        }

        String[] names = new String[files.length];
        for(int i = 0; i < files.length; i++)
        {
            names[i] = files[i].getName();
        }

        return names;
    }

    public static String[] toFileNames(File[] files)
    {
        String[] names = new String[files.length];
        for(int i = 0; i < names.length; i++)
        {
            names[i] = files[i].getName();
        }

        return names;
    }

    public static String[] toFilePaths(File[] files)
    {
        String[] names = new String[files.length];
        for(int i = 0; i < names.length; i++)
        {
            names[i] = files[i].getAbsolutePath();
        }

        return names;
    }

    public static String[] listFilesPaths(String pathname)
    {
        return listFilesPaths(fromName(pathname));
    }

    public static String[] listFilesPaths(File file)
    {
        File[] files = listFiles(file);
        if(files == null)
        {
            return null;
        }

        String[] names = new String[files.length];
        for(int i = 0; i < files.length; i++)
        {
            try
            {
                names[i] = files[i].getCanonicalPath();
            } catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        return names;
    }

    public static File[] listFiles(String pathname, FileFilter fileFilter)
    {
        return listFiles(fromName(pathname), fileFilter);
    }

    public static File[] listFiles(File file, FileFilter fileFilter)
    {
        return file.listFiles(fileFilter);
    }

    public static File[] listFiles(String pathname, FilenameFilter filenameFilter)
    {
        return listFiles(fromName(pathname), filenameFilter);
    }

    public static File[] listFiles(File file, FilenameFilter filenameFilter)
    {
        return file.listFiles(filenameFilter);
    }

    public static File[] listFilesLikeNames(String pathname, String... names)
    {
        return listFilesLikeNames(fromName(pathname), names);
    }

    public static File[] listFilesLikeNames(File file, String[] names)
    {
        final List<String> list = asList(names);
        return file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return list.contains(name);
            }
        });
    }

    public static File[] listFilesLikeNamesNoCase(String pathname, String... names)
    {
        return listFilesLikeNamesNoCase(fromName(pathname), names);
    }

    public static File[] listFilesLikeNamesNoCase(File file, String... names)
    {
        final List<String> list = new ArrayList<>();
        for(String name : names)
        {
            list.add(name.toLowerCase());
        }

        return file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return list.contains(name.toLowerCase());
            }
        });
    }

    public static File[] listFilesDislikeNames(String pathname, String... names)
    {
        return listFilesDislikeNames(fromName(pathname), names);
    }

    public static File[] listFilesDislikeNames(File file, String... names)
    {
        final List<String> list = asList(names);
        return file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return !list.contains(name);
            }
        });
    }

    public static File[] listFilesDislikeNamesNoCase(String pathname, String... names)
    {
        return listFilesDislikeNamesNoCase(fromName(pathname), names);
    }

    public static File[] listFilesDislikeNamesNoCase(File file, String... names)
    {
        final List<String> list = new ArrayList<>();
        for(String name : names)
        {
            list.add(name.toLowerCase());
        }

        return file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return !list.contains(name.toLowerCase());
            }
        });
    }

}
