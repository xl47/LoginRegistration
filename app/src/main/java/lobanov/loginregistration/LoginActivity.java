package lobanov.loginregistration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    Button registrationButton;
    EditText loginEditText, passwordEditText;
    TextView errorTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        errorTextView = (TextView) findViewById(R.id.errorTextView);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                SharedPreferences sharedPreferences = getSharedPreferences("LoginBase", MODE_PRIVATE);
                if (sharedPreferences.contains(loginEditText.getText().toString()))
                {
                    if(sharedPreferences.getString(loginEditText.getText().toString(),"").equals(passwordEditText.getText().toString()))
                    {
                        errorTextView.setText("Welcome, " + loginEditText.getText().toString());
                    }
                    else
                    {
                        errorTextView.setText("Wrong password");
                    }
                }
                else
                {
                    errorTextView.setText("No such login");
                }
            }
        });

        registrationButton = (Button) findViewById(R.id.registrationButton);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorTextView.setText("");
                Context context = getApplicationContext();
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivityForResult(intent, 0);
            }
        });



       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                loginEditText.setText(data.getStringExtra("LOGIN"));
                passwordEditText.setText(data.getStringExtra("PASS"));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_registration) {
            errorTextView.setText("");
            Context context = getApplicationContext();
            Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
            startActivityForResult(intent, 0);
        }

        return super.onOptionsItemSelected(item);
    }
}
