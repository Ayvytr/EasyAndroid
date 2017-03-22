package com.ayvytr.easyandroidlibrary;

import com.ayvytr.easyandroidlibrary.tools.FileTool;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
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
        File[] files;
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

    @Test
    public void testExtension()
    {
        String extension = FileTool.getExtension("build.gradle");
        assertEquals(extension, "gradle");

        assertEquals(FileTool.isTyped("build.gradle"), true);
        assertEquals(FileTool.isTyped("gradle"), false);

        assertEquals(FileTool.getExtension("gradle"), "");

        assertEquals(FileTool.getExtension("aaaaa"), "");
        assertEquals(FileTool.getExtension("aaaaa.aa"), "aa");

        extension = FileTool.getExtension("......aaa");
        assertEquals(extension, "");

        extension = FileTool.getExtension(".aaa.aaa");
        assertEquals(extension, "aaa");


    }

    @Test
    public void testListFilesLikeNames()
    {
        File[] reads = FileTool.listFilesWithNames(".", "read");
        assertEquals(0, reads.length);

        //README.md README_CN.md
        reads = FileTool.listFilesLikeNames(".", "READ");
        assertEquals(2, reads.length);

        // .gradle gradle build.gradle gradle.properties gradlew gradlew.bat settings.gradle
        File[] gradles = FileTool.listFilesLikeNames(new File("."), "gradle");
        assertEquals(7, gradles.length);

        gradles = FileTool.listFilesLikeNames(new File("."), "GRADLE");
        assertEquals(0, gradles.length);
    }

    @Test
    public void testListFilesLikeNamesNoCase()
    {
        File[] reads = FileTool.listFilesLikeNamesNoCase(".", "read");
        assertEquals(2, reads.length);

        //README.md README_CN.md
        reads = FileTool.listFilesLikeNamesNoCase(".", "ReaD");
        assertEquals(2, reads.length);

        // .gradle gradle build.gradle gradle.properties gradlew gradlew.bat settings.gradle
        File[] gradles = FileTool.listFilesLikeNamesNoCase(new File("."), "GRADL");
        assertEquals(7, gradles.length);

        gradles = FileTool.listFilesLikeNamesNoCase(new File("."), "gRADLE");
        assertEquals(7, gradles.length);

        File[] files = FileTool.listFilesLikeNamesNoCase(new File("."), "gRADLE", "READ");
        assertEquals(9, files.length);
    }

    @Test
    public void testListFilesDislikeNames()
    {
        File[] allFiles = new File(".").listFiles();

        File[] reads = FileTool.listFilesDislikeNames(".", "read");
        assertArrayEquals(allFiles, reads);

        reads = FileTool.listFilesDislikeNames(".", "READ");
        assertEquals(reads.length, allFiles.length - 2);

        reads = FileTool.listFilesDislikeNames(".", "gradle");
        assertEquals(reads.length, allFiles.length - 7);

    }

    @Test
    public void testListFilesDislikeNamesNoCase()
    {
        File[] allFiles = new File(".").listFiles();

        File[] reads = FileTool.listFilesDislikeNamesNoCase(".", "rx");
        assertArrayEquals(allFiles, reads);

        reads = FileTool.listFilesDislikeNamesNoCase(".", "reaD");
        assertEquals(reads.length, allFiles.length - 2);

        reads = FileTool.listFilesDislikeNamesNoCase(".", "GRAD");
        assertEquals(reads.length, allFiles.length - 7);

        reads = FileTool.listFilesDislikeNamesNoCase(".", "GRAD", "rEAd");
        assertEquals(reads.length, allFiles.length - 9);
    }

    @Test
    public void testListFilesWithExtension()
    {
        File[] mds = FileTool.listFilesWithExtension(".", "md");
        assertEquals(mds.length, 2);

        File[] gradles = FileTool.listFilesWithExtension(".", "GRADLE");
        assertEquals(gradles.length, 2);

        gradles = FileTool.listFilesWithExtension(".", ".GRADLE");
        assertEquals(gradles.length, 2);

        File[] bats = FileTool.listFilesWithExtension(".", "bat");
        assertEquals(bats.length, 1);

        bats = FileTool.listFilesWithExtension(".", ".bat");
        assertEquals(bats.length, 1);
    }

    @Test
    public void testListAllFiles()
    {
        File[] listFiles;
        listFiles = FileTool.listFiles("gradle", true);
        assertEquals(listFiles.length, 3);
    }

    @Test
    public void testListFilesByFileFilter()
    {
    }
}

