package clwater.xposelearn;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by gengzhibo on 17/7/12.
 */

public class Main implements IXposedHookLoadPackage  {


    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
//        XposedBridge.log("gzb " + "handleLoadPackage");

        if (lpparam.packageName.equals("clwater.otherpro")) {
            XposedBridge.log("gzb " + lpparam.packageName);

            XposedHelpers.findAndHookMethod("clwater.otherpro.MainActivity", lpparam.classLoader, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //不能通过Class.forName()来获取Class ，在跨应用时会失效
                    Class c=lpparam.classLoader.loadClass("clwater.otherpro.MainActivity");
                    Field field=c.getDeclaredField("textView1");
                    field.setAccessible(true);
                    //param.thisObject 为执行该方法的对象，在这里指MainActivity
                    TextView textView= (TextView) field.get(param.thisObject);
                    textView.setText("Hello Xposed text1");
                }
            });
        }


    }



}
