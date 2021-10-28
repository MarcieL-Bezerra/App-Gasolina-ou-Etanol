package santosstampasgasolinaeetanol.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    AdView adView;
    EditText Etanol;
    EditText gasolina;
    TextView Lblescolha;
    TextView Lblresultado;
    Button Button;
    ImageView Imgresultado;


    String valgasol = "";
    String valetanol = "";
    String decisao = "";
    Double resultado =0.0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Button = findViewById(R.id.Btnresultado);
        Etanol = findViewById(R.id.Txtetanol);
        gasolina = findViewById(R.id.Txtgasolina);
        Lblescolha = findViewById(R.id.Lblescolha);
        Lblresultado = findViewById(R.id.Lblresultado);
        Imgresultado = findViewById(R.id.Imgresultado);


    }

    public void cliquebotao(View view) {
        EscondeTeclado(view);
        valetanol = Etanol.getText().toString();
        valgasol = gasolina.getText().toString();

        if (valetanol.equals("")) {

            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Campo Vazio");
            alertDialog.setMessage("Favor preencher todos os campos");
            alertDialog.show();
            Lblescolha.setText("");

        } else if (valetanol.equals("0")) {

            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Campo Vazio");
            alertDialog.setMessage("Favor preencher todos os campos");
            alertDialog.show();
            Lblescolha.setText("");

        }else{

            if (valgasol.equals("")) {

                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Campo Vazio");
                alertDialog.setMessage("Favor preencher todos os campos");
                alertDialog.show();
                Lblescolha.setText("");

            } else if (valgasol.equals("0")) {

                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Campo Vazio");
                alertDialog.setMessage("Favor preencher todos os campos");
                alertDialog.show();
                Lblescolha.setText("");

            }else {

                Double nvaletanol = Double.parseDouble(valetanol);
                Double nvalgasol = Double.parseDouble(valgasol);
                resultado = (nvaletanol/nvalgasol);


                if (resultado >= 0.74){

                    decisao = "Gasolina";
                    Imgresultado.setImageResource(R.drawable.g);
                    Lblescolha.setText(decisao);
                    Lblescolha.setTextColor(Color.YELLOW);
                    Lblresultado.setVisibility(View.VISIBLE);
                    Lblresultado.setText("*** Com base no consenso a melhor opção para esse cálculo é: "+ "\n" + decisao + "\n" + "\n"+ "Resultado " + String.format ("%.2f",resultado*100) + "%" +"\n" +"\n" + "*** Na média, uma relação de 73% ou menos do preço do etanol em relação ao preço da gasolina, favorece o uso do etanol.  Se for 74% ou mais, aconselha-se usar gasolina. ");
                }else {

                    decisao = "Etanol";
                    Imgresultado.setImageResource(R.drawable.e);
                    Lblescolha.setText(decisao);
                    Lblescolha.setTextColor(Color.GREEN);
                    Lblresultado.setVisibility(View.VISIBLE);
                    Lblresultado.setText("*** Com base no consenso a melhor opção para esse cálculo é: "+ "\n" + decisao + "\n" + "\n"+ "Resultado " + String.format ("%.2f",resultado*100) + "%" +"\n" +"\n" + "*** Na média, uma relação de 73% ou menos do preço do etanol em relação ao preço da gasolina, favorece o uso do etanol.  Se for 74% ou mais, aconselha-se usar gasolina. ");
                }


                /**Lblescolha.setText("Nd zerado - " + String.format ("%.2f",resultado));*/



            }



        }
    }




    public void EscondeTeclado(View v){
        if(v!=null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

        }

    }
}