package com.jxxx.gaotang.conpoment.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.gson.Gson;
import com.jxxx.gaotang.entity.SmsDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/5.
 */

public class ContactUtil {

    /**
     * 用户点击通讯录获取联系人电话
     * @param context
     * @param uri
     * @return
     */
    public String[] getPhoneContacts(Context context, Uri uri){
        String[] contact=new String[2];
        //得到ContentResolver对象
        ContentResolver cr = context.getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor=cr.query(uri,null,null,null,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            //取得联系人姓名
            int nameFieldColumnIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0]=cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if(phone != null){
                phone.moveToFirst();
                try {
                    contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }catch (Exception e){
                    contact[1] = "";
                }
            }
            phone.close();
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }

    /**
     * 获取全部通讯录联系人电话
     * @param context
     * @return
     */
    public String getAllContact(Context context){
        List<Map<String, String>> allContact = new ArrayList<>();
        // 获取联系人数据
        ContentResolver cr = context.getContentResolver();
        //获取所有电话信息（而不是联系人信息），这样方便展示
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,// 姓名
                ContactsContract.CommonDataKinds.Phone.NUMBER,// 电话号码
        };
        Cursor cursor = cr.query(uri, projection, null, null, null);
        if (cursor == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String number = cursor.getString(1);
            String phone = number.replaceAll("\\s*", "");
            String first = number.substring(0,1);
            Map<String, String> map = new HashMap<String, String>();
            map.put(phone,name);
            allContact.add(map);
       /*     if (phone.length() == 11 && first.equals("1")){
                Map<String,String> map = new HashMap<String,String>();
                map.put(phone,name);
                allContact.add(map);
            }*/
           /* if (number.length() == 11){

            }*/

        }
        cursor.close();
        return new Gson().toJson(allContact);
    }

    /**
     * 获取短信信息
     * @return smsBuilder.toString()
     */
    @SuppressLint("LongLogTag")
    @SuppressWarnings("unused")
    public String getSmsMessage(Context context){

        List<SmsDTO> con = new ArrayList<>();
        final String SMS_URI_ALL   = "content://sms/";
        final String SMS_URI_INBOX = "content://sms/inbox";
        final String SMS_URI_SEND  = "content://sms/sent";
        final String SMS_URI_DRAFT = "content://sms/draft";

        StringBuilder smsBuilder = new StringBuilder();

        try{
            ContentResolver cr = context.getContentResolver();
            String[] projection = new String[]{"_id", "address", "person",
                    "body", "date", "type"};
            Uri uri = Uri.parse(SMS_URI_ALL);
            Cursor cur = cr.query(uri, projection, null, null, "date desc");

            if (cur.moveToFirst()) {
                String name;
                String phoneNumber;
                String smsbody;
                String date;
                String type;

                int nameColumn = cur.getColumnIndex("person");  //发送人
                int phoneNumberColumn = cur.getColumnIndex("address");    //号码
                int smsbodyColumn = cur.getColumnIndex("body");    //内容
                int dateColumn = cur.getColumnIndex("date");    //时间
                int typeColumn = cur.getColumnIndex("type");    //接收还是发送

                while(cur.moveToNext()){
                    name = cur.getString(2);
                    phoneNumber = cur.getString(1);
                    smsbody = cur.getString(3);


                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd hh:mm:ss");
                    Date d = new Date(Long.parseLong(cur.getString(4)));
                    date = dateFormat.format(d);

                    int typeId = cur.getInt(5);
                    if(typeId == 1){
                        type = "接收";
                        String smsmsg = name+":"+phoneNumber+":"+smsbody+":"+date+":"+type+"\n";
                        con.add(new SmsDTO(name,phoneNumber,smsbody,date,type));
                    } else if(typeId == 2){
                        type = "发送";
                    } else {
                        type = "";
                    }
                    //System.out.println("nsc :"+name+":"+phoneNumber+":"+smsbody+":"+date+":"+type +"\n");
//                    String smsmsg = name+":"+phoneNumber+":"+smsbody+":"+date+":"+type+"\n";
//                    con.add(new SmsDTO(name,phoneNumber,smsbody,date,type));
                    if(smsbody == null) {
                        smsbody = "";
                    }

                };
                cur.close();
            } else {
                smsBuilder.append("no result!");
            }

            smsBuilder.append("getSmsInPhone has executed!");
        } catch(SQLiteException ex) {
            Log.d("SQLiteException in getSmsInPhone", ex.getMessage());
        }
        return new Gson().toJson(con);
    }
}
