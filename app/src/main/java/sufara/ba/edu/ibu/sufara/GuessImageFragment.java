package sufara.ba.edu.ibu.sufara;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GuessImageFragment extends Fragment {

    ImageView imageView;
    RadioGroup radioGroup;
    Button nextBtn;
    String questionType = "";
    Random rn = new Random();
    //ovo je instanca, tj. objekat
    ActionBarActivity mActivity;
    Vibrator vibrator;
    Toast toast;
    String randomImage;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;

    public static GuessImageFragment newInstance(String questionType) {
        GuessImageFragment fragment = new GuessImageFragment();
        Bundle args = new Bundle();
        args.putString("questionType", questionType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity=(ActionBarActivity)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionType = getArguments().getString("questionType");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_guess_image, container, false);

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        mActivity.getSupportActionBar().setTitle("Answer question " + GuessImageActivity.questionCounter);
        mActivity.getSupportActionBar().setSubtitle("Score: " + (((GuessImageActivity) mActivity).getTrueCounter() * 10));

        imageView = (ImageView) rootView.findViewById(R.id.image);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) rootView.findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) rootView.findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) rootView.findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) rootView.findViewById(R.id.radioButton4);
        nextBtn = (Button) rootView.findViewById(R.id.nextBtn);

        vibrator = ((Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE));

        ArrayList<String> answers = new ArrayList<>();

        switch (questionType){

            case "harfovi":
                randomImage = GuessImageActivity.harfsImages.get(rn.nextInt(11));
                int res3 = getResources().getIdentifier(randomImage, "drawable", mActivity.getPackageName());
                imageView.setImageResource(res3);

                answers = generateFalseAnswersVehicles(randomImage);
                break;
        }

        Collections.shuffle(answers);

        radioButton1.setText(answers.get(0));
        radioButton2.setText(answers.get(1));
        radioButton3.setText(answers.get(2));
        radioButton4.setText(answers.get(3));



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton answerRB = (RadioButton)rootView.findViewById(radioGroup.getCheckedRadioButtonId());

                if (randomImage.equals(answerRB.getText().toString().toLowerCase())){
                    showToast(true, "THIS IS TRUE");
                    ((GuessImageActivity) mActivity).increaseTrueCounter();
                }else {
                    showToast(false, "FALSE, The correct answer is " + randomImage.toUpperCase());
                    vibrator.vibrate(750);// vibration for 650 milliseconds
                }

                if (GuessImageActivity.questionCounter<12) {
                    ((GuessImageActivity) mActivity).ubaciFragment();
                    Log.i("GuessImageFragment", "insert fragment");
                }else{
                    Log.i("GuessImageFragment", "show score");
                    AlertDialog.Builder scoreDialog = new AlertDialog.Builder(getActivity());
                    scoreDialog.setTitle("Your score is: " + ((GuessImageActivity) mActivity).getTrueCounter()*10);
                    scoreDialog.setMessage("True answers: " + ((GuessImageActivity) mActivity).getTrueCounter() + "\nFalse answers: " + (10-((GuessImageActivity) mActivity).getTrueCounter()));
                    scoreDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GuessImageActivity.questionCounter=0;
                            ((GuessImageActivity) mActivity).setTrueCounter(0);
                            mActivity.finish();
                        }
                    });
                    scoreDialog.show();
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //ovo ispod je dodano nakon sto sam ovo komentirao ispod
                nextBtn.setEnabled(true);

            }
        });

        return rootView;
    }

    public ArrayList<String> generateFalseAnswersVehicles (String trueAnswer){
        ArrayList<String> randomAnswers = new ArrayList<>();
        randomAnswers.add(trueAnswer);
        do{
            String falseAnswer = GuessImageActivity.harfsImages.get(rn.nextInt(5));
            if (!randomAnswers.contains(falseAnswer))
                randomAnswers.add(falseAnswer);
        }while(randomAnswers.size() < 4);

        return randomAnswers;
    }

    public void showToast (boolean isCorrect, String text){
        Toast toast = new Toast(mActivity);

        View toastView = mActivity.getLayoutInflater().inflate(R.layout.custom_toast, null);
        ImageView toastImage = (ImageView) toastView.findViewById(R.id.toastImage);
        TextView toastText = (TextView) toastView.findViewById(R.id.toastText);

        toastText.setText(text);
        toastText.setTextColor(isCorrect ? Color.BLUE : Color.RED);
        toastImage.setImageResource(isCorrect ? R.drawable.correct : R.drawable.incorrect);


        toast.setGravity(Gravity.CENTER, 0, 17);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);

        toast.show();
    }
}
