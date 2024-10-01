package fr.isep.td1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.text.TextUtils; // For validation
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    private Button editButton, colorButton, sizeButton;
    private TextView textView;
    private EditText editText, sizeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize components
        initViews();

        //Set button click listeners
        setButtonListeners();

    }

    private void initViews(){
        editButton = findViewById(R.id.edit_button);
        colorButton = findViewById(R.id.color_button);
        sizeButton = findViewById(R.id.size_button);
        textView = findViewById(R.id.text);
        editText = findViewById(R.id.edit_text);
        sizeText = findViewById(R.id.new_size);
    }

    private void setButtonListeners () {
        //Edit text button
        editButton.setOnClickListener(view -> {
            String newText = editText.getText().toString();
            if (!newText.isEmpty()) {
                textView.setText(newText);
                editText.setText(""); //Clear the input field
            }
        });

        // Change text color button
        colorButton.setOnClickListener(view -> {
            Random random = new Random();
            int randomColor = Color.rgb(
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256)
            );
            textView.setTextColor(randomColor);
        });

        // Change text size button
        sizeButton.setOnClickListener(view -> {
            String newSizeInput = sizeText.getText().toString();
            if (!TextUtils.isEmpty(newSizeInput)) {
                try {
                    float newSize = Float.parseFloat(newSizeInput);
                    if (newSize >= 10 && newSize <= 100) { // Validate size limits
                        textView.setTextSize(newSize);
                        sizeText.setText(""); // Clear the input field
                    } else {
                        sizeText.setError("Size must be between 10 and 100");
                    }
                } catch (NumberFormatException e) {
                    sizeText.setError("Please enter a valid number");
                }
            }
        });
    }
}