package com.tonychau.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new BookRecViewAdapter(this);
        booksRecView = findViewById(R.id.booksRecView);

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "Tony da best", "Tony C.", 1234,
                "https://i.imgur.com/DvpvklR.png", "A story of Tony",
                "A work of Longer desc"));
        books.add(new Book(1, "Zazil da best", "Zazil M.", 1234,
                "https://i.ibb.co/dDTcMRk/Bon-Bon-And-Luna.jpg",
                "A story of Zazil", "A work of Longer desc"));
        adapter.setBooks(books);
    }
}