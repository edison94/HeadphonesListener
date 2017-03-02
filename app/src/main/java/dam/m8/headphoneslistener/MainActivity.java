package dam.m8.headphoneslistener;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button iniciar;
    private Button detener;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializacion de los botones y el TextView que mostrara el estado del servicio
        status = (TextView) findViewById(R.id.status);
        iniciar = (Button) findViewById(R.id.start);
        detener = (Button) findViewById(R.id.stop);

        //Añadimos el listener para iniciar el servicio y cambiar el texto del TextView
        iniciar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                setStatus(getString(R.string.servicio_corriendo));
                startService(new Intent(MainActivity.this, ListenerService.class));
            }
        });

        //Añadimos el listener para detener el servicio y cambiar el texto del TextView
        detener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setStatus(getString(R.string.servicio_detenido));
                stopService(new Intent(MainActivity.this, ListenerService.class));
            }
        });
    }

    //Metodo para cambiar el texto del TextView
    private void setStatus(String stat) {
        status.setText(stat);
    }
}
