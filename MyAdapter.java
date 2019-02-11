package com.example.kontess.booklibrary;


        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import java.util.List;

/**
 * Created by Kontess on 14.5.2018.
 */

public class MyAdapter extends BaseAdapter {


    private LayoutInflater inflater;
    private List<Book> booklist;

    public MyAdapter(Activity activity, List<Book> booklist) {
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.booklist = booklist;
    }


    @Override
    public int getCount() {
        return this.booklist.size();
    }

    @Override
    public Object getItem(int position) {
        return this.booklist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View adapterView;
        adapterView = inflater.inflate(R.layout.liste_gorunumu, null);

        // name , subject, author , deathyear

        TextView name = (TextView) adapterView.findViewById(R.id.lbl_kitapadi);
        TextView subject = (TextView) adapterView.findViewById(R.id.lbl_konusu);
        TextView author = (TextView) adapterView.findViewById(R.id.lbl_yazar);
        TextView deathyear = (TextView) adapterView.findViewById(R.id.lbl_yazarolum);


        Book book = booklist.get(position);

        name.setText(book.getName());
        subject.setText(book.getSubjects());
        author.setText(book.getAuthor());
        deathyear.setText(book.getDeathyear().toString());


        return adapterView;
    }
}
