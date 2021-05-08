package com.nisyst.ecommerce.ui.main.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.nisyst.ecommerce.R;
import com.nisyst.ecommerce.adapters.AdminPageAdapter;
import com.nisyst.ecommerce.adapters.ClientPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientScreen extends AppCompatActivity {

    @BindView(R.id.tabClient)
    TabLayout tabClient;
    @BindView(R.id.vpClient)
    ViewPager vpClient;

    ClientPageAdapter clientPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_screen);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        try {

            tabClient.addTab(tabClient.newTab().setText("Inventory"));
            tabClient.addTab(tabClient.newTab().setText("Items"));
            tabClient.addTab(tabClient.newTab().setText("Orders"));
            tabClient.setTabGravity(TabLayout.GRAVITY_FILL);

            clientPageAdapter = new ClientPageAdapter(this, getSupportFragmentManager(), tabClient.getTabCount());
            vpClient.setAdapter(clientPageAdapter);

            vpClient.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabClient));
            tabClient.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    vpClient.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

/*
public static void senPushdNotification(final String body, final String title, final String fcmToken) {
    new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                OkHttpClient client = new OkHttpClient();
                JSONObject json = new JSONObject();
                JSONObject notificationJson = new JSONObject();
                JSONObject dataJson = new JSONObject();
                notificationJson.put("text", body);
                notificationJson.put("title", title);
                notificationJson.put("priority", "high");
                dataJson.put("customId", "02");
                dataJson.put("badge", 1);
                dataJson.put("alert", "Alert");
                json.put("notification", notificationJson);
                json.put("data", dataJson);
                json.put("to", fcmToken);
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
                Request request = new Request.Builder()
                        .header("Authorization", "key=YOUR_FCM_KEY")
                        .url("https://fcm.googleapis.com/fcm/send")
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                String finalResponse = response.body().string();
                Log.i("TAG", finalResponse);
            } catch (Exception e) {

                Log.i("TAG", e.getMessage());
            }
            return null;
        }
    }.execute();

    AAAAAAwYszA:APA91bGZZmYgDOztbg051g4BHPqqiMTza8NAR5jycp6neZcSxUXQEat_zn2ekDp8zf_4gNWOpPvbdhbNYETnaxe32j4TyJR8DMKR37HgZBjM8VRrbwoWoC273gGfFCT2kqvzDv5pBPSe
}


* */