package com.ayvytr.easyandroidlibrary.tools.withcontext;

import com.ayvytr.easyandroidlibrary.Easy;

import java.io.File;

/**
 * Desc:
 * Date: 2017/4/6
 *
 * @author davidwang
 */

public class Env
{
    public static File getFilesDir()
    {
        return Easy.getContext().getFilesDir();
    }

    public static File getCacheDir()
    {
        return Easy.getContext().getCacheDir();
    }

}
