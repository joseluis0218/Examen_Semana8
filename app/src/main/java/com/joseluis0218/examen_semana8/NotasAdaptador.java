package com.joseluis0218.examen_semana8;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.joseluis0218.examen_semana8.models.Notas;
import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotasAdaptador extends RecyclerView.Adapter<NotasAdaptador.ViewHolder> {

    private static final String TAG = NotasAdaptador.class.getSimpleName();

    private List<Notas> notes;

    public NotasAdaptador(){
        this.notes = new ArrayList<>();
    }

    public void setNotes(List<Notas> notes) {
        this.notes = notes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleText;
        public TextView contentText;
        public TextView text_favorite;
        public TextView text_archivate;
        public RelativeTimeTextView dateText;
        public CheckBox favoriteStar;
        public ImageButton archivarButton;


        public ViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.title_text);
            contentText = (TextView) itemView.findViewById(R.id.content_text);
            dateText = (RelativeTimeTextView) itemView.findViewById(R.id.date_text);
            favoriteStar = (CheckBox) itemView.findViewById(R.id.favorite_star);
            archivarButton = (ImageButton)itemView.findViewById(R.id.archivar_icon);
            text_favorite = (TextView) itemView.findViewById(R.id.favorito_text);
            text_archivate = (TextView)itemView.findViewById(R.id.archivar_text);



        }
    }

    @Override
    public NotasAdaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotasAdaptador.ViewHolder viewHolder, final int position) {
        final Notas note = this.notes.get(position);
        viewHolder.titleText.setText(note.getTitle());
        viewHolder.contentText.setText(note.getContent());
        viewHolder.text_favorite.setText("Favorito");
        viewHolder.text_archivate.setText("Archivar");

        viewHolder.dateText.setReferenceTime(note.getDate().getTime());
        viewHolder.favoriteStar.setChecked(note.getState());

        viewHolder.favoriteStar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                note.setState(b);
            }
        });
        // PopupMenu
        viewHolder.archivarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                RepositorioNotas.delete(note.getId());
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, notes.size());

                Toast.makeText(v.getContext(), "Nota eliminada correctamente", Toast.LENGTH_LONG).show();
            }
        });

        // OnClick on CardView
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), NotasDetallesActivity.class);
                intent.putExtra("ID", note.getId());
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

}

