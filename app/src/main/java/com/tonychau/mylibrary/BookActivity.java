package com.tonychau.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initView();

        String desc = "Arguably a masterpiece of the Science Fiction genre, " + "\n" +
                "Dune laid the foundation for many of the epics that followed its publication. " + "\n" +
                "Herbert addresses overarching themes of family politics, religion, " +"\n" +
                "environment, and the all-encompassing temptation of power, " +"\n" +
                "all set against the backdrop of a futuristic civilization that, " +"\n" +
                "at times, can be unflinchingly prophetic. " +"\n" +
                "You will taste the sand in your mouth and soon be hungry for the next installment.";
        //TODO: Get the data from recycler view in here
        Book book = new Book(1, "Tony da best", "Tony C.", 1234,
                "https://i.imgur.com/DvpvklR.png", "A story of Tony",
                desc);

        setData(book);
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageURL())
                .into(bookImage);
    }

    private void initView() {
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToWantToRead = findViewById(R.id.btnAddToWantToReadList);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyReadingList);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorite);

        bookImage = findViewById(R.id.imgBook);
    }
}