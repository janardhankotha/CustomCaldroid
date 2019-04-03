package com.android.customcaldroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //  CaldroidSampleCustomFragment caldroidFragment2 = new CaldroidSampleCustomFragment();

    private List<DailyEvent> pojodateselection = new ArrayList<DailyEvent>();
    private boolean undo = false;
    // private CaldroidFragment caldroidFragment;
    private CaldroidFragment caldroidFragment2;
    private TextView mTextView;
    private Date mCurrentSelectDate = null;
    SimpleDateFormat formatter;

    private void setCustomResourceForDates() {
        Calendar cal = Calendar.getInstance();
        Date todaysDate = cal.getTime();
        mTextView.setText(formatter.format(todaysDate));

        // Min date is last 7 days
        cal.add(Calendar.DATE, -7);
        Date blueDate = cal.getTime();

        // Max date is next 7 days
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        Date greenDate = cal.getTime();

       /* if (caldroidFragment != null) {
            ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
            ColorDrawable green = new ColorDrawable(Color.GREEN);
            caldroidFragment.setBackgroundDrawableForDate(blue, blueDate);
            caldroidFragment.setBackgroundDrawableForDate(green, greenDate);
            caldroidFragment.setTextColorForDate(R.color.white, blueDate);
            caldroidFragment.setTextColorForDate(R.color.white, greenDate);
        }*/
        if (caldroidFragment2 != null) {
            ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
            ColorDrawable green = new ColorDrawable(Color.GREEN);
            caldroidFragment2.setBackgroundDrawableForDate(blue, blueDate);
            caldroidFragment2.setBackgroundDrawableForDate(green, greenDate);
            caldroidFragment2.setTextColorForDate(R.color.white, blueDate);
            caldroidFragment2.setTextColorForDate(R.color.white, greenDate);
        }
        HashMap<String, Object> extraData = (HashMap<String, Object>) caldroidFragment2.getExtraData();
        extraData.put("YOUR_CUSTOM_DATA_KEY1",greenDate);
        extraData.put("YOUR_CUSTOM_DATA_KEY2", "Jana");

        caldroidFragment2.setExtraData(extraData);
        caldroidFragment2.refreshView();

// Refresh view
        // caldroidFragment.refreshView();
      /*  HashMap<String, Object> extraData = (HashMap<String, Object>) caldroidFragment.getExtraData();
        Map<String, Integer> myExtradata = new HashMap<String, Integer>();

        extraData.put("MY", greenDate );*/
        //   caldroidFragment.refreshView();

        Log.e("testing","greendate===="+greenDate);

       /* if (caldroidFragment != null) {
            ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
            ColorDrawable green = new ColorDrawable(Color.GREEN);
            caldroidFragment.setBackgroundDrawableForDate(blue, blueDate);
            caldroidFragment.setBackgroundDrawableForDate(green, greenDate);
            caldroidFragment.setTextColorForDate(R.color.white, blueDate);
            caldroidFragment.setTextColorForDate(R.color.white, greenDate);
        }*/
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textview);
        formatter = new SimpleDateFormat("dd MMM yyyy");
        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        //  caldroidFragment = new CaldroidFragment();
        caldroidFragment2 = new CaldroidFragment();
        // //////////////////////////////////////////////////////////////////////
        // **** This is to show customized fragment. If you want customized
        // version, uncomment below line ****
