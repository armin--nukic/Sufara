package sufara.ba.edu.ibu.sufara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class LectureActivity extends ActionBarActivity {

    String harf;
    String[] arr = {"alif", "da", "ha", "shin", "sin", "ta", "thsa", "ra", "za", "sad", "dzim", "ba", "tha", "kha", "ajn"};
    String data = arr[0];

    int i = 0;

    int resID;

    int resLecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        Intent intent = getIntent();

        // 2. get message value from intent
        harf = intent.getStringExtra("harf");

        Button btnGame = (Button)findViewById(R.id.btnGame);

        Button btnNext = (Button)findViewById(R.id.btnNext);
        Button btnPrev = (Button)findViewById(R.id.btnPrev);
        final TextView txtLecture = (TextView)findViewById(R.id.txtLecture);
        final ImageView imgHarf = (ImageView)findViewById(R.id.imgHarf);

        resID = getResources().getIdentifier(harf, "drawable", getPackageName());
        imgHarf.setImageResource(resID);

        resLecture = getResources().getIdentifier(harf+"_lecture", "string", getPackageName());
        txtLecture.setText(resLecture);

        //txtLecture.setText(data);

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LectureActivity.this, GameActivity.class);

                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i < 6) {
                    i++;
                }

                data = arr[i];

                resID = getResources().getIdentifier(data, "drawable", getPackageName());
                imgHarf.setImageResource(resID);

                resLecture = getResources().getIdentifier(data + "_lecture", "string", getPackageName());
                txtLecture.setText(resLecture);

            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i > 0) {
                    i--;
                }

                data = arr[i];

                resID = getResources().getIdentifier(data, "drawable", getPackageName());
                imgHarf.setImageResource(resID);

                resLecture = getResources().getIdentifier(data + "_lecture", "string", getPackageName());
                txtLecture.setText(resLecture);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lecture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
