package com.example.paperdb;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;

public class BookDetailActivity extends AppCompatActivity {

    private ImageView detailImage;
    private EditText detailAuthor;
    private EditText detailTitle;
    private Button buttonExit;
    private Button updateButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        detailImage = findViewById(R.id.detailImage);
        detailAuthor = findViewById(R.id.detailAuthor);
        detailTitle = findViewById(R.id.detailTitle);
        buttonExit = findViewById(R.id.buttonExit);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        String selectedBookTitle = intent.getStringExtra("book");
        Book book = Paper.book().read(selectedBookTitle, null);
        detailAuthor.setText(book.getAuthor());
        detailTitle.setText(book.getTitle());

        ImageView imageView = findViewById(R.id.detailImage);

        String imageName = book.getImagePath();

        if (imageName != null && !imageName.isEmpty()) {
            try {
                int imageResource = getResources().getIdentifier(imageName, "drawable", getPackageName());
                if (imageResource != 0) {
                    imageView.setImageResource(imageResource);
                }
            } catch (Exception e) {
            }
        }
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        updateButton.setOnClickListener(view -> {
            if (selectedBookTitle == null) {
                return;
            }
            String title = detailTitle.getText().toString();
            String autor = detailAuthor.getText().toString();
            if (!title.isEmpty() && !autor.isEmpty()) {
                Book bookUp = new Book( title, autor, imageName);
                Paper.book().write(selectedBookTitle, bookUp);
            }
        });

        deleteButton.setOnClickListener(view -> {
            if (selectedBookTitle == null) {
                return;
            }
            Paper.book().delete(selectedBookTitle);
            Intent intent1 = new Intent(BookDetailActivity.this, MainActivity.class);
            startActivity(intent1);
        });
    }
}