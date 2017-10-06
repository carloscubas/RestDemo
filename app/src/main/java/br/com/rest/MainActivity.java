package br.com.rest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import br.com.rest.api.ApiClient;
import br.com.rest.api.ApiServices;
import br.com.rest.api.Contatos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private ApiServices apiServices;
    private Contatos contatos;
    private ListaAdapter adapter;
    private ProgressDialog progress = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = ProgressDialog.show(MainActivity.this,
                "Aguarde ...", "Recebendo informações da web", true, true);

        lista = (ListView) findViewById(R.id.lista);

        apiServices = ApiClient.getClient().create(ApiServices.class);

        Call<Contatos> call = apiServices.findAll("application/json");
        call.enqueue(new Callback<Contatos>() {
            @Override
            public void onResponse(Call<Contatos> call, Response<Contatos> response) {

                if (response.isSuccessful()) {
                    contatos = response.body();
                    adapter = new ListaAdapter(getApplicationContext(), contatos.getContatos());
                    lista.setAdapter(adapter);
                    progress.dismiss();
                } else {
                    System.out.println(response.errorBody());
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Contatos> call, Throwable t) {
                t.printStackTrace();
            }

        });

    }
}
