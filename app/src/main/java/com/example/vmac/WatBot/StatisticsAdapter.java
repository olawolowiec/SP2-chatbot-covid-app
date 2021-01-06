package com.example.vmac.WatBot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatisticsHolder> {

    List<Stats> statsList;
    Context context;
    int t1,t2,t3;

    public StatisticsAdapter(List<Stats> personList, Context context) {
        this.statsList = personList;
        this.context = context;
    }

    @NonNull
    @Override
    public StatisticsAdapter.StatisticsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.statistics_row, viewGroup, false);
        return new StatisticsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsAdapter.StatisticsHolder statisticsHolder, int i) {

        Stats stats = statsList.get(i);
        statisticsHolder.dateTextView.setText(stats.getDate().substring(0,11));
        statisticsHolder.casesTextView.setText("W tym dniu " + String.valueOf(stats.getCases()) + "  zostało zdiagnozowanych z wynikiem  pozytywnym Sars-Cov2");
        statisticsHolder.deathsTextView.setText("Z powodu koronawirusa oraz chorób współistniejących zmarło " + String.valueOf(stats.getDeaths()) + " osób.");
        statisticsHolder.cumulativeTextView.setText(String.valueOf(stats.getCumulativeNr()));

        boolean isExpanded = statsList.get(i).isExpanded();
        statisticsHolder.statisticsDetailLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return statsList.size();
    }

    public class StatisticsHolder extends RecyclerView.ViewHolder{

        ConstraintLayout statisticsDetailLayout;
        TextView dateTextView, casesTextView, deathsTextView, cumulativeTextView;

        public StatisticsHolder(@NonNull View itemView) {
            super(itemView);

            dateTextView = itemView.findViewById(R.id.date);
            casesTextView = itemView.findViewById(R.id.cases);
            deathsTextView = itemView.findViewById(R.id.deaths);
            cumulativeTextView = itemView.findViewById(R.id.cumulative);
            statisticsDetailLayout = itemView.findViewById(R.id.statisticsDetailLayout);

            dateTextView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Stats stats  = statsList.get(getAdapterPosition());
                    stats.setExpanded(!stats.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
