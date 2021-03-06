package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView mingredientsIv;
    private TextView mOriginTextView;
    private TextView mAlsoKnownTvTextView;
    private TextView mIngredientsTextView;
    private TextView mDescriptionTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mingredientsIv = findViewById(R.id.image_iv);
        mOriginTextView = findViewById(R.id.origin_tv);
        mAlsoKnownTvTextView = findViewById(R.id.also_known_tv);
        mIngredientsTextView = findViewById(R.id.ingredients_tv);
        mDescriptionTextView = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json,
                                        getString(R.string.detail_unknown_description));
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .error(getResources().getDrawable(R.drawable.no_image_available))
                .into(mingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        mOriginTextView.setText(sandwich.getPlaceOfOrigin());
        mAlsoKnownTvTextView.setText(android.text.TextUtils.join(", ",sandwich.getAlsoKnownAs()));
        mDescriptionTextView.setText(sandwich.getDescription());
        mIngredientsTextView.setText(android.text.TextUtils.join(", ",sandwich.getIngredients()));


    }
}
