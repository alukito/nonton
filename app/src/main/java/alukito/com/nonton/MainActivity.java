package alukito.com.nonton;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alukito.model.Movie;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.recycler_movies)
    RecyclerView moviesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        moviesView.setHasFixedSize(true);

        // use a linear layout manager
        moviesView.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter (see also next example)
        moviesView.setAdapter(new MyAdapter(createDummyMovies()));
    }

    private List<Movie> createDummyMovies() {
        Movie movie1 = new Movie();
        movie1.setTitle("The One");

        Movie movie2 = new Movie();
        movie2.setTitle("The Two");

        return Arrays.asList(movie1, movie2);
    }

    private static class MyAdapter extends RecyclerView.Adapter<MovieViewHolder> {

        private final List<Movie> movieList;

        public MyAdapter(List<Movie> movieList) {
            this.movieList = movieList;
        }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View movieItemView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.movie_item, parent, false);
            return new MovieViewHolder(movieItemView);
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position) {
            Movie movie = movieList.get(position);
            holder.titleText.setText(movie.getTitle());
        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }
    }

    private static class MovieViewHolder extends RecyclerView.ViewHolder {

        final ImageView movieImage;
        final TextView titleText;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.image_movie);
            titleText = (TextView) itemView.findViewById(R.id.text_title);
        }
    }
}
