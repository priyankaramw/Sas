package southernit.lk.sas;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        textView = (TextView) findViewById(R.id.textView);

        controller = new DB_Controller(this, "", null, 1);

    }

    public void btn_click(View view) {
        switch (view.getId()) {
            case R.id.button_add:
                try {
                    controller.insert_student(firstName.getText().toString(), lastName.getText().toString());
                } catch (SQLiteException e) {
                    Toast.makeText(this, "Already exists", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_delete:
                controller.delete_student(firstName.getText().toString());
                break;
            case R.id.button_update:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Enter new FIRSTNAME ");

                final EditText new_firstname = new EditText(this);
                dialog.setView(new_firstname);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.update_studnet(firstName.getText().toString(), new_firstname.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.button_list:
                controller.list_all_students(textView);
                break;
        }
    }
}
