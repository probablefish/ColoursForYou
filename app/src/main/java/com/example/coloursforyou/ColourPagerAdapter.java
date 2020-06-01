package com.example.coloursforyou;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ColourPagerAdapter extends RecyclerView.Adapter<ColourPagerAdapter.ColourPagerViewHolder> {

    private Colour[] colours;

    ColourPagerAdapter(Colour[] someColours) {
        colours = someColours;
    }

    @NonNull
    @Override
    public ColourPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.colour_view_pager, parent, false);
        return new ColourPagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColourPagerViewHolder holder, int position) {
        View view = holder.itemView;
        bindColour(colours[position], view);
    }

    @Override
    public int getItemCount() {
        return colours.length;
    }

    static class ColourPagerViewHolder extends RecyclerView.ViewHolder {

        ColourPagerViewHolder(View view) {
            super(view);
        }
    }

    private void bindColour(Colour aColour, View aView) {
        bindColourName(aColour, aView);
        bindHex(aColour, aView);
        bindBackgroundColour(aColour, aView);
    }

    private void bindColourName(Colour aColour, View aView) {
        TextView colourName = aView.findViewById(R.id.colour_name);
        colourName.setText(aColour.getName());
    }

    private void bindHex(Colour aColour, View aView) {
        TextView colourHex = aView.findViewById(R.id.colour_hex);
        colourHex.setText(aColour.getHex().toUpperCase());
    }

    private void bindBackgroundColour(Colour aColour, View aView) {
        View view = aView.findViewById(R.id.colour_view);
        view.setBackgroundColor(Color.parseColor(aColour.getHex()));
    }


}

