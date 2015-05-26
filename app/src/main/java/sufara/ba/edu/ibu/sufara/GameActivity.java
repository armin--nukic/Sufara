package sufara.ba.edu.ibu.sufara;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class GameActivity extends ActionBarActivity {

    ImageView imgAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button btnNextGame = (Button)findViewById(R.id.btnNextGame);
        Button btnPrevGame = (Button)findViewById(R.id.btnPrevGame);

        ImageView img1 = (ImageView)findViewById(R.id.img1);
        ImageView img2 = (ImageView)findViewById(R.id.img2);
        ImageView img3 = (ImageView)findViewById(R.id.img3);
        ImageView img4 = (ImageView)findViewById(R.id.img4);
        ImageView img5 = (ImageView)findViewById(R.id.img5);
        ImageView img6 = (ImageView)findViewById(R.id.img6);

        imgAnswer = (ImageView)findViewById(R.id.imgAnswer);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resID = getResources().getIdentifier("alif", "drawable", getPackageName());
                imgAnswer.setImageResource(resID);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
