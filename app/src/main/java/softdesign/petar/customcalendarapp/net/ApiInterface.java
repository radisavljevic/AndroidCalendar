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

    //@GET("events2")
    //Call<List<Events>> getEventsPerMonth();

    @POST("/events2")
    Call<Events> postEvent(@Body Events event);

    @GET("events2/{date}")
    Call<ArrayList<Events>> getEventsPerDate(@Path("date") String date);

    @GET("events2/{mesec}/{godina}")
    Call<List<Events>> getEventsPerMonth(@Path("mesec") String mesec, @Path("godina") String godina);
//    @GET("projekti")
//    Call<List<Projekat>> dajListuProjekata();
//
//    @GET("projekti/{emailKorisnika}")
//    Call<List<Projekat>> dajListuProjekataPoNazivuKorisnika(@Path("emailKorisnika") String emailKorisnika);
//
//    @POST("projekti")
//    Call<Projekat> postujProjekat(@Body Projekat projekat);
//
//    @POST("taskovi")
//    Call<Task> sacuvajTask(@Body Task task);
//
//    @GET("taskovi/{projekatId}")
//    Call<List<Task>> dajListuTaskovaProjekta(@Path("projekatId") String projekatId);
//
//    @GET("taskovi/korisnik/{kreator}")
//    Call<List<Task>> dajListuTaskovaNaKojimaJeKorisnik(@Path("kreator") String kreator);
//
//    @GET("taskovi/zavrseni/{kreator}")
//    Call<List<Task>> dajListuZavrsenihTaskovaKorisnika(@Path("kreator") String kreator);
//
//    @POST("taskovi/update")
//    Call<Task> updateTask(@Body Task task);
//
//    @POST("taskovi/status")
//    Call<Task> updateStatusTask(@Body Task task);
//
//    @POST("korisnici")
//    Call<Korisnik> sacuvajKorisnika(@Body Korisnik korisnik);
//
//    @GET("korisnici")
//    Call<List<Korisnik>> dajSveKorisnike();
//
//    @GET("korisnici/{taskId}")
//    Call<List<Korisnik>> dajKorisnikeTaska(@Path("taskId") int taskId);
//
//    @POST("promet")
//    Call<List<Promet>> sacuvajPromet(@Body List<Promet> prometList);
//
//    @GET("promet/{taskId}")
//    Call<String> obrisiPrometPodIdTaska(@Path("taskId") int taskId);
//
//    @FormUrlEncoded
//    @POST("send")
//    Call<ResponseBody> sendNotification(
//            @Field("token") String token,
//            @Field("title") String title,
//            @Field("body") String body
//    );
//
//    @GET("komercijalisti/{sifra}")
//    Call<Komercijalista> dajKomercijalistu(@Path("sifra") String sifra);
//
//    @GET("komitenti/{sifraKomitenta}")
//    Call<Komitent> dajKomitenta(@Path("sifraKomitenta") String sifra);
//
//    @GET("komitenti/lozinka/{lozinkaKomitenta}")
//    Call<Komitent> dajKomitentaPoLozinci(@Path("lozinkaKomitenta") String lozinka);
//
//    @GET("komitenti/odkomercijaliste/{sifraKomercijaliste}")
//    Call<List<Komitent>> dajKomitentaOdKomercijaliste(@Path("sifraKomercijaliste") String sifra);
//
//    @GET("komitenti/naziv/{naziv}")
//    Call<List<Komitent>> dajKomitentePoNazivu(@Path("naziv") String naziv);
//
//    @GET("radnejedinice/{sifk}")
//    Call<List<RadneJedinice>> dajRadneJediniceodKomitenta(@Path("sifk") String sifra);
//
//    @GET("radnejedinice/odkomercijaliste/{sifkomercijaliste}")
//    Call<List<RadneJedinice>> dajRadneJediniceodKomercijaliste(@Path("sifkomercijaliste") String sifra);
//
//    @GET("radnejedinice/naziv/{naziv}")
//    Call<List<RadneJedinice>> dajRadneJedinicePoNazivuKomitenta(@Path("naziv") String naziv);
//
//    @GET("radnejedinice/nazivradnejedinice/{nazivKomit}/{nazivRJ}")
//    Call<List<RadneJedinice>> dajRadneJedinicePoNazivimaKomitIRj(@Path("nazivKomit") String nazivKomit, @Path("nazivRJ") String nazivRJ);
//
//    @GET("artikli/glavnegrupe")
//    Call<List<GlavneGrupe>> dajGlavneGrupe();
//
//    @GET("artikli/glavnegrupe/{sifk}/{sifRj}")
//    Call<List<GlavneGrupe>> dajGlavneGrupePocedura(@Path("sifk") String sifk, @Path("sifRj") String sifRj);
//
//    @GET("artikli/podgrupe")
//    Call<List<Grupe>> dajGrupe();
//
//    @GET("artikli/podgrupe/{sifk}/{sifRj}")
//    Call<List<Grupe>> dajGrupeProcedura(@Path("sifk") String sifk, @Path("sifRj") String sifRj);
//
//    @POST("trebovanje/dtrebkup")
//    Call<dTreb_Kup> saljiDtrbKup(@Body dTreb_Kup dTreb_kup);
//
//    @POST("trebovanje/trebkuplist")
//    Call<List<Treb_Kup>> saljiTrebovanjeListe(@Body List<Treb_Kup> treb_kup_list);
//
//    @POST("trebovanje/dtrebkupstatus")
//    Call<String> SaljiStatus(@Body String id);
//
//    @Multipart
//    @POST("images/upload")
//    Call<ResponseBody> uploadImage(@Part MultipartBody.Part filePart);
//
//    @Multipart
//    @POST("images/upload")
//    Call<ResponseBody> uploadImage2(@Part MultipartBody.Part filePart, @Part("taskId") RequestBody description, @Part("kreator") RequestBody description2);
//
//
//    @GET("foto/{taskId}")
//    Call<List<Foto>> dajSlikePodIdTask(@Path("taskId") int taskId);
//
//
//    @GET("chat/{taskId}")
//    Call<List<Chat>> dajChatListTaska(@Path("taskId") int taskId);
//
//    @POST("chat")
//    Call<Chat> saljiChat(@Body Chat chat);
//
//    @GET("otvorenestavke/{sifk}")
//    Call<List<OtvoreneStavke>> dajListuOtvorenihStavki(@Path("sifk") String sifk);


    //dodato za filtriranje RJ od komerc i komit!!!!
//    @GET("radnejedinice/odkomercijalisteikomitenta/{sifrakomerc}/{sifKomit}")
//    Call<List<RadneJedinice>> dajRadneJediniceodKomercIKomit(@Path("sifrakomerc") String sifKomerc, @Path("sifKomit") String sifraKomit);
//
//    @POST("foto/chat")
//    Call<AndroidChatNaFoto> saljiChatNaFoto(@Body AndroidChatNaFoto androidChatNaFoto);
//
//    @GET("foto/chat/{fotoId}")
//    Call<List<AndroidChatNaFoto>> dajChatPodFotoId(@Path("fotoId") String fotoId);
//
//    @GET("aktivnosti")
//    Call<List<AndroidAktivnosti>> dajSveAktivnosti();
//
//    @GET("aktivnosti/{kreator}")
//    Call<List<AndroidAktivnosti>> dajMojeAktivnosti(@Path("kreator") String kreator);
}
