package com.ayvytr.easyandroid.tools;

import com.ayvytr.easyandroid.exception.UnsupportedInitializationException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * 文件操作类，判断是不是文件/目录，是不是存在，重命名，创建文件/目录，列出文件/目录，
 * 获取文件名/文件标题（不包含扩展名), 有没有扩展名，读/写文件等功能.
 *
 * @author Ayvytr <a href="https://github.com/Ayvytr" target="_blank">'s GitHub</a>
 * @since 1.0.0
 */

public class FileTool
{
    //默认byte数组大小，读取文件时使用
    public static final int DEFAULT_BYTE_SIZE = 1024;

    public FileTool()
    {
        throw new UnsupportedInitializationException();
    }

    /**
     * 根据文件名打开文件并返回
     *
     * @param pathname 文件名
     * @return File
     */
    public static File open(String pathname)
    {
        return new File(pathname);
    }

    /**
     * 根据目录名和文件名打开路径
     *
     * @param parent 目录名
     * @param child  文件名
     * @return File
     */
    public static File open(String parent, String child)
    {
        return new File(parent, child);
    }

    /**
     * 根据文件名打开文件并返回
     *
     * @param pathname 文件名
     * @return File
     */
    public static File of(String pathname)
    {
        return new File(pathname);
    }

    /**
     * 根据目录名和文件名打开文件并返回，{@link File#File(String, String)}
     *
     * @param parent 目录
     * @param child  文件名
     * @return File
     */
    public static File of(String parent, String child)
    {
        return new File(parent, child);
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isExists(String filePath)
    {
        return isExists(of(filePath));
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
        return rename(of(filePath), newName);
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
        return isDir(of(dirPath));
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
        return isFile(of(filePath));
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
        createDir(of(pathname));
    }

    /**
     * 根据file创建目录.
     *
     * @param file File实例
     */
    public static void createDir(File file)
    {
        if(file != null && !isExists(file))
        {
            file.mkdirs();
        }
    }

    /**
     * 创建目录
     *
     * @param pathname 文件名
     */
    public static void createFile(String pathname)
    {
        createFile(of(pathname));
    }

    /**
     * 创建目录
     *
     * @param file 文件
     */
    public static void createFile(File file)
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
        writeFile(of(filepath), content, isAppend);
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
        return readFile(of(pathname));
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
     * @return byte array
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
        return getLastModified(of(filePath));
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
        return getName(of(pathname));
    }

    /**
     * 获取file的文件名
     *
     * @param file 文件
     * @return 文件名
     */
    public static String getName(File file)
    {
        return file.getName();
    }

    /**
     * 获取文件名，返回全小写名称
     *
     * @param pathname 文件名
     * @return 全小写文件名
     */
    public static String getLowerName(String pathname)
    {
        return getLowerName(of(pathname));
    }

    /**
     * 获取文件名，返回全小写名称
     *
     * @param file 文件
     * @return 全小写文件名
     */
    public static String getLowerName(File file)
    {
        return file.getName().toLowerCase();
    }


    /**
     * 获取文件标题，不包含扩展名
     *
     * @param pathname 文件路径名
     * @return 文件标题
     */
    public static String getTitle(String pathname)
    {
        return getTitle(of(pathname));
    }

