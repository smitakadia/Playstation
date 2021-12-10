package com.example.playstation.utils;

import android.text.TextUtils;
import android.text.format.DateUtils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@SuppressWarnings("SpellCheckingInspection")
public class DateUtil {

    public static final String ONLY_DATE_FORMAT = "dd MMMM yyyy";

    public static final String REVERSE_DATE_FORMAT = "yyyy-MM-dd";

    public static final String ONLY_SERVER_DATE_FORMAT = "yyyy-MM-dd";

    public static final String ONLY_TIME_FORMAT = "hh:mm a";

    public static final String EXPIRATION_DATE_FORMAT = "dd/MM/yyyy";
    public static final String Signup_DATE_FORMAT = "yyyy/MM/dd";

    public static final String TIMESTAMP_FORMAT = ONLY_DATE_FORMAT + " | " + ONLY_TIME_FORMAT;
    public static final String SERVER_TIMESTAMP_FORMAT = ONLY_SERVER_DATE_FORMAT + " " + ONLY_TIME_FORMAT;

    private static final String FILENAME_TIMESTAMP_FORMAT = "yyyyMMddHHmmss";

    public static String getTimeFromMillis(long millis) {

        long seconds = millis / 1000;

        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;

        if (seconds >= 1 * 60 * 60) { // Number of seconds in hour

            return (String.format("%02d:%02d:%02d", h, m, s));

        } else {

            return (String.format("%02d:%02d", m, s));
        }


    }

    public static String getDateFormatedddMMYYYY(String date_str) {
        String date_return = "";
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",Locale.ENGLISH);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        try {
            Date date1 = df.parse(date_str);
            DateFormat outputFormatter1 = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
//            outputFormatter1.setTimeZone(TimeZone.getTimeZone("UTC"));
            date_return = outputFormatter1.format(date1); //
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date_return;

    }

    public static String getDateFormated(String date_str) {
        String date_return = "";
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",Locale.ENGLISH);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date1 = df.parse(date_str);
            DateFormat outputFormatter1 = new SimpleDateFormat("dd/MM/yyyy");
//            outputFormatter1.setTimeZone(TimeZone.getTimeZone("UTC"));
            date_return = outputFormatter1.format(date1); //
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date_return;

    }

    public static String getDateFormatedYYYYMMdd(String date_str) {
        String date_return = "";
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",Locale.ENGLISH);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
//        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date date1 = df.parse(date_str);
            DateFormat outputFormatter1 = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
//            outputFormatter1.setTimeZone(TimeZone.getTimeZone("UTC"));
            date_return = outputFormatter1.format(date1); //
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date_return;

    }
  public static String get12HourTime(String date_str) {
        String date_return = "";
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",Locale.ENGLISH);
        DateFormat df = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
//        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            Date date1 = df.parse(date_str);
            DateFormat outputFormatter1 = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
//            outputFormatter1.setTimeZone(TimeZone.getTimeZone("UTC"));
            date_return = outputFormatter1.format(date1); //
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date_return;

    }

