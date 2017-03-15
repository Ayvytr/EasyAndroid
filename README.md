[![GitHub release](https://img.shields.io/github/release/Ayvytr/AndroidEasyDeveloper.svg)](https://github.com/Ayvytr/AndroidEasyDeveloper/releases)
[![](https://jitpack.io/v/Ayvytr/AndroidEasyDeveloper.svg)](https://jitpack.io/#Ayvytr/AndroidEasyDeveloper)
<a href="http://www.methodscount.com/?lib=com.github.Ayvytr%3AAndroidEasyDeveloper%3A1.0.0"><img src="https://img.shields.io/badge/Methods count-core: 15 | deps: 19163-e91e63.svg"/></a>
<a href="http://www.methodscount.com/?lib=com.github.Ayvytr%3AAndroidEasyDeveloper%3A1.0.0"><img src="https://img.shields.io/badge/Size-20 KB-e91e63.svg"/></a>
<a href="http://www.methodscount.com/?lib=com.github.Ayvytr%3AAndroidEasyDeveloper%3A1.0.0"><img src="https://img.shields.io/badge/Methods and size-core: 15 | deps: 19163 | 20 KB-e91e63.svg"/></a>

#AndroidEasyDeveloper
###Contains 
1. Resources

    Dp and sp from 0 to 100
    
    Styles for some Views
 
2. CustomView

    CenterTextView
 
    LeftCenterTextView
 
    RightCenterTextView

3. *Tool classes 
    
    DensityTool
    
    TextTool
    
    ScreenTool
    
    ...
    
##USE
    About Context: 
        1.Call 'Easy.getDefault().init(this);' on your Application.onCreate();
        2.Call DensityTool.dp2px(...) ...
    
    Others:
        1.Use directly;
        
##Build

###Step 1. Add the JitPack repository to your build file

####Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
####Step 2. Add the dependency

	dependencies {
	        compile 'com.github.Ayvytr:AndroidEasyDeveloper:1.1.0'
	}
