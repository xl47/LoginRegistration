package lobanov.loginregistration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.TreeSet;

public class RegistrationActivity extends AppCompatActivity {
    Button confirmButton, cancelButton;
    EditText loginEditText, passwordEditText;
    TextView errorTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        errorTextView = (TextView) findViewById(R.id.errorTextView);
        errorTextView.setText("");
        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmButton = (Button) findViewById(R.id.confirmButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("LoginBase",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (sharedPreferences.contains(loginEditText.getText().toString()))
                {
                    errorTextView.setText("Такой логин уже есть");
                }
                else if(loginEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals(""))
                {
                    errorTextView.setText("пустой логин или пароль недопустимы");
                }
                else
                {
                    errorTextView.setText("Пробуем добавить");
                    editor.putString(loginEditText.getText().toString(), passwordEditText.getText().toString());
                    editor.commit();
                    Intent answerIntent = new Intent();
                    answerIntent.putExtra("LOGIN",loginEditText.getText().toString());
                    answerIntent.putExtra("PASS", passwordEditText.getText().toString());
                    setResult(RESULT_OK,answerIntent);
                    finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        if (id == R.id.action_login) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
