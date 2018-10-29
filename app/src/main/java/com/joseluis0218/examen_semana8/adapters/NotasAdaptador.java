package com.joseluis0218.examen_semana8.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.joseluis0218.examen_semana8.activities.NotasDetallesActivity;
import com.joseluis0218.examen_semana8.R;
import com.joseluis0218.examen_semana8.models.Notas;
import com.joseluis0218.examen_semana8.repository.RepositorioNotas;

import java.util.ArrayList;
import java.util.List;

import static com.joseluis0218.examen_semana8.repository.RepositorioNotas.updateArchive;
import static com.joseluis0218.examen_semana8.repository.RepositorioNotas.updateStar;

public class NotasAdaptador extends RecyclerView.Adapter<NotasAdaptador.ViewHolder> {


    private List<Notas> notes;

    public NotasAdaptador(List<Notas> notes){
        this.notes = notes;
    }

    public void setNotes(List<Notas> notes) {
        this.notes = notes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleText;
        TextView contentText;
        TextView text_favorite;
        TextView text_archivate;
        RelativeTimeTextView dateText;
        CheckBox favoriteStar;
        CheckBox archivateIcon;
        ImageButton menuButton;

        public ViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.title_text);
            contentText = (TextView) itemView.findViewById(R.id.content_text);
            dateText = (RelativeTimeTextView) itemView.findViewById(R.id.date_text);
            favoriteStar = (CheckBox) itemView.findViewById(R.id.favorite_star);
            archivateIcon = (CheckBox) itemView.findViewById(R.id.archivar_icon);
            text_favorite = (TextView) itemView.findViewById(R.id.favorito_text);
            text_archivate = (TextView)itemView.findViewById(R.id.archivar_text);
            menuButton = (ImageButton)itemView.findViewById(R.id.menu_button);


        }
    }
    @NonNull
    @Override
    public NotasAdaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotasAdaptador.ViewHolder viewHolder, final int position) {
        final Notas note = this.notes.get(position);
        viewHolder.titleText.setText(note.getTitle());
        viewHolder.contentText.setText(note.getContent());
        viewHolder.text_favorite.setText("Favorito");
        viewHolder.text_archivate.setText("Archivar");

        viewHolder.dateText.setReferenceTime(note.getDate().getTime());
        viewHolder.favoriteStar.setChecked(note.getFavorite());
        viewHolder.archivateIcon.setChecked(note.getArchivate());

        viewHolder.favoriteStar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                note.setFavorite(b);
                updateStar(note.getId(), b);

            }
        });
        viewHolder.archivateIcon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                note.setArchivate(b);
                updateArchive(note.getId(), b);

            }
        });
        // PopupMenu
        viewHolder.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                PopupMenu popup = new PopupMenu(v.getContext(), v);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.remove_button:
                                RepositorioNotas.delete(note.getId());
                                notes.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, notes.size());

                                Toast.makeText(v.getContext(), "Nota eliminada correctamente", Toast.LENGTH_LONG).show();

                                break;
                        }
                        return false;
                    }
                });
                popup.show();
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

