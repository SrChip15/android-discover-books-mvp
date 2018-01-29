package com.example.android.bookfindr;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class EffectiveRecyclerView extends RecyclerView {

	private View mEmptyView;

	private AdapterDataObserver mDataObserver = new AdapterDataObserver() {
		@Override
		public void onChanged() {
			super.onChanged();
			updateEmptyStatus();
		}
	};

	public EffectiveRecyclerView(Context context) {
		super(context);
	}

	public EffectiveRecyclerView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public EffectiveRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * Designate a view as the empty view. When the backing adapter has no
	 * data this view will be made visible and the recycler view hidden.
	 */
	public void setEmptyView(View emptyView) {
		// Initialize empty view
		this.mEmptyView = emptyView;
	}

	/**
	 * This method ensures that the updateEmptyStatus() method callback is correctly called
	 * whenever there is a change in the adapter's data set
	 */
	@Override
	public void setAdapter(RecyclerView.Adapter adapter) {
		// Unregister the adapter data observer on the old adapter
		if (getAdapter() != null) {
			getAdapter().unregisterAdapterDataObserver(mDataObserver);
		}

		// Check whether new adapter is not null and register an adapter data observer on it
		if (adapter != null) {
			adapter.registerAdapterDataObserver(mDataObserver);
		}

		// Set new adapter
		super.setAdapter(adapter);

		// Check whether the new adapter is empty or not
		updateEmptyStatus();
	}

	/**
	 * Update the recycler view with an empty view when there is no data on the adapter
	 */
	private void updateEmptyStatus() {
		// Check whether empty view and adapter have been properly initialized
		if (mEmptyView != null && getAdapter() != null) {

			// Determine whether empty view should be visible or not. If the adapter has zero items
			// then the empty view is made visible otherwise it is removed from view
			final boolean showEmptyView = getAdapter().getItemCount() == 0;
			mEmptyView.setVisibility(showEmptyView ? VISIBLE : GONE);

			// Hide or show recycler view based on showEmptyView
			setVisibility(showEmptyView ? GONE : VISIBLE);
		}
	}
}
