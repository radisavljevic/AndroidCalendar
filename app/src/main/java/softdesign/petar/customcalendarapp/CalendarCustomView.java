package softdesign.petar.customcalendarapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import softdesign.petar.customcalendarapp.net.ApiClient;
import softdesign.petar.customcalendarapp.net.ApiInterface;
import softdesign.petar.customcalendarapp.net.ServiceNetClass;
import softdesign.petar.customcalendarapp.net.ServiceNetClass2;

public class CalendarCustomView extends LinearLayout {

    private static final String TAG = CalendarCustomView.class.getSimpleName();
    private ImageView previousButton, nextButton;
    private TextView currentDate;
    private GridView gridView;
    private Button addEventButton;
    private static final int MAX_CALENDAR_DAYS = 42;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    private SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private DBOpenHelper dbOpenHelper;
    private Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    //private GridAdapter mAdapter;
    //private DatabaseQuery mQuery;
    MyGridAdapter myGridAdapter;
    AlertDialog alertDialog;
    List<Date> dates = new ArrayList<>();
    List<Events> eventsList;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public CalendarCustomView(Context context) {
        super(context);
    }

    public CalendarCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CalendarCustomView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeLayout();
        setUpCalendar();
        setUpPreviousButton();
        setUpNextButton();
        setGridCellClickEvents();
        setGridCellOnLongClickEvents();
    }

    private void setUpPreviousButton() {
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH, -1);
                setUpCalendar();
            }
        });
    }

    private void setUpNextButton() {
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.add(Calendar.MONTH, 1);
                setUpCalendar();
            }
        });
    }

    private void setGridCellClickEvents() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                final View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_new_event_layout, null);
                final EditText EventName = addView.findViewById(R.id.eventname);
                final TextView EventTime = addView.findViewById(R.id.eventtime);
                ImageButton SetTime = addView.findViewById(R.id.seteventtime);
                Button AddEvent = addView.findViewById(R.id.addevent);
                SetTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minutes = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog
                                , new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat hformate = new SimpleDateFormat("K:mm a", Locale.ENGLISH);
                                String event_time = hformate.format(c.getTime());
                                EventTime.setText(event_time);
                            }
                        }, hours, minutes, false);
                        timePickerDialog.show();
                    }
                });
                final String date = eventDateFormat.format(dates.get(position));
                final String month = monthFormat.format(dates.get(position));
                final String year = yearFormat.format(dates.get(position));

                AddEvent.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        saveEvent2(EventName.getText().toString(),
                                EventTime.getText().toString(), date, month, year);
                        setUpCalendar();
                        alertDialog.dismiss();
                    }
                });

                builder.setView(addView);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    private void setGridCellOnLongClickEvents() {
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final String date = eventDateFormat.format(dates.get(position));

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                final View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout, null);

                final RecyclerView recyclerView = showView.findViewById(R.id.EventsRV);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);

                ServiceNetClass serviceNetClass = new ServiceNetClass(context, showView, alertDialog, builder, date, recyclerView);
                serviceNetClass.execute();