    /**
     * Set date's values to calendar instance.
     */
    public static void applyDateToCalendar(String date, Calendar calendar) {

        if (!TextUtils.isEmpty(date)) {

            String[] splits = date.split("-");


            if (splits.length == 3) {

                calendar.set(Calendar.YEAR, Integer.parseInt(splits[2]));
                calendar.set(Calendar.MONTH, Integer.parseInt(splits[1]) - 1);
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splits[0]));
            }
        }
    }

    public static String formatTime(int hour, int minute, String format) {

        String covt = "%02d:%02d";

        String time = String.format(Locale.ENGLISH, covt, hour, minute);

        return convertTimestampFromTo(time, "HH:mm", format);
    }

    public static String invertDate(String date) {

        if (date != null) {

            String[] splits = date.split("-");

            if (splits.length == 3) {

                return splits[2] + "-" + splits[1] + "-" + splits[0];
            }
        }

        return date;
    }

    /**
     * Get Local Date from UTC Date and Time.
     */
    public static String getOnlyLocalDateFromUTC(String date, String time) {

        return formatAndGetLocalTimeFromUTC(date + " " + time, ONLY_DATE_FORMAT);
    }

    /**
     * Get Local Time from UTC Date and Time.
     */
    public static String getOnlyLocalTimeFromUTC(String date, String time) {

        return formatAndGetLocalTimeFromUTC(date + " " + time, ONLY_TIME_FORMAT);
    }

    /**
     * Get Full Local Date from UTC Date and Time.
     */
    public static String getFullLocalTimeFromUTC(String date, String time) {

        return formatAndGetLocalTimeFromUTC(date + " " + time, TIMESTAMP_FORMAT);
    }

    /**
     * Get Local Date from UTC timestamp.
     */
    public static String getOnlyLocalDateFromUtcTimeStamp(long millis) {

        return formatAndGetLocalTimeFromUTCTimeStamp(millis, ONLY_DATE_FORMAT);
    }

    /**
     * Get Local Time from UTC timestamp.
     */
    public static String getOnlyLocalTimeFromUtcTimeStamp(long millis) {

        return formatAndGetLocalTimeFromUTCTimeStamp(millis, ONLY_TIME_FORMAT);
    }

    /**
     * Get Full Local Date from UTC Timestamp.
     */
    public static String getFullLocalTimeFromUtcTimeStamp(long millis) {

        return formatAndGetLocalTimeFromUTCTimeStamp(millis, TIMESTAMP_FORMAT);
    }

    /**
     * Get Local Date on device.
     */
    public static String getCurrentDate() {

        return getDateTimeFromFormat(System.currentTimeMillis(), ONLY_DATE_FORMAT);
    }

    /**
     * Get Local Date in 'yyyy-MM-dd' date format.
     *
     * @return "2019-08-09"
     */
    public static String getInvertedCurrentDate() {

        return getDateTimeFromFormat(System.currentTimeMillis(), REVERSE_DATE_FORMAT);
    }

    /**
     * Get Local Time.
     */
    public static String getCurrentTime() {

        return getDateTimeFromFormat(System.currentTimeMillis(), ONLY_TIME_FORMAT);
    }

    /**
     * Get Local Date and Time.
     */
    public static String getCurrentDateAndTime() {

        return getDateTimeFromFormat(System.currentTimeMillis(), TIMESTAMP_FORMAT);
    }

    /**
     * Get Timestamp for file name. eg. 20190531184530
     */
    public static String getTimeStampForFileName() {

        return getDateTimeFromFormat(System.currentTimeMillis(), FILENAME_TIMESTAMP_FORMAT);
    }

    /**
     * Get Timestamp for date.
     */
    public static long getTimeStampForDate(String date) {

        return getTimeStampFromDateAndTime(date, "12:00 pm");
    }

    /**
     * Get Timestamp for date.
     */
    public static long getTimeStampForServerDate(String date) {

        return getTimeStampFromServerDateAndTime(date, "12:00 pm");
    }

    /**
     * Get date from timestamp.
     */
    public static String getOnlyDateFromTimestamp(long millis) {

        return getDateTimeFromFormat(millis, ONLY_DATE_FORMAT);
    }

    /**
     * Get time from timestamp.
     */
    public static String getOnlyTimeFromTimestamp(long millis) {

        return getDateTimeFromFormat(millis, ONLY_TIME_FORMAT);
    }

    /**
     * Get Date and time from given format.
     */
    private static String getDateTimeFromFormat(long millis, String format) {

        return new SimpleDateFormat(format, Locale.ENGLISH).format(millis);
    }

    /**
     * Get UTC Date from Local Date and Time.
     */
    public static long getUTCTimeStampFromLocalDateTime(String date, String time) {

        return getTimeStampFromDateAndTimeForTimezone(date, time, TimeZone.getDefault());
    }

    /**
     * Get Local timestamp from UTC Date and Time.
     */
    public static long getLocalTimeStampFromUTCDateTime(String date, String time) {

        return getTimeStampFromDateAndTimeForTimezone(date, time, TimeZone.getTimeZone("UTC"));
    }

    public static String convertTimestampFromTo(String timestamp, String from, String to) {

        try {

            SimpleDateFormat sdfF = new SimpleDateFormat(from, Locale.ENGLISH);
            SimpleDateFormat sdfT = new SimpleDateFormat(to, Locale.ENGLISH);

            return sdfT.format(sdfF.parse(timestamp).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
            return timestamp;
        }
    }

    /**
     * Get Local Date/Time from UTC String Timestamp in format.
     */
    public static String formatAndGetLocalTimeFromUTC(String fullTimeStamp, String format) {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT, Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            SimpleDateFormat sdfOutPutToSend = new SimpleDateFormat(format, Locale.ENGLISH);
            sdfOutPutToSend.setTimeZone(TimeZone.getDefault());

            Date gmtDate = sdf.parse(fullTimeStamp);

            return sdfOutPutToSend.format(gmtDate);

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get Local Date from UTC timestamp in given format
     */
    private static String formatAndGetLocalTimeFromUTCTimeStamp(long millis, String format) {

        SimpleDateFormat sdfOutPutToSend = new SimpleDateFormat(format, Locale.ENGLISH);
        sdfOutPutToSend.setTimeZone(TimeZone.getDefault());

        Date gmtDate = new Date(millis);

        return sdfOutPutToSend.format(gmtDate);
    }

    /**
     * Get Local Timestamp from UTC Date and Time for given timezone
     */
    private static long getTimeStampFromDateAndTimeForTimezone(String date, String time, TimeZone timeZone) {

        String fullTimeStamp = date + " " + time;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT, Locale.ENGLISH);
            sdf.setTimeZone(timeZone);

            return sdf.parse(fullTimeStamp).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Get time stamp from given date and time
     */
    private static long getTimeStampFromDateAndTime(String date, String time) {

        String fullTimeStamp = date + " " + time;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT, Locale.ENGLISH);

            return sdf.parse(fullTimeStamp).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Get time stamp from given date and time
     */
    public static String getRelativeDateFromServerTime(String date, String time) {

        String fullTimeStamp = date + " " + time;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(SERVER_TIMESTAMP_FORMAT, Locale.ENGLISH);

            Date d = sdf.parse(fullTimeStamp);

            if (d == null) return "";

            String relativeDate;

            long diff = Math.abs(d.getTime() - System.currentTimeMillis());

            if (diff < DateUtils.SECOND_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
            } else if (diff < DateUtils.MINUTE_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
            } else if (diff < DateUtils.HOUR_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
            } else if (diff < DateUtils.DAY_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS).toString();
            } else if (diff < DateUtils.WEEK_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS).toString();
            } else if (diff < DateUtils.YEAR_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.WEEK_IN_MILLIS).toString();
            } else {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.YEAR_IN_MILLIS).toString();
            }
            return relativeDate;


        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get time stamp from given date and time
     */
    public static String getRelativeDateFromServerTime(String timestamp) {

        String fullTimeStamp = timestamp;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date d = sdf.parse(fullTimeStamp);

            if (d == null) return "";

            String relativeDate;

            long diff = Math.abs(d.getTime() - System.currentTimeMillis());

            if (diff < DateUtils.SECOND_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
            } else if (diff < DateUtils.MINUTE_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
            } else if (diff < DateUtils.HOUR_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
            } else if (diff < DateUtils.DAY_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS).toString();
            } else if (diff < DateUtils.WEEK_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS).toString();
            } else if (diff < DateUtils.YEAR_IN_MILLIS) {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.WEEK_IN_MILLIS).toString();
            } else {
                relativeDate = DateUtils.getRelativeTimeSpanString(d.getTime(), System.currentTimeMillis(), DateUtils.YEAR_IN_MILLIS).toString();
            }
            return relativeDate;

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Get time stamp from given date and time
     */
    private static long getTimeStampFromServerDateAndTime(String date, String time) {

        String fullTimeStamp = date + " " + time;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(SERVER_TIMESTAMP_FORMAT, Locale.ENGLISH);

            return sdf.parse(fullTimeStamp).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }





}
