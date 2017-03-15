package com.ayvytr.easyandroidlibrary.tools;

import com.ayvytr.easyandroidlibrary.exception.UnsupportedInitializationException;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/8/11
 *     desc  : 文件相关工具类
 * </pre>
 */
public class FileTool
{

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


//    /**
//     * 复制或移动目录
//     *
//     * @param srcDirPath  源目录路径
//     * @param destDirPath 目标目录路径
//     * @param isMove      是否移动
//     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
//     */
//    private static boolean copyOrMoveDir(String srcDirPath, String destDirPath, boolean isMove)
//    {
//        return copyOrMoveDir(fromName(srcDirPath), fromName(destDirPath), isMove);
//    }
//
//    /**
//     * 复制或移动目录
//     *
//     * @param srcDir  源目录
//     * @param destDir 目标目录
//     * @param isMove  是否移动
//     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
//     */
//    private static boolean copyOrMoveDir(File srcDir, File destDir, boolean isMove)
//    {
//        if(srcDir == null || destDir == null) return false;
//        // 如果目标目录在源目录中则返回false，看不懂的话好好想想递归怎么结束
//        // srcPath : F:\\MyGithub\\AndroidUtilCode\\utilcode\\src\\test\\res
//        // destPath: F:\\MyGithub\\AndroidUtilCode\\utilcode\\src\\test\\res1
//        // 为防止以上这种情况出现出现误判，须分别在后面加个路径分隔符
//        String srcPath = srcDir.getPath() + File.separator;
//        String destPath = destDir.getPath() + File.separator;
//        if(destPath.contains(srcPath)) return false;
//        // 源文件不存在或者不是目录则返回false
//        if(!srcDir.exists() || !srcDir.isDirectory()) return false;
//        // 目标目录不存在返回false
//        if(!createOrExistsDir(destDir)) return false;
//        File[] files = srcDir.listFiles();
//        for(File file : files)
//        {
//            File oneDestFile = new File(destPath + file.getName());
//            if(file.isFile())
//            {
//                // 如果操作失败返回false
//                if(!copyOrMoveFile(file, oneDestFile, isMove)) return false;
//            }
//            else if(file.isDirectory())
//            {
//                // 如果操作失败返回false
//                if(!copyOrMoveDir(file, oneDestFile, isMove)) return false;
//            }
//        }
//        return !isMove || deleteDir(srcDir);
//    }
//
//    /**
//     * 复制或移动文件
//     *
//     * @param srcFilePath  源文件路径
//     * @param destFilePath 目标文件路径
//     * @param isMove       是否移动
//     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
//     */
//    private static boolean copyOrMoveFile(String srcFilePath, String destFilePath, boolean isMove)
//    {
//        return copyOrMoveFile(fromName(srcFilePath), fromName(destFilePath), isMove);
//    }
//
//    /**
//     * 复制或移动文件
//     *
//     * @param srcFile  源文件
//     * @param destFile 目标文件
//     * @param isMove   是否移动
//     * @return {@code true}: 复制或移动成功<br>{@code false}: 复制或移动失败
//     */
//    private static boolean copyOrMoveFile(File srcFile, File destFile, boolean isMove)
//    {
//        if(srcFile == null || destFile == null) return false;
//        // 源文件不存在或者不是文件则返回false
//        if(!srcFile.exists() || !srcFile.isFile()) return false;
//        // 目标文件存在且是文件则返回false
//        if(destFile.exists() && destFile.isFile()) return false;
//        // 目标目录不存在返回false
//        if(!createOrExistsDir(destFile.getParentFile())) return false;
//        try
//        {
//            return writeFileFromIS(destFile, new FileInputStream(srcFile), false)
//                    && !(isMove && !deleteFile(srcFile));
//        } catch(FileNotFoundException e)
//        {
//            e.printStackTrace();
//            return false;
//        }
//    }

//    /**
//     * 复制目录
//     *
//     * @param srcDirPath  源目录路径
//     * @param destDirPath 目标目录路径
//     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
//     */
//    public static boolean copyDir(String srcDirPath, String destDirPath)
//    {
//        return copyDir(fromName(srcDirPath), fromName(destDirPath));
//    }
//
//    /**
//     * 复制目录
//     *
//     * @param srcDir  源目录
//     * @param destDir 目标目录
//     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
//     */
//    public static boolean copyDir(File srcDir, File destDir)
//    {
//        return copyOrMoveDir(srcDir, destDir, false);
//    }
//
//    /**
//     * 复制文件
//     *
//     * @param srcFilePath  源文件路径
//     * @param destFilePath 目标文件路径
//     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
//     */
//    public static boolean copyFile(String srcFilePath, String destFilePath)
//    {
//        return copyFile(fromName(srcFilePath), fromName(destFilePath));
//    }
//
//    /**
//     * 复制文件
//     *
//     * @param srcFile  源文件
//     * @param destFile 目标文件
//     * @return {@code true}: 复制成功<br>{@code false}: 复制失败
//     */
//    public static boolean copyFile(File srcFile, File destFile)
//    {
//        return copyOrMoveFile(srcFile, destFile, false);
//    }
//
//    /**
//     * 移动目录
//     *
//     * @param srcDirPath  源目录路径
//     * @param destDirPath 目标目录路径
//     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
//     */
//    public static boolean moveDir(String srcDirPath, String destDirPath)
//    {
//        return moveDir(fromName(srcDirPath), fromName(destDirPath));
//    }
//
//    /**
//     * 移动目录
//     *
//     * @param srcDir  源目录
//     * @param destDir 目标目录
//     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
//     */
//    public static boolean moveDir(File srcDir, File destDir)
//    {
//        return copyOrMoveDir(srcDir, destDir, true);
//    }
//
//    /**
//     * 移动文件
//     *
//     * @param srcFilePath  源文件路径
//     * @param destFilePath 目标文件路径
//     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
//     */
//    public static boolean moveFile(String srcFilePath, String destFilePath)
//    {
//        return moveFile(fromName(srcFilePath), fromName(destFilePath));
//    }
//
//    /**
//     * 移动文件
//     *
//     * @param srcFile  源文件
//     * @param destFile 目标文件
//     * @return {@code true}: 移动成功<br>{@code false}: 移动失败
//     */
//    public static boolean moveFile(File srcFile, File destFile)
//    {
//        return copyOrMoveFile(srcFile, destFile, true);
//    }
//
//    /**
//     * 删除目录
//     *
//     * @param dirPath 目录路径
//     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
//     */
//    public static boolean deleteDir(String dirPath)
//    {
//        return deleteDir(fromName(dirPath));
//    }
//
//    /**
//     * 删除目录
//     *
//     * @param dir 目录
//     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
//     */
//    public static boolean deleteDir(File dir)
//    {
//        if(dir == null) return false;
//        // 目录不存在返回true
//        if(!dir.exists()) return true;
//        // 不是目录返回false
//        if(!dir.isDirectory()) return false;
//        // 现在文件存在且是文件夹
//        File[] files = dir.listFiles();
//        if(files != null && files.length != 0)
//        {
//            for(File file : files)
//            {
//                if(file.isFile())
//                {
//                    if(!deleteFile(file)) return false;
//                }
//                else if(file.isDirectory())
//                {
//                    if(!deleteDir(file)) return false;
//                }
//            }
//        }
//        return dir.delete();
//    }
//
//    /**
//     * 删除文件
//     *
//     * @param srcFilePath 文件路径
//     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
//     */
//    public static boolean deleteFile(String srcFilePath)
//    {
//        return deleteFile(fromName(srcFilePath));
//    }
//
//    /**
//     * 删除文件
//     *
//     * @param file 文件
//     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
//     */
//    public static boolean deleteFile(File file)
//    {
//        return file != null && (!file.exists() || file.isFile() && file.delete());
//    }
//
//    /**
//     * 删除目录下的所有文件
//     *
//     * @param dirPath 目录路径
//     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
//     */
//    public static boolean deleteFilesInDir(String dirPath)
//    {
//        return deleteFilesInDir(fromName(dirPath));
//    }
//
//    /**
//     * 删除目录下的所有文件
//     *
//     * @param dir 目录
//     * @return {@code true}: 删除成功<br>{@code false}: 删除失败
//     */
//    public static boolean deleteFilesInDir(File dir)
//    {
//        if(dir == null) return false;
//        // 目录不存在返回true
//        if(!dir.exists()) return true;
//        // 不是目录返回false
//        if(!dir.isDirectory()) return false;
//        // 现在文件存在且是文件夹
//        File[] files = dir.listFiles();
//        if(files != null && files.length != 0)
//        {
//            for(File file : files)
//            {
//                if(file.isFile())
//                {
//                    if(!deleteFile(file)) return false;
//                }
//                else if(file.isDirectory())
//                {
//                    if(!deleteDir(file)) return false;
//                }
//            }
//        }
//        return true;
//    }

