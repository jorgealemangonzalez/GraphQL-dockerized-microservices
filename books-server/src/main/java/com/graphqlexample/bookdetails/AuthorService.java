package com.graphqlexample.bookdetails;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.fetcher.ApolloResponseFetchers;
import example.BookByIdQuery;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

public class AuthorService {

    private static final String BASE_URL = "http://localhost:8081/graphql";
    private final ApolloClient apolloClient;

    public AuthorService() {
        apolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(new OkHttpClient.Builder().build())
                .build();
    }

    public Object getAuthorById(String authorId) {
        apolloClient
                .query(BookByIdQuery.builder().id(authorId).build())
                .responseFetcher(ApolloResponseFetchers.NETWORK_ONLY)
                .enqueue(new ApolloCall.Callback<BookByIdQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<BookByIdQuery.Data> response) {
                        response.data().bookById().
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        e.printStackTrace();
                    }
//
//                    @Override public void onResponse(@NotNull Response<FeedQuery.Data> dataResponse) {
//
//                        final StringBuffer buffer = new StringBuffer();
//                        for (FeedQuery.Data.Feed feed : dataResponse.data().feed()) {
//                            buffer.append("name:" + feed.repository().fragments().repositoryFragment().name());
//                            ().login());
//                            buffer.append(" postedBy: " + feed.postedBy().login());
//                        }
//
//                        // onResponse returns on a background thread. If you want to make UI updates make sure they are done on the Main Thread.
//                        MainActivity.this.runOnUiThread(new Runnable() {
//                            @Override public void run() {
//                                TextView txtResponse = (TextView) findViewById(R.id.txtResponse);
//                                txtResponse.setText(buffer.toString());
//                            }
//                        });
//
//                    }
//
//                    @Override public void onFailure(@NotNull Throwable t) {
//                        Log.e(TAG, t.getMessage(), t);
//                    }
                });
        return null;
    }
}
