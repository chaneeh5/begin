package com.juicy.app;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.juicy.app.adapters.VideoGameBaseAdpater;
import com.juicy.app.objects.VideoGame;
import com.juicy.app.objects.VideoGame.VideoGameConsole;
import com.juicy.app.web.GetVideoGames;

public class VideoGameBaseAdapterActivity extends ListActivity {

    private List<VideoGame> games;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        // MAKE GET REQUEST TO RETRIEVE GAMES
        games = GetVideoGames.getGamesByPlatform(VideoGameConsole.XBOX);

        // USE VIDEO GAME ADAPTER
        VideoGameBaseAdpater vAdapter = new VideoGameBaseAdpater(this, games);

        // SET THIS ADAPTER AS YOUR LIST ACTIVITY'S ADAPTER
        this.setListAdapter(vAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        VideoGame vg = games.get(position);

        String name = vg.getName();
        System.out.println("CLICKED ON " + name);
    }
}
