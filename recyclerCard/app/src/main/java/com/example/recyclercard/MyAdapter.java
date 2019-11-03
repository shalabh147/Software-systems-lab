package com.example.recyclercard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.db.AppDataBase;
import com.example.db.Node;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    private List<Node> mDataSet;
    private List<Boolean> mExpand;
    private ArrayList<String> mChildren;
    private Context context;
    private Context actcon;
    private int rootid;

    public static class myViewHolder extends RecyclerView.ViewHolder {
        public TextView nodeNameView;
        public TextView nodeDateView;
        public TextView nodeChildView;
        public TextView nodeDescView;
        public Button nodeButton;
        public myViewHolder(View v) {
            super(v);
            nodeNameView = v.findViewById(R.id.nodeName);
            nodeDateView = v.findViewById(R.id.nodeDate);
            nodeChildView = v.findViewById(R.id.children);
            nodeDescView = v.findViewById(R.id.nodeDesc);
            nodeButton = v.findViewById(R.id.nodeEdit);
        }
    }

    @Override
    public MyAdapter.myViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        myViewHolder vh = new myViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {

        holder.itemView.setOnTouchListener(new OnDoubleTapListener(context) {
            @Override
            public void onDoubleTap(MotionEvent e) {

                if(mDataSet.get(position).getParent_id()==rootid) {
                    Intent intent = new Intent(actcon, Home.class);
                    intent.putExtra("ID:", String.valueOf(mDataSet.get(position).getId()));
                    intent.putExtra("ROOT:", String.valueOf(rootid));
                    ((Activity) actcon).startActivityForResult(intent, 3);
                }
                else
                {
                    Intent intent = new Intent(actcon, SubTaskDetail.class);
                    intent.putExtra("ID:", String.valueOf(mDataSet.get(position).getId()));
                    intent.putExtra("ROOT:", String.valueOf(rootid));
                    ((Activity) actcon).startActivityForResult(intent, 4);
                }
            }

            @Override
            public void onSingleTapConfirmed(MotionEvent e) {
                mExpand.set(position, !mExpand.get(position));

                notifyItemChanged(position);
            }
        });

        holder.nodeButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Context con = view.getContext();
                        Intent intent = new Intent(actcon, popup_edit.class);
                        intent.putExtra("ID:", String.valueOf(mDataSet.get(position).getId()));
                        //intent.putExtra("ID:","0");
                        ((Activity) actcon).startActivityForResult(intent, 2);

                    }
                }
        );

        holder.nodeNameView.setText(mDataSet.get(position).getName());
        holder.nodeDateView.setText(mDataSet.get(position).getDate());
        holder.nodeDescView.setText(mDataSet.get(position).getDescription());
        List<Node> tlist = AppDataBase.getAppDatabase(context).nodeDao().findbyParentId(mDataSet.get(position).getId());
        mChildren = new ArrayList<String>();
        if(mExpand.get(position)) {
            for (int i = 0; i < min(tlist.size(), 4); i++) {
                mChildren.add(tlist.get(i).getName());
            }
            if (tlist.size() > 4) {
                mChildren.add("...");
            }
        }
        String temp = new String();
        for(int i = 0; i < mChildren.size(); i++)
        {
            temp = temp.concat("+");
            temp = temp.concat(mChildren.get(i));
            temp = temp.concat("\n");
        }

        holder.nodeDescView.setVisibility(mExpand.get(position) ? View.VISIBLE : View.GONE);
        holder.nodeButton.setVisibility(mExpand.get(position) ? View.VISIBLE : View.GONE);

        holder.nodeChildView.setText(temp);
    }

    public MyAdapter(List<Node> myDataSet,List<Boolean> expand, Context context, Context actcon, int rootid) {
        this.mDataSet = myDataSet;
        this.mExpand = expand;
        this.context = context;
        this.actcon = actcon;
        this.rootid = rootid;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class OnDoubleTapListener implements View.OnTouchListener {

        private GestureDetector gestureDetector;

        public OnDoubleTapListener(Context c) {
            gestureDetector = new GestureDetector(c, new GestureListener());
        }

        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                OnDoubleTapListener.this.onDoubleTap(e);
                return super.onDoubleTap(e);
            }

            public boolean onSingleTapConfirmed(MotionEvent e) {
                OnDoubleTapListener.this.onSingleTapConfirmed(e);
                return super.onSingleTapConfirmed(e);
            }
        }

        public void onDoubleTap(MotionEvent e) {
            // To be overridden when implementing listener
        }

        public void onSingleTapConfirmed(MotionEvent e) {
            // To be overridden when implementing listener
        }
    }
}
