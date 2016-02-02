package com.pccoe.emergency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * It gives items to show on the {@link android.widget.ListView}
 * on the {@link MainActivity}
 *
 * @author Akshay Chordiya
 * @since 27/12/2015.
 */
public class EmergencyAdapter extends ArrayAdapter<Emergency> {

    private ArrayList<Emergency> mSongs;

    public EmergencyAdapter(Context context, ArrayList<Emergency> mSongs) {
        super(context, R.layout.row_layout, mSongs);
        this.mSongs = mSongs;
    }

    /**
     * Give UI of each item to Adapter
     * @param position of the item
     * @param convertView view of the item
     * @param parent parent of item
     * @return the complete UI of current position item
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout,
                    parent, false);
            holder = new ViewHolder();
            holder.mSongName = (TextView) convertView.findViewById(R.id.title);
            holder.mSongArtist = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Emergency song = mSongs.get(position);
        holder.mSongName.setText(song.getName());
        holder.mSongArtist.setText(Integer.toString(song.getNumber()));
        return convertView;
    }

    /**
     * Tells the adapter how many items are there
     * @return number of items
     */
    @Override
    public int getCount() {
        return mSongs.size();
    }

    /**
     * View Holder which holds the UI
     */
    static class ViewHolder {
        TextView mSongName;
        TextView mSongArtist;
    }
}
