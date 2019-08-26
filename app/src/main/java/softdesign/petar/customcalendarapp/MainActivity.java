package softdesign.petar.customcalendarapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public CalendarCustomView calendarCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarCustomView = (CalendarCustomView)findViewById(R.id.custom_calendar_vvv);
    }
}
