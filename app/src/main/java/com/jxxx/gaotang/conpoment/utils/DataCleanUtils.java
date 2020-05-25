package com.jxxx.gaotang.conpoment.utils;

import android.os.Environment;
import android.util.Log;

import com.blankj.utilcode.util.CleanUtils;
import com.blankj.utilcode.util.SPUtils;
import com.jxxx.gaotang.conpoment.constants.ConstValues;


/**
 * author： TongGuHermit
 * created on： 2018/12/28
 */

public class DataCleanUtils {

    public static boolean outLogin(){
        try {
            CleanUtils.deleteFilesInDir(Environment.getExternalStorageDirectory().getPath()+ ConstValues.FILE_ROOT_DIRECTORY);
        }catch (Exception e){
            Log.e("cleanData",e.getMessage());
        }
        return true;
    }

    public static boolean cleanData(){
        SPUtils.getInstance().put(ConstValues.ISLOGIN,false);
        SPUtils.getInstance().remove(ConstValues.USERID);
        SPUtils.getInstance().remove(ConstValues.USER_PHONE);
        SPUtils.getInstance().remove(ConstValues.TOKEN);
        SPUtils.getInstance().remove(ConstValues.USER_NAME);
        SPUtils.getInstance().remove(ConstValues.USER_CREATETIME);
        SPUtils.getInstance().remove(ConstValues.NICKNAME);
        SPUtils.getInstance().remove(ConstValues.AVATAR);
        SPUtils.getInstance().remove(ConstValues.IS_REALNAME);
        try {
            CleanUtils.deleteFilesInDir(Environment.getExternalStorageDirectory().getPath()+ConstValues.FILE_ROOT_DIRECTORY);
        }catch (Exception e){
            Log.e("cleanData",e.getMessage());
        }
        return true;
    }
}
