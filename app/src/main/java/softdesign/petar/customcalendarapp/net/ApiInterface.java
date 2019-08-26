package softdesign.petar.customcalendarapp.net;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
//import softdesign.petar.bonesa.model.AndroidAktivnosti;
//import softdesign.petar.bonesa.model.AndroidChatNaFoto;
//import softdesign.petar.bonesa.model.Chat;
//import softdesign.petar.bonesa.model.Foto;
//import softdesign.petar.bonesa.model.GlavneGrupe;
//import softdesign.petar.bonesa.model.Grupe;
//import softdesign.petar.bonesa.model.Komercijalista;
//import softdesign.petar.bonesa.model.Komitent;
//import softdesign.petar.bonesa.model.Korisnik;
//import softdesign.petar.bonesa.model.OtvoreneStavke;
//import softdesign.petar.bonesa.model.Projekat;
//import softdesign.petar.bonesa.model.Promet;
//import softdesign.petar.bonesa.model.RadneJedinice;
//import softdesign.petar.bonesa.model.Task;
//import softdesign.petar.bonesa.model.Treb_Kup;
//import softdesign.petar.bonesa.model.dTreb_Kup;
import softdesign.petar.customcalendarapp.Events;


public interface ApiInterface {

    @POST("/events2")
    Call<Events> postEvent(@Body Events event);

    @GET("events2/{date}")
    Call<ArrayList<Events>> getEventsPerDate(@Path("date") String date);

    @GET("events2/{mesec}/{godina}")
    Call<List<Events>> getEventsPerMonth(@Path("mesec") String mesec, @Path("godina") String godina);

}
