package com.ayvytr.easyandroidlibrary;

import com.ayvytr.easyandroidlibrary.tools.FileTool;

import org.junit.Test;

import java.io.File;

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
    public void testListFilesWithNames()
    {
        File[] reads = FileTool.listFilesWithNames(".", "read");
        assertEquals(0, reads.length);

        //README.md README_CN.md
        reads = FileTool.listFilesWithNames(".", "READ");
        assertEquals(2, reads.length);

        // .gradle gradle build.gradle gradle.properties gradlew gradlew.bat settings.gradle
        File[] gradles = FileTool.listFilesWithNames(new File("."), "gradle");
        assertEquals(7, gradles.length);

        gradles = FileTool.listFilesWithNames(new File("."), "GRADLE");
        assertEquals(0, gradles.length);
    }

    @Test
    public void testListFilesWithNamesNoCase()
    {
        File[] reads = FileTool.listFilesWithNamesNoCase(".", "read");
        assertEquals(2, reads.length);

        //README.md README_CN.md
        reads = FileTool.listFilesWithNamesNoCase(".", "ReaD");
        assertEquals(2, reads.length);

        // .gradle gradle build.gradle gradle.properties gradlew gradlew.bat settings.gradle
        File[] gradles = FileTool.listFilesWithNamesNoCase(new File("."), "GRADL");
        assertEquals(7, gradles.length);

        gradles = FileTool.listFilesWithNamesNoCase(new File("."), "gRADLE");
        assertEquals(7, gradles.length);

        File[] files = FileTool.listFilesWithNamesNoCase(new File("."), "gRADLE", "READ");
        assertEquals(9, files.length);
    }

    @Test
    public void testListFilesWithoutNames()
    {
        File[] allFiles = new File(".").listFiles();

        File[] reads = FileTool.listFilesWithoutNames(".", "read");
        assertArrayEquals(allFiles, reads);

        reads = FileTool.listFilesWithoutNames(".", "READ");
        assertEquals(reads.length, allFiles.length - 2);

        reads = FileTool.listFilesWithoutNames(".", "gradle");
        assertEquals(reads.length, allFiles.length - 7);

    }

    @Test
    public void testListFilesWithoutNamesNoCase()
    {
        File[] allFiles = new File(".").listFiles();

        File[] reads = FileTool.listFilesWithoutNamesNoCase(".", "rx");
        assertArrayEquals(allFiles, reads);

        reads = FileTool.listFilesWithoutNamesNoCase(".", "reaD");
        assertEquals(reads.length, allFiles.length - 2);

        reads = FileTool.listFilesWithoutNamesNoCase(".", "GRAD");
        assertEquals(reads.length, allFiles.length - 7);

        reads = FileTool.listFilesWithoutNamesNoCase(".", "GRAD", "rEAd");
        assertEquals(reads.length, allFiles.length - 9);
    }

    @Test
    public void testListFilesWithExtension()
    {
        File[] mds = FileTool.listFilesWithExtension(".", "md");
        assertEquals(mds.length, 3);

        File[] gradles = FileTool.listFilesWithExtension(".", "GRADLE");
        assertEquals(gradles.length, 2);

        File[] bats = FileTool.listFilesWithExtension(".", "bat");
        assertEquals(bats.length, 1);
    }
}
