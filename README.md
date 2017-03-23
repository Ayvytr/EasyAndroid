[![GitHub release](https://img.shields.io/github/release/Ayvytr/EasyAndroid.svg)](https://github.com/Ayvytr/EasyAndroid/releases)
[![](https://jitpack.io/v/Ayvytr/EasyAndroid.svg)](https://jitpack.io/#Ayvytr/EasyAndroid)

<a href="http://www.methodscount.com/?lib=com.github.Ayvytr%3AEasyAndroid%3A1.7.0"><img src="https://img.shields.io/badge/Methods count-core: 732 | deps: 19163-e91e63.svg"/></a>
<a href="http://www.methodscount.com/?lib=com.github.Ayvytr%3AEasyAndroid%3A1.7.0"><img src="https://img.shields.io/badge/Size-75 KB-e91e63.svg"/></a>

<a href="http://www.methodscount.com/?lib=com.github.Ayvytr%3AEasyAndroid%3A1.7.0"><img src="https://img.shields.io/badge/Methods and size-core: 732 | deps: 19163 | 75 KB-e91e63.svg"/></a>

# EasyAndroid Library
Simplify development steps for Android Developers.(为Android开发者提供的简化开发过程的库)

> ## [Chinese](README_CN.md)


> ## [Download Docs](https://github.com/Ayvytr/EasyAndroid/releases/download/aar/EasyAndroidDocs-1.7.7z) 

> ## [Download Aar](https://github.com/Ayvytr/EasyAndroid/releases/download/aar/EasyAndroid-1.7.0.aar)

> ## Use
    (About Context classes: You need call 'Easy.getDefault().init(this);' 
	on your 'Application.onCreate()' first) 
    Then, use directly;

## Build

### 1. Add the JitPack repository to your build file

#### Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
#### 2. Add the dependency

	dependencies {
	        compile 'com.github.Ayvytr:AndroidEasyDeveloper:1.7.0'
	}


## Contains 

### Resource
	Dp and sp from 0 to 100
	Some styles about TextView, etc.


### Custom View (About TextView aren't very useful)

	CenterGravityTextView
	LeftCenterGravityTextView
	RightCenterGravityTextView

	SeekBarPressure: double Thumb SeekBar

### Tool classes with out Context
	Convert 	Type conversion class, modeled on the C# Convert class (for this 
			kind of love have alone bell)
			Provides most of the basic types to bool, int, byte conversion, and isZero methods
		toBool
		izZero
		toInt
		toByte

	EncodeTool 	Encoding and decoding related operation class
		urlEncode
		urlDecode
		base64Encode
		base64Decode
		base64UrlSafeEncode
		htmlEncode
		htmlDecode

	EncryptTool	Encryption and decryption related operations
		encryptMD2ToString
		encryptMD2
		encryptMD5ToString
		encryptMD5
		encryptSHA1ToString
		encryptSHA1
		encryptSHA224ToString
		encryptSHA224
		encryptSHA256ToString
		encryptSHA256
		encryptSHA384ToString
		encryptSHA384
		encryptSHA512ToString
		encryptSHA512
		hashTemplate
		encryptHmacMD5ToString
		encryptHmacMD5
		encryptHmacSHA1ToString
		encryptHmacSHA1
		encryptHmacSHA224ToString
		encryptHmacSHA224
		encryptHmacSHA256ToString
		encryptHmacSHA256
		encryptHmacSHA384ToString
		encryptHmacSHA384
		encryptHmacSHA512ToString
		encryptHmacSHA512
		hmacTemplate
		encryptDES2Base64
		encryptDES2HexString
		encryptDES
		decryptBase64DES
		decryptHexStringDES
		decryptDES
		encrypt3DES2Base64
		encrypt3DES2HexString
		encrypt3DES
		decryptBase64_3DES
		decryptHexString3DES
		decrypt3DES
		encryptAES2Base64
		encryptAES2HexString
		encryptAES
		decryptBase64AES
		decryptHexStringAES
		decryptAES
		desTemplate
		bytes2HexString
		hexString2Bytes
		hex2Dec
		base64Encode
		base64Decode

	FileTool	File operation class, judgment is not a file / directory, is it present, 
				rename, create file / directory, list file/directory, get file name / file
				title (not including extension), there is no extension, 
				read / write Documents and other methods
		createDir
        createFile
        fromName
        getByteArray
        getExtension
        getExtension
        getLastModified
        getLowerName
        getName
        getNamesExtensionsList
        getNamesList
        getTitle
        hasExtension
        isDir
        isExists
        isFile
        isTyped
        listAll
        listAllDirs
        listAllDirsDislikeNames
        listAllDirsDislikeNamesNoCase
        listAllDirsLikeNames
        listAllDirsLikeNamesNoCase
        listAllDirsWithNames
        listAllDirsWithNamesNoCase
        listAllDirsWithoutNames
        listAllDirsWithoutNamesNoCase
        listAllDislikeNames
        listAllDislikeNamesNoCase
        listAllLikeNames
        listAllLikeNamesNoCase
        listAllWithExtension
        listAllWithNames
        listAllWithNamesNoCase
        listAllWithoutExtension
        listAllWithoutNames
        listAllWithoutNamesNoCase
        listDirs
        listDirsDislikeNames
        listDirsDislikeNamesNoCase
        listDirsLikeNames
        listDirsLikeNamesNoCase
        listDirsWithNames
        listDirsWithNamesNoCase
        listDirsWithoutNames
        listDirsWithoutNamesNoCase
        listFiles
        listFilesDislikeNames
        listFilesDislikeNamesNoCase
        listFilesLikeNames
        listFilesLikeNamesNoCase
        listFilesNames
        listFilesPaths
        listFilesWithExtension
        listFilesWithNames
        listFilesWithNamesNoCase
        listFilesWithoutExtension
        listFilesWithoutNames
        listFilesWithoutNamesNoCase
        of
        open
        read
        readFile
        rename
        toFileNames
        toFilePaths
        write
        writeFile

	RegexTool	Regular tool classes, including verification of mobile phone number, 
				mailbox, ID number and so on
		isMobileSimple
		isMobileExact
		isTel
		isIDCard15
		isIDCard18
		isEmail
		isURL
		isZh
		isUsername
		isDate
		isIP
		isMatch
		getMatches
		getSplits
		getReplaceFirst
		getReplaceAll

	TextTool	Provides a number of string manipulation functions, including sentenced, 
				is not a string, segmentation string (will remove the end of the regex) 
				and other functions
		isEmpty
		isDigit
		isNumber
		reverse
		emptyString
		split
		isBlank

### Tool classes with Context
	Easy	The library of the single case of the import class,  to use the Context 
			classes, you need to initialize this class
		getContext
		checkInitState
		getDefault
		init
		release
		getClipboardManager
		getWindowManager
		getKeyguardManager

	BarTool		About StatusBar, ActionBar peration tool class
		setColor	
		setColorForSwipeBack
		setColorNoTranslucent
		setColorDiff
		setTranslucent
		setTranslucentForCoordinatorLayout
		setTransparent
		setTranslucentDiff
		setColorForDrawerLayout
		setColorNoTranslucentForDrawerLayout
		setColorForDrawerLayout
		setColorForDrawerLayoutDiff
		setTranslucentForDrawerLayout
		setTranslucentForDrawerLayoutDiff
		setTransparentForImageView
		setTranslucentForImageViewInFragment
		clearPreviousSetting
		addTranslucentView
		createStatusBarView
		setRootView
		setTransparentForWindow
		transparentStatusBar
		createTranslucentStatusBarView
		getStatusBarHeight
		calculateStatusColor
		setTransparentStatusBar
		hideStatusBar
		isStatusBarExists
		getActionBarHeight
		showNotificationBar
		hideNotificationBar
		invokePanels

	ClipboardTool	Clipboard operation class
		setText
		getText
		getText
		setUri
		getUri
		setIntent
		getIntent

	DensityTool		The Dp - Px transformation class provides three types of 
					overloaded methods, int, float, and double, that minimize 
					external casts.
		px2dp
		dp2px

	IntentTool	Get the usual Intent.
		getInstallAppIntent
		getUninstallAppIntent
		getLaunchAppIntent
		getAppDetailsSettingsIntent
		getShareTextIntent
		getShareImageIntent
		getComponentIntent
		getShutdownIntent
		getDialIntent
		getCallIntent
		getSendSmsIntent
		getCaptureIntent

	ResTool		Get the Drawable, String, dimension, color, Configuration in the resource.
		getDrawable
		getString
		getDimen
		getDimenFloat
		getDimenToDp
		getDimenFloatToDp
		getColor
		getConfiguration

	ScreenTool		Provides the screen size, width, screen rotation direction, set to 
					vertical screen, is not horizontal / vertical screen, get 
					screenshots (include StatusBar or not), screen is locked or not.
		getDisplayMetrics
		getScreenWidth
		getScreenHeight
		setLandscape
		setPortrait
		isLandscape
		isPortrait
		getScreenRotationAngle
		getRotationAngle
		captureWithStatusBar
		captureWithoutStatusBar
		isScreenLock

	ToastTool 	The Toast tool class provides easy Toast creation and output capabilities.
    	make
		makeLong
		show
		showLong
		    
> ## Test Classes for library classes
	ConvertTest
	FileToolTest
	TextToolTest
	DensityToolTest

> ### Quote below, thanks for their libraries
1. https://github.com/Blankj/AndroidUtilCode
2. https://github.com/Trinea/android-common
3. https://github.com/wyouflf/xUtils3
4. https://github.com/jingle1267/android-utils

> ### TODO:
1. Complements Convert class
2. Create my PrettyVideoPlayer
3. Complements my library, Add more useful features
4. Personal website
5. Separate Android and Java code, Packaged into different libraries
6. Publish to JCenter