    /**
     * 获取目录下所有文件
     *
     * @param dirPath     目录路径
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(String dirPath, boolean isRecursive)
    {
        return listFilesInDir(fromName(dirPath), isRecursive);
    }

    /**
     * 获取目录下所有文件
     *
     * @param dir         目录
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(File dir, boolean isRecursive)
    {
        if(!isDir(dir)) return null;
        if(isRecursive) return listFilesInDir(dir);
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if(files != null && files.length != 0)
        {
            Collections.addAll(list, files);
        }
        return list;
    }

    /**
     * 获取目录下所有文件包括子目录
     *
     * @param dirPath 目录路径
     * @return 文件链表
     */
    public static List<File> listFilesInDir(String dirPath)
    {
        return listFilesInDir(fromName(dirPath));
    }

    /**
     * 获取目录下所有文件包括子目录
     *
     * @param dir 目录
     * @return 文件链表
     */
    public static List<File> listFilesInDir(File dir)
    {
        if(!isDir(dir)) return null;
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if(files != null && files.length != 0)
        {
            for(File file : files)
            {
                list.add(file);
                if(file.isDirectory())
                {
                    list.addAll(listFilesInDir(file));
                }
            }
        }
        return list;
    }

    /**
     * 获取目录下所有后缀名为suffix的文件
     * <p>大小写忽略</p>
     *
     * @param dirPath     目录路径
     * @param suffix      后缀名
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, String suffix,
                                                      boolean isRecursive)
    {
        return listFilesInDirWithFilter(fromName(dirPath), suffix, isRecursive);
    }

    /**
     * 获取目录下所有后缀名为suffix的文件
     * <p>大小写忽略</p>
     *
     * @param dir         目录
     * @param suffix      后缀名
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(File dir, String suffix, boolean isRecursive)
    {
        if(isRecursive) return listFilesInDirWithFilter(dir, suffix);
        if(dir == null || !isDir(dir)) return null;
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if(files != null && files.length != 0)
        {
            for(File file : files)
            {
                if(file.getName().toUpperCase().endsWith(suffix.toUpperCase()))
                {
                    list.add(file);
                }
            }
        }
        return list;
    }

    /**
     * 获取目录下所有后缀名为suffix的文件包括子目录
     * <p>大小写忽略</p>
     *
     * @param dirPath 目录路径
     * @param suffix  后缀名
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, String suffix)
    {
        return listFilesInDirWithFilter(fromName(dirPath), suffix);
    }

    /**
     * 获取目录下所有后缀名为suffix的文件包括子目录
     * <p>大小写忽略</p>
     *
     * @param dir    目录
     * @param suffix 后缀名
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(File dir, String suffix)
    {
        if(dir == null || !isDir(dir)) return null;
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if(files != null && files.length != 0)
        {
            for(File file : files)
            {
                if(file.getName().toUpperCase().endsWith(suffix.toUpperCase()))
                {
                    list.add(file);
                }
                if(file.isDirectory())
                {
                    list.addAll(listFilesInDirWithFilter(file, suffix));
                }
            }
        }
        return list;
    }

    /**
     * 获取目录下所有符合filter的文件
     *
     * @param dirPath     目录路径
     * @param filter      过滤器
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, FilenameFilter filter,
                                                      boolean isRecursive)
    {
        return listFilesInDirWithFilter(fromName(dirPath), filter, isRecursive);
    }

    /**
     * 获取目录下所有符合filter的文件
     *
     * @param dir         目录
     * @param filter      过滤器
     * @param isRecursive 是否递归进子目录
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(File dir, FilenameFilter filter,
                                                      boolean isRecursive)
    {
        if(isRecursive) return listFilesInDirWithFilter(dir, filter);
        if(dir == null || !isDir(dir)) return null;
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if(files != null && files.length != 0)
        {
            for(File file : files)
            {
                if(filter.accept(file.getParentFile(), file.getName()))
                {
                    list.add(file);
                }
            }
        }
        return list;
    }

    /**
     * 获取目录下所有符合filter的文件包括子目录
     *
     * @param dirPath 目录路径
     * @param filter  过滤器
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(String dirPath, FilenameFilter filter)
    {
        return listFilesInDirWithFilter(fromName(dirPath), filter);
    }

    /**
     * 获取目录下所有符合filter的文件包括子目录
     *
     * @param dir    目录
     * @param filter 过滤器
     * @return 文件链表
     */
    public static List<File> listFilesInDirWithFilter(File dir, FilenameFilter filter)
    {
        if(dir == null || !isDir(dir)) return null;
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if(files != null && files.length != 0)
        {
            for(File file : files)
            {
                if(filter.accept(file.getParentFile(), file.getName()))
                {
                    list.add(file);
                }
                if(file.isDirectory())
                {
                    list.addAll(listFilesInDirWithFilter(file, filter));
                }
            }
        }
        return list;
    }

