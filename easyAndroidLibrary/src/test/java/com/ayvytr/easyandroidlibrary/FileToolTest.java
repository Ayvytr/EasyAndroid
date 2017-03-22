package com.ayvytr.easyandroidlibrary;

import com.ayvytr.easyandroidlibrary.tools.FileTool;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author davidwang
 * @date 2017/3/16
 */

public class FileToolTest
{
    String content = "hello, this is file content.你好，这些是文件内容";
    String fileName = "hello.txt";

    @Test
    public void testWriteRead()
    {
        FileTool.write(fileName, content, false);
        String s = FileTool.read(fileName);
        assertEquals(content, s);

        FileTool.writeFile(fileName, content, false);
        s = FileTool.readFile(fileName);
        assertEquals(content, s);
    }

    /**
     * 这个跑之前，先跑 testWriteRead，保证已有文件内容
     *
     * @see FileToolTest#testWriteRead()
     */
    @Test
    public void testWriteAppend()
    {
        FileTool.write(fileName, content, true);
        String s = FileTool.read(fileName);
        assertEquals(content + content, s);

        s = FileTool.readFile(fileName);
        assertEquals(content + content, s);
    }

    @Test
    public void testFile()
    {
        File file = FileTool.fromName(fileName);
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile());
        try
        {
            System.out.println(file.getCanonicalFile());
            System.out.println(file.getCanonicalPath());
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testListFiles()
    {
        File[] files = FileTool.listFiles(("."));
        String[] names = FileTool.listFilesNames(".");
        String[] paths = FileTool.listFilesPaths(".");

        files = FileTool.listFiles(".", new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.compareToIgnoreCase(name) == 0;
            }
        });

        files = FileTool.listFiles(".", new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                return pathname.getName().compareToIgnoreCase("readme.md") == 0;
            }
        });

        files = FileTool.listFilesWithNames(".", "build.gradle");
        assertEquals(files.length, 1);

        files = FileTool.listFilesWithNamesNoCase(".", "BUILD.GRADLE");
        assertEquals(files.length, 1);

        files = FileTool.listFilesWithoutNames(".", "build.gradle");
        assertEquals(files.length, FileTool.listFiles(".").length - 1);

        files = FileTool.listFilesWithoutNamesNoCase(".", "BUILD.gradle");
        assertEquals(files.length, FileTool.listFiles(".").length - 1);

    }
}