//                EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext()
//                        ,collectEventByDate2(date));
//                recyclerView.setAdapter(eventRecyclerAdapter);
//                eventRecyclerAdapter.notifyDataSetChanged();
//
//                builder.setView(showView);
//                alertDialog = builder.create();
//                alertDialog.show();
//
                return true;
            }
        });
    }

    private ArrayList<Events> collectEventByDate(String date) {
        ArrayList<Events> arrayList = new ArrayList<>();
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readEvents(date, database);
        while (cursor.moveToNext()) {
            String event = cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
            String time = cursor.getString(cursor.getColumnIndex(DBStructure.TIME));
            String Date = cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
            String month = cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
            String year = cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));
            Events events = new Events(event, time, Date, month, year);
            arrayList.add(events);
        }
        cursor.close();
        dbOpenHelper.close();

        return arrayList;
    }

    private void saveEvent(String event, String time, String date, String month, String year) {
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.saveEvent(event, time, date, month, year, database);
        dbOpenHelper.close();
        Toast.makeText(context, "Event saved", Toast.LENGTH_SHORT).show();

    }

    private void saveEvent2(String event, String time, String date, String month, String year) {
        Events Event = new Events(event, time, date, month, year);
        Log.e(TAG, Event.toString());
        Call<Events> call = apiInterface.postEvent(Event);
        call.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Uspešno sačuvan novi Event", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Neuspešno sačuvan novi Event", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Toast.makeText(context, "Neuspešno sačuvan novi Event, failed", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });

    }

    private void initializeLayout() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout, this);
        nextButton = view.findViewById(R.id.next_month);
        previousButton = view.findViewById(R.id.previous_month);
        currentDate = view.findViewById(R.id.display_current_date);
        gridView = view.findViewById(R.id.calendar_grid);
    }

    private void setUpCalendar() {
        eventsList = new ArrayList<>();
        String currDate = dateFormat.format(calendar.getTime());
        currentDate.setText(currDate);
        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth);
        //collectEventPerMonth2(monthFormat.format(calendar.getTime()), yearFormat.format(calendar.getTime()));
        while (dates.size() < MAX_CALENDAR_DAYS) {
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);

        }

        ServiceNetClass2 serviceNetClass2 = new ServiceNetClass2(context,alertDialog,
                monthFormat.format(calendar.getTime()), yearFormat.format(calendar.getTime()),
                eventsList,dates,gridView,calendar);
         serviceNetClass2.execute();

//        Call<List<Events>> call = apiInterface.getEventsPerMonth(monthFormat.format(calendar.getTime()), yearFormat.format(calendar.getTime()));
//        call.enqueue(new Callback<List<Events>>() {
//            @Override
//            public void onResponse(Call<List<Events>> call, Response<List<Events>> response) {
//                if (response.isSuccessful()) {
//
//
//                    List<Events> listaEvents = response.body();
//                    eventsList.addAll(listaEvents);
//                    for (Events e : listaEvents) {
//                        Log.e(TAG, e.toString());
//                    }
//
//
//                    myGridAdapter = new MyGridAdapter(context, dates, calendar, eventsList);
//                    gridView.setAdapter(myGridAdapter);
//
//
//                    Toast.makeText(context, "Uspešno učitani Eventovi", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(context, "Neuspešno učitan Event", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Events>> call, Throwable t) {
//                Toast.makeText(context, "Neuspešno učitan Event, failed", Toast.LENGTH_LONG).show();
//                t.printStackTrace();
//            }
//        });
//
//        while (dates.size() < MAX_CALENDAR_DAYS) {
//            dates.add(monthCalendar.getTime());
//            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
//
//        }
//        myGridAdapter = new MyGridAdapter(context, dates, calendar, eventsList);
//        gridView.setAdapter(myGridAdapter);


    }

    private void collectEventPerMonth(String Month, String Year) {
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.readEventsPerMonth(Month, Year, database);
        while (cursor.moveToNext()) {
            String event = cursor.getString(cursor.getColumnIndex(DBStructure.EVENT));
            String time = cursor.getString(cursor.getColumnIndex(DBStructure.TIME));
            String date = cursor.getString(cursor.getColumnIndex(DBStructure.DATE));
            String month = cursor.getString(cursor.getColumnIndex(DBStructure.MONTH));
            String year = cursor.getString(cursor.getColumnIndex(DBStructure.YEAR));
            Events events = new Events(event, time, date, month, year);
            eventsList.add(events);
            Log.e("AAA", "event: " + event + "time: " + time + "DATE: " + date + "Month: " + month + "Year: " + year);
        }
        cursor.close();
        dbOpenHelper.close();
    }

    private void collectEventPerMonth2(String Month, String Year) {
        Call<List<Events>> call = apiInterface.getEventsPerMonth(Month, Year);
        call.enqueue(new Callback<List<Events>>() {
            @Override
            public void onResponse(Call<List<Events>> call, Response<List<Events>> response) {
                if (response.isSuccessful()) {
                    List<Events> listaEvents = response.body();
                    eventsList.addAll(listaEvents);
                    for (Events e : listaEvents) {
                        Log.e("event", e.toString());
                    }
                    Toast.makeText(context, "Uspešno učitani Eventovi", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Neuspešno učitan Event", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Events>> call, Throwable t) {
                Toast.makeText(context, "Neuspešno učitan Event, failed", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }


}
