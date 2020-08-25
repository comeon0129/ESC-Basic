package com.example.eosbasicapp;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.w3c.dom.Text;

public class ContactActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView avatar;
    private TextView name;
    private TextView phone;
    private TextView email;
    private ImageButton prev;
    private TextView page;
    private ImageButton next;

    private int currentpage=0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        setUpUI();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setInfo(currentpage);
    }

    private void setUpUI(){
        toolbar = findViewById(R.id.contact_toolbar);
        avatar = findViewById(R.id.contact_iv_avatar);
        name = findViewById(R.id.contact_tv_name);
        phone = findViewById(R.id.contact_tv_phone);
        email = findViewById(R.id.contact_tv_email);
        prev = findViewById(R.id.contact_ibtn_prev);
        page = findViewById(R.id.contact_tv_page);
        next = findViewById(R.id.contact_ibtn_next);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentpage--;
                setInfo(currentpage);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentpage++;
                setInfo(currentpage);
            }
        });
    }

    private void setInfo(int index){
        name.setText(DummyData.contacts.get(index).getName());
        phone.setText(DummyData.contacts.get(index).getPhone());
        email.setText(DummyData.contacts.get(index).getEmail());

        if(index==0){
            prev.setVisibility(View.GONE);
        }else{
            prev.setVisibility(View.VISIBLE);
        }

        if(index == DummyData.contacts.size()-1){
            next.setVisibility(View.GONE);
        }else{
            next.setVisibility(View.VISIBLE);
        }

        page.setText((index+1)+"/"+DummyData.contacts.size());
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            case R.id.menu_contact_call:
                Intent callIntent= new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ phone.getText()));
                startActivity(callIntent);
                break;

            case R.id.menu_contact_edit:
                //TODO:수정
                break;

            case R.id.menu_contact_message:
                Intent messageIntent= new Intent(ContactActivity.this,MessageActivity.class);
                messageIntent.putExtra("phone_num",phone.getText().toString());
                startActivity(messageIntent);
                break;

            case R.id.menu_contact_delete:
                DummyData.contacts.remove(currentpage);

                if(DummyData.contacts.size()==0){
                    Toast.makeText(ContactActivity.this,"보여줄 연락처가 없습니다.",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    if(currentpage!=0){
                        currentpage--;
                    }
                    setInfo(currentpage);

                }
        }

        return super.onOptionsItemSelected(item);
    }
}