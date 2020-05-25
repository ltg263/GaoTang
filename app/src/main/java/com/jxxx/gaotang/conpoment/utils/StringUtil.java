package com.jxxx.gaotang.conpoment.utils;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/8/4.
 */

public class StringUtil {

    public static String replaceNull(String str){
        if (TextUtils.isEmpty(str)){
            return "";
        }
        return str;
    }

    public static String replaceNullToZero(String str){
        if (TextUtils.isEmpty(str)){
            return "0";
        }
        return str;
    }

    public static String replaceNullToOne(String str){
        if (TextUtils.isEmpty(str)){
            return "1";
        }
        return str;
    }

    public static  boolean checkIsNotEmpty(TextView edt){
        if (ObjectUtils.isEmpty(edt.getText())){
            return false;
        }
        if (TextUtils.isEmpty(edt.getText().toString())){
            return false;
        }
        return true;
    }

    public static String replaceHtmlImgToAbsolutelyUrl(String baseUrl, String html){
        Pattern pattern = Pattern.compile("src\\s*=\\s*\"(.+?)\"");
        Matcher matcher = pattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String str = matcher.group(0);
            if (!str.contains("http://")){
                matcher.appendReplacement(sb,str.substring(0,5)+baseUrl+str.substring(6));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String replacrPhoneNum2Star(String phoneNum){
        if (ObjectUtils.isEmpty(phoneNum)){
            return "";
        }
        if (phoneNum.length()<7){
            return phoneNum;
        }
        return phoneNum.substring(0,3)+"****"+phoneNum.substring(7,phoneNum.length());
    }

    /**
     * 银行卡号中间每四位插入一个空格
     * @param str
     * @return
     */
    public static String insertSpacePerFour(String str){
        if (ObjectUtils.isEmpty(str)){
            return "";
        }
        if (str.length()<16){
            return str;
        }
        return str.substring(0,4)+" **** ****  "+str.substring(12,16)+" "+str.substring(16,str.length());
    }

    /**
     * 校验银行卡卡号
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }
    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

    /**
     * 替换非utf8字符，慎用  会出现死循环
     * @param text
     * @return
     */
    public static String filterOffUtf8Mb4(String text)  {
        try {
            byte[] bytes = text.getBytes("UTF-8");
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            int i = 0;
            while (i < bytes.length) {
                short b = bytes[i];
                if (b > 0) {
                    buffer.put(bytes[i++]);
                    continue;
                }
                b += 256;
                if ((b ^ 0xC0) >> 4 == 0) {
                    buffer.put(bytes, i, 2);
                    i += 2;
                }
                else if ((b ^ 0xE0) >> 4 == 0) {
                    buffer.put(bytes, i, 3);
                    i += 3;
                }
                else if ((b ^ 0xF0) >> 4 == 0) {
                    i += 4;
                }
            }
            buffer.flip();
            return new String(buffer.array(), "utf-8");
        } catch (Exception e) {
            Log.e("Exception",e.getMessage().toString());
        }
        return text;
    }

    public static boolean isUTF8(String key){
        try {
            key.getBytes("utf-8");
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 检测是否有emoji字符
     * @param source
     * @return FALSE，包含图片
     */
    public static boolean containsEmoji(String source)
    {
        if (source.equals(""))
        {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++)
        {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint))
            {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint)
    {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     * @param source
     * @return
     */
    public static String filterEmoji(String source)
    {

        if (!containsEmoji(source))
        {
            return source;//如果不包含，直接返回
        }
        //到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++)
        {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint))
            {
                if (buf == null)
                {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else
            {
            }
        }

        if (buf == null)
        {
            return source;//如果没有找到 emoji表情，则返回源字符串
        }
        else
        {
            if (buf.length() == len)
            {
                //这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else
            {
                return buf.toString();
            }
        }

    }
}
