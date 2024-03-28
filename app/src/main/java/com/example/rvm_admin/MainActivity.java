package com.example.rvm_admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the login button
        Button loginButton = findViewById(R.id.loginbtn);

        // Find the EditText fields for username and password
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);

        // Instantiate the RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Make API request
        ApiManager.fetchData(requestQueue, new ApiManager.ApiListener() {
            @Override
            public void onSuccess(JSONArray response) {
                // Parse JSON response and populate userList
                for (int i = 0; i < response.length(); i++) {
                    try {
                        User user = User.fromJson(response.getJSONObject(i));
                        userList.add(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });

        // Set an OnClickListener on the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the logic to check credentials here
                String inputUsername = usernameEditText.getText().toString().trim();
                String inputPassword = passwordEditText.getText().toString().trim();

                boolean isValid = false;

                for (User user : userList) {
                    if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
                        isValid = true;
                        break;
                    }
                }

                // Display validation result
                if (isValid) {
                    Toast.makeText(MainActivity.this, "Valid credentials", Toast.LENGTH_SHORT).show();
                    // Open MainActivity2 if credentials are valid
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
