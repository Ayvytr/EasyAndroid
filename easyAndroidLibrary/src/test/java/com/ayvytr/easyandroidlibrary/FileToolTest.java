package com.ayvytr.easyandroidlibrary;

import com.ayvytr.easyandroidlibrary.tools.FileTool;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;

import static com.ayvytr.easyandroidlibrary.tools.FileTool.listFilesLikeNames;
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
        reads = listFilesLikeNames(".", "READ");
        assertEquals(2, reads.length);

        // .gradle gradle build.gradle gradle.properties gradlew gradlew.bat settings.gradle
        File[] gradles = listFilesLikeNames(new File("."), "gradle");
        assertEquals(7, gradles.length);

        gradles = listFilesLikeNames(new File("."), "GRADLE");
        assertEquals(0, gradles.length);

        gradles = FileTool.listFilesLikeNames(".", true, "gradle");
        gradles = FileTool.listFilesLikeNames(".", true, ".gradle");
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

        gradles = FileTool.listFilesLikeNamesNoCase(".", true, "GRADle");
        gradles = FileTool.listFilesLikeNamesNoCase(".", true, "xml");
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

        String pathname = "app\\src\\main\\res";
        File[] mipmaps = FileTool.listFilesDislikeNames(pathname, true, "mipmap");
        File[] launchers = FileTool.listFilesDislikeNames(pathname, true, "launcher");
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

        String pathname = "app\\src\\main\\res";
        File[] mipmaps = FileTool.listFilesDislikeNamesNoCase(pathname, true, "MIPmAP");
        File[] launchers = FileTool.listFilesDislikeNamesNoCase(pathname, true, "_LAUNCHER");
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

        File[] mds1 = FileTool.listFilesWithExtension(".", true, "md");
        assertEquals(mds1.length, 2);

        File[] apps = FileTool.listFilesWithExtension("app/src", true, ".java");
        assertEquals(apps.length, 3);
    }

    @Test
    public void testListFilesWithoutExtension()
    {
        File[] files = FileTool.listFilesWithoutExtension("app/src", ".java");
        files = FileTool.listFilesWithoutExtension("app/src", true, ".java");
    }

    @Test
    public void testListAllFiles()
    {
        File[] listFiles;
        listFiles = FileTool.listFiles("gradle", true);
        assertEquals(listFiles.length, 3);
    }

    @Test
    public void testListFilesAll()
    {
        File[] files = FileTool.listFiles("app", true);
        files = FileTool.listFiles("app", false);
        assertEquals(files.length, 7);
        files = FileTool.listFiles(".idea", true);
        files = FileTool.listFiles("easyandroidlibrary", false);
        assertEquals(files.length, 6);
    }


    @Test
    public void testListFilesWithNames()
    {
        File[] files = FileTool.listFilesWithNames(".", true, "build.gradle");
        assertEquals(files.length, 3);
        files = FileTool.listFilesWithNames("app", true, "ic_launcher.png");
        assertEquals(files.length, 10);
    }

    @Test
    public void testListFilesWithNamesNoCase()
    {
        File[] files = FileTool.listFilesWithNamesNoCase(".", true, "BUILD.gradle");
        assertEquals(files.length, 3);
        files = FileTool.listFilesWithNamesNoCase("app", true, "ic_launcher.PNg");
        assertEquals(files.length, 10);
    }

    @Test
    public void testListFilesWithoutNames()
    {
        File[] files = FileTool.listFilesWithoutNames("app", true, "app");
        files = FileTool.listFilesWithoutNames(".", true, "build.gradle");
        files = FileTool.listFilesWithoutNames(".", true, "build.gradle");

        String pathname = "app\\src\\main\\res";
        files = FileTool.listFilesWithoutNames(pathname, true, "ic_launcher.png");
        assertEquals(files.length, FileTool.listFiles(pathname, true).length - 5);
    }

    @Test
    public void testListFilesWithoutNamesNoCase()
    {
        File[] files = FileTool.listFilesWithoutNames("app", true, "app");

        String pathname = "app\\src\\main\\res";
        files = FileTool.listFilesWithoutNamesNoCase(pathname, true, "IC_LAUNcher.png");
        assertEquals(files.length, FileTool.listFiles(pathname, true).length - 5);
    }

    @Test
    public void testListDirs()
    {
        File[] dirs = FileTool.listDirs(".");
        dirs = FileTool.listDirs("app/src", true);
    }

    @Test
    public void testListDirsWithNames()
    {
        File[] dirs = FileTool.listDirsWithNames(".", "test");
        assertEquals(dirs.length, 0);
        dirs = FileTool.listDirsWithNames(".", "app");
        assertEquals(dirs.length, 1);
        dirs = FileTool.listDirsWithNames(".", "Gradle");
        assertEquals(dirs.length, 0);
    }

    @Test
    public void testListAllDirsWithNames()
    {
        File[] dirs = FileTool.listDirsWithNames(".", true, "test");
        dirs = FileTool.listDirsWithNames(".", true, "app");
        dirs = FileTool.listDirsWithNames(".", true, "src");
        assertEquals(dirs.length, 2);
    }

    @Test
    public void testListAllDirsWithNamesNoCase()
    {
        File[] dirs = FileTool.listDirsWithNamesNoCase(".", true, "TEst");
        dirs = FileTool.listDirsWithNamesNoCase(".", true, "apP");
        dirs = FileTool.listDirsWithNamesNoCase(".", true, "SRC");
        assertEquals(dirs.length, 2);
    }

    @Test
    public void testListDirsWithoutNames()
    {
        File[] dirs = FileTool.listDirsWithoutNames(".", "app");
        dirs = FileTool.listDirsWithoutNames(".", "AA");
        dirs = FileTool.listDirsWithoutNames(".", "gradle", "app", ".gradle");
    }

    @Test
    public void testListAllDirsWithoutNames()
    {
        File[] dirs = FileTool.listDirsWithoutNames(".", true, "app");
        dirs = FileTool.listDirsWithoutNames(".", true, "AA");
        dirs = FileTool.listDirsWithoutNames(".", true, "gradle", "app", ".gradle");
    }

    @Test
    public void testListDirsWithoutNamesNoCase()
    {
        File[] dirs = FileTool.listDirsWithoutNamesNoCase("app/src", "main", "test");
        dirs = FileTool.listDirsWithoutNamesNoCase(".", "AAA");
        dirs = FileTool.listDirsWithoutNamesNoCase(".", "GRADLE", "APP", ".GrADLE");
    }

    @Test
    public void testListAllDirsWithoutNamesNoCase()
    {
        File[] dirs = FileTool
                .listDirsWithoutNamesNoCase("app/src/main/java", true, "src", "BUild");
        dirs = FileTool.listDirsWithoutNamesNoCase("app", true, "AA", "Main");
        dirs = FileTool.listDirsWithoutNamesNoCase(".", true, "gradle", "aPP", ".gradle");
    }

    @Test
    public void testListDirsLikeNames()
    {
        File[] dirs = FileTool.listDirsLikeNames(".", "gradle");
        assertEquals(dirs.length, 2);
        dirs = FileTool.listDirsLikeNames(".", "d");
        assertEquals(dirs.length, 5);
    }

    @Test
    public void testListDirsLikeNamesNoCase()
    {
        File[] dirs = FileTool.listDirsLikeNamesNoCase(".", "GRADLE");
        assertEquals(dirs.length, 2);
        dirs = FileTool.listDirsLikeNamesNoCase(".", "D", "id");
        assertEquals(dirs.length, 5);
    }

    @Test
    public void testListAllDirsLikeNames()
    {
        File[] dirs = FileTool.listDirsLikeNames("app/src", true, "main");
        dirs = FileTool.listDirsLikeNames("app/src", true, "tes");
        dirs = FileTool.listDirsLikeNames("app/src", true, "and");
    }

    @Test
    public void testListAllDirsLikeNamesNoCase()
    {
        File[] dirs = FileTool.listDirsLikeNamesNoCase("app/src", true, "MAIN");
        dirs = FileTool.listDirsLikeNamesNoCase("app/src", true, "TesT");
        dirs = FileTool.listDirsLikeNamesNoCase("app/src", true, "AND");
    }

    @Test
    public void testListDirsDislikeNames()
    {
        File[] dirs = FileTool.listDirsDislikeNames(".", "gradle");
        assertEquals(dirs.length, 5);
        dirs = FileTool.listDirsDislikeNames(".", "d");
        assertEquals(dirs.length, 2);
    }

    @Test
    public void testListDirsDislikeNamesNoCase()
    {
        File[] dirs = FileTool.listDirsDislikeNamesNoCase(".", "GRADLE");
        assertEquals(dirs.length, 5);
        dirs = FileTool.listDirsDislikeNamesNoCase(".", "D", "id");
        assertEquals(dirs.length, 2);
    }

    @Test
    public void testListAllDirsDislikeNames()
    {
        File[] dirs = FileTool.listDirsDislikeNames("app/src", true, "main");
        dirs = FileTool.listDirsDislikeNames("app/src", true, "tes");
        dirs = FileTool.listDirsDislikeNames("app/src", true, "and");
    }

    @Test
    public void testListAllDirsDislikeNamesNoCase()
    {
        File[] dirs = FileTool.listDirsDislikeNamesNoCase("app/src", true, "MAIN");
        dirs = FileTool.listDirsDislikeNamesNoCase("app/src", true, "TesT");
        dirs = FileTool.listDirsDislikeNamesNoCase("app/src", true, "AND");
    } 
}

