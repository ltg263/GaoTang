package com.jxxx.gaotang.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.gyf.immersionbar.ImmersionBar;
import com.jxxx.gaotang.R;
import com.jxxx.gaotang.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClockActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_righttext)
    TextView mTvRighttext;
    @BindView(R.id.iv_rightimg)
    ImageView mIvRightimg;
    @BindView(R.id.rl_actionbar)
    RelativeLayout mRlActionbar;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.ll_pick_type)
    LinearLayout mLlPickType;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.ll_pick_time)
    LinearLayout mLlPickTime;
    @BindView(R.id.cb_seven)
    CheckBox mCbSeven;
    @BindView(R.id.tv_seven)
    TextView mTvSeven;
    @BindView(R.id.cb_one)
    CheckBox mCbOne;
    @BindView(R.id.tv_one)
    TextView mTvOne;
    @BindView(R.id.cb_two)
    CheckBox mCbTwo;
    @BindView(R.id.tv_two)
    TextView mTvTwo;
    @BindView(R.id.cb_three)
    CheckBox mCbThree;
    @BindView(R.id.tv_three)
    TextView mTvThree;
    @BindView(R.id.cb_four)
    CheckBox mCbFour;
    @BindView(R.id.tv_four)
    TextView mTvFour;
    @BindView(R.id.cb_five)
    CheckBox mCbFive;
    @BindView(R.id.tv_five)
    TextView mTvFive;
    @BindView(R.id.cb_six)
    CheckBox mCbSix;
    @BindView(R.id.tv_six)
    TextView mTvSix;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;

    private String remindType;
    private int hour=0;
    private int min=0;
    ArrayList<Integer> testDays = new ArrayList<>();
    private List<String> mFeedTypeList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_clock;
    }

    @Override
    protected void initViews() {
        mIvBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_common_back));
        mRlActionbar.setBackgroundColor(getResources().getColor(R.color.color_FF7F00));
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(R.id.rl_actionbar).fitsSystemWindows(true).init();
        mFeedTypeList.add("血糖提醒");
        mFeedTypeList.add("血压提醒");
        initAllCb();
    }

    private void initAllCb() {
        mCbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    for (int i = 0; i < testDays.size(); i++) {
                        if (testDays.get(i) == 2) {
                            testDays.remove(i);
                        }
                    }
                    mTvOne.setTextColor(getResources().getColor(R.color.color_2b2b2b));
                } else {
                    testDays.add(Calendar.MONDAY);
                    mTvOne.setTextColor(getResources().getColor(R.color.color_FF7F00));
                }
            }
        });
        mCbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    for (int i = 0; i < testDays.size(); i++) {
                        if (testDays.get(i) == 3) {
                            testDays.remove(i);
                        }
                    }
                    mTvTwo.setTextColor(getResources().getColor(R.color.color_2b2b2b));
                } else {
                    testDays.add(Calendar.TUESDAY);
                    mTvTwo.setTextColor(getResources().getColor(R.color.color_FF7F00));
                }
            }
        });
        mCbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    for (int i = 0; i < testDays.size(); i++) {
                        if (testDays.get(i) == 4) {
                            testDays.remove(i);
                        }
                    }
                    mTvThree.setTextColor(getResources().getColor(R.color.color_2b2b2b));
                } else {
                    testDays.add(Calendar.WEDNESDAY);
                    mTvThree.setTextColor(getResources().getColor(R.color.color_FF7F00));
                }
            }
        });
        mCbFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    for (int i = 0; i < testDays.size(); i++) {
                        if (testDays.get(i) == 5) {
                            testDays.remove(i);
                        }
                    }
                    mTvFour.setTextColor(getResources().getColor(R.color.color_2b2b2b));
                } else {
                    testDays.add(Calendar.THURSDAY);
                    mTvFour.setTextColor(getResources().getColor(R.color.color_FF7F00));
                }
            }
        });
        mCbFive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    for (int i = 0; i < testDays.size(); i++) {
                        if (testDays.get(i) == 6) {
                            testDays.remove(i);
                        }
                    }
                    mTvFive.setTextColor(getResources().getColor(R.color.color_2b2b2b));
                } else {
                    testDays.add(Calendar.FRIDAY);
                    mTvFive.setTextColor(getResources().getColor(R.color.color_FF7F00));
                }
            }
        });
        mCbSix.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    for (int i = 0; i < testDays.size(); i++) {
                        if (testDays.get(i) == 7) {
                            testDays.remove(i);
                        }
                    }
                    mTvSix.setTextColor(getResources().getColor(R.color.color_2b2b2b));
                } else {
                    testDays.add(Calendar.SATURDAY);
                    mTvSix.setTextColor(getResources().getColor(R.color.color_FF7F00));
                }
            }
        });
        mCbSeven.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    for (int i = 0; i < testDays.size(); i++) {
                        if (testDays.get(i) == 1) {
                            testDays.remove(i);
                        }
                    }
                    mTvSeven.setTextColor(getResources().getColor(R.color.color_2b2b2b));
                } else {
                    testDays.add(Calendar.SUNDAY);
                    mTvSeven.setTextColor(getResources().getColor(R.color.color_FF7F00));
                }
            }
        });
    }

    public void selectorCustom(Context mContext, final List<String> list, String title, final TextView textView) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                textView.setText(list.get(options1));
                remindType = list.get(options1);
            }
        }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {

            }
        })
                .setTitleText(title)
                .setDividerColor(Color.BLACK)
                .setSubmitColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(16)
                .build();

        pvOptions.setPicker(list);//添加数据源
        pvOptions.show();
    }


    private void createAlarm(String message, int hour, int minutes, int resId) {

        String packageName = getApplication().getPackageName();
        Uri ringtoneUri = Uri.parse("android.resource://" + packageName + "/" + resId);
        //action为AlarmClock.ACTION_SET_ALARM
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                //闹钟的小时
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                //闹钟的分钟
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes)
                //响铃时提示的信息
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                //用于指定该闹铃触发时是否振动
                .putExtra(AlarmClock.EXTRA_VIBRATE, true)
                //一个 content: URI，用于指定闹铃使用的铃声，也可指定 VALUE_RINGTONE_SILENT 以不使用铃声。
                //如需使用默认铃声，则无需指定此 extra。
                .putExtra(AlarmClock.EXTRA_RINGTONE, ringtoneUri)
                //一个 ArrayList，其中包括应重复触发该闹铃的每个周日。
                // 每一天都必须使用 Calendar 类中的某个整型值（如 MONDAY）进行声明。
                //对于一次性闹铃，无需指定此 extra
                .putExtra(AlarmClock.EXTRA_DAYS, testDays)
                //如果为true，则调用startActivity()不会进入手机的闹钟设置界面
                .putExtra(AlarmClock.EXTRA_SKIP_UI, false);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void selectorDate(Context mContext, final TextView textView) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();

        Calendar endDate = Calendar.getInstance();


        //正确设置方式 原因：注意事项有说明

        Calendar now = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //Toast.makeText(MainActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                String time = sdf.format(date);
                textView.setText(time);
                hour = date.getHours();
                min = date.getMinutes();
            }
        })
                .setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setTitleText("请选时间")//标题文字
                .isCyclic(false)//是否循环滚动
                .setRangDate(now, null)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .build();
        pvTime.show();
    }



    @OnClick({R.id.iv_back, R.id.ll_pick_type, R.id.ll_pick_time, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.ll_pick_type:
                selectorCustom(this, mFeedTypeList, "提醒类型",mTvType);
                break;
            case R.id.ll_pick_time:
                selectorDate(this,mTvTime);
                break;
            case R.id.btn_submit:
                createAlarm(remindType,hour,min,1);
                break;
        }
    }
}
