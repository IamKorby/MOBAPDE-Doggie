package com.example.angeloamadora.quiz2;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ngoc on 3/2/2016.
 */
public class UnitCursorAdapter extends CursorRecyclerViewAdapter<UnitCursorAdapter.UnitViewHolder>{

    private OnItemClickListener mOnItemClickListener;

    public UnitCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    public void setmOnItemClickListener(OnItemClickListener m){
        this.mOnItemClickListener = m;
    }

    @Override
    public UnitViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // TODO
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.unit_item, viewGroup, false);

        return new UnitViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UnitViewHolder viewHolder, Cursor cursor) {
        // TODO
        String title = cursor.getString(cursor.getColumnIndex(Note.COLUMN_TITLE));
        viewHolder.tvUnit.setText(title);
        viewHolder.container.setTag(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call onItemClick here
                int dbId = Integer.parseInt(v.getTag().toString());
                mOnItemClickListener.onItemClick(dbId);
            }
        });
        viewHolder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //call onItemClick here
                int dbId = Integer.parseInt(v.getTag().toString());
                mOnItemClickListener.onItemLongClick(dbId);
                return true;
            }
        });
    }

    public class UnitViewHolder extends RecyclerView.ViewHolder{
        TextView tvUnit;
        View container;

        public UnitViewHolder(View itemView){
            super(itemView);
            tvUnit = (TextView) itemView.findViewById(R.id.tv_unit);
            container = itemView.findViewById(R.id.container);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(int id);
        public void onItemLongClick(int id);
    }

}
