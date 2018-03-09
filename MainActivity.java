package com.example.tanmoy.recyclerviewwithonclicklistener;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String arrmonth[] = {"jan","feb","mar","apr","may","jun","july","aug","sep","oct","nov","dec"};
    String arrname[] = {"A","B","C","D","E","F","G","H","I","J","K","L"};
    RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(new RecyclerAdapter());

        recycler_view.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recycler_view, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("MonthName", arrmonth[position]);
                intent.putExtra("Name", arrname[position]);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        }
    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Myholder>{

        @Override
        public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, null);
            final Myholder myholder = new Myholder(view);
            return myholder;
        }

        @Override
        public void onBindViewHolder(Myholder holder, final int position) {
            holder.tv_row1.setText(arrmonth[position]);
            holder.tv_row2.setText(arrname[position]);
            /*holder.tv_row1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("MonthName", arrmonth[position]);
                    startActivity(intent);
                }
            });*/
        }

        @Override
        public int getItemCount() {
            return arrmonth.length;
        }

            class Myholder extends RecyclerView.ViewHolder {

            TextView tv_row1,tv_row2;

            public Myholder(View itemView) {

                super(itemView);
                tv_row1 = (TextView) itemView.findViewById(R.id.tv_row1);
                tv_row2 = (TextView) itemView.findViewById(R.id.tv_row2);
            }
        }
    }

    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }
}
