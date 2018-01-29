package com.example.android.bookfindr.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.bookfindr.EffectiveRecyclerView;
import com.example.android.bookfindr.R;
import com.example.android.bookfindr.adapter.BookAdapter;
import com.example.android.bookfindr.model.Book;
import com.example.android.bookfindr.model.BooksInteractor;
import com.example.android.bookfindr.model.BooksInteractorImpl;
import com.example.android.bookfindr.presenter.BooksPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsFragment extends Fragment implements BooksView {

	BookAdapter adapter;
	@BindView(R.id.search_results_rv)
	EffectiveRecyclerView recyclerView;
	@BindView(R.id.empty_state_tv)
	TextView emptyStateTextView;
	@BindView(R.id.progress_bar)
	ProgressBar progressBar;
	BooksInteractor interactor;
	BooksPresenter presenter;
	private String userInput;

	public static final String USER_INPUT = "userInput";

	public ResultsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		Bundle args = getArguments();
		if (args != null) this.userInput = args.getString(USER_INPUT);
		interactor = new BooksInteractorImpl();
		presenter = new BooksPresenter(interactor);
		presenter.bind(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.search_results, container, false);
		ButterKnife.bind(this, view);

		adapter = new BookAdapter(getActivity());
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);

		presenter.performSearch(userInput);
		return view;
	}

	@Override
	public void updateUi(List<Book> books) {
		if (books.isEmpty()) {
			emptyStateTextView.setVisibility(View.VISIBLE);
		} else {
			emptyStateTextView.setVisibility(View.GONE);
			progressBar.setVisibility(View.GONE);
		}
		adapter.clearData();
		adapter.setData(books);
	}

	@Override
	public void onDestroy() {
		presenter.unbind();
		super.onDestroy();
	}
}
