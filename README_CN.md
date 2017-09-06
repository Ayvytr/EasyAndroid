[![jCenter](https://img.shields.io/badge/jCenter-2.1.0-re.svg)](https://bintray.com/ayvytr/maven/EasyAndroid/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache--2.0%20-blue.svg)](license)

# EasyAndroid Library
Simplify development steps for Android Developers.(为Android开发者提供的简化开发过程的库)


# Build

## JCenter
	compile 'com.ayvytr:EasyAndroid:2.1.0'

## ~~JitPack (Deprecated)~~

> ## [Chinese](README_CN.md)

> ## Use
    (About Context classes: You need call 'Easy.getDefault().init(this);' 
	on your 'Application.onCreate()' first) 
    Then, use directly;
    
> ### [I want print logger](https://github.com/Ayvytr/Logger)

## 2.1.0 添加了<font color=red>SpManager</font>和<font color=red>Sp</font>，SpManager管理Sp，Sp封装了SharedPreference各种
操作，精简了SharedPreferences操作（也有SpTest类进行了测试，保证通过测试才发布呦！）

## 1.8.5 <font color=red>NewAuthEditText</font>, like Zhifubao's payment View，instead <font color=red>AuthEditText</font>
{类似支付宝支付时的输入密码控件，代替<font color=red>AuthEditText</font>(
重构了这个控件，修复了布局文件中看不到预览，没有自定义属性，以及不合理地重写View方法的问题)}


## 1.8.4 <font color=red>QuickIndexView</font>, like WeChat's contact index bar

## 1.8.1 <font color=red>AuthEditText</font>, like Zhifubao's payment View，Start version 1.8.5, it deprecated.

## 1.8.0 <font color=red>FlowLayout</font>

## 1.7.7 <font color=red>Colors</font> class comes!
* <font color=red>Colors</font> class, provides more than a thousand colors for you, and these colors still provides in color resource too.

## 1.7.6 New members joined!
1. <font color=red>Packages</font> and <font color=red>AppInfo</font> classes, get all applications more simpler.
2. <font color=red>BitmapTool</font> class, to convert, zoom or rotate Bitmap and Drawable.
3. Fixed some bintrayUpload problems.


## Contains 

### SpManager Sp
    SpManager管理Sp，Sp封装了SharedPreferences操作，可以直接使用Sp管理SharedPreference实例，不过依然推荐使用SpManager来
    产生Sp实例（已在SpTest类中进行了测试，应该没bug了）
    
    用法：
        //获取默认的Sp实例
        Sp defaultSp = SpManager.getDefault(context).getSp();
        //获取指定名称的Sp实例
        Sp yourSp = SpManager.getDefault(context).getSp("your_sp_name");

        //使用 获取值
        int i = yourSp.getInt("key");
        i = yourSp.getInt("key", 10);
        //设置值
        yourSp.putInt("key", 111).putInt("key2", 222);
        //获取所有值
        Map<String, ?> all = yourSp.getAll();
        //删除值
        yourSp.remove("key");

        //删除默认的sp文件
        SpManager.getDefault(context).deleteSp();
        //删除指定名称的sp文件
        SpManager.getDefault(context).deleteSp("your_sp_name");
        
### Resource
	Dp and sp from -300 to 300
	Some styles about TextView, etc.


### Custom View (About TextView aren't very useful)

	CenterGravityTextView
	LeftCenterGravityTextView
	RightCenterGravityTextView

	SeekBarPressure: double Thumb SeekBar

	FlowLayout
	
	AuthEditText

### PopupWindows  [See Readme->](README_PopupWindow.md)
	AlphaPopupWindow 
	BasePopupWindow
	ToggleSoftInputPopupWindow
	TopPopupWindow

### Tool classes with out Context
	BitmapTool		Convert, rotate and zoom Bitmap or Drawable.  		
		toBitmap
		toBitmap2
		toDrawable
		toDrawable
		toByteArray
		zoom
		zoom2Drawable
		rotate
		rotate2Drawable

	Colors 		provides more than a thousand colors and  methods red, green, blue color, etc.
		alpha
		argb
		b
		g
		r
		rgb

	Convert 	Type conversion class, modeled on the C# Convert class (for this 
			kind of love have alone bell)
			Provides most of the basic types to bool, int, byte conversion, and isZero methods
		toBool
		izZero
		toInt
		toByte
		toString

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

	BitmapTool		Convert, rotate and zoom Bitmap or Drawable  
		toBitmap
		toBitmap2
		toDrawable
		toDrawable
		toByteArray
		zoom
		zoom2Drawable
		rotate
		rotate2Drawable

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

	Managers	Get Android Manager classes instance.
		getAccessibilityManager
		getAccountManager
		getActivityManager
		getAlarmManager
		getAppOpsManager
		getAppWidgetManager
		getAudioManager
		getBatteryManager
		getBluetoothManager
		getCameraManager
		getCaptioningManager
		getCarrierConfigManager
		getClipboardManager
		getConsumerIrManager
		getDisplayManagerCompat
		getDownloadManager
		getFingerprintManager
		getHardwarePropertiesManager
		getInputMethodManager
		getJobScheduler
		getKeyguardManager
		getLauncherApps
		getLayoutInflater
		getLocationManager
		getMediaRouter
		getMediaSessionManager
		getMidiManager
		getNfcManager
		getNotificationManager
		getNsdManager
		getPackageManager
		getPowerManager
		getPrintManager
		getRestrictionsManager
		getSensorManager
		getStorageManage
		getTelecomManager
		getTelephonyManager
		getTextServicesManager
		getUiModeManager
		getUserManager
		getVibrator
		getWallpaperManager
		getWindowManager
		    
> ## Test Classes for library classes
	ConvertTest
	FileToolTest
	TextToolTest
	DensityToolTest

> ### Quote below, thanks for their libraries
1. https://github.com/Blankj/AndroidUtilCode


