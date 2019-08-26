package softdesign.petar.customcalendarapp.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Petar on 8/3/2017.
 */

public class ApiClient {

    public static final String BASE_URL = "http://89.216.23.159:8484/";
                                            //Bonesa 37.0.68.197:8080
                                            //212.200.120.118
                                            // nedeljkovic novi server 212.200.251.250
                                            //port test 89.216.23.159:8484
                                            //192.168.77.112 moj lokal
                                            //Gombit primergy 95.140.123.38:8080
                                            //Moj test 89.216.23.159:8484
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.MINUTES)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }


}
