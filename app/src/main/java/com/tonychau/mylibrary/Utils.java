package com.tonychau.mylibrary;

import com.bumptech.glide.util.Util;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils() {
        if(allBooks == null) {
            allBooks = new ArrayList<>();
            initData();
        }
        if(alreadyReadBooks == null) {
            alreadyReadBooks = new ArrayList<>();
        }
        if(wantToReadBooks == null) {
            wantToReadBooks = new ArrayList<>();
        }
        if(currentlyReadingBooks == null) {
            currentlyReadingBooks = new ArrayList<>();
        }
        if(favoriteBooks == null) {
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        //TODO: add inital data

        allBooks.add(new Book(1, "Tony da best", "Tony C.", 1234,
                "https://i.imgur.com/DvpvklR.png", "A story of Tony",
                "A work of Longer desc"));
        allBooks.add(new Book(2, "Zazil da best", "Zazil M.", 1234,
                "https://i.ibb.co/dDTcMRk/Bon-Bon-And-Luna.jpg",
                "A story of Zazil", "A work of Longer desc"));
    }

    public static synchronized Utils getInstance() {
        if(instance != null){
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBookById(int id) {
        for(Book b: allBooks) {
            if(b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        return alreadyReadBooks.add(book);
    }
    public boolean addToWantToRead(Book book) {
        return wantToReadBooks.add(book);
    }
    public boolean addToFavorite(Book book) {
        return favoriteBooks.add(book);
    }
    public boolean addToCurrentlyReading(Book book) {
        return currentlyReadingBooks.add(book);
    }
}
