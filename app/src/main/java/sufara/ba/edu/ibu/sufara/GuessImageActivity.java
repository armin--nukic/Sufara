package sufara.ba.edu.ibu.sufara;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class GuessImageActivity extends ActionBarActivity {
    Bundle bundle;
    public static int questionCounter = 0;
    private int trueCounter=0;


    public static ArrayList<String> harfsImages = new ArrayList<>(Arrays.asList("za", "tha", "sin", "sad", "ra", "ba", "kha", "dzim", "ta", "da", "ajn", "kaf", "mim", "nun", "vav", "zal", "lam", "ka", "fa", "ja", "dal", "gajn", "he"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_image);

        bundle = getIntent().getExtras();

        if (savedInstanceState == null) {
            ubaciFragment();
        }
    }
    public int getTrueCounter() {
        return trueCounter;
    }

    public void setTrueCounter(int trueCounter) {
        this.trueCounter = trueCounter;
    }
    public void increaseTrueCounter (){
        this.trueCounter++;
    }

    public void ubaciFragment(){
        questionCounter++;
        //here is fragment for gameactivity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, GuessImageFragment.newInstance(bundle.getString("questionType")))
                .commit();
    }
}
