package clwater.xposelearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        textView.setText("main");

        Log.i("gzb", "before inflate");
        getLayoutInflater().inflate(R.layout.activity_main2, null);
        Log.i("gzb", "after inflate");
    }
}
