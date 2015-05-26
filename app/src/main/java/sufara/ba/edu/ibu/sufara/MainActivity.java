package sufara.ba.edu.ibu.sufara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    Button harfoviBtn, lecturesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        harfoviBtn = (Button)findViewById(R.id.harfoviBtn);
        lecturesBtn = (Button)findViewById(R.id.buttonlectures);

        lecturesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentt = new Intent(MainActivity.this,ListLecturesActivity.class);
                startActivity(intentt);
            }
        });

        harfoviBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuessImageActivity.questionCounter = 0;
                Intent intent = new Intent(MainActivity.this, GuessImageActivity.class);
                intent.putExtra("questionType", "harfovi");
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
