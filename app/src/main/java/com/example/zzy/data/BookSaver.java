package com.example.zzy.data;

import android.content.Context;

import com.example.zzy.data.model.Book;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BookSaver {
    public BookSaver(Context context) {
        this.context = context;
    }

    Context context;//用于读写内部文件

    public ArrayList<Book> getBooks() {
        return books;
    }

    ArrayList<Book> books=new ArrayList<Book>();//用于保存数据

    public void save()
    {
        try {
            //序列化
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput("Serializable.txt",Context.MODE_PRIVATE)
            );
            outputStream.writeObject(books);
            outputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Book> load()
    {
        try {
            //返回读入的数据
            ObjectInputStream inputStream = new ObjectInputStream(
                    context.openFileInput("Serializable.txt")
            );
            books= (ArrayList<Book>) inputStream.readObject();
            inputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return books;//返回读入的数据
    }
}
