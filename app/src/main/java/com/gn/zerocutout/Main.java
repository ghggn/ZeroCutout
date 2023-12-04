package com.gn.zerocutout;


import android.graphics.Insets;
import android.graphics.Rect;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class Main implements IXposedHookZygoteInit {
  private String strCutoutClass = "android.view.DisplayCutout";

  private String strCutoutPath = strCutoutClass + "$CutoutPathParserInfo";
  private String strCutOutBounds = strCutoutClass + "$Bounds";

  private void hookCutoutInit() {
    Class<?> cutoutpathclass = XposedHelpers.findClass(strCutoutPath, null);
    Class<?> cutoutboundsclass = XposedHelpers.findClass(strCutOutBounds, null);


    XposedHelpers.findAndHookConstructor(strCutoutClass, null, Rect.class, Insets.class, Rect.class, Rect.class, Rect.class, Rect.class, cutoutpathclass, boolean.class, new XC_MethodHook() {
      @Override
      protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        param.args[0] = new Rect(0, 0, 0, 0);
        param.args[1] = null;
        param.args[2] = new Rect(0, 0, 0, 0);
        param.args[3] = new Rect(0, 0, 0, 0);
        param.args[4] = new Rect(0, 0, 0, 0);
        param.args[5] = new Rect(0, 0, 0, 0);
        param.args[6] = null;
      }
    });

    XposedHelpers.findAndHookConstructor(strCutoutClass, null, Rect.class, Insets.class, Rect[].class, cutoutpathclass, boolean.class, new XC_MethodHook() {
      @Override
      protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        param.args[0] = new Rect(0, 0, 0, 0);
        param.args[1] = null;
        param.args[2] = new Rect[]{new Rect(0, 0, 0, 0), new Rect(0, 0, 0, 0), new Rect(0, 0, 0, 0), new Rect(0, 0, 0, 0)};
        param.args[3] = null;
      }
    });

    XposedHelpers.findAndHookConstructor(strCutoutClass, null, Rect.class, Insets.class, cutoutboundsclass, cutoutpathclass, new XC_MethodHook() {
      @Override
      protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        param.args[0] = new Rect(0, 0, 0, 0);
        param.args[1] = null;
        param.args[2] = XposedHelpers.newInstance(cutoutboundsclass, new Rect(0, 0, 0, 0), new Rect(0, 0, 0, 0), new Rect(0, 0, 0, 0), new Rect(0, 0, 0, 0), true);
        param.args[3] = null;
      }
    });
  }

  @Override
  public void initZygote(StartupParam startupParam) throws Throwable {
    hookCutoutInit();
  }
}
