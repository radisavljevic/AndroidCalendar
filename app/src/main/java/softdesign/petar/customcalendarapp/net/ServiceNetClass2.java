package softdesign.petar.customcalendarapp.net;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import softdesign.petar.customcalendarapp.Events;
import softdesign.petar.customcalendarapp.MyGridAdapter;

public class ServiceNetClass2 extends AsyncTask<String, Void, String> {

    private static final String TAG = "ServiceNetClass2";
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private AlertDialog alertDialog;
    private Context context;
    private String mesec, godina;
    private List<Events> listaEvents;
    private List<Date> dates;
    private GridView gridView;
    private Calendar calendar;
    private ProgressDialog p;
    private MyGridAdapter myGridAdapter;

    public ServiceNetClass2(Context context, AlertDialog alertDialog,
                            String mesec, String godina, List<Events> listaEvents, List<Date> dates,
                            GridView gridView, Calendar calendar) {
        this.alertDialog = alertDialog;
        this.context = context;
        this.mesec = mesec;
        this.godina = godina;
        this.listaEvents = listaEvents;
        this.dates = dates;
        this.gridView = gridView;
        this.calendar = calendar;
    }

    @Override
    protected String doInBackground(String... strings) {

        Call<List<Events>> call = apiInterface.getEventsPerMonth(mesec, godina);
        try {
            listaEvents = call.execute().body();
            if (listaEvents != null)
                Log.e(TAG, listaEvents.toArray().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Uspešno učitani Eventovi";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        myGridAdapter = new MyGridAdapter(context, dates, calendar, listaEvents);
        gridView.setAdapter(myGridAdapter);
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }
}
