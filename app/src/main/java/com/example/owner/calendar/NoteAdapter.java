package com.example.owner.calendar;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// 為了配合RecycleView 建置的調適器
public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {     //extends RectclerView.Adpter<NoteAdapter.NoteHolder>
    //private List<Note> notes =  new ArrayList();
    private OnItemClickListener listener;

    // part 10
    public NoteAdapter() {
        super(DIFE_CALLBACK);

    }

    private static final DiffUtil.ItemCallback<Note> DIFE_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note note, @NonNull Note t1) {
            return note.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note note, @NonNull Note t1) {
            return note.getDecription().equals(t1.getDecription());
        }
    };

    //-----------------
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
//    Note currentNote = notes.get(position);
        Note currentNote = getItem(position);                         //part 10 befor     Note currentNote = notes.get(position);
        noteHolder.textViewTitle.setText(currentNote.getTime());
        noteHolder.textViewDescription.setText(currentNote.getDecription());

        Log.v("***", "1次 : " + getItem(position));

    }

//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }
//
//    public void setNotes(List<Note> notes) {
//        this.notes = notes;
//        notifyDataSetChanged();
//    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);

            //updata
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    // updata
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
