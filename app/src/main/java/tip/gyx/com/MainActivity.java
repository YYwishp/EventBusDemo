package tip.gyx.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册订阅者
        EventBus.getDefault().register(this);

        button = (Button) findViewById(R.id.button);


        text = (TextView) findViewById(R.id.text);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

    }

    @Subscribe
    public void onMessageEvent(MyEvent event) {
        String string = event.getString();

        text.setText(string);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        //注销订阅者
        EventBus.getDefault().unregister(this);
    }

}
