package com.ayvytr.easyandroidtest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ayvytr.easyandroid.sp.Sp;
import com.ayvytr.easyandroid.sp.SpManager;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;


/**
 * Created by Do on 2017/9/4.
 */

@RunWith(AndroidJUnit4.class)
public class SpTest
{
    @Test
    public void testSp()
    {
        Context context = InstrumentationRegistry.getTargetContext();
        Sp sp = SpManager.getDefault(context).getDefault();
        assertNotNull(sp);
        SpManager.getDefault(context).deleteDefault();

        //String
        String str = sp.getString("k");
        assertEquals(str, "");
        str = sp.getString("k", "default");
        assertEquals(str, "default");

        sp.putString("key", "value");
        str = sp.getString("k");
        assertEquals(str, "");
        str = sp.getString("key");
        assertEquals(str, "value");

        //bool
        boolean bool = sp.getBool("bool");
        assertEquals(bool, false);
        bool = sp.getBool("bool", true);
        assertEquals(bool, true);

        sp.putBool("bool", true);
        bool = sp.getBool("bool", true);
        assertEquals(bool, true);

        //int
        int i = sp.getInt("int");
        assertEquals(i, 0);
        i = sp.getInt("int", 20);
        assertEquals(i, 20);

        sp.putInt("int", 10);
        i = sp.getInt("int");
        assertEquals(i, 10);

        //long
        long l = sp.getLong("long");
        assertEquals(l, 0);
        l = sp.getLong("long", 10);
        assertEquals(l, 10);

        sp.putLong("long", 20);
        l = sp.getLong("long");
        assertEquals(l, 20);

        //float
        float f = sp.getFloat("float");
        assertEquals(f, 0F, 0.00001);
        f = sp.getFloat("float", 20);
        assertEquals(f, 20F, 0.00001);

        sp.putFloat("float", 10);
        f = sp.getFloat("float");
        assertEquals(f, 10F, 0.00001);

        //String Set
        Set<String> stringSet = sp.getStringSet("string-set");
        assertNull(stringSet);

        Set<String> defStringSet = new HashSet<>();
        defStringSet.add("a");
        defStringSet.add("b");
        stringSet = sp.getStringSet("string-set", defStringSet);

        boolean isEqual = isEqualsSet(defStringSet, stringSet);
        assertTrue(isEqual);

        sp.putStringSet("string-set", defStringSet);
        stringSet = sp.getStringSet("string-set");
        isEqual = isEqualsSet(defStringSet, stringSet);
        assertTrue(isEqual);

        Sp newSp = SpManager.getDefault(context).getSp("new-name");
        newSp.putString("str", "value");
    }

    private boolean isEqualsSet(Set<String> defStringSet, Set<String> stringSet)
    {
        boolean isEqual;
        if(defStringSet == stringSet)
        {
            isEqual = true;
        }
        else if(defStringSet.size() == stringSet.size())
        {
            Iterator<String> iterator = defStringSet.iterator();
            Iterator<String> iterator1 = stringSet.iterator();
            while(iterator.hasNext() && iterator1.hasNext())
            {
                if(!iterator.next().equals(iterator1.next()))
                {
                    break;
                }
            }

            isEqual = true;
        }
        else
        {
            isEqual = false;
        }

        return isEqual;
    }
}