    /**
     * 获取文件标题
     *
     * @param file File
     * @return 文件标题
     */
    public static String getTitle(File file)
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
        return getExtension(of(pathname));
    }

    /**
     * 根据File返回扩展名
     *
     * @param file 文件
     * @return 扩展名
     */
    public static String getExtension(File file)
    {
        String name = file.getName();
        if(!hasExtension(file))
        {
            return "";
        }

        return name.substring(name.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 判断文件是不是有扩展名
     *
     * @param pathname 文件名
     * @return true 有扩展名
     */
    public static boolean hasExtension(String pathname)
    {
        return hasExtension(of(pathname));
    }

    /**
     * 判断文件是不是有扩展名
     *
     * @param file 文件
     * @return {@code true} 有扩展名<br>{@code false} 没有扩展名
     */
    public static boolean hasExtension(File file)
    {
        //如果是目录，直接返回，判断扩展名没有意义
        if(file.isDirectory())
        {
            return false;
        }

        String name = file.getName();

        //需要考虑隐藏文件，隐藏文件以.开头，需要进行处理
        while(name.startsWith("."))
        {
            name = name.substring(1);
        }

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
     * @param pathname 文件名
     * @return {@code true} 是类型化文件<br>{@code false} 不是类型化文件
     */
    public static boolean isTyped(String pathname)
    {
        return isTyped(of(pathname));
    }

    /**
     * 判断文件是不是类型化的文件（相当于判断是不是有扩展名)
     *
     * @param file File
     * @return {@code true} 是类型化文件<br> {@code false} 不是类型化文件
     */
    public static boolean isTyped(File file)
    {
        return hasExtension(file);
    }

    /**
     * 列出名为pathname文件下的所有文件
     *
     * @param pathname 文件名
     * @return 文件列表
     */
    public static File[] listFiles(String pathname)
    {
        return listFiles(of(pathname));
    }

    /**
     * 列出文件file下的所有文件
     *
     * @param file 文件
     * @return 文件列表
     */
    public static File[] listFiles(File file)
    {
        return listFiles(file, false);
    }

    /**
     * 列出名为pathname文件下的所有文件
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @return 文件列表
     */
    public static File[] listFiles(String pathname, boolean isAllFiles)
    {
        return listFiles(of(pathname), isAllFiles);
    }

    /**
     * 列出名为pathname文件下的所有文件
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @return 文件列表
     */
    public static File[] listFiles(File file, boolean isAllFiles)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAll(list, file);
            File[] files = new File[list.size()];
            return list.toArray(files);
        }
        else
        {
            return file.listFiles();
        }
    }

    /**
     * 列出文件file下所有文件，递归调用
     *
     * @param list 要传出的文件列表
     * @param file 文件
     */
    public static void listAll(List<File> list, File file)
    {
        File[] files = file.listFiles();
        list.addAll(Arrays.asList(files));
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAll(list, f);
            }
        }
    }

    /**
     * 列出名为pathname文件包含的所有文件名称
     *
     * @param pathname 文件名
     * @return 文件名称列表
     */
    public static String[] listFilesNames(String pathname)
    {
        return listFilesNames(of(pathname));
    }

    /**
     * 列出file文件包含的所有文件名称
     *
     * @param file 文件
     * @return 文件名称列表
     */
    public static String[] listFilesNames(File file)
    {
        return listFilesNames(file, false);
    }

    /**
     * 列出名为pathname文件包含的所有文件名称
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @return 文件名称列表
     */
    public static String[] listFilesNames(String pathname, boolean isAllFiles)
    {
        return listFilesNames(of(pathname), isAllFiles);
    }

    /**
     * 列出file文件包含的所有文件名称
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @return 文件名称列表
     */
    public static String[] listFilesNames(File file, boolean isAllFiles)
    {
        File[] files = listFiles(file, isAllFiles);
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

    /**
     * 将文件数组转换为文件名称数组返回
     *
     * @param files 文件数组
     * @return 文件名称数组
     */
    public static String[] toFileNames(File[] files)
    {
        String[] names = new String[files.length];
        for(int i = 0; i < names.length; i++)
        {
            names[i] = files[i].getName();
        }

        return names;
    }

    /**
     * 将文件数组转换为文件路径数组返回
     *
     * @param files 文件数组
     * @return 文件路径数组
     */
    public static String[] toFilePaths(File[] files)
    {
        String[] names = new String[files.length];
        for(int i = 0; i < names.length; i++)
        {
            names[i] = files[i].getAbsolutePath();
        }

        return names;
    }

    /**
     * 列出名为pathname文件包含的所有文件名称
     *
     * @param pathname 文件名
     * @return 文件名称列表
     */
    public static String[] listFilesPaths(String pathname)
    {
        return listFilesPaths(of(pathname));
    }

    /**
     * 列出file文件中所有文件路径
     *
     * @param file 文件
     * @return 文件名称列表
     */
    public static String[] listFilesPaths(File file)
    {
        return listFilesPaths(file, false);
    }

    /**
     * 列出名为pathname文件中所有文件路径
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @return 文件名称列表
     */
    public static String[] listFilesPaths(String pathname, boolean isAllFiles)
    {
        return listFilesPaths(of(pathname), isAllFiles);
    }

    /**
     * 列出file文件中所有文件路径
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @return 文件名称列表
     */
    public static String[] listFilesPaths(File file, boolean isAllFiles)
    {
        File[] files = listFiles(file, isAllFiles);
        if(files == null)
        {
            return null;
        }

        String[] names = new String[files.length];
        try
        {
            for(int i = 0; i < files.length; i++)
            {
                names[i] = files[i].getCanonicalPath();
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }

        return names;
    }

    /**
     * 列出名为pathname文件中所有文件路径
     *
     * @param pathname   文件名
     * @param fileFilter 筛选器
     * @return 文件名称列表
     */
    public static File[] listFiles(String pathname, FileFilter fileFilter)
    {
        return listFiles(of(pathname), fileFilter);
    }

    /**
     * 列出file文件中所有文件路径
     *
     * @param file       文件
     * @param fileFilter 筛选器
     * @return 文件名称列表
     */
    public static File[] listFiles(File file, FileFilter fileFilter)
    {
        return file.listFiles(fileFilter);
    }

    /**
     * 列出名为pathname文件中所有文件路径
     *
     * @param pathname   文件名
     * @param fileFilter 筛选器
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @return 文件名称列表
     */
    public static File[] listFiles(String pathname, FileFilter fileFilter, boolean isAllFiles)
    {
        return listFiles(of(pathname), fileFilter, isAllFiles);
    }

    /**
     * 列出file文件中所有文件路径
     *
     * @param file       文件
     * @param fileFilter 筛选器
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @return 文件名称列表
     */
    public static File[] listFiles(File file, FileFilter fileFilter, boolean isAllFiles)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAll(list, file, fileFilter);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFiles(file, fileFilter);
        }
    }

    /**
     * 列出file文件中所有文件，递归调用
     *
     * @param list       文件列表，传出参数
     * @param file       文件
     * @param fileFilter 筛选器
     */
    public static void listAll(List<File> list, File file, FileFilter fileFilter)
    {
        File[] files = listFiles(file, fileFilter);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAll(list, f, fileFilter);
            }
        }
    }

    /**
     * 列出名为pathname文件中所有符合筛选器的文件
     *
     * @param pathname       文件名
     * @param filenameFilter 筛选器
     * @return 文件数组
     */
    public static File[] listFiles(String pathname, FilenameFilter filenameFilter)
    {
        return listFiles(of(pathname), filenameFilter);
    }

    /**
     * 列出file文件中所有符合筛选器的文件
     *
     * @param file           文件
     * @param filenameFilter 筛选器
     * @return 文件数组
     */
    public static File[] listFiles(File file, FilenameFilter filenameFilter)
    {
        return file.listFiles(filenameFilter);
    }

    /**
     * 列出名为pathname文件中所有符合筛选器的文件
     *
     * @param pathname       文件名
     * @param filenameFilter 筛选器
     * @param isAllFiles     是不是包含所有目录文件
     * @return 文件数组
     */
    public static File[] listFiles(String pathname, FilenameFilter filenameFilter,
                                   boolean isAllFiles)
    {
        return listFiles(of(pathname), filenameFilter, isAllFiles);
    }

    /**
     * 列出file文件中所有符合筛选器的文件
     *
     * @param file           文件
     * @param filenameFilter 筛选器
     * @param isAllFiles     是不是包含所有目录文件
     * @return 文件数组
     */
    public static File[] listFiles(File file, FilenameFilter filenameFilter, boolean isAllFiles)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAll(list, file, filenameFilter);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFiles(file, filenameFilter);
        }
    }

    /**
     * 列出file文件中所有文件，递归调用
     *
     * @param list           文件列表，传出参数
     * @param file           文件
     * @param filenameFilter 筛选器
     */
    public static void listAll(List<File> list, File file, FilenameFilter filenameFilter)
    {
        File[] files = listFiles(file, filenameFilter);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAll(list, f, filenameFilter);
            }
        }
    }

    /**
     * 返回名为pathname目录下所有符合names中任意名称的文件
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithNames(String pathname, String... names)
    {
        return listFilesWithNames(of(pathname), names);
    }

    /**
     * 返回file目录下所有符合names中任意名称的文件
     *
     * @param file  文件
     * @param names 名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithNames(File file, String... names)
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

    /**
     * 返回名为pathname目录下所有符合names中任意名称的文件
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithNames(String pathname, boolean isAllFiles, String... names)
    {
        return listFilesWithNames(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有符合names中任意名称的文件
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithNames(File file, boolean isAllFiles, String[] names)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllWithNames(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFilesWithNames(file, names);
        }
    }

    /**
     * 列出file目录中所有符合names中任意名称的文件，递归调用
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称列表
     */
    public static void listAllWithNames(List<File> list, File file, String[] names)
    {
        File[] files = listFilesWithNames(file, names);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAllWithNames(list, f, names);
            }
        }
    }

    /**
     * 返回名为pathname目录下所有符合names中任意名称的文件，不区分大小写
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithNamesNoCase(String pathname, String... names)
    {
        return listFilesWithNamesNoCase(of(pathname), names);
    }

    /**
     * 返回file目录下所有符合names中任意名称的文件，不区分大小写
     *
     * @param file  文件
     * @param names 名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithNamesNoCase(File file, String... names)
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

    /**
     * 返回名为pathname目录下所有符合names中任意名称的文件，不区分大小写
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithNamesNoCase(String pathname, boolean isAllFiles,
                                                  String... names)
    {
        return listFilesWithNamesNoCase(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有符合names中任意名称的文件，不区分大小写
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithNamesNoCase(File file, boolean isAllFiles, String[] names)
    {
        if(isAllFiles)
        {
            ArrayList<File> list = new ArrayList<>();
            listAllWithNamesNoCase(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFilesWithNamesNoCase(file, names);
        }
    }

    /**
     * 列出file目录中所有符合names中任意名称的文件，不区分大小写，递归调用
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称列表
     */
    public static void listAllWithNamesNoCase(ArrayList<File> list, File file, String[] names)
    {
        File[] files = listFilesWithNamesNoCase(file, names);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAllWithNamesNoCase(list, f, names);
            }
        }
    }

    /**
     * 返回名为pathname目录下所有不符合names中任意名称的文件
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithoutNames(String pathname, String... names)
    {
        return listFilesWithoutNames(of(pathname), names);
    }

    /**
     * 返回file目录下所有不符合names中任意名称的文件
     *
     * @param file  文件
     * @param names 名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithoutNames(File file, String... names)
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

    /**
     * 返回名为pathname目录下所有不符合names中任意名称的文件
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithoutNames(String pathname, boolean isAllFiles, String... names)
    {
        return listFilesWithoutNames(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有不符合names中任意名称的文件
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithoutNames(File file, boolean isAllFiles, String[] names)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllWithoutNames(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFilesWithoutNames(file, names);
        }
    }

    /**
     * 列出file目录下所有不符合names中任意名称的文件，递归调用
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称列表
     */
    public static void listAllWithoutNames(List<File> list, File file, String[] names)
    {
        File[] files = listFilesWithoutNames(file, names);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAllWithoutNames(list, f, names);
            }
        }
    }

    /**
     * 返回名为pathname目录下所有不符合names中任意名称的文件，不区分大小写
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithoutNamesNoCase(String pathname, String... names)
    {
        return listFilesWithoutNamesNoCase(of(pathname), names);
    }

    /**
     * 返回file目录下所有不符合names中任意名称的文件，不区分大小写
     *
     * @param file  文件
     * @param names 名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithoutNamesNoCase(File file, String... names)
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

    /**
     * 返回名为pathname目录下所有不符合names中任意名称的文件，不区分大小写
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithoutNamesNoCase(String pathname, boolean isAllFiles,
                                                     String... names)
    {
        return listFilesWithoutNamesNoCase(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有不符合names中任意名称的文件，不区分大小写
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件数组
     */
    public static File[] listFilesWithoutNamesNoCase(File file, boolean isAllFiles, String[] names)
    {
        if(isAllFiles)
        {
            ArrayList<File> list = new ArrayList<>();
            listAllWithoutNamesNoCase(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFilesWithoutNamesNoCase(file, names);
        }
    }

    /**
     * 列出file目录下所有不符合names中任意名称的文件，不区分大小写，递归调用
     *
     * @param list  文件列表
     * @param file  文件
     * @param names 名称列表
     */
    public static void listAllWithoutNamesNoCase(ArrayList<File> list, File file, String[] names)
    {
        File[] files = listFilesWithoutNamesNoCase(file, names);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAllWithoutNamesNoCase(list, f, names);
            }
        }
    }

    /**
     * 返回名为pathname目录下所有包含names列表中任意名称的文件列表
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 文件列表
     */
    public static File[] listFilesLikeNames(String pathname, String... names)
    {
        return listFilesLikeNames(of(pathname), names);
    }

    /**
     * 返回file目录下所有包含names列表中任意名称的文件列表
     *
     * @param file  文件
     * @param names 名称列表
     * @return 文件列表
     */
    public static File[] listFilesLikeNames(File file, final String... names)
    {
        final List<String> list = getNamesList(names);
        return file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                for(String s : list)
                {
                    if(name.contains(s))
                    {
                        return true;
                    }
                }

                return false;
            }
        });
    }

    /**
     * 返回名为pathname目录下所有包含names列表中任意名称的文件列表
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件列表
     */
    public static File[] listFilesLikeNames(String pathname, boolean isAllFiles, String... names)
    {
        return listFilesLikeNames(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有包含names列表中任意名称的文件列表
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件列表
     */
    public static File[] listFilesLikeNames(File file, boolean isAllFiles, String[] names)
    {
        if(isAllFiles)
        {
            ArrayList<File> list = new ArrayList<>();
            listAllLikeNames(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFilesLikeNames(file, names);
        }
    }

    /**
     * 列出file文件中所有包含name数组中名称的文件，递归调用
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称数组
     */
    public static void listAllLikeNames(ArrayList<File> list, File file, String[] names)
    {
        File[] files = listFilesLikeNames(file, names);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAllLikeNames(list, f, names);
            }
        }
    }

    /**
     * 返回名为pathname目录下所有包含names列表中任意名称的文件列表，不区分大小写
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 文件列表
     */
    public static File[] listFilesLikeNamesNoCase(String pathname, String... names)
    {
        return listFilesLikeNamesNoCase(of(pathname), names);
    }

    /**
     * 返回file目录下所有包含names列表中任意名称的文件列表，不区分大小写
     *
     * @param file  文件
     * @param names 名称列表
     * @return 文件列表
     */
    public static File[] listFilesLikeNamesNoCase(File file, String... names)
    {
        final List<String> list = getNamesList(names, true);
        return file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                String lowerName = name.toLowerCase();
                for(String s : list)
                {
                    if(lowerName.contains(s))
                    {
                        return true;
                    }
                }

                return false;
            }
        });
    }


    /**
     * 返回名为pathname目录下所有包含names列表中任意名称的文件列表，不区分大小写
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件列表
     */
    public static File[] listFilesLikeNamesNoCase(String pathname, boolean isAllFiles,
                                                  String... names)
    {
        return listFilesLikeNamesNoCase(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有包含names列表中任意名称的文件列表，不区分大小写
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件列表
     */
    public static File[] listFilesLikeNamesNoCase(File file, boolean isAllFiles, String[] names)
    {
        if(isAllFiles)
        {
            ArrayList<File> list = new ArrayList<>();
            listAllLikeNamesNoCase(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFilesLikeNamesNoCase(file, names);
        }
    }

    /**
     * 列出file文件中所有包含name数组中名称的文件，不区分大小写，递归调用
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称数组
     */
    public static void listAllLikeNamesNoCase(ArrayList<File> list, File file, String[] names)
    {
        File[] files = listFilesLikeNamesNoCase(file, names);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAllLikeNamesNoCase(list, f, names);
            }
        }
    }

    /**
     * 根据names数组返回列表
     *
     * @param names 名称数组
     * @return 列表
     */
    public static List<String> getNamesList(String[] names)
    {
        return getNamesList(names, false);
    }

    /**
     * 根据names数组返回列表
     *
     * @param names      名称数组
     * @param ignoreCase true：不区分大小写; false:区分大小写
     * @return 列表
     */
    public static List<String> getNamesList(String[] names, boolean ignoreCase)
    {
        if(!ignoreCase)
        {
            return Arrays.asList(names);
        }

        List<String> list = new ArrayList<>();
        for(String name : names)
        {
            list.add(name.toLowerCase());
        }

        return list;
    }

    /**
     * 返回名为pathname目录下所有不包含names列表中任意名称的文件列表
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 文件列表
     */
    public static File[] listFilesDislikeNames(String pathname, String... names)
    {
        return listFilesDislikeNames(of(pathname), names);
    }

    /**
     * 返回file目录下所有不包含names列表中任意名称的文件列表
     *
     * @param file  文件
     * @param names 名称列表
     * @return 文件列表
     */
    public static File[] listFilesDislikeNames(File file, String... names)
    {
        final List<String> list = getNamesList(names);
        return file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                for(String s : list)
                {
                    if(name.contains(s))
                    {
                        return false;
                    }
                }

                return true;
            }
        });
    }


    /**
     * 返回名为pathname目录下所有不包含names列表中任意名称的文件列表
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件列表
     */
    public static File[] listFilesDislikeNames(String pathname, boolean isAllFiles, String... names)
    {
        return listFilesDislikeNames(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有不包含names列表中任意名称的文件列表
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件列表
     */
    public static File[] listFilesDislikeNames(File file, boolean isAllFiles, String[] names)
    {
        if(isAllFiles)
        {
            ArrayList<File> list = new ArrayList<>();
            listAllDislikeNames(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFilesDislikeNames(file, names);
        }
    }

    /**
     * 列出file文件中所有不符合name数组中名称的文件，递归调用
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称数组
     */
    public static void listAllDislikeNames(ArrayList<File> list, File file, String[] names)
    {
        File[] files = listFilesDislikeNames(file, names);
        list.addAll(Arrays.asList(files));
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAllDislikeNames(list, f, names);
            }
        }
    }

    /**
     * 返回名为pathname目录下所有不包含names列表中任意名称的文件列表，不区分大小写
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 文件列表
     */
    public static File[] listFilesDislikeNamesNoCase(String pathname, String... names)
    {
        return listFilesDislikeNamesNoCase(of(pathname), names);
    }

    /**
     * 返回file目录下所有不包含names列表中任意名称的文件列表，不区分大小写
     *
     * @param file  文件
     * @param names 名称列表
     * @return 文件列表
     */
    public static File[] listFilesDislikeNamesNoCase(File file, String... names)
    {
        final List<String> list = getNamesList(names, true);
        return file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                String lowerName = name.toLowerCase();
                for(String s : list)
                {
                    if(lowerName.contains(s))
                    {
                        return false;
                    }
                }

                return true;
            }
        });
    }

    /**
     * 返回名为pathname目录下所有不包含names列表中任意名称的文件列表，不区分大小写
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件列表
     */
    public static File[] listFilesDislikeNamesNoCase(String pathname, boolean isAllFiles,
                                                     String... names)
    {
        return listFilesDislikeNamesNoCase(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有不包含names列表中任意名称的文件列表，不区分大小写
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 文件列表
     */
    public static File[] listFilesDislikeNamesNoCase(File file, boolean isAllFiles, String[] names)
    {
        if(isAllFiles)
        {
            ArrayList<File> list = new ArrayList<>();
            listAllDislikeNamesNoCase(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFilesDislikeNamesNoCase(file, names);
        }
    }

    /**
     * 列出file文件中所有不包含name数组中名称的文件，不区分大小写，递归调用
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称数组
     */
    public static void listAllDislikeNamesNoCase(ArrayList<File> list, File file, String[] names)
    {
        File[] files = listFilesDislikeNamesNoCase(file, names);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAllDislikeNamesNoCase(list, f, names);
            }
        }
    }

    /**
     * 返回名为pathname目录下所有符合extensions列表中任意扩展名文件列表，不区分扩展名大小写
     *
     * @param pathname   文件名
     * @param extensions 扩展名列表
     * @return 文件列表
     */
    public static File[] listFilesWithExtension(String pathname, String... extensions)
    {
        return listFilesWithExtension(of(pathname), extensions);
    }

    /**
     * 返回file目录下所有符合extensions列表中任意扩展名文件列表，不区分扩展名大小写
     *
     * @param file       文件
     * @param extensions 扩展名列表
     * @return 文件列表
     */
    public static File[] listFilesWithExtension(File file, String... extensions)
    {
        final List<String> list = getNamesExtensionsList(extensions);
        return file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if(list.contains(getExtension(name)))
                {
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 返回名为pathname目录下所有符合extensions列表中任意扩展名文件列表，不区分扩展名大小写
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param extensions 扩展名列表
     * @return 文件列表
     */
    public static File[] listFilesWithExtension(String pathname, boolean isAllFiles,
                                                String... extensions)
    {
        return listFilesWithExtension(of(pathname), isAllFiles, extensions);
    }

    /**
     * 返回file目录下所有符合extensions列表中任意扩展名文件列表，不区分扩展名大小写
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param extensions 扩展名列表
     * @return 文件列表
     */
    public static File[] listFilesWithExtension(File file, boolean isAllFiles, String... extensions)
    {
        if(isAllFiles)
        {
            ArrayList<File> list = new ArrayList<>();
            listAllWithExtension(list, file, extensions);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFilesWithExtension(file, extensions);
        }
    }

    /**
     * 列出file目录中所有符合extensions中任意扩展名的文件，递归调用
     *
     * @param list       文件列表，传出参数
     * @param file       文件
     * @param extensions 扩展名列表
     */
    public static void listAllWithExtension(List<File> list, File file, String[] extensions)
    {
        File[] files = listFilesWithExtension(file, extensions);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAllWithExtension(list, f, extensions);
            }
        }
    }

    /**
     * 根据扩展名数组返回扩展名列表
     *
     * @param extensions 扩展名数组
     * @return 数组名列表
     */
    public static List<String> getNamesExtensionsList(String[] extensions)
    {
        List<String> list = new ArrayList<>();
        for(String e : extensions)
        {
            if(e.lastIndexOf(".") == e.length() - 1)
            {
                continue;
            }
            list.add(e.substring(e.lastIndexOf(".") + 1).toLowerCase());
        }

        return list;
    }

    /**
     * 返回名为pathname目录下所有不符合extensions列表中任意扩展名文件列表，不区分扩展名大小写
     *
     * @param pathname   文件名
     * @param extensions 扩展名列表
     * @return 文件列表
     */
    public static File[] listFilesWithoutExtension(String pathname, String... extensions)
    {
        return listFilesWithoutExtension(of(pathname), extensions);
    }

    /**
     * 返回file目录下所有不符合extensions列表中任意扩展名文件列表，不区分扩展名大小写
     *
     * @param file       文件
     * @param extensions 扩展名列表
     * @return 文件列表
     */
    public static File[] listFilesWithoutExtension(File file, String... extensions)
    {
        final List<String> list = getNamesExtensionsList(extensions);
        return file.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if(list.contains(getExtension(name)))
                {
                    return false;
                }
                return true;
            }
        });
    }

    /**
     * 返回名为pathname目录下所有不符合extensions列表中任意扩展名文件列表，不区分扩展名大小写
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param extensions 扩展名列表
     * @return 文件列表
     */
    public static File[] listFilesWithoutExtension(String pathname, boolean isAllFiles,
                                                   String... extensions)
    {
        return listFilesWithoutExtension(of(pathname), isAllFiles, extensions);
    }

    /**
     * 返回file目录下所有不符合extensions列表中任意扩展名文件列表，不区分扩展名大小写
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param extensions 扩展名列表
     * @return 文件列表
     */
    public static File[] listFilesWithoutExtension(File file, boolean isAllFiles,
                                                   String... extensions)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllWithoutExtension(list, file, extensions);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listFilesWithoutExtension(file, extensions);
        }
    }

    /**
     * 列出file目录中所有不符合extensions中任意扩展名的文件，递归调用
     *
     * @param list       文件列表，传出参数
     * @param file       文件
     * @param extensions 扩展名列表
     */
    public static void listAllWithoutExtension(List<File> list, File file, String[] extensions)
    {
        File[] files = listFilesWithoutExtension(file, extensions);
        list.addAll(Arrays.asList(files));
        files = listFiles(file);
        for(File f : files)
        {
            if(f.isDirectory())
            {
                listAllWithoutExtension(list, f, extensions);
            }
        }
    }

    /**
     * 返回名为pathname目录下所有目录
     *
     * @param pathname 文件名
     * @return 目录列表
     */
    public static File[] listDirs(String pathname)
    {
        return listDirs(of(pathname));
    }

    /**
     * 返回file目录下所有目录
     *
     * @param file 文件
     * @return 目录列表
     */
    public static File[] listDirs(File file)
    {
        return file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.isDirectory();
            }
        });
    }

    /**
     * 返回名为pathname目录下所有目录
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @return 目录列表
     */
    public static File[] listDirs(String pathname, boolean isAllFiles)
    {
        return listDirs(of(pathname), isAllFiles);
    }

    /**
     * 返回file目录下所有目录
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @return 目录列表
     */
    public static File[] listDirs(File file, boolean isAllFiles)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllDirs(list, file);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listDirs(file);
        }
    }

    /**
     * 列出file目录下所有目录，包括子目录
     *
     * @param list 文件列表，传出参数
     * @param file 文件
     */
    public static void listAllDirs(List<File> list, File file)
    {
        File[] files = listDirs(file);
        list.addAll(Arrays.asList(files));
        for(File f : files)
        {
            listAllDirs(list, f);
        }
    }

    /**
     * 返回名为pathname目录下所有符合names列表中任意名称的目录
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithNames(String pathname, String... names)
    {
        return listDirsWithNames(of(pathname), names);
    }

    /**
     * 返回file目录下所有符合names列表中任意名称的目录
     *
     * @param file  文件
     * @param names 名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithNames(File file, String... names)
    {
        final List<String> list = getNamesList(names);
        return file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.isDirectory() && list.contains(getName(pathname));
            }
        });
    }

    /**
     * 返回名为pathname目录下所有符合names列表中任意名称的目录
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithNames(String pathname, boolean isAllFiles, String... names)
    {
        return listDirsWithNames(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有符合names列表中任意名称的目录
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithNames(File file, boolean isAllFiles, String... names)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllDirsWithNames(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listDirsWithNames(file, names);
        }
    }

    /**
     * 列出file目录下所有符合names列表中任意名称的目录，包括子目录
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称列表
     */
    public static void listAllDirsWithNames(List<File> list, File file, String[] names)
    {
        File[] files = listDirsWithNames(file, names);
        list.addAll(Arrays.asList(files));
        files = listDirs(file);
        for(File f : files)
        {
            listAllDirsWithNames(list, f, names);
        }
    }

    /**
     * 返回名为pathname目录下所有符合names列表中任意名称的目录，不区分大小写
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithNamesNoCase(String pathname, String... names)
    {
        return listDirsWithNamesNoCase(of(pathname), names);
    }

    /**
     * 返回file目录下所有符合names列表中任意名称的目录，不区分大小写
     *
     * @param file  文件
     * @param names 名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithNamesNoCase(File file, String... names)
    {
        final List<String> list = getNamesList(names, true);
        return file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.isDirectory() && list.contains(getLowerName(pathname));
            }
        });
    }

    /**
     * 返回名为pathname目录下所有符合names列表中任意名称的目录，不区分大小写
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithNamesNoCase(String pathname, boolean isAllFiles,
                                                 String... names)
    {
        return listDirsWithNamesNoCase(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有符合names列表中任意名称的目录，不区分大小写
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithNamesNoCase(File file, boolean isAllFiles, String... names)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllDirsWithNamesNoCase(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listDirsWithNamesNoCase(file, names);
        }
    }

    /**
     * 列出file目录下所有符合names列表中任意名称的目录，忽略大小写，包括子目录
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称列表
     */
    public static void listAllDirsWithNamesNoCase(List<File> list, File file, String[] names)
    {
        File[] files = listDirsWithNamesNoCase(file, names);
        list.addAll(Arrays.asList(files));
        files = listDirs(file);
        for(File f : files)
        {
            listAllDirsWithNamesNoCase(list, f, names);
        }
    }

    /**
     * 返回名为pathname目录下所有不符合names列表中任意名称的目录
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithoutNames(String pathname, String... names)
    {
        return listDirsWithoutNames(of(pathname), names);
    }

    /**
     * 返回file目录下所有不符合names列表中任意名称的目录
     *
     * @param file  文件
     * @param names 名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithoutNames(File file, String... names)
    {
        final List<String> list = getNamesList(names);
        return file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.isDirectory() && !list.contains(getName(pathname));
            }
        });
    }

    /**
     * 返回名为pathname目录下所有不符合names列表中任意名称的目录
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithoutNames(String pathname, boolean isAllFiles, String... names)
    {
        return listDirsWithoutNames(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有不符合names列表中任意名称的目录
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithoutNames(File file, boolean isAllFiles, String... names)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllDirsWithoutNames(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listDirsWithoutNames(file, names);
        }
    }

    /**
     * 列出file目录下所有不符合names列表中任意名称的目录，包括子目录
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称列表
     */
    public static void listAllDirsWithoutNames(List<File> list, File file, String[] names)
    {
        File[] files = listDirsWithoutNames(file, names);
        list.addAll(Arrays.asList(files));
        files = listDirs(file);
        for(File f : files)
        {
            listAllDirsWithoutNames(list, f, names);
        }
    }

    /**
     * 返回名为pathname目录下所有不符合names列表中任意名称的目录，不区分大小写
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithoutNamesNoCase(String pathname, String... names)
    {
        return listDirsWithoutNamesNoCase(of(pathname), names);
    }

    /**
     * 返回file目录下所有不符合names列表中任意名称的目录，不区分大小写
     *
     * @param file  文件
     * @param names 名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithoutNamesNoCase(File file, String... names)
    {
        final List<String> list = getNamesList(names, true);
        return file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.isDirectory() && !list.contains(getLowerName(pathname));
            }
        });
    }

    /**
     * 返回名为pathname目录下所有不符合names列表中任意名称的目录，不区分大小写
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithoutNamesNoCase(String pathname, boolean isAllFiles,
                                                    String... names)
    {
        return listDirsWithoutNamesNoCase(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有不符合names列表中任意名称的目录，不区分大小写
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsWithoutNamesNoCase(File file, boolean isAllFiles, String... names)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllDirsWithoutNamesNoCase(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listDirsWithoutNamesNoCase(file, names);
        }
    }

    /**
     * 列出file目录下所有不符合names列表中任意名称的目录，忽略大小写，包括子目录
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称列表
     */
    public static void listAllDirsWithoutNamesNoCase(List<File> list, File file, String[] names)
    {
        File[] files = listDirsWithoutNamesNoCase(file, names);
        list.addAll(Arrays.asList(files));
        files = listDirs(file);
        for(File f : files)
        {
            listAllDirsWithoutNamesNoCase(list, f, names);
        }
    }

    /**
     * 返回名为pathname目录下所有包含names列表中任意名称的目录
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 目录列表
     */
    public static File[] listDirsLikeNames(String pathname, String... names)
    {
        return listDirsLikeNames(of(pathname), names);
    }

    /**
     * 返回file目录下所有包含names列表中任意名称的目录
     *
     * @param file  文件
     * @param names 名称列表
     * @return 目录列表
     */
    public static File[] listDirsLikeNames(File file, String... names)
    {
        final List<String> list = getNamesList(names);
        return file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if(!pathname.isDirectory())
                {
                    return false;
                }

                String name = getName(pathname);
                for(String s : list)
                {
                    if(name.contains(s))
                    {
                        return true;
                    }
                }

                return false;
            }
        });
    }

    /**
     * 返回名为pathname目录下所有包含names列表中任意名称的目录
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsLikeNames(String pathname, boolean isAllFiles, String... names)
    {
        return listDirsLikeNames(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有包含names列表中任意名称的目录
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsLikeNames(File file, boolean isAllFiles, String... names)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllDirsLikeNames(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listDirsLikeNames(file, names);
        }
    }

    /**
     * 列出file目录下所有包含names列表中任意名称的目录，包括子目录
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称列表
     */
    public static void listAllDirsLikeNames(List<File> list, File file, String[] names)
    {
        File[] files = listDirsLikeNames(file, names);
        list.addAll(Arrays.asList(files));
        files = listDirs(file);
        for(File f : files)
        {
            listAllDirsLikeNames(list, f, names);
        }
    }

    /**
     * 返回名为pathname目录下所有包含names列表中任意名称的目录，不区分大小写
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 目录列表
     */
    public static File[] listDirsLikeNamesNoCase(String pathname, String... names)
    {
        return listDirsLikeNamesNoCase(of(pathname), names);
    }

    /**
     * 返回file目录下所有包含names列表中任意名称的目录，不区分大小写
     *
     * @param file  文件
     * @param names 名称列表
     * @return 目录列表
     */
    public static File[] listDirsLikeNamesNoCase(File file, String... names)
    {
        final List<String> list = getNamesList(names, true);
        return file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if(!pathname.isDirectory())
                {
                    return false;
                }

                String name = getLowerName(pathname);
                for(String s : list)
                {
                    if(name.contains(s))
                    {
                        return true;
                    }
                }

                return false;
            }
        });
    }

    /**
     * 返回名为pathname目录下所有包含names列表中任意名称的目录，不区分大小写
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsLikeNamesNoCase(String pathname, boolean isAllFiles,
                                                 String... names)
    {
        return listDirsLikeNamesNoCase(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有包含names列表中任意名称的目录，不区分大小写
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsLikeNamesNoCase(File file, boolean isAllFiles, String... names)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllDirsLikeNamesNoCase(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listDirsLikeNamesNoCase(file, names);
        }
    }

    /**
     * 列出file目录下所有包含names列表中任意名称的目录，忽略大小写，包括子目录
     *
     * @param list  文件列表，传出参数
     * @param names 名称列表
     * @param file  文件
     */
    public static void listAllDirsLikeNamesNoCase(List<File> list, File file, String[] names)
    {
        File[] files = listDirsLikeNamesNoCase(file, names);
        list.addAll(Arrays.asList(files));
        files = listDirs(file);
        for(File f : files)
        {
            listAllDirsLikeNamesNoCase(list, f, names);
        }
    }

    /**
     * 返回名为pathname目录下所有不包含names列表中任意名称的目录
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 目录列表
     */
    public static File[] listDirsDislikeNames(String pathname, String... names)
    {
        return listDirsDislikeNames(of(pathname), names);
    }

    /**
     * 返回file目录下所有不包含names列表中任意名称的目录
     *
     * @param file  文件
     * @param names 名称列表
     * @return 目录列表
     */
    public static File[] listDirsDislikeNames(File file, String... names)
    {
        final List<String> list = getNamesList(names);
        return file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if(!pathname.isDirectory())
                {
                    return false;
                }

                String name = getName(pathname);
                for(String s : list)
                {
                    if(name.contains(s))
                    {
                        return false;
                    }
                }

                return true;
            }
        });
    }

    /**
     * 返回名为pathname目录下所有不包含names列表中任意名称的目录
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsDislikeNames(String pathname, boolean isAllFiles, String... names)
    {
        return listDirsDislikeNames(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有不包含names列表中任意名称的目录
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsDislikeNames(File file, boolean isAllFiles, String... names)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllDirsDislikeNames(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listDirsDislikeNames(file, names);
        }
    }

    /**
     * 列出file目录下所有不包含names列表中任意名称的目录，包括子目录
     *
     * @param list  文件列表，传出参数
     * @param file  文件
     * @param names 名称列表
     */
    public static void listAllDirsDislikeNames(List<File> list, File file, String[] names)
    {
        File[] files = listDirsDislikeNames(file, names);
        list.addAll(Arrays.asList(files));
        files = listDirs(file);
        for(File f : files)
        {
            listAllDirsDislikeNames(list, f, names);
        }
    }

    /**
     * 返回名为pathname目录下所有不包含names列表中任意名称的目录，不区分大小写
     *
     * @param pathname 文件名
     * @param names    名称列表
     * @return 目录列表
     */
    public static File[] listDirsDislikeNamesNoCase(String pathname, String... names)
    {
        return listDirsDislikeNamesNoCase(of(pathname), names);
    }

    /**
     * 返回file目录下所有不包含names列表中任意名称的目录，不区分大小写
     *
     * @param file  文件
     * @param names 名称列表
     * @return 目录列表
     */
    public static File[] listDirsDislikeNamesNoCase(File file, String... names)
    {
        final List<String> list = getNamesList(names, true);
        return file.listFiles(new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if(!pathname.isDirectory())
                {
                    return false;
                }

                String name = getLowerName(pathname);
                for(String s : list)
                {
                    if(name.contains(s))
                    {
                        return false;
                    }
                }

                return true;
            }
        });
    }

    /**
     * 返回名为pathname目录下所有不包含names列表中任意名称的目录，不区分大小写
     *
     * @param pathname   文件名
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsDislikeNamesNoCase(String pathname, boolean isAllFiles,
                                                    String... names)
    {
        return listDirsDislikeNamesNoCase(of(pathname), isAllFiles, names);
    }

    /**
     * 返回file目录下所有不包含names列表中任意名称的目录，不区分大小写
     *
     * @param file       文件
     * @param isAllFiles <br>{@code true} 包含所有目录文件<br>{@code false} 仅当前目录
     * @param names      名称列表
     * @return 目录列表
     */
    public static File[] listDirsDislikeNamesNoCase(File file, boolean isAllFiles, String... names)
    {
        if(isAllFiles)
        {
            List<File> list = new ArrayList<>();
            listAllDirsDislikeNamesNoCase(list, file, names);
            return list.toArray(new File[list.size()]);
        }
        else
        {
            return listDirsDislikeNamesNoCase(file, names);
        }
    }

    /**
     * 列出file目录下所有不包含names列表中任意名称的目录，忽略大小写，包括子目录
     *
     * @param list  文件列表，传出参数
     * @param names 名称列表
     * @param file  文件
     */
    public static void listAllDirsDislikeNamesNoCase(List<File> list, File file, String[] names)
    {
        File[] files = listDirsDislikeNamesNoCase(file, names);
        list.addAll(Arrays.asList(files));
        files = listDirs(file);
        for(File f : files)
        {
            listAllDirsDislikeNamesNoCase(list, f, names);
        }
    }
}
