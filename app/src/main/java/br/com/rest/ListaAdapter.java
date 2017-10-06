package br.com.rest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;

import br.com.rest.api.Contato;

public class ListaAdapter extends BaseAdapter {

    private Context context;
    private List<Contato> listaContatos;
    private ViewHolder holder;

    public ListaAdapter(Context context, List<Contato> listaIssues) {
        this.context = context;
        this.listaContatos = listaIssues;
    }

    @Override
    public int getCount() {
        return listaContatos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaContatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaContatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contato c = listaContatos.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.linha, null);
            holder = new ViewHolder();
            holder.titulo = (TextView) convertView.findViewById(R.id.nome);
            holder.foto = (ImageView) convertView.findViewById(R.id.foto);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titulo.setText(c.getNome());

        Ion.with(holder.foto)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error)
                .animateIn(R.anim.fade_in)
                .load(Constants.PATH_URL + "/ContatosWeb/img/" + c.getFoto());

        return convertView;
    }

    static class ViewHolder {
        TextView titulo;
        ImageView foto;
    }
}
