[![jCenter](https://img.shields.io/badge/jCenter-1.8.5-red.svg)](https://bintray.com/ayvytr/maven/EasyAndroid/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache--2.0%20-blue.svg)](license)

# EasyAndroid 库

# 编译

## JCenter
	compile 'com.ayvytr:EasyAndroid:1.8.5'
	
## ~~JitPack (已弃用)~~

> ## [English](README.md)

> ## 使用
    (有关Context类:   先在 'Application.onCreate()' 中调用 'Easy.getDefault().init(this);')
    直接使用
    
> ### [我要打印日志](https://github.com/Ayvytr/Logger)

## 1.8.5 <font color=red>NewAuthEditText</font>, 类似支付宝支付时的输入密码控件，代替<font color=red>AuthEditText</font>(
重构了这个控件，修复了布局文件中看不到预览，没有自定义属性，以及不合理地重写View方法的问题)

## 1.8.4 <font color=red>QuickIndexView</font>, 类似微信的联系人字母索引控件，但是功能更多，可以加入顶部，底部图片，设置弹出Toast的效果，更改索引字体颜色和Toast字体颜色等方法

## 1.8.1 <font color=red>AuthEditText</font>, 类似支付宝支付时的输入密码控件，从1.8.5开始弃用.

## 1.8.0 <font color=red>FlowLayout</font> 流式布局

## 1.7.7 <font color=red>Colors</font> 类来啦!
* <font color=red>Colors</font> 类，提供了超过1000种颜色，资源中同时也提供了这些颜色，太(henhao)多(hen)啦(qiangda)！


## 1.7.6 加入了新成员！
1. Packages类和AppInfo Bean类，获取所有安装的应用更方便！ 
2. BitmapTool类，提供了转换，缩放，旋转Bitmap和Drawable的众多方法！
3. 修正了bintrayUpload过程中的某些错误.


## 包含 

### 资源
	从 -300~300 dp 和 sp 资源
	有关TextView等View的Style


### 自定义View (有关TextView的类可能作用不大)

	CenterGravityTextView
	LeftCenterGravityTextView
	RightCenterGravityTextView

	SeekBarPressure: 双Thumb的SeekBar

	FlowLayout
	
	AuthEditText

### PopupWindows  [浏览 Readme->](README_PopupWindow.md)
	AlphaPopupWindow 
	BasePopupWindow
	ToggleSoftInputPopupWindow
	TopPopupWindow

### Tool classes with out Context
	BitmapTool		转换，缩放，旋转Bitmap和Drawable等操作。
		toBitmap
		toBitmap2
		toDrawable
		toDrawable
		toByteArray
		zoom
		zoom2Drawable
		rotate
		rotate2Drawable

	Colors 		提供了超过1000种颜色，以及获取红绿蓝等方法。
		alpha
		argb
		b
		g
		r
		rgb

	Convert		类型转换类，仿照了C#的Convert类（对这个类情(zhen)有(de)独(hao)钟(yong)）
				提供了大部分基本类型到bool，int，byte的转换，以及isZero方法
		toBool
		izZero
		toInt
		toByte

	EncodeTool 	编码解码相关操作类
		urlEncode
		urlDecode
		base64Encode
		base64Decode
		base64UrlSafeEncode
		htmlEncode
		htmlDecode

	EncryptTool	加密解密相关操作类
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

	FileTool	文件操作类，判断是不是文件/目录，是不是存在，重命名，创建文件/目录，列出文件/目录，
				获取文件名/文件标题（不包含扩展名), 有没有扩展名，读/写文件等方法
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

	RegexTool	正则工具类，包括验证手机号，邮箱，身份证号码等
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

	TextTool	提供了众多的字符串操作功能，包括判空，是不是字符串，分割字符串（会去掉末尾的regex）等功能
		isEmpty
		isDigit
		isNumber
		reverse
		emptyString
		split
		isBlank

### Tool classes with Context
	Easy	这个库的单例入口类, 使用有关Context的类之前，需要初始化这个类
		getContext
		checkInitState
		getDefault
		init
		release
		getClipboardManager
		getWindowManager
		getKeyguardManager

	BarTool	StatusBar, ActionBar操作工具类		
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

	ClipboardTool	剪贴板操作类
		setText
		getText
		getText
		setUri
		getUri
		setIntent
		getIntent

	DensityTool	Dp - Px 相互转化类，提供了int，float，double 3种类型的重载方法，尽可能减少外部强制类型转换.
		px2dp
		dp2px

	IntentTool	获取常用的Intent
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

	ResTool		获取资源中Drawable，String，dimension，color, Configuration.
		getDrawable
		getString
		getDimen
		getDimenFloat
		getDimenToDp
		getDimenFloatToDp
		getColor
		getConfiguration

	ScreenTool	提供了获取屏幕尺寸，宽高，屏幕旋转方向，设置为竖屏，是不是横屏/竖屏，获取屏幕截图（包含/不包含状态栏)， 判断是不是锁屏的功能.
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

	ToastTool 	Toast工具类，提供简便的Toast创建和输出功能
    	make
		makeLong
		show
		showLong

	Managers	获取Android管理类实例``
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
		    
> ## 库的测试类
	ConvertTest
	FileToolTest
	TextToolTest
	DensityToolTest


> ### 借鉴如下，感谢他们的库
1. https://github.com/Blankj/AndroidUtilCode


> ### TODO:
1. 完善Convert类
2. 开发我自己的视频播放器 PrettyVideoPlayer
3. 完善这个库，添加更多实用的功能
4. 个人网站
5. 分离Android和Java代码，打包成不同的库