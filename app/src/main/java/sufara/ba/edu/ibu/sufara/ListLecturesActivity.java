package sufara.ba.edu.ibu.sufara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListLecturesActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);




        ListView lecturesList = (ListView)findViewById(R.id.lecturesList);

        String[] values = new String[] {"alif", "da", "ha", "shin", "sin", "ta", "thsa", "ra", "za", "sad", "dzim", "ba", "tha", "kha", "ajn"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        lecturesList.setAdapter(adapter);

        lecturesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String harf = String.valueOf(parent.getItemAtPosition(position));

                Intent intent = new Intent(ListLecturesActivity.this, LectureActivity.class);

                intent.putExtra("harf", harf);

                Bundle harfData = new Bundle();
                harfData.putString("data", "Harf "+harf);
                intent.putExtras(harfData);

                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lectures, menu);
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
