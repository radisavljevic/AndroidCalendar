package softdesign.petar.customcalendarapp.net;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import retrofit2.Call;
import softdesign.petar.customcalendarapp.EventRecyclerAdapter;
import softdesign.petar.customcalendarapp.Events;

public class ServiceNetClass extends AsyncTask<String, Void, String> {

    private static final String TAG = "ServiceNetClass";
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private AlertDialog alertDialog;
    private Context context;
    private View showView;
    private AlertDialog.Builder builder;
    private String date;
    private ArrayList<Events> arrayList;
    private RecyclerView recyclerView;
    private ProgressDialog p;

    public ServiceNetClass(Context context, View view, AlertDialog alertDialog,
                           AlertDialog.Builder builder, String date, RecyclerView recyclerView) {
        this.alertDialog = alertDialog;
        this.context = context;
        this.showView = view;
        this.builder = builder;
        this.date = date;
        this.recyclerView = recyclerView;
    }



    @Override
    protected String doInBackground(String... strings) {

        Call<ArrayList<Events>> call = apiInterface.getEventsPerDate(date);
        try {
            arrayList = call.execute().body();
            if(arrayList != null)
            Log.e(TAG, arrayList.toArray().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Finished!";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        p = new ProgressDialog(context);
        p.setMessage("Please wait...It is downloading");
        p.setIndeterminate(false);
        p.setCancelable(false);
        p.show();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext()
                , arrayList);
        recyclerView.setAdapter(eventRecyclerAdapter);
        eventRecyclerAdapter.notifyDataSetChanged();

        builder.setView(showView);
        alertDialog = builder.create();
        alertDialog.show();
        p.dismiss();
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
