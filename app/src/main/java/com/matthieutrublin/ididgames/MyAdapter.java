package com.matthieutrublin.ididgames;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by matthieutrublin on 28/12/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ImageInstagram[] setImg;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView blockImage;
        public ViewHolder(ImageView i) {
            super(i);
            blockImage = i;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ImageInstagram[] img) {
        setImg = img;
        //MainActivity.mDataset = img;
    }



    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        // create a new view
        ImageView blockImage = new ImageView(MainActivity.context);
        blockImage.setLayoutParams(new ViewGroup.LayoutParams(MainActivity.width/3,
                MainActivity.width/3));
        blockImage.setClickable(true);
        ViewHolder vh = new ViewHolder(blockImage);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.blockImage.setImageResource( R.drawable.load);
        final Bitmap bitmap = MainActivity.getBitmapFromMemCache(setImg[position].identifier);
        if (bitmap != null) {
            holder.blockImage.setImageBitmap(bitmap);
        } else {
            new DownloadImageTask(holder.blockImage, setImg[position].identifier).execute(setImg[position].smallSizeUrl);
        }

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return setImg.length;
    }
}



