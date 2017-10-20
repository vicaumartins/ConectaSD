package br.com.vicaumartins.conectasd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity {
    Button barCodeButton;
    TextView msg, nome, descricao, valor, categoria;
    String barcodeReaderResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barCodeButton = (Button) findViewById(R.id.barCode);
        msg = (TextView) findViewById(R.id.msg);
        nome = (TextView) findViewById(R.id.nome);
        descricao = (TextView) findViewById(R.id.descricao);
        valor = (TextView) findViewById(R.id.valor);
        categoria = (TextView) findViewById(R.id.categoria);

        barCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanBarcode(view);
            }
        });
    }

    public void scanBarcode(View v) {
        Intent intent = new Intent(this, CodeReader.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            if(requestCode == CommonStatusCodes.SUCCESS){
                if(data != null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    barcodeReaderResult = barcode.displayValue;
                    msg.setText(barcodeReaderResult);
                }else{
                    resetTextViews();
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public void resetTextViews(){
        msg.setText("");
        nome.setText("");
        descricao.setText("");
        categoria.setText("");
        valor.setText("");
    }
}
