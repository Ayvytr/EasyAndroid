package com.ayvytr.easyandroidlibrary;

import com.ayvytr.easyandroidlibrary.tools.FileTool;

import org.junit.Test;

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
}