    /**
     * 获取目录下指定文件名的文件包括子目录
     * <p>大小写忽略</p>
     *
     * @param dirPath  目录路径
     * @param fileName 文件名
     * @return 文件链表
     */
    public static List<File> searchFileInDir(String dirPath, String fileName)
    {
        return searchFileInDir(fromName(dirPath), fileName);
    }

    /**
     * 获取目录下指定文件名的文件包括子目录
     * <p>大小写忽略</p>
     *
     * @param dir      目录
     * @param fileName 文件名
     * @return 文件链表
     */
    public static List<File> searchFileInDir(File dir, String fileName)
    {
        if(dir == null || !isDir(dir)) return null;
        List<File> list = new ArrayList<>();
        File[] files = dir.listFiles();
        if(files != null && files.length != 0)
        {
            for(File file : files)
            {
                if(file.getName().toUpperCase().equals(fileName.toUpperCase()))
                {
                    list.add(file);
                }
                if(file.isDirectory())
                {
                    list.addAll(searchFileInDir(file, fileName));
                }
            }
        }
        return list;
    }

    /**
     * 写入content到file，不处理filepath，content异常
     *
     * @param filepath 文件路径
     * @param content  文件内容
     * @param isAppend 是不是追加模式
     */
    public void writeFile(String filepath, String content, boolean isAppend)
    {
        writeFile(new File(filepath), content, isAppend);
    }

    /**
     * 写入content到file，不处理file，content异常
     *
     * @param file     File
     * @param content  文件内容
     * @param isAppend 是不是追加模式
     */
    public void writeFile(File file, String content, boolean isAppend)
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
     * @return
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
     * @return
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

}