//		 caldroidFragment = new CaldroidSampleCustomFragment();

        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment2.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

            // Uncomment this to customize startDayOfWeek
            args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
                    CaldroidFragment.SUNDAY); // Tuesday

            // Uncomment this line to use Caldroid in compact mode
            args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

            // Uncomment this line to use dark theme
            //   args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);
            args.putInt(CaldroidFragment.THEME_RESOURCE, R.style.CaldroidDefaultDark);


            //  caldroidFragment.setArguments(args);
            caldroidFragment2.setArguments(args);

        }

        setCustomResourceForDates();

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment2);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                //  L.t(getApplicationContext(), formatter.format(date));

                mTextView.setText(formatter.format(date));

                SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");

                String strDate = mdformat.format(date.getTime());

                Log.e("testing","strDate = "+strDate);


                if (pojodateselection == null || pojodateselection.size() == 0) {
                    Log.e("testing", "pojodateselection start = " + pojodateselection.size());
                    DailyEvent feedInfo = new DailyEvent();
                    feedInfo.setDate(strDate);
                    pojodateselection.add(feedInfo);
                    setDateSelection();
                }else{

                    Log.e("testing", "pojodateselection start2 = " + pojodateselection.size());
                    //  ArrayList<String> arrayListkey = new ArrayList<String>();
                    //  arrayListkey.clear();
                    List<Dailyarray> arrayListkey = new ArrayList<Dailyarray>();
                    arrayListkey.clear();

                    String strkeydate = "" ;
                    for (int i = 0; i < pojodateselection.size(); i++) {
                        Log.e("testing", "size before = " + pojodateselection.size());
                        DailyEvent keyitem = pojodateselection.get(i);

                        String keywrd = keyitem.getDate();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");



                        Log.e("testing","dtStart setDateSelection = "+keywrd);
                        Date cleareddate = null;

                        try {
                            cleareddate = format.parse(keywrd);

                            Log.e("testing","date in cleareddate 111 = "+cleareddate);
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }


                        Log.e("testing", "keywrd = " + keywrd);
                        if (strDate.equals(keywrd)) {
                            Log.e("testing", "equal start1 = " + pojodateselection.size());
                            // pojodateselection.remove(strDate);
                            caldroidFragment2.clearBackgroundDrawableForDate(cleareddate);

                            strkeydate = "yes";

                            caldroidFragment2.refreshView();
                        } else {
                            Log.e("testing", "equal start2 = " + pojodateselection.size());

                            strkeydate = "no";
                            Dailyarray feedInfoarray = new Dailyarray();
                            feedInfoarray.setDate(keywrd);
                            arrayListkey.add(feedInfoarray);
                        }


                    }
                    if (strkeydate == null || strkeydate.trim().length() == 0 || strkeydate.trim().equals("null")){

                        Dailyarray feedInfoarray = new Dailyarray();
                        feedInfoarray.setDate(strDate);
                        arrayListkey.add(feedInfoarray);

                    }else if (strkeydate.equals("yes")){

                    }else{
                        Dailyarray feedInfoarray = new Dailyarray();
                        feedInfoarray.setDate(strDate);
                        arrayListkey.add(feedInfoarray);
                    }

                    if (arrayListkey == null || arrayListkey.size() == 0) {
                        Log.e("testing", "arrayListkey start1 = " + arrayListkey.size());
                        pojodateselection.clear();
                        setDateSelection();
                    }  else {
                        pojodateselection.clear();
                        for(int i=0;i<arrayListkey.size();i++) {
                            Log.e("testing", "arrayListkey start1 = " + arrayListkey.size());

                            Dailyarray feedInfoarray = arrayListkey.get(i);


                            DailyEvent feedInfo = new DailyEvent();
                            feedInfo.setDate(feedInfoarray.getDate());
                            pojodateselection.add(feedInfo);
                        }
                        setDateSelection();
                    }

                }




               /* if(mCurrentSelectDate != null){
                    ColorDrawable white = new ColorDrawable(Color.WHITE);
                    caldroidFragment2.setBackgroundDrawableForDate(white, mCurrentSelectDate);
                    caldroidFragment2.setTextColorForDate(R.color.black, mCurrentSelectDate);
                    caldroidFragment2.refreshView();
                }
                ColorDrawable red = new ColorDrawable(Color.RED);
                caldroidFragment2.setBackgroundDrawableForDate(red, date);
                caldroidFragment2.setTextColorForDate(R.color.white, date);
                mCurrentSelectDate = date;
                caldroidFragment2.refreshView();*/
            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                //    L.t(getApplicationContext(), text);
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                //   L.t(getApplicationContext(),
                //         "Long click " + formatter.format(date));
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment2.getLeftArrowButton() != null) {
                    // L.t(getApplicationContext(),
                    //       "Caldroid view is created");
                }
            }

        };

        // Setup Caldroid
        caldroidFragment2.setCaldroidListener(listener);
    }

    private void setDateSelection() {


        Log.e("testing","pojodateselection = "+pojodateselection.size());

        if (pojodateselection == null || pojodateselection.size() == 0){

        }else{


            for(int i=0;i<pojodateselection.size();i++){
                Date greenstrDate;

                DailyEvent feedInfo = pojodateselection.get(i);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dtStart = feedInfo.getDate();

                Log.e("testing","dtStart setDateSelection = "+dtStart);
                greenstrDate = null;

                try {
                    greenstrDate = format.parse(dtStart);

                    Log.e("testing","date in format 111 = "+greenstrDate);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                caldroidFragment2.refreshView();
                if (caldroidFragment2 != null) {

                    // Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.carttheme, null);
                    //  ColorDrawable blue = new ColorDrawable(getResources().getColor(android.R.color.darker_gray));
                    //  ColorDrawable blue = new ColorDrawable(getResources().getDrawable(R.drawable.aboutus));

                    //BackgroundColorSpan sam = new BackgroundColorSpan(getba(R.drawable.calendarcircle))
                    caldroidFragment2.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.badge_circle2), greenstrDate);
                    caldroidFragment2.setTextColorForDate(R.color.white, greenstrDate);


                }

            }


        }
    }
    /**
     * Save current states of the Caldroid here
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (caldroidFragment2 != null) {
            caldroidFragment2.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
