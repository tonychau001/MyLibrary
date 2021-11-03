package com.tonychau.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.bumptech.glide.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private final String ALL_BOOKS_KEY = "all_books";
    private final String ALREADY_READ_BOOKS = "already_read_books";
    private final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private final String FAVORITE_BOOKS = "favorite_books";

    private static Utils instance;

    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if(getAllBooks() == null) {
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if(getAlreadyReadBooks() == null) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getWantToReadBooks() == null) {
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getCurrentlyReadingBooks() == null) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(getFavoriteBooks() == null) {
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        //TODO: add inital data

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "Tony da best", "Tony C.", 1234,
                "https://i.imgur.com/DvpvklR.png", "A story of Tony",
                "A work of Longer desc"));
        books.add(new Book(2, "Zazil da best", "Zazil M.", 1234,
                "https://i.ibb.co/dDTcMRk/Bon-Bon-And-Luna.jpg",
                "A story of Zazil", "A work of Longer desc"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.apply();
    }

    public static synchronized Utils getInstance(Context context) {
        if(instance != null){
            return instance;
        } else {
            instance = new Utils(context);
            return instance;
        }
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return books;
    }

    public  ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }

    public  ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS, null), type);
        return books;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();
        if(books != null) {
            for(Book b: books) {
                if(b.getId() == id) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if(books != null) {
            if(books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if(books != null) {
            if(books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToFavorite(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if(books != null) {
            if(books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(books != null) {
            if(books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if(books != null) {
            for(Book b: books) {
                if(books.remove(b)) {
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(ALREADY_READ_BOOKS);
                    editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeWanToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if(books != null) {
            for(Book b: books) {
                if (books.add(b)) {
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(WANT_TO_READ_BOOKS);
                    editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeFromCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(books != null) {
            for(Book b: books) {
                if (books.add(b)) {
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(CURRENTLY_READING_BOOKS);
                    editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeFromFavorites(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if(books != null) {
            for(Book b: books) {
                if (books.add(book)) {
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(FAVORITE_BOOKS);
                    editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                    editor.commit();
                    return true;
                }
            }
        }
        return false;
    }
}
