## 简介

通过 hook android.view.DisplayCutout 的构造方法实现系统无 cutout
用于打孔屏 水滴屏修改状态栏高度时 使 framework-res.apk 内的 values/dimen.xml/status_bar_height 生效

## 使用方法:

1.  制作 RRO apk,在 apk 内自定义 status_bar_height(建议一并覆盖 status_bar_height_default,status_bar_height_portrait,status_bar_height_landscape)
2.  使用 magisk module 将 apk 覆盖到/system/vendor/overlay
3.  安装该软件即可是自定义的 status_bar_height 生效
