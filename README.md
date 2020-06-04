## Androidx 简单好用的Dialog封装库

### 1、依赖

```

```

###  2、使用

#### 2.1、样式介绍

* DEFAULT - 默认弹窗
* BOTTOM_SHEET - 底部弹窗
* FULL_SCREEN - 全屏弹窗

#### 2.2、示例

* 默认弹窗

```kotlin
AndroidDialog.builder(R.layout.dialog_confirm){
                setCornerRadius {
                    floatArrayOf(24f,24f,24f,24f,24f,24f,24f,24f)
                }
            }.alert(supportFragmentManager,"")
```

![image](https://github.com/xqy666666/Android-Dialog/blob/master/dialog.gif)



* 底部弹窗

```kotlin
AndroidDialog.builder(R.layout.dialog_bottom,
                      AndroidDialog.BOTTOM_SHEET){
                setCornerRadius {
                    floatArrayOf(24f,24f,24f,24f,0f,0f,0f,0f)
                }
                
            }.alert(supportFragmentManager,"")
